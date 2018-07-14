package pl.sdacademy.cardealer.model;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseDictionary extends BaseModel {

    private String name;

    public BaseDictionary() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
