package pl.sdacademy.cardealer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.sdacademy.cardealer.dto.TransferDto;
import pl.sdacademy.cardealer.model.Car;
import pl.sdacademy.cardealer.model.Customer;
import pl.sdacademy.cardealer.model.Transfer;
import pl.sdacademy.cardealer.services.CustomerService;
import pl.sdacademy.cardealer.services.DictionaryService;

@Controller("transaction")
public class TransactionController {

    CustomerService customerService;

    public TransactionController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/transfer")
    public String carTransfer(Model model) {
        TransferDto transferDto = new TransferDto();
        transferDto.setCar(new Car());
        transferDto.setCustomer(new Customer());
        transferDto.setCustomerList(customerService.loadAllCustomers());
        model.addAttribute("transferDto", transferDto);
        model.addAttribute("transfer", new Transfer());
        return "addTransfer";
    }

}
