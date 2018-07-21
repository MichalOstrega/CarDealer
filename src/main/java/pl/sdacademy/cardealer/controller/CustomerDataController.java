package pl.sdacademy.cardealer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.sdacademy.cardealer.dto.AddCarDropDownListDto;
import pl.sdacademy.cardealer.dto.CustomerDto;
import pl.sdacademy.cardealer.dto.TransactionDto;
import pl.sdacademy.cardealer.model.Car;
import pl.sdacademy.cardealer.model.Customer;
import pl.sdacademy.cardealer.services.CustomerService;
import pl.sdacademy.cardealer.services.DefaultCustomerService;
import pl.sdacademy.cardealer.services.DictionaryService;
import pl.sdacademy.cardealer.services.ValidatorServices;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerDataController {

    CustomerService customerService;
    ValidatorServices validatorServices;
    DictionaryService dictionaryService;

    @Autowired
    public CustomerDataController(CustomerService customerService, ValidatorServices validatorServices, DictionaryService dictionaryService) {
        this.customerService = customerService;
        this.validatorServices = validatorServices;
        this.dictionaryService = dictionaryService;
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
    public String addCustomer(Model model, @RequestParam("type") String type, @RequestParam(value = "transaction", required = false) boolean reqTransaction) {

        CustomerDto customerDto = new CustomerDto();
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


        if (customerDto.isTransactionRequest() == true) {
            TransactionDto transactionDto = new TransactionDto();
            transactionDto.setCar(new Car());
            transactionDto.setCustomer(customerSaved);
            transactionDto.setCustomerExist(true);
            transactionDto.setValidNumber(customerDto.getType() == "company" ? customerSaved.getNip() : customerSaved.getPesel());
            model.addAttribute("transactionDto", transactionDto);
            return "addTransaction";
        }

        return "redirect:/";
    }

    @GetMapping
    public String showCustomers(Model model) {
        List<Customer> customers = customerService.loadAllCustomers();
        model.addAttribute("headerMsg", "All");
        model.addAttribute("customers", customers);
        return "customers";
    }


}
