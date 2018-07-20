package pl.sdacademy.cardealer.dto;

import org.springframework.stereotype.Component;
import pl.sdacademy.cardealer.model.Car;
import pl.sdacademy.cardealer.model.Customer;

import java.util.List;

@Component
public class TransactionDto {
    private Car car;
    private Customer customer;
    private boolean customerExist;
    private boolean carExist;
    private String validNumber;
    private Long customerRadio;


    public boolean isCustomerExist() {
        return customerExist;
    }

    public void setCustomerExist(boolean customerExist) {
        this.customerExist = customerExist;
    }

    public boolean isCarExist() {
        return carExist;
    }

    public void setCarExist(boolean carExist) {
        this.carExist = carExist;
    }

    public Long getCustomerRadio() {
        return customerRadio;
    }

    public void setCustomerRadio(Long customerRadio) {
        this.customerRadio = customerRadio;
    }

    public String getValidNumber() {
        return validNumber;
    }

    public void setValidNumber(String validNumber) {
        this.validNumber = validNumber;
    }

    public TransactionDto() {
    }


    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
