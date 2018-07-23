package pl.sdacademy.cardealer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Contract extends BaseModelVersion {

    @OneToOne
    @JoinColumn(name = "transfer_id")
    private Transaction transfer;

    @OneToOne
    @JoinColumn(name = "sale_id")
    private Sale sale;

    @OneToOne
    @JoinColumn(name = "purchase_id")
    private Purchase purchase;

    @Column
    private String content;

    @Column
    private String transaction;

    public Contract() {
    }

    public String getTransaction() {
        return transaction;
    }

    public void setTransaction(String transaction) {
        this.transaction = transaction;
    }

    public Transaction getTransfer() {
        return transfer;
    }

    public void setTransfer(Transaction transfer) {
        this.transfer = transfer;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
