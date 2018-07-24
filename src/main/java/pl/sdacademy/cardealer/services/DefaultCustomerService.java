package pl.sdacademy.cardealer.services;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.sdacademy.cardealer.model.Customer;
import pl.sdacademy.cardealer.repository.CustomerRepository;

import java.util.List;

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

    @Override
    public List<Customer> loadAllPersonCustomers() {
        return customerRepository.findAllByPeselIsNotNull();
    }

    @Override
    public List<Customer> loadAllCompanyCustomers() {
        return customerRepository.findAllByNipIsNotNull();
    }

    @Override
    public List<Customer> loadAllCustomers() {
        return customerRepository.findAll(new Sort(Sort.Direction.ASC, "id"));
    }
}
