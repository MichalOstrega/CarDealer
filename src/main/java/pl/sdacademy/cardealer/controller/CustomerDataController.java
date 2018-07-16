package pl.sdacademy.cardealer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sdacademy.cardealer.dto.AddCarDropDownListDto;
import pl.sdacademy.cardealer.model.Car;
import pl.sdacademy.cardealer.model.Customer;
import pl.sdacademy.cardealer.services.DefaultCustomerService;
import pl.sdacademy.cardealer.services.ValidatorServices;

import javax.validation.Valid;

@Controller
@RequestMapping("/customers")
public class CustomerDataController {

    DefaultCustomerService customerService;
    ValidatorServices validatorServices;

    public CustomerDataController(DefaultCustomerService customerService, ValidatorServices validatorServices) {
        this.customerService = customerService;
        this.validatorServices = validatorServices;
    }

    @GetMapping("/new")
    public String addCustomer(Model model) {
        Customer customer = new Customer();
        model.addAttribute("addedCustomer", customer);
        return "addCustomer";
    }

    @PostMapping
    public String saveVehicle(
            @Valid @ModelAttribute("addedCustomer") Customer customerToSave,
            BindingResult bindingResult,
            Model model) {


        if (bindingResult.hasErrors()) {
            model.addAttribute("addedCustomer", customerToSave);
            return "addCustomer";
        }
        if (customerService.findByNIP(customerToSave.getNip()) != null) {
            bindingResult.rejectValue("nip", "nip", "Customer exists");
            model.addAttribute("addedCustomer", customerToSave);
            return "addCustomer";
        }
        if (!validatorServices.validateNIP(customerToSave.getNip())){
            bindingResult.rejectValue("nip", "nip", "Incorrect NIP");
            model.addAttribute("addedCustomer", customerToSave);
            return "addCustomer";
        }
        if (!validatorServices.validatePesel(customerToSave.getPesel())){
            bindingResult.rejectValue("pesel", "pesel", "Incorrect PESEL");
            model.addAttribute("addedCustomer", customerToSave);
            return "addCustomer";
        }
        if (customerService.findByPESEL(customerToSave.getPesel()) != null) {
            bindingResult.rejectValue("pesel", "pesel", "Customer exists");
            model.addAttribute("addedCustomer", customerToSave);
            return "addCustomer";
        }

        customerService.addCustomer(customerToSave);

        return "redirect:/";
    }
}
