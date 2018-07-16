package pl.sdacademy.cardealer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sdacademy.cardealer.model.Customer;
import pl.sdacademy.cardealer.services.DefaultCustomerService;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    DefaultCustomerService customerService;

    @GetMapping("/new")
    public String addCustomer(Model model) {
        Customer customer = new Customer();
        model.addAttribute("addedCustomer", customer);
        return "addCustomer";
    }
}
