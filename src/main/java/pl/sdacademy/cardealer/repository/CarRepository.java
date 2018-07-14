package pl.sdacademy.cardealer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sdacademy.cardealer.model.Car;

public interface CarRepository extends JpaRepository<Car,Long> {
}
