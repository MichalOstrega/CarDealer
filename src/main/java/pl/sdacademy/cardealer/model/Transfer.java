package pl.sdacademy.cardealer.model;

import javax.persistence.Entity;

@Entity
public class Transfer extends BaseModelTransaction {


    @Override
    public String toString() {
        return "Transfer";
    }
}
