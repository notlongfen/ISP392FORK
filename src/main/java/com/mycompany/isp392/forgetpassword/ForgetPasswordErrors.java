/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.isp392.forgetpassword;

/**
 *
 * @author notlongfen
 */
public class ForgetPasswordErrors {
    private String userIDError, tokenError, expiredDatesError, statusError, error;

    public ForgetPasswordErrors() {
        userIDError = "";
        tokenError = "";
        expiredDatesError = "";
        statusError = "";
        error = "";
    }

    public String getUserIDError() {
        return userIDError;
    }

    public void setUserIDError(String userIDError) {
        this.userIDError = userIDError;
    }

    public String getTokenError() {
        return tokenError;
    }

    public void setTokenError(String tokenError) {
        this.tokenError = tokenError;
    }

    public String getExpiredDatesError() {
        return expiredDatesError;
    }

    public void setExpiredDatesError(String expiredDatesError) {
        this.expiredDatesError = expiredDatesError;
    }

    public String getStatusError() {
        return statusError;
    }

    public void setStatusError(String statusError) {
        this.statusError = statusError;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
