package pl.sdacademy.cardealer.services;

import pl.sdacademy.cardealer.model.Account;

import java.util.List;

public interface AccountService {

    List<Account> getAllAccounts();

    Long sumIncomes(List<Account> accounts);
    Long sumPayment(List<Account> accounts);

    List<Account> getTransactionsBetween(String transactionType, String from, String to);

    List<Account> getTransactionsByType(String transactionType);
}
