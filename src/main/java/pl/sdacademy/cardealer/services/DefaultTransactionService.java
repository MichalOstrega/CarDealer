package pl.sdacademy.cardealer.services;

import org.springframework.stereotype.Service;
import pl.sdacademy.cardealer.model.Purchase;
import pl.sdacademy.cardealer.model.Sale;
import pl.sdacademy.cardealer.model.Transfer;
import pl.sdacademy.cardealer.repository.PurchaseRepository;
import pl.sdacademy.cardealer.repository.SaleRepository;
import pl.sdacademy.cardealer.repository.TransferRepository;

import java.util.List;

@Service
public class DefaultTransactionService implements  TransactionService{
    private TransferRepository transferRepository;
    private SaleRepository saleRepository;
    private PurchaseRepository purchaseRepository;

    public DefaultTransactionService(TransferRepository transferRepository, SaleRepository saleRepository, PurchaseRepository purchaseRepository) {
        this.transferRepository = transferRepository;
        this.saleRepository = saleRepository;
        this.purchaseRepository = purchaseRepository;
    }

    public Purchase savePurchase(Purchase purchase) {
        return purchaseRepository.save(purchase);
    }

    public Transfer saveTransfer(Transfer transfer){
        return transferRepository.save(transfer);
    }

    public Sale saveSale(Sale sale) {
        return saleRepository.save(sale);
    }

    @Override
    public List<Transfer> getTransfers() {
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


}
