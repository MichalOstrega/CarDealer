package pl.sdacademy.cardealer.model;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class Customer extends BaseModelVersion {


    @Column(name = "surname")
    private String surname;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "name")
    private String name;

    @Valid
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address= new Address();

    @Pattern(regexp = "^\\d{10}$", message = "Must be 10 digits")
    @Column(name = "NIP")
    private String nip;

    @Pattern(regexp = "^\\d{11}$", message = "Must be 11 digits")
    @Column(name = "PESEL")
    private String pesel;

    public Customer() {
        this.address = new Address();
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }
}
