package pl.sdacademy.cardealer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sdacademy.cardealer.model.Account;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> findAllByContract_PurchaseIsNotNull();
    List<Account> findAllByContract_SaleIsNotNull();
    List<Account> findAllByContract_TransferIsNotNull();
}

