package pl.sdacademy.cardealer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sdacademy.cardealer.model.CarModel;

import java.util.List;

public interface CarModelRepository extends JpaRepository<CarModel,Long> {
    List<CarModel> findByBrand_IdOrderByName(Long id);
}
