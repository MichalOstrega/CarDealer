package pl.sdacademy.cardealer.services;

import org.springframework.stereotype.Service;
import pl.sdacademy.cardealer.model.Account;
import pl.sdacademy.cardealer.repository.AccountRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DefaultAccountService implements AccountService {

    AccountRepository accountRepository;
    SimpleDateFormat simpleDateFormat;
    int oneDay = (1000 * 60 * 60 * 24);


    public DefaultAccountService(AccountRepository accountRepository) {

        simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
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
    public List<Account> getTransactionsByType(String transactionType) {
        return getTransactionsBetween(transactionType, "01/01/2018", simpleDateFormat.format(new Date()));
    }

    private String getTransactionType(Account account) {
        return account.getContract().getTransaction().getTransactionType();
    }

    @Override
    public List<Account> getTransactionsBetween(String transactionType, String from, String to) {
        try {
            Date toDate = new Date(simpleDateFormat.parse(to).getTime() + oneDay);
            Date fromDate = simpleDateFormat.parse(from);
            List<Account> allByDateBetween = accountRepository.findAllByDateBetween(fromDate, toDate);
            if (transactionType != null && !transactionType.equals("")) {
                allByDateBetween = filterListByType(allByDateBetween, transactionType);
            }
            return allByDateBetween;
        } catch (ParseException e) {
            e.printStackTrace();
            return getAllAccounts();
        }
    }

    private List<Account> filterListByType(List<Account> allByDateBetween, String transactionType) {
        return allByDateBetween.stream().filter(account -> getTransactionType(account).equals(transactionType)).collect(Collectors.toList());
    }
}
