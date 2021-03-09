package com.eviro.assessment.grad001.elizabethmorolong.repo;

import com.eviro.assessment.grad001.elizabethmorolong.model.AccountModel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SystemDB {

    private static List<AccountModel> accountModelList = new ArrayList<>();

    public static List<AccountModel> getAccountByAccountNum(String accNum) {
        return accountModelList
                .stream()
                .filter(accountModel -> accountModel.getAccountNumber().equalsIgnoreCase(accNum))
                .collect(Collectors.toList());
    }

    public static void setAccountList(List<AccountModel> accountModelList) {
        SystemDB.accountModelList = accountModelList;
    }
}
