package pl.sdacademy.cardealer.services;

import pl.sdacademy.cardealer.model.Customer;
import pl.sdacademy.cardealer.repository.CustomerRepository;

public class DefaultCustomerService implements CustomerService {

    CustomerRepository customerRepository;

    public DefaultCustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer findById(Long ID) {
        return customerRepository.findOne(ID);
    }

    @Override
    public Customer findByPESEL(Long PESEL) {
        return customerRepository.findByPesel(PESEL);
    }

    @Override
    public Customer findByNIP(Long NIP) {
        return customerRepository.findByNip(NIP);
    }
}
