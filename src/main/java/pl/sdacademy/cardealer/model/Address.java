package pl.sdacademy.cardealer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class Address extends BaseModelVersion {

    @NotNull
    @Size(min = 1,max = 50, message = "Input City")
    @Column(name = "city")
    private String city;

    @NotNull
    @Size(min = 1,max = 50, message = "Input Street")
    @Column(name = "street")
    private String street;

    @NotNull
    @Size(min = 1,max = 50, message = "Input House Number")
    @Column(name = "house_number")
    private String houseNumber;

    @Size(max = 10)
    @Column(name = "flat_number")
    private String flatNumber;

    @NotNull
    @Pattern(regexp = "[0-9]{2}-[0-9]{3}",message = "Must be like 00-000")
    @Column(name = "zip_code")
    private String zipCode;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getFlatNumber() {
        return flatNumber;
    }

    public void setFlatNumber(String flatNumber) {
        this.flatNumber = flatNumber;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
