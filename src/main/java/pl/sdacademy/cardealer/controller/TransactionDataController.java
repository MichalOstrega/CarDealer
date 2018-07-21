package pl.sdacademy.cardealer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.sdacademy.cardealer.dto.TransactionDto;
import pl.sdacademy.cardealer.model.*;
import pl.sdacademy.cardealer.services.CarDataService;
import pl.sdacademy.cardealer.services.CustomerService;
import pl.sdacademy.cardealer.services.TransactionService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/transactions")
public class TransactionDataController {

    private CustomerService customerService;
    private CarDataService carDataService;
    private TransactionService transactionService;

    public TransactionDataController(CustomerService customerService, CarDataService carDataService, TransactionService transactionService) {
        this.transactionService = transactionService;
        this.customerService = customerService;
        this.carDataService = carDataService;
    }

    @GetMapping("/transfers")
    public String showAllTransfers(Model model) {
        String name = "Transfers";
        List<Transfer> transfers = transactionService.getTransfers();
        model.addAttribute("headerMsg", name);
        model.addAttribute("transactions", transfers);
        return "transactions";
    }

    @GetMapping("/sales")
    public String showAllSales(Model model) {
        String name = "Sales";
        List<Sale> transfers = transactionService.getSales();
        model.addAttribute("headerMsg", name);
        model.addAttribute("transactions", transfers);
        return "transactions";
    }

    @GetMapping("/purchases")
    public String showAllPurchases(Model model) {
        String name = "Purchases";
        List<Purchase> transfers = transactionService.getPurchases();
        model.addAttribute("headerMsg", name);
        model.addAttribute("transactions", transfers);
        return "transactions";
    }

    @GetMapping("/new/{transaction}")
    public String carTransaction(Model model,
                                 @RequestParam(value = "vin", required = false) String vin,
                                 @PathVariable("transaction") String transactionType) {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setTransactionType(transactionType);
        Car car;
        if (vin != null && carDataService.loadCarByVIN(vin) != null) {
            car = carDataService.loadCarByVIN(vin);
            if (car.isSold()) {
                return "redirect:/";
            }
            transactionDto.setCarExist(true);
            transactionDto.setPrice(car.getPrice());
        } else {
            car = new Car();
        }
        transactionDto.setCar(car);
        transactionDto.setCustomer(new Customer());
        transactionDto.setCustomerExist(false);
        model.addAttribute("transactionDto", transactionDto);
        return "addTransaction";
    }

    @PostMapping("/car")
    public String checkCar(@Valid @ModelAttribute("transactionDto") TransactionDto transactionDto,
                           BindingResult bindingResult,
                           Model model) {
        Car byVIN = carDataService.loadCarByVIN(transactionDto.getCar().getVin());
        if (byVIN == null) {
            bindingResult.setNestedPath("car");
            bindingResult.rejectValue("vin", "vin", "Car doesn't exists. Check inputed number or first create car");
            return "addTransaction";
        } else if (byVIN.isSold()) {
            bindingResult.setNestedPath("car");
            bindingResult.rejectValue("vin", "vin", "Car cannot be sold twice");
            model.addAttribute("transactionDto", transactionDto);
            bindingResult.setNestedPath("");
            return "addTransaction";
        } else {
            transactionDto.setCarExist(true);
            transactionDto.setCar(byVIN);
            transactionDto.setPrice(byVIN.getPrice());
            model.addAttribute("transactionDto", transactionDto);
        }
        return "addTransaction";
    }

    @PostMapping("/customer")
    public String checkCustomer(@Valid @ModelAttribute("transactionDto") TransactionDto transactionDto,
                                BindingResult bindingResult,
                                Model model) {
        Customer byNIP = customerService.findByNIP(transactionDto.getValidNumber());
        Customer byPESEL = customerService.findByPESEL(transactionDto.getValidNumber());
        if (byNIP == null && byPESEL == null) {
            bindingResult.rejectValue("validNumber", "validNumber", "Customer doesn't exists. Check inputed number or first create customer");
            model.addAttribute("transactionDto", transactionDto);
            return "addTransaction";
        } else {
            transactionDto.setCustomerExist(true);
            if (byNIP != null) {
                transactionDto.setCustomer(byNIP);
                transactionDto.setValidNumber(byNIP.getNip());
            } else if (byPESEL != null) {
                transactionDto.setValidNumber(byPESEL.getPesel());
                transactionDto.setCustomer(byPESEL);
            }
            model.addAttribute("transactionDto", transactionDto);
        }
        return "addTransaction";
    }

    @Transactional
    @PostMapping
    public String addTransaction(@Valid @ModelAttribute("transactionDto") TransactionDto transactionDto,
                                 BindingResult bindingResult,
                                 Model model) {

        Car car = transactionDto.getCar();

        Customer customer = transactionDto.getCustomer();
        if (car != null && customer != null) {

            String transactionType = transactionDto.getTransactionType();
            switch (transactionType) {
                case "transfer": {
                    Transfer transfer = (Transfer) getTransaction(new Transfer(), transactionDto, car, customer);
                    transactionService.saveTransfer(transfer);
                    carDataService.addCar(car);
                    break;
                }
                case "purchase" : {
                    Purchase purchase = (Purchase) getTransaction(new Purchase(), transactionDto, car, customer);
                    transactionService.savePurchase(purchase);
                    carDataService.addCar(car);
                    break;
                }
                case "sale": {
                    Sale sale = (Sale) getTransaction(new Sale(), transactionDto, car, customer);
                    car.setSold(true);
                    carDataService.updateCar(car);
                    transactionService.saveSale(sale);
                    break;
                }
                default:
                    break;
            }
        }

        return "redirect:/transactions/"+ transactionDto.getTransactionType()+"s";
    }


    private BaseModelTransaction getTransaction(
            BaseModelTransaction transaction,
            @Valid @ModelAttribute("transactionDto") TransactionDto transactionDto,
            Car car, Customer customer) {
        transaction.setCustomer(customer);
        transaction.setCar(car);
        transaction.setPrice(transactionDto.getPrice());
        return transaction;
    }
}