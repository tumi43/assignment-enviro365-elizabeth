package com.eviro.assessment.grad001.elizabethmorolong.model;

import java.math.BigDecimal;

public class AccountModel {

    public AccountModel() {
        this.balance = BigDecimal.ZERO;
        this.overDraft = BigDecimal.ZERO;
    }

    public AccountModel(String id, String accountNumber, BigDecimal balance) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.accType = "SavingsAccount";
    }

    public AccountModel(String id, String accountNumber, BigDecimal balance, BigDecimal overDraft) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.accType = "CurrentAccount";

        // Assigned overdraft can never be more that 100K
        if (overDraft.intValue() > 100000) {
            this.overDraft = new BigDecimal(100000);
        } else {
            this.overDraft = overDraft;
        }
    }

    private String id;
    private String accountNumber;
    private Long customerNumber;
    private BigDecimal balance;
    private BigDecimal overDraft;
    private boolean isActive;
    private String accType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public boolean isActive() {
        return this.balance.intValue() > 1000;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Long getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(Long customerNumber) {
        this.customerNumber = customerNumber;
    }

    public BigDecimal getOverDraft() {
        return overDraft;
    }

    public void setOverDraft(BigDecimal overDraft) {
        this.overDraft = overDraft;
    }

    public String getAccType() {
        return accType;
    }

    public void setAccType(String accType) {
        this.accType = accType;
    }

}
