package pl.sdacademy.cardealer.services;

import pl.sdacademy.cardealer.model.*;

import java.util.List;

public interface TransactionService {

    public Purchase savePurchase(Purchase purchase);

    public Transaction saveTransfer(Transaction transfer);

    public Sale saveSale(Sale sale);

    List<Transaction> getTransfers();
    List<Purchase> getPurchases();
    List<Sale> getSales();

    Account saveAccount(Account account);
    List<Account> getAccounts();

    PriceHistory savePriceHistory(PriceHistory priceHistory);

}
