package pl.sdacademy.cardealer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sdacademy.cardealer.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
}

