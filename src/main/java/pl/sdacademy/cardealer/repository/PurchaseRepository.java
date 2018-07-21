package pl.sdacademy.cardealer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sdacademy.cardealer.model.Purchase;

public interface PurchaseRepository extends JpaRepository<Purchase,Long> {
}
