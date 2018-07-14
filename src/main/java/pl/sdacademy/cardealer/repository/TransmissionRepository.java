package pl.sdacademy.cardealer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sdacademy.cardealer.model.Transmission;

public interface TransmissionRepository extends JpaRepository<Transmission,Long> {
}
