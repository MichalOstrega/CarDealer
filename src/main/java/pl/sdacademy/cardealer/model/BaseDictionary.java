package pl.sdacademy.cardealer.model;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Size;

@MappedSuperclass
public abstract class BaseDictionary extends BaseModel {

//    @Size(min = 1, max = 30)
    private String name;

    public BaseDictionary() {
    }

    public BaseDictionary(String name) {
        this.name = name;
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
