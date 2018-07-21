package pl.sdacademy.cardealer.dto;

import org.springframework.stereotype.Component;
import pl.sdacademy.cardealer.model.Car;
import pl.sdacademy.cardealer.model.Customer;

import javax.validation.Valid;
import java.util.List;

@Component
public class TransactionDto {
    @Valid
    private Car car;
    @Valid
    private Customer customer;
    private boolean customerExist;
    private boolean carExist;
    private String validNumber;
    private Integer price;
    /*
    * 1 - transfer
    * 2 - purchase (default)
    * 3 - sale
    *
    * */
    private String transactionType;

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

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
