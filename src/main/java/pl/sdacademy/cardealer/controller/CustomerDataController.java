package pl.sdacademy.cardealer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.sdacademy.cardealer.dto.CustomerDto;
import pl.sdacademy.cardealer.dto.TransactionDto;
import pl.sdacademy.cardealer.model.Car;
import pl.sdacademy.cardealer.model.Customer;
import pl.sdacademy.cardealer.services.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerDataController {

    CustomerService customerService;
    DefaultValidatorServices validatorServices;
    DictionaryService dictionaryService;
    CarDataService carDataService;

    @Autowired
    public CustomerDataController(
            CustomerService customerService,
            DefaultValidatorServices validatorServices,
            DictionaryService dictionaryService,
            CarDataService carDataService) {
        this.customerService = customerService;
        this.validatorServices = validatorServices;
        this.dictionaryService = dictionaryService;
        this.carDataService = carDataService;

    }

    @GetMapping("/persons")
    public String showPersons(Model model) {
        List<Customer> customers = customerService.loadAllPersonCustomers();
        model.addAttribute("headerMsg", "Persons");
        model.addAttribute("customers", customers);
        return "customers";
    }

    @GetMapping("/companies")
    public String showCompanies(Model model) {
        List<Customer> customers = customerService.loadAllCompanyCustomers();
        model.addAttribute("headerMsg", "Companies");
        model.addAttribute("customers", customers);
        return "customers";
    }

    @GetMapping("/new")
    public String addCustomer(Model model,
                              @RequestParam("type") String type,
                              @RequestParam(value = "carid", required = false) Long carId,
                              @RequestParam(value = "transactionType", required = false) String reqTransaction) {

        CustomerDto customerDto = new CustomerDto();
        customerDto.setCarId(carId);
        customerDto.setTransactionRequest(reqTransaction);
        customerDto.setCustomer(new Customer());
        model.addAttribute("customerDto", customerDto);
        if ((type.equals("company") || type.equals("person"))) {
            customerDto.setType(type);
        } else {
            return ":/";
        }
        return "addCustomer";
    }


    @GetMapping("/{id}")
    public String showCarDetails(Model model, @PathVariable("id") Long id) {
        Customer customer = customerService.findById(id);
        if (customer == null) {
            return "redirect:/";
        }
        model.addAttribute("customer", customer);
        return "customerDetails";
    }

    @PostMapping
    public String saveCustomer(
            @Valid @ModelAttribute("customerDto") CustomerDto customerDto,
            BindingResult bindingResult,
            Model model) {


        Customer customerToSave = customerDto.getCustomer();

        bindingResult.setNestedPath("customer");
        if (bindingResult.hasErrors()) {
            model.addAttribute("customerDto", customerDto);
            bindingResult.setNestedPath("");
            return "addCustomer";
        }
        if (customerToSave.getNip() != null && customerService.findByNIP(customerToSave.getNip()) != null) {
            bindingResult.rejectValue("nip", "nip", "Customer exists");
            model.addAttribute("customerDto", customerDto);
            bindingResult.setNestedPath("");
            return "addCustomer";
        }
        if (customerToSave.getNip() != null && !validatorServices.validateNIP(customerToSave.getNip())) {
            bindingResult.rejectValue("nip", "nip", "Incorrect NIP");
            model.addAttribute("customerDto", customerDto);
            bindingResult.setNestedPath("");
            return "addCustomer";
        }
        if (customerToSave.getPesel() != null && !validatorServices.validatePesel(customerToSave.getPesel())) {
            bindingResult.rejectValue("pesel", "pesel", "Incorrect PESEL");
            model.addAttribute("customerDto", customerDto);
            bindingResult.setNestedPath("");
            return "addCustomer";
        }
        if (customerToSave.getPesel() != null && customerService.findByPESEL(customerToSave.getPesel()) != null) {
            bindingResult.rejectValue("pesel", "pesel", "Customer exists");
            model.addAttribute("customerDto", customerDto);
            bindingResult.setNestedPath("");
            return "addCustomer";
        }

        Customer customerSaved = customerService.addCustomer(customerToSave);


        if (customerDto.getTransactionRequest() != null && !customerDto.getTransactionRequest().equals("")) {
            TransactionDto transactionDto = new TransactionDto();
            transactionDto.setTransactionType(customerDto.getTransactionRequest());
            Long carId = customerDto.getCarId();

            if (carId != null && carDataService.loadCarById(carId) != null) {
                transactionDto.setCar(carDataService.loadCarById(carId));
                transactionDto.setCarExist(true);
            } else {
                transactionDto.setCar(new Car());
            }
            transactionDto.setCustomer(customerSaved);
            transactionDto.setCustomerExist(true);
            transactionDto.setValidNumber(customerDto.getType() == "company" ? customerSaved.getNip() : customerSaved.getPesel());
            model.addAttribute("transactionDto", transactionDto);
            return "addTransaction";
        }

        return "redirect:/customers";
    }

    @GetMapping
    public String showCustomers(Model model) {
        List<Customer> customers = customerService.loadAllCustomers();
        model.addAttribute("headerMsg", "All");
        model.addAttribute("customers", customers);
        return "customers";
    }


}
