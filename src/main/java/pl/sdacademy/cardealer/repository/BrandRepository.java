package pl.sdacademy.cardealer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sdacademy.cardealer.model.Brand;

public interface BrandRepository extends JpaRepository<Brand,Long> {
}
