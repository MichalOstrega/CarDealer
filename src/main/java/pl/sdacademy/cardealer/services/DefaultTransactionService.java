package pl.sdacademy.cardealer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sdacademy.cardealer.model.*;
import pl.sdacademy.cardealer.repository.*;

import java.util.List;

@Service
public class DefaultTransactionService implements TransactionService {
    private TransactionRepository transactionRepository;
    private AccountRepository accountRepository;
    private PriceHistoryRepository priceHistoryRepository;

    @Autowired
    public DefaultTransactionService(TransactionRepository transactionRepository,
                                     AccountRepository accountRepository, PriceHistoryRepository priceHistoryRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
        this.priceHistoryRepository = priceHistoryRepository;
    }


    public Transaction saveTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }


    @Override
    public List<Transaction> getTransactions() {
        return transactionRepository.findAll();
    }


    public TransactionRepository getTransactionRepository() {
        return transactionRepository;
    }

    public void setTransactionRepository(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public AccountRepository getAccountRepository() {
        return accountRepository;
    }

    public void setAccountRepository(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public PriceHistoryRepository getPriceHistoryRepository() {
        return priceHistoryRepository;
    }

    public void setPriceHistoryRepository(PriceHistoryRepository priceHistoryRepository) {
        this.priceHistoryRepository = priceHistoryRepository;
    }



    @Override
    public Account saveAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public PriceHistory savePriceHistory(PriceHistory priceHistory) {
        return priceHistoryRepository.save(priceHistory);
    }

    @Override
    public List<Transaction> getTransactionsByType(String transactionType) {
        return transactionRepository.findAllByTransactionTypeIs(transactionType);
    }


}
