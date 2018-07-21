package pl.sdacademy.cardealer.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Purchase extends BaseModelTransaction{

    public Purchase() {

    }

    @Override
    public String toString() {
        return "Purchase";
    }
}
