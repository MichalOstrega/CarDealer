package pl.sdacademy.cardealer.model;

import javax.persistence.Entity;

@Entity
public class Transaction extends BaseModelTransaction {


    @Override
    public String toString() {
        return "Transaction";
    }
}
