package pl.sdacademy.cardealer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.sdacademy.cardealer.dto.TransactionDto;
import pl.sdacademy.cardealer.model.Car;
import pl.sdacademy.cardealer.model.Customer;
import pl.sdacademy.cardealer.model.Transfer;
import pl.sdacademy.cardealer.services.CarDataService;
import pl.sdacademy.cardealer.services.CustomerService;

import javax.validation.Valid;

@Controller
@RequestMapping("/transaction")
public class TransactionController {

    CustomerService customerService;
    CarDataService carDataService;

    public TransactionController(CustomerService customerService, CarDataService carDataService) {

        this.customerService = customerService;
        this.carDataService = carDataService;
    }

    @GetMapping
    public String carTransfer(Model model, @RequestParam(value = "vin", required = false) String vin) {
        TransactionDto transactionDto = new TransactionDto();
        Car car;
        if (vin != null && carDataService.loadCarByVIN(vin) != null) {
            car = carDataService.loadCarByVIN(vin);
            transactionDto.setCarExist(true);
        } else {
            car = new Car();
        }
        transactionDto.setCar(car);
        transactionDto.setCustomer(new Customer());
        transactionDto.setCustomerRadio(1l);
        transactionDto.setCustomerExist(false);
        model.addAttribute("transactionDto", transactionDto);
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

    @PostMapping("/car")
    public String checkCar(@Valid @ModelAttribute("transactionDto") TransactionDto transactionDto,
                           BindingResult bindingResult,
                           Model model) {
        Car byVIN = carDataService.loadCarByVIN(transactionDto.getCar().getVin());
        if (byVIN == null) {
            bindingResult.setNestedPath("car");
            bindingResult.rejectValue("vin", "vin", "Car doesn't exists. Check inputed number or first create car");
            model.addAttribute("transactionDto", transactionDto);
            return "addTransaction";
        } else {
            transactionDto.setCarExist(true);
            transactionDto.setCar(byVIN);
            model.addAttribute("transactionDto", transactionDto);
        }
        return "addTransaction";
    }

}
