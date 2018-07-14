package pl.sdacademy.cardealer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sdacademy.cardealer.model.CarModel;

public interface CarModelRepository extends JpaRepository<CarModel,Long> {
}
