package com.eviro.assessment.grad001.elizabethmorolong.expectionhandler;

public class AccountNotFoundException extends  RuntimeException{

    private String errorMessage;

    public AccountNotFoundException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
