package pl.sdacademy.cardealer.services;

import pl.sdacademy.cardealer.model.Account;

import java.util.List;

public interface AccountService {

    List<Account> getAllAccounts();

    List<Account> getTransfers();
    List<Account> getSales();
    List<Account> getPurchases();



    Long sumIncomes(List<Account> accounts);
    Long sumPayment(List<Account> accounts);
}
