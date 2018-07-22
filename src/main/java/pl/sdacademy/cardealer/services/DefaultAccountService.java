package pl.sdacademy.cardealer.services;

import org.springframework.stereotype.Service;
import pl.sdacademy.cardealer.model.Account;
import pl.sdacademy.cardealer.repository.AccountRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

@Service
public class DefaultAccountService implements AccountService {

    AccountRepository accountRepository;

    public DefaultAccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public Long sumIncomes(List<Account> accounts) {
        return accounts.stream().mapToLong(Account::getIncome).sum();
    }

    @Override
    public Long sumPayment(List<Account> accounts) {
        return accounts.stream().mapToLong(Account::getPayment).sum();
    }


    @Override
    public List<Account> getTransfers() {
        return getAllAccounts().stream().filter(account -> account.getContract().getTransfer() != null).collect(Collectors.toList());
    }

    @Override
    public List<Account> getSales() {
        return getAllAccounts().stream().filter(account -> account.getContract().getSale() != null).collect(Collectors.toList());
    }

    @Override
    public List<Account> getPurchases() {
        return getAllAccounts().stream().filter(account -> account.getContract().getPurchase() != null).collect(Collectors.toList());
    }
}
