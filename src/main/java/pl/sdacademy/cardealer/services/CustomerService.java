package pl.sdacademy.cardealer.services;

import pl.sdacademy.cardealer.model.Customer;

public interface CustomerService {

    Customer findById(Long ID);
    Customer findByPESEL(String PESEL);
    Customer findByNIP(String NIP);


    Customer addCustomer(Customer customerToSave);
}
