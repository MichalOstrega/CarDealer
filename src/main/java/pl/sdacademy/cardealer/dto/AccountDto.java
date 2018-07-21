package pl.sdacademy.cardealer.dto;

import pl.sdacademy.cardealer.model.Account;

public class AccountDto {

    private Account account;

    private String transactionType;

    public AccountDto() {
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
}
