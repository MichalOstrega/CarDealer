package pl.sdacademy.cardealer.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class Account extends BaseModelVersion {


    private Long income;

    private Long payment;

    @ManyToOne
    @JoinColumn(name = "contract_id")
    private Contract contract;

    private Date date;

    public Account() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getIncome() {
        return income;
    }

    public void setIncome(Long income) {
        this.income = income;
    }

    public Long getPayment() {
        return payment;
    }

    public void setPayment(Long payment) {
        this.payment = payment;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }
}
