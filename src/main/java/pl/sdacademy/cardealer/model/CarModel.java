package pl.sdacademy.cardealer.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="model")
public class CarModel extends BaseDictionary {

    @OneToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    public CarModel(Brand brand, String name) {
        super(name);
        this.brand = brand;
    }

    public CarModel() {
        super();
    }

    public CarModel(String name) {
        super(name);
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }
}
