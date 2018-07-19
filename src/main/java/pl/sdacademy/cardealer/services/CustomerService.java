package pl.sdacademy.cardealer.services;

import pl.sdacademy.cardealer.model.Customer;

import java.util.List;

public interface CustomerService {

    Customer findById(Long ID);
    Customer findByPESEL(String PESEL);
    Customer findByNIP(String NIP);


    Customer addCustomer(Customer customerToSave);

    List<Customer> loadAllPersonCustomers();

    List<Customer> loadAllCompanyCustomers();

    List<Customer> loadAllCustomers();

}
