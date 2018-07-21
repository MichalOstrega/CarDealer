package pl.sdacademy.cardealer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sdacademy.cardealer.model.Transfer;

public interface TransferRepository extends JpaRepository<Transfer,Long> {
}
