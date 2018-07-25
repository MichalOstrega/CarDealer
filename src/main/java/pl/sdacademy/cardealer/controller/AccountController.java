package pl.sdacademy.cardealer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping
    public String getAllAccounts(Model model){
        List<Account> accounts = accountService.getAllAccounts();

        AccountDto accountDto = getAccountDtoWithFields(accounts, "");
        model.addAttribute("accountDto", accountDto);

        return "accounts";
    }

    @GetMapping("/{transactionType}")
    public String getTransfers(Model model, @PathVariable("transactionType") String transactionType){


        List<Account> accountList = accountService.getTransactionsByType(transactionType);
        AccountDto accountDto = getAccountDtoWithFields(accountList, transactionType);


        model.addAttribute("accountDto", accountDto);

        return "accounts";
    }







    @PostMapping
    public String getAccounts(@ModelAttribute AccountDto accountDto, Model model) {
        String from = accountDto.getFrom();
        String to = accountDto.getTo();


        List<Account> transactionsBetween = accountService.getTransactionsBetween(accountDto.getTransactionType(), from, to);

        accountDto = getAccountDtoWithFields(transactionsBetween, accountDto.getTransactionType());
        accountDto.setFrom(from);
        accountDto.setTo(to);

        model.addAttribute("accountDto", accountDto);

        return "accounts";


    }

    private AccountDto getAccountDtoWithFields(List<Account> accounts, String transactionType) {
        AccountDto accountDto = new AccountDto();
        accountDto.setTransactionType(transactionType);
        Long sumIncomes = accountService.sumIncomes(accounts);
        Long sumPayment = accountService.sumPayment(accounts);
        accountDto.setAccounts(accounts);
        accountDto.setSumIncomes(sumIncomes);
        accountDto.setSumPayments(sumPayment);
        accountDto.setDate(new Date());
        return accountDto;
    }
}
