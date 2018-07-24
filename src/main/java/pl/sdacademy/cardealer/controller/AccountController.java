package pl.sdacademy.cardealer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sdacademy.cardealer.dto.AccountDto;
import pl.sdacademy.cardealer.model.Account;
import pl.sdacademy.cardealer.services.AccountService;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/accounts")
public class AccountController {

    AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/all")
    public String getAllAccounts(Model model){
        List<Account> accounts = accountService.getAllAccounts();

        AccountDto accountDto = getAccountDtoWithFields(accounts);
        model.addAttribute("accountDto", accountDto);

        return "accounts";
    }

    @GetMapping("/transfers")
    public String getTransfers(Model model){
        List<Account> accounts = accountService.getTransfers();

        AccountDto accountDto = getAccountDtoWithFields(accounts);
        model.addAttribute("accountDto", accountDto);

        return "accounts";
    }

    @GetMapping("/sales")
    public String getSales(Model model){
        List<Account> accounts = accountService.getSales();

        AccountDto accountDto = getAccountDtoWithFields(accounts);
        model.addAttribute("accountDto", accountDto);

        return "accounts";
    }



    @GetMapping("/purchases")
    public String getPurchases(Model model){
        List<Account> accounts = accountService.getPurchases();

        AccountDto accountDto = getAccountDtoWithFields(accounts);
        model.addAttribute("accountDto", accountDto);

        return "accounts";
    }

    private AccountDto getAccountDtoWithFields(List<Account> accounts) {
        AccountDto accountDto = new AccountDto();
        Long sumIncomes = accountService.sumIncomes(accounts);
        Long sumPayment = accountService.sumPayment(accounts);
        accountDto.setAccounts(accounts);
        accountDto.setSumIncomes(sumIncomes);
        accountDto.setSumPayments(sumPayment);
        accountDto.setDate(new Date());
        return accountDto;
    }
}
