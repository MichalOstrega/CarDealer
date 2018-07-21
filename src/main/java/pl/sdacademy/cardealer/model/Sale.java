package pl.sdacademy.cardealer.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Sale extends BaseModelTransaction{


    public Sale() {

    }

    @Override
    public String toString() {
        return "Sale";
    }
}
