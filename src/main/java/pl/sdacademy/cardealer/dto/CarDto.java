package pl.sdacademy.cardealer.dto;

import org.springframework.stereotype.Component;
import pl.sdacademy.cardealer.model.Car;
import pl.sdacademy.cardealer.model.Customer;

import javax.validation.Valid;

@Component
public class CarDto {
    private AddCarDropDownListDto dropList;
    @Valid
    private Car car;
    private String transactionRequest;

    private Long customerId;

    public CarDto() {
    }

    public String getTransactionRequest() {
        return transactionRequest;
    }

    public void setTransactionRequest(String transactionRequest) {
        this.transactionRequest = transactionRequest;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public AddCarDropDownListDto getDropList() {
        return dropList;
    }

    public void setDropList(AddCarDropDownListDto dropList) {
        this.dropList = dropList;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }


}
