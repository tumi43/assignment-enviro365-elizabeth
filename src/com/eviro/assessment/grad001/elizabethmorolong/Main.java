package com.eviro.assessment.grad001.elizabethmorolong;

import com.eviro.assessment.grad001.elizabethmorolong.expectionhandler.AccountNotFoundException;
import com.eviro.assessment.grad001.elizabethmorolong.expectionhandler.WithdrawalAmountTooLargeException;
import com.eviro.assessment.grad001.elizabethmorolong.model.AccountModel;
import com.eviro.assessment.grad001.elizabethmorolong.repo.SystemDB;
import com.eviro.assessment.grad001.elizabethmorolong.service.AccountService;
import com.eviro.assessment.grad001.elizabethmorolong.service.impl.AccountServiceImpl;

import java.math.BigDecimal;
import java.util.Arrays;

public class Main {

    private static AccountService accountService = new AccountServiceImpl();

    //SystemDB is being initialized in the SystemDB class constructor.
    public static void main(String[] args) {

        initializeDB();

        //TEST - Savings Account: Successfully withdraw.
        System.out.printf("START: Testing Successful withdraw flow: %n");
        runExecution("1", new BigDecimal(85));
        System.out.printf("END: Testing Successful withdraw flow: %n %n");


        System.out.printf("START: Test behaviour for incorrect account number (AccountNotFoundException): %n");
        runExecution("555", new BigDecimal(85));
        System.out.printf("END: Test behaviour for incorrect account number: %n %n");


        System.out.printf("START: Test behaviour for withdrawal amount that's too high (WithdrawalAmountTooLargeException): %n");
        runExecution("1", new BigDecimal(1500));
        System.out.printf("END: Test behaviour for withdrawal amount that's too high : %n %n");

        System.out.printf("START: - CurrentAccount: Test successful withdrawal: %n");
        runExecution("5", new BigDecimal(5000));
        System.out.printf("END: - CurrentAccount:  Test successful withdrawal: %n %n");

        System.out.printf("START: - CurrentAccount: Test behaviour for withdrawal amount that's too high (WithdrawalAmountTooLargeException): %n");
        runExecution("3", new BigDecimal(50000));
        System.out.printf("END: - CurrentAccount: Test behaviour for withdrawal amount that's too high (WithdrawalAmountTooLargeException): %n %n");

        System.out.printf("START: - CurrentAccount: Test behaviour for withdrawal that takes money from over draft: %n");
        runExecution("3", new BigDecimal(5000));
        System.out.printf("END: - CurrentAccount: Test behaviour for withdrawal that takes money from over draft : %n  %n");

    }

    private static void runExecution(String accountNumber, BigDecimal withdrawalAmount) {
        try {
            accountService.withdraw(accountNumber, withdrawalAmount);
        } catch (AccountNotFoundException ex) {
            System.out.printf("Error occurred : %s %n", ex.getErrorMessage());
        } catch (WithdrawalAmountTooLargeException ex) {
            System.out.printf("Error occurred : %s %n", ex.getErrorMessage());
        }
    }

    private static void initializeDB() {
        SystemDB.setAccountList(Arrays.asList(
                new AccountModel("101", "1", new BigDecimal(2000)),
                new AccountModel("102", "2", new BigDecimal(5000)),
                new AccountModel("103", "3", new BigDecimal(1000), new BigDecimal(10000)),
                new AccountModel("104", "4", new BigDecimal(5000), new BigDecimal(20000)),
                new AccountModel("105", "5", new BigDecimal(0), new BigDecimal(20000))
        ));
    }

}
