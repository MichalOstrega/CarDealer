package pl.sdacademy.cardealer.services;

import pl.sdacademy.cardealer.model.*;

import java.util.List;

public interface TransactionService {


    public Transaction saveTransaction(Transaction transaction);


    List<Transaction> getTransactions();

    List<Transaction> getTransactionsByType(String transactionType);



    Account saveAccount(Account account);
    List<Account> getAccounts();

    PriceHistory savePriceHistory(PriceHistory priceHistory);

}
