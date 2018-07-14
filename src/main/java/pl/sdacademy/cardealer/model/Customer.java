package pl.sdacademy.cardealer.model;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

public class Customer extends BaseModelVersion {

    @Column(name = "surname")
    private String surname;

    @Column(name = "name")
    private String name;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @Column(name = "NIP")
    private int NIP;

    @Column(name = "PESEL")
    private int PESEL;

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

    public int getNIP() {
        return NIP;
    }

    public void setNIP(int NIP) {
        this.NIP = NIP;
    }

    public int getPESEL() {
        return PESEL;
    }

    public void setPESEL(int PESEL) {
        this.PESEL = PESEL;
    }
}
