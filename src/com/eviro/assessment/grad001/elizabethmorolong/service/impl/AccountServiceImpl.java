package com.eviro.assessment.grad001.elizabethmorolong.service.impl;

import com.eviro.assessment.grad001.elizabethmorolong.expectionhandler.AccountNotFoundException;
import com.eviro.assessment.grad001.elizabethmorolong.model.AccountModel;
import com.eviro.assessment.grad001.elizabethmorolong.repo.SystemDB;
import com.eviro.assessment.grad001.elizabethmorolong.service.AccountService;

import java.math.BigDecimal;
import java.util.List;

public class AccountServiceImpl implements AccountService {

    private SavingsAccountComponent savingsAccountComponent = new SavingsAccountComponent();
    private CurrentAccountComponent currentAccountComponent = new CurrentAccountComponent();

    @Override
    public void withdraw(String accountNumber, BigDecimal withdrawAmount) {

        //We are expecting a list since the spec mentioned that its possible to have accounts with similar techIds and accountNumber which in turn can be treated the same/as one
        List<AccountModel> accountList = SystemDB.getAccountByAccountNum(accountNumber);

        String accountType = accountType(accountList);

        switch (accountType) {
            case "SavingsAccount":
                System.out.println("Accessing Your Savings Account");
                savingsAccountComponent.withdraw(accountNumber, withdrawAmount, accountList);
                break;
            case "CurrentAccount":
                System.out.println("Accessing Your Current Account");
                currentAccountComponent.withdraw(accountNumber, withdrawAmount, accountList);
                break;
            default:
                System.out.println("Invalid accountType");
        }

    }

    private String accountType(List<AccountModel> accountList) {
        AccountModel accountModel = accountList.stream()
                .findFirst()
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));

        return accountModel.getAccType();
    }

}
