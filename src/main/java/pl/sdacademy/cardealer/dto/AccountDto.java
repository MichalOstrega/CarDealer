package pl.sdacademy.cardealer.dto;

import pl.sdacademy.cardealer.model.Account;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AccountDto {
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");

    private List<Account> accounts;

    private Long sumIncomes;

    private Long sumPayments;

    private String transactionType;

    private Date date;

    private String from = "01/01/2018";

    private String to = simpleDateFormat.format(new Date());

    public AccountDto() {
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getSumIncomes() {
        return sumIncomes;
    }

    public void setSumIncomes(Long sumIncomes) {
        this.sumIncomes = sumIncomes;
    }

    public Long getSumPayments() {
        return sumPayments;
    }

    public void setSumPayments(Long sumPayments) {
        this.sumPayments = sumPayments;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
}
