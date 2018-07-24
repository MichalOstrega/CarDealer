package pl.sdacademy.cardealer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sdacademy.cardealer.model.Customer;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Long> {

    Customer findByPesel(String PESEL);
    Customer findByNip(String NIP);

    List<Customer> findAllByPeselIsNotNull();
    List<Customer> findAllByNipIsNotNull();


}
