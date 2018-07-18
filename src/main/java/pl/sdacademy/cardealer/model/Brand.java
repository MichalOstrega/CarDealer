package pl.sdacademy.cardealer.model;

import javax.persistence.Entity;

@Entity
public class Brand extends BaseDictionary {
    public Brand(String name) {
        super(name);
    }

    public Brand() {
    }
}
