package pl.sdacademy.cardealer.services;

import pl.sdacademy.cardealer.model.Account;
import pl.sdacademy.cardealer.model.Purchase;
import pl.sdacademy.cardealer.model.Sale;
import pl.sdacademy.cardealer.model.Transfer;

import java.util.List;

public interface TransactionService {

    public Purchase savePurchase(Purchase purchase);

    public Transfer saveTransfer(Transfer transfer);

    public Sale saveSale(Sale sale);

    List<Transfer> getTransfers();
    List<Purchase> getPurchases();
    List<Sale> getSales();

    Account saveAccount(Account account);
    List<Account> getAccounts();

}
