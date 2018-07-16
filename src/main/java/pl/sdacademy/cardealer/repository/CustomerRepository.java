package pl.sdacademy.cardealer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sdacademy.cardealer.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Long> {

    Customer findByPesel(String PESEL);
    Customer findByNip(String NIP);


}
