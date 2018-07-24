package pl.sdacademy.cardealer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sdacademy.cardealer.model.PriceHistory;

public interface PriceHistoryRepository extends JpaRepository<PriceHistory,Long> {
}
