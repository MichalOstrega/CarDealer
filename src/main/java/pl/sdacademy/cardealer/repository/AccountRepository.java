package pl.sdacademy.cardealer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sdacademy.cardealer.model.Account;

import java.util.Date;
import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> findAllByContract_Transaction_TransactionTypeIs(String transactionType);

    List<Account> findAllByDateBetween(Date from, Date to);
}

