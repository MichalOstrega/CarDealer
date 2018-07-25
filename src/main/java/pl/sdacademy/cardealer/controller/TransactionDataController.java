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
import java.util.Date;
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
                    Transaction transaction = getTransaction(transactionDto, car, customer);

                    Contract contract = getContract(transactionType, transaction);

                    car.getPriceHistory().setAcquireCarPrice(0l);
                    car.getPriceHistory().setAcquireCarContract(contract);

                    Account account = new Account(contract,new Date());
                    account.setPayment(0l);

                    carDataService.addCar(car);
                    transactionService.saveTransaction(transaction);
                    contractService.saveContract(contract);
                    transactionService.saveAccount(account);

                    break;
                }
                case "purchase": {
                    Transaction transaction = getTransaction(transactionDto, car, customer);

                    /*
                     * After purchase a car, new owner is Car Dealer
                     * */
                    car.setCustomer(customerService.findById(5l));

                    Contract contract = getContract(transactionType, transaction);

                    car.getPriceHistory().setAcquireCarPrice(transactionDto.getPrice());
                    car.getPriceHistory().setPrice(Double.valueOf(transactionDto.getPrice()* 1.2).longValue());
                    car.getPriceHistory().setAcquireCarContract(contract);


                    Account account = new Account(contract,new Date());
                    account.setPayment(car.getPriceHistory().getAcquireCarPrice());

                    carDataService.addCar(car);
                    transactionService.saveTransaction(transaction);
                    contractService.saveContract(contract);
                    transactionService.saveAccount(account);
                    break;
                }
                case "sale": {


                    Transaction transaction = getTransaction(transactionDto, car, customer);

                    car.setSold(true);
                    car.setVisible(false);
                    car.setCustomer(customer);

                    Contract contract = getContract(transactionType, transaction);

                    car.getPriceHistory().setSellCarPrice(transactionDto.getPrice());
                    car.getPriceHistory().setSellCarContract(contract);

                    Account account = new Account(contract,new Date());
                    account.setIncome(transactionDto.getPrice());

                    /*
                     * If Aqcuire car price is 0, Car Dealer has to pay 80% of price to last owner
                     */
                    if (car.getPriceHistory().getAcquireCarPrice() == 0) {
                        account.setPayment(Double.valueOf(transactionDto.getPrice() * 0.8).longValue());
                    }

                    carDataService.updateCar(car);
                    transactionService.saveTransaction(transaction);
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

    private Contract getContract(String transactionType, Transaction transaction) {
        Contract contract = new Contract();
        contract.setTransaction(transaction);
        contract.setContent(transactionType);
        return contract;
    }


    private Transaction getTransaction(
            @Valid @ModelAttribute("transactionDto") TransactionDto transactionDto,
            Car car, Customer customer) {
        Transaction transaction = new Transaction();
        transaction.setCustomer(customer);
        transaction.setCar(car);
        transaction.setPrice(transactionDto.getPrice());
        transaction.setTransactionType(transactionDto.getTransactionType());
        return transaction;
    }
}
