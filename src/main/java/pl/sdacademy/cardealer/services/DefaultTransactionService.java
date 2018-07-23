package pl.sdacademy.cardealer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sdacademy.cardealer.model.*;
import pl.sdacademy.cardealer.repository.*;

import java.util.List;

@Service
public class DefaultTransactionService implements TransactionService {
    private TransferRepository transferRepository;
    private SaleRepository saleRepository;
    private PurchaseRepository purchaseRepository;
    private AccountRepository accountRepository;
    private PriceHistoryRepository priceHistoryRepository;

    @Autowired
    public DefaultTransactionService(TransferRepository transferRepository,
                                     SaleRepository saleRepository,
                                     PurchaseRepository purchaseRepository,
                                     AccountRepository accountRepository, PriceHistoryRepository priceHistoryRepository) {
        this.transferRepository = transferRepository;
        this.saleRepository = saleRepository;
        this.purchaseRepository = purchaseRepository;
        this.accountRepository = accountRepository;
        this.priceHistoryRepository = priceHistoryRepository;
    }

    public Purchase savePurchase(Purchase purchase) {
        return purchaseRepository.save(purchase);
    }

    public Transaction saveTransfer(Transaction transfer) {
        return transferRepository.save(transfer);
    }

    public Sale saveSale(Sale sale) {
        return saleRepository.save(sale);
    }

    @Override
    public List<Transaction> getTransfers() {
        return transferRepository.findAll();
    }

    @Override
    public List<Purchase> getPurchases() {
        return purchaseRepository.findAll();
    }

    @Override
    public List<Sale> getSales() {
        return saleRepository.findAll();
    }

    public TransferRepository getTransferRepository() {
        return transferRepository;
    }

    public void setTransferRepository(TransferRepository transferRepository) {
        this.transferRepository = transferRepository;
    }

    public SaleRepository getSaleRepository() {
        return saleRepository;
    }

    public void setSaleRepository(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    public PurchaseRepository getPurchaseRepository() {
        return purchaseRepository;
    }

    public void setPurchaseRepository(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
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
}
