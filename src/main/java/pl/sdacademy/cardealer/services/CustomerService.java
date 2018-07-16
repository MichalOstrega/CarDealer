package pl.sdacademy.cardealer.services;

import pl.sdacademy.cardealer.model.Customer;

public interface CustomerService {

    Customer findById(Long ID);
    Customer findByPESEL(Long PESEL);
    Customer findByNIP(Long NIP);


}
