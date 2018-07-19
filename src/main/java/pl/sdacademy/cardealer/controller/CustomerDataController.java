package pl.sdacademy.cardealer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.sdacademy.cardealer.dto.AddCarDropDownListDto;
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

    public CustomerDataController(CustomerService customerService, ValidatorServices validatorServices, DictionaryService dictionaryService) {
        this.customerService = customerService;
        this.validatorServices = validatorServices;
        this.dictionaryService = dictionaryService;
    }

    @GetMapping("/new")
    public String addCustomer(Model model, @RequestParam("type") int type) {
        Customer customer = new Customer();
        switch (type) {
            case 1: {
                model.addAttribute("type", "company");
                break;
            }
            case 2: {
                model.addAttribute("type", "person");
                break;
            }
            default: {
                return "redirect:/";
            }
        }
        model.addAttribute("addedCustomer", customer);
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
            @Valid @ModelAttribute("addedCustomer") Customer customerToSave,
            BindingResult bindingResult,
            Model model) {


        if (bindingResult.hasErrors()) {
            saveCustomerAddAtribute(customerToSave, model);
            return "addCustomer";
        }
        if (customerToSave.getNip() != null && customerService.findByNIP(customerToSave.getNip()) != null) {
            bindingResult.rejectValue("nip", "nip", "Customer exists");
            saveCustomerAddAtribute(customerToSave, model);
            return "addCustomer";
        }
        if (customerToSave.getNip() != null && !validatorServices.validateNIP(customerToSave.getNip())) {
            bindingResult.rejectValue("nip", "nip", "Incorrect NIP");
            saveCustomerAddAtribute(customerToSave, model);
            return "addCustomer";
        }
        if (customerToSave.getPesel() != null && !validatorServices.validatePesel(customerToSave.getPesel())) {
            bindingResult.rejectValue("pesel", "pesel", "Incorrect PESEL");
            saveCustomerAddAtribute(customerToSave, model);
            return "addCustomer";
        }
        if (customerToSave.getPesel() != null && customerService.findByPESEL(customerToSave.getPesel()) != null) {
            bindingResult.rejectValue("pesel", "pesel", "Customer exists");
            saveCustomerAddAtribute(customerToSave, model);
            return "addCustomer";
        }

        customerService.addCustomer(customerToSave);

        return "redirect:/";
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

    @GetMapping
    public String showCustomers(Model model) {
        List<Customer> customers = customerService.loadAllCustomers();
        model.addAttribute("headerMsg", "All");
        model.addAttribute("customers", customers);
        return "customers";
    }

    private void saveCustomerAddAtribute(@Valid @ModelAttribute("addedCustomer") Customer customerToSave, Model model) {
        if (customerToSave.getNip() == null && customerToSave.getPesel() != null) {
            model.addAttribute("type", "person");
        } else if (customerToSave.getNip() != null && customerToSave.getPesel() == null) {
            model.addAttribute("type", "company");
        }

        model.addAttribute("addedCustomer", customerToSave);
    }
}
