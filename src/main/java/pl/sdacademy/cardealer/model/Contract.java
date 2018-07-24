package pl.sdacademy.cardealer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Contract extends BaseModelVersion {

    @OneToOne
    @JoinColumn(name = "transaction_id")
    private Transaction transaction;


    @Column
    private String content;


    public Contract() {
    }



    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
