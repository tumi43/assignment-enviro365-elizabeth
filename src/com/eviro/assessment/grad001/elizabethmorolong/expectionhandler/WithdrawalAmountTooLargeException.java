package com.eviro.assessment.grad001.elizabethmorolong.expectionhandler;

public class WithdrawalAmountTooLargeException extends RuntimeException{

    private String errorMessage;

    public WithdrawalAmountTooLargeException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
