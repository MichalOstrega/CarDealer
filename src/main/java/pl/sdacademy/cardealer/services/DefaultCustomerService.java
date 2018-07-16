package pl.sdacademy.cardealer.services;

import org.springframework.stereotype.Service;
import pl.sdacademy.cardealer.model.Customer;
import pl.sdacademy.cardealer.repository.CustomerRepository;

@Service
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
    public Customer findByPESEL(String PESEL) {
        return customerRepository.findByPesel(PESEL);
    }

    @Override
    public Customer findByNIP(String NIP) {
        return customerRepository.findByNip(NIP);
    }

    @Override
    public Customer addCustomer(Customer customerToSave) {
        return customerRepository.save(customerToSave);
    }
}
