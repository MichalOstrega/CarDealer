package pl.sdacademy.cardealer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sdacademy.cardealer.model.Brand;
import pl.sdacademy.cardealer.model.ProductionYear;

public interface ProductionYearRepository extends JpaRepository<ProductionYear,Long> {
}
