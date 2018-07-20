package pl.sdacademy.cardealer.dto;

import org.springframework.stereotype.Component;
import pl.sdacademy.cardealer.model.Customer;

import javax.validation.Valid;

@Component

public class CustomerDto {
    private String type;
    @Valid
    private Customer customer;
    private boolean transactionRequest;

    public CustomerDto() {
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

    public boolean isTransactionRequest() {
        return transactionRequest;
    }

    public void setTransactionRequest(boolean transactionRequest) {
        this.transactionRequest = transactionRequest;
    }
}
