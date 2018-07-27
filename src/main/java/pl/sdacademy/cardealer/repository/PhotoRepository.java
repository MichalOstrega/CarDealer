package pl.sdacademy.cardealer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sdacademy.cardealer.model.Photo;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
}
