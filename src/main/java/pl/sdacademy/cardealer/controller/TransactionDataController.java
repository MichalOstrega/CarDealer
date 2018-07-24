package pl.sdacademy.cardealer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.sdacademy.cardealer.dto.TransactionDto;
import pl.sdacademy.cardealer.model.*;
import pl.sdacademy.cardealer.services.CarDataService;
import pl.sdacademy.cardealer.services.ContractService;
import pl.sdacademy.cardealer.services.CustomerService;
import pl.sdacademy.cardealer.services.TransactionService;

import javax.validation.Path;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/transactions")
public class TransactionDataController {

    private CustomerService customerService;
    private CarDataService carDataService;
    private TransactionService transactionService;
    private ContractService contractService;
    private List<String> availableTransactionTypes = Arrays.asList(new String[]{"transfer", "sale", "purchase"});

    @Autowired
    public TransactionDataController(CustomerService customerService, CarDataService carDataService, TransactionService transactionService, ContractService contractService) {
        this.transactionService = transactionService;
        this.customerService = customerService;
        this.carDataService = carDataService;
        this.contractService = contractService;
    }

    @GetMapping("/{transactionType}")
    public String showAllTransfers(Model model,
                                   @PathVariable("transactionType") String name) {


        if (!availableTransactionTypes.contains(name)) {
            return "redirect:/";
        }

        List<Transaction> transfers = transactionService.getTransactionsByType(name);
        model.addAttribute("headerMsg", name.toUpperCase());
        model.addAttribute("transactions", transfers);
        return "transactions";
    }


    @GetMapping("/new/{transaction}")
    public String carTransaction(Model model,
                                 @RequestParam(value = "vin", required = false) String vin,
                                 @PathVariable("transaction") String transactionType) {
        if (!availableTransactionTypes.contains(transactionType)) {
            return "redirect:/";
        }

        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setTransactionType(transactionType);
        Car car;
        if (vin != null && carDataService.loadCarByVIN(vin) != null) {
            car = carDataService.loadCarByVIN(vin);
            if (car.isSold()) {
                return "redirect:/";
            }
            transactionDto.setCarExist(true);
            transactionDto.setPrice(car.getPriceHistory().getPrice());

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
        Car carByVIN = carDataService.loadCarByVIN(transactionDto.getCar().getVin());
        if (carByVIN == null) {
            bindingResult.setNestedPath("car");
            bindingResult.rejectValue("vin", "vin", "Car doesn't exists. Check inputed number or first create car");
            bindingResult.setNestedPath("");
            return "addTransaction";
        } else if (carByVIN.isSold()) {
            bindingResult.setNestedPath("car");
            bindingResult.rejectValue("vin", "vin", "Car cannot be sold twice");
            model.addAttribute("transactionDto", transactionDto);
            bindingResult.setNestedPath("");
            return "addTransaction";
        } else {
            transactionDto.setCarExist(true);
            transactionDto.setCar(carByVIN);
            transactionDto.setPrice(carByVIN.getPriceHistory().getPrice());
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
                    Transaction transaction = getTransaction(new Transaction(), transactionDto, car, customer);

                    Contract contract = new Contract();
                    contract.setTransaction(transaction);
                    contract.setContent(transactionType);

                    PriceHistory priceHistory = car.getPriceHistory();
                    priceHistory.setPrice(Double.valueOf(car.getPriceHistory().getPrice() * 1.2).longValue());
                    priceHistory.setAcquireCarPrice(transactionDto.getPrice());
                    priceHistory.setAcquireCarContract(contract);


                    Account account = new Account();
                    account.setPayment(0l);
                    account.setContract(contract);

                    carDataService.addCar(car);
                    transactionService.savePriceHistory(priceHistory);
                    transactionService.saveTransaction(transaction);
                    contractService.saveContract(contract);
                    transactionService.saveAccount(account);

                    break;
                }
                case "purchase": {
                    Transaction transaction = getTransaction(new Transaction(), transactionDto, car, customer);

                    car.setCustomer(customerService.findById(5l));

                    Contract contract = new Contract();
                    contract.setTransaction(transaction);
                    contract.setContent(transactionType);

                    PriceHistory priceHistory = car.getPriceHistory();
                    priceHistory.setAcquireCarPrice(transactionDto.getPrice());
                    priceHistory.setPrice(Double.valueOf(priceHistory.getAcquireCarPrice() * 1.2).longValue());
                    priceHistory.setAcquireCarContract(contract);


                    Account account = new Account();
                    account.setContract(contract);
                    account.setPayment(priceHistory.getAcquireCarPrice());

                    carDataService.addCar(car);
                    transactionService.savePriceHistory(priceHistory);
                    transactionService.saveTransaction(transaction);
                    contractService.saveContract(contract);
                    transactionService.saveAccount(account);
                    break;
                }
                case "sale": {
                    Transaction transaction = getTransaction(new Transaction(), transactionDto, car, customer);

                    car.setSold(true);
                    car.setVisible(false);
                    car.setCustomer(customer);


                    Contract contract = new Contract();
                    contract.setTransaction(transaction);
                    contract.setContent(transactionType);

                    PriceHistory priceHistory = car.getPriceHistory();
                    priceHistory.setSellCarPrice(transactionDto.getPrice());
                    priceHistory.setSellCarContract(contract);

                    Account account = new Account();
                    account.setContract(contract);
                    account.setIncome(transactionDto.getPrice());

                    carDataService.updateCar(car);
                    transactionService.saveTransaction(transaction);
                    transactionService.savePriceHistory(priceHistory);
                    contractService.saveContract(contract);
                    transactionService.saveAccount(account);
                    break;
                }
                default:
                    break;
            }
        }

        return "redirect:/transactions/" + transactionDto.getTransactionType();
    }


    private Transaction getTransaction(
            Transaction transaction,
            @Valid @ModelAttribute("transactionDto") TransactionDto transactionDto,
            Car car, Customer customer) {
        transaction.setCustomer(customer);
        transaction.setCar(car);
        transaction.setPrice(transactionDto.getPrice());
        transaction.setTransactionType(transactionDto.getTransactionType());
        return transaction;
    }
}
