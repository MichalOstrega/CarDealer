package pl.sdacademy.cardealer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sdacademy.cardealer.model.Transaction;

public interface TransferRepository extends JpaRepository<Transaction,Long> {
}
