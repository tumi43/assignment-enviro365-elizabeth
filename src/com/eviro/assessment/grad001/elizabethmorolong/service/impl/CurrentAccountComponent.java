package com.eviro.assessment.grad001.elizabethmorolong.service.impl;

import com.eviro.assessment.grad001.elizabethmorolong.expectionhandler.WithdrawalAmountTooLargeException;
import com.eviro.assessment.grad001.elizabethmorolong.model.AccountModel;
import com.eviro.assessment.grad001.elizabethmorolong.service.AccountFunctionalityService;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.math.BigDecimal;
import java.util.List;

public class CurrentAccountComponent implements AccountFunctionalityService {


    @Override
    public void deposit(String accountNumber, BigDecimal bigDecimal) {
        throw new NotImplementedException();
    }

    @Override
    public void withdraw(String accountNumber, BigDecimal withdrawal, List<AccountModel> accountList) {

        AccountModel accountModel = combineToOneAccount(accountList);

        System.out.printf("Available Account Balance: %s %n", accountModel.getBalance().intValue());
        System.out.printf("Available OverDraft Balance: %s %n", accountModel.getOverDraft().intValue());
        System.out.printf("Attempting to withdraw %s %n", withdrawal);

        processWithdrawal(withdrawal, accountModel);
    }

    @Override
    public BigDecimal calculateInterest() {
        throw new NotImplementedException();
    }


    private void processWithdrawal(BigDecimal withdrawalAmount, AccountModel currentAccount) {
        //1.Run validations
        BigDecimal withdrawalLimit = calculateWithdrawalLimit(currentAccount);

        // If the withdrawalAmount is greater than the limit then throw an exception
        if (withdrawalAmount.compareTo(withdrawalLimit) == 1)
            throw new WithdrawalAmountTooLargeException("Limit exceeded , available amount is %s");

        //2.Process withdrawal
        BigDecimal newBalance = currentAccount.getBalance().subtract(withdrawalAmount);
        currentAccount.setBalance(newBalance);

        System.out.printf("Remaining balance(Current Balance + Overdraft): %s.%n", withdrawalLimit.subtract(withdrawalAmount));

        //TODO: I would also ensure we write the availableBalance back to the SystemDB for go live preparations
    }

    private BigDecimal calculateWithdrawalLimit(AccountModel accountModel) {
        //Over draft amounts are stored as negative values hence we.
        return accountModel.getBalance().add(accountModel.getOverDraft());
    }

    private AccountModel combineToOneAccount(List<AccountModel> savingAccountAccountList) {

        //Initialize object to hold calculated balance.
        AccountModel accountModel = new AccountModel();

        //Iterate through accounts and add their available balance to 1 account(main savings account)
        for (AccountModel acc : savingAccountAccountList) {
            BigDecimal newBalance = accountModel.getBalance().add(acc.getBalance());
            BigDecimal newOverDraft = accountModel.getOverDraft().add(acc.getOverDraft());
            accountModel.setBalance(newBalance);
            accountModel.setOverDraft(newOverDraft);
        }

        return accountModel;
    }


}
