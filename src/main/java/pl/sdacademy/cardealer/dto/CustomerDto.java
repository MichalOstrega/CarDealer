package pl.sdacademy.cardealer.dto;

import org.springframework.stereotype.Component;
import pl.sdacademy.cardealer.model.Customer;

import javax.validation.Valid;

@Component

public class CustomerDto {
    private String type;
    @Valid
    private Customer customer;
    private String transactionRequest;
    private Long carId;

    public CustomerDto() {

    }




    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }


    public String getTransactionRequest() {
        return transactionRequest;
    }

    public void setTransactionRequest(String transactionRequest) {
        this.transactionRequest = transactionRequest;
    }
}
