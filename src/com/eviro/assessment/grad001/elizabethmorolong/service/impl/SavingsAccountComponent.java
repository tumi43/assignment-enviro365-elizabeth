package com.eviro.assessment.grad001.elizabethmorolong.service.impl;

import com.eviro.assessment.grad001.elizabethmorolong.expectionhandler.WithdrawalAmountTooLargeException;
import com.eviro.assessment.grad001.elizabethmorolong.model.AccountModel;
import com.eviro.assessment.grad001.elizabethmorolong.service.AccountFunctionalityService;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.math.BigDecimal;
import java.util.List;

public class SavingsAccountComponent implements AccountFunctionalityService {


    @Override
    public void deposit(String accountNumber, BigDecimal bigDecimal) {
        throw new NotImplementedException();
    }

    @Override
    public void withdraw(String accountNumber, BigDecimal withdrawalAmount, List<AccountModel> accountModelList) {

        // Not sure if I fully understood page 2 point number 4 but...
        // From my understanding sounds like we can have more than one account with same accountNum or technical Id's which we need to treat as one account or the same savings account
        AccountModel savingAccount = combineToOneAccount(accountModelList);
        System.out.printf("Account Balance: %s %n", savingAccount.getBalance().intValue());
        System.out.printf("Attempting to withdraw %s %n", withdrawalAmount);

        //Check if the account is valid by virtue of available balance, If not throw WithdrawalAmountTooLargeException
        if (!savingAccount.isActive())
            throw new WithdrawalAmountTooLargeException("Cannot withdraw from savings account with less than R1000.00");

        //Process withdrawal
        processWithdrawal(withdrawalAmount, savingAccount);
        System.out.println("Withdrawal Successful.");

    }

    @Override
    public BigDecimal calculateInterest() {
        throw new NotImplementedException();
    }

    private void processWithdrawal(BigDecimal withdrawalAmount, AccountModel savingAccount) {
        //1.Run validations
        BigDecimal withdrawalLimit = calculateWithdrawalLimit(savingAccount.getBalance());
        // If the withdrawalAmount is greater than the limit then throw an exception
        if (withdrawalAmount.compareTo(withdrawalLimit) > 0)
            throw new WithdrawalAmountTooLargeException(String.format("Limit exceeded , available amount is %s", withdrawalLimit.intValue()));

        //2.Process withdrawal
        BigDecimal availableBalance = savingAccount.getBalance().subtract(withdrawalAmount);
        System.out.printf("Remaining balance: %s.%n", availableBalance.intValue());
        //TODO: I would also ensure we write the availableBalance back to the SystemDB for go live preparations
    }

    private BigDecimal calculateWithdrawalLimit(BigDecimal available) {
        return available.subtract(new BigDecimal(1000));
    }

    //Retrieve account of Savings type and Active state, by virtue of available balance being greater than 1000 as per spec.
    private boolean isSavingsAccount(String accountNumber, AccountModel accountModel) {
        if (accountModel.getAccType().equalsIgnoreCase("SavingsAccount")) {
            return true;
        } else {
            return false;
        }
    }

    // based on page 2 point number 4, having multiple accounts means that the are accounts are to be treated as equal.
    // I am adding all the balances from those separate accounts(with similar AccNum, customerNumber or Tid) under 1 accountModel
    private AccountModel combineToOneAccount(List<AccountModel> savingAccountAccountList) {

        //Initialize object to hold calculated balance.
        AccountModel accountModel = new AccountModel();

        //Iterate through accounts and add their available balance to 1 account(main savings account)
        for (AccountModel acc : savingAccountAccountList) {
            BigDecimal newBalance = accountModel.getBalance().add(acc.getBalance());
            accountModel.setBalance(newBalance);
        }

        return accountModel;
    }

}
