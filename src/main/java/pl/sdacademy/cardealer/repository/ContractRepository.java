package pl.sdacademy.cardealer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sdacademy.cardealer.model.Contract;

public interface ContractRepository extends JpaRepository<Contract, Long> {
}
