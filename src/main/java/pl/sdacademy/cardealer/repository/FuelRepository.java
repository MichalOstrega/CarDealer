package pl.sdacademy.cardealer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sdacademy.cardealer.model.Fuel;

public interface FuelRepository extends JpaRepository<Fuel,Long> {
}
