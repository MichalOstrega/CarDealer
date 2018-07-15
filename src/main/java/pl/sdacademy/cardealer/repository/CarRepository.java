package pl.sdacademy.cardealer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sdacademy.cardealer.model.Car;

import java.util.List;

public interface CarRepository extends JpaRepository<Car,Long> {

    List<Car> findAllBySoldIsTrue();
    List<Car> findAllBySoldIsFalse();
    Car findByVin(String Vin);

}
