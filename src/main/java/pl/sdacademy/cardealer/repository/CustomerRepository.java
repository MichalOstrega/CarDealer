package pl.sdacademy.cardealer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sdacademy.cardealer.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Long> {

    Customer findByPesel(Long PESEL);
    Customer findByNip(Long NIP);


}
