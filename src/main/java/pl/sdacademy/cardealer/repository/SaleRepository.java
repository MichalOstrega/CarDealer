package pl.sdacademy.cardealer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sdacademy.cardealer.model.Sale;

public interface SaleRepository extends JpaRepository<Sale,Long> {
}
