package pl.sdacademy.cardealer.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Sale extends BaseModelTransaction{

    @Column(name = "price")
    private int price;

    public Sale() {

    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
