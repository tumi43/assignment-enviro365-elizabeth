package com.eviro.assessment.grad001.elizabethmorolong.service;

import com.eviro.assessment.grad001.elizabethmorolong.model.AccountModel;

import java.math.BigDecimal;
import java.util.List;

public interface AccountFunctionalityService {

    void deposit(String accountNumber, BigDecimal bigDecimal);

    void withdraw(String accountNumber, BigDecimal bigDecimal, List<AccountModel> accountList);

    BigDecimal calculateInterest();

}
