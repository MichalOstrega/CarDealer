package pl.sdacademy.cardealer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Customer extends BaseModelVersion {

    @Column(name = "surname")
    private String surname;

    @Column(name = "name")
    private String name;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @Column(name = "NIP")
    private int nip;

    @Column(name = "PESEL")
    private int pesel;

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

    public int getNip() {
        return nip;
    }

    public void setNip(int nip) {
        this.nip = nip;
    }

    public int getPesel() {
        return pesel;
    }

    public void setPesel(int pesel) {
        this.pesel = pesel;
    }
}
