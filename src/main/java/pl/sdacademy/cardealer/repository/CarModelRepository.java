package pl.sdacademy.cardealer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sdacademy.cardealer.model.CarModel;

import java.util.Set;

public interface CarModelRepository extends JpaRepository<CarModel,Long> {
    Set<CarModel> findByBrand_IdOrderByName(Long id);
}
