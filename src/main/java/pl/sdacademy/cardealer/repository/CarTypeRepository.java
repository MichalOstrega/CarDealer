package pl.sdacademy.cardealer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sdacademy.cardealer.model.Brand;
import pl.sdacademy.cardealer.model.CarType;

public interface CarTypeRepository extends JpaRepository<CarType,Long> {
}
