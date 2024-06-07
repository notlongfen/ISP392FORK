/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.isp392.user;

/**
 * UserError class represents error messages for user validation.
 */
public class UserError {
    private String userIDError;
    private String userNameError;
    private String emailError;
    private String phoneError;
    private String birthdayError;
    private String passwordError;
    private String confirmError;
    private String error;

    public UserError(String userIDError, String userNameError, String emailError, String phoneError, String birthdayError, String passwordError, String confirmError, String error) {
        this.userIDError = userIDError;
        this.userNameError = userNameError;
        this.emailError = emailError;
        this.phoneError = phoneError;
        this.birthdayError = birthdayError;
        this.passwordError = passwordError;
        this.confirmError = confirmError;
        this.error = error;
    }

    public UserError() {
        this.userIDError = "";
        this.userNameError = "";
        this.emailError = "";
        this.phoneError = "";
        this.birthdayError = "";
        this.passwordError = "";
        this.confirmError = "";
        this.error = "";
    }

    // Getters and Setters for all the error fields
    public String getUserIDError() {
        return userIDError;
    }

    public void setUserIDError(String userIDError) {
        this.userIDError = userIDError;
    }

    public String getUserNameError() {
        return userNameError;
    }

    public void setUserNameError(String userNameError) {
        this.userNameError = userNameError;
    }

    public String getEmailError() {
        return emailError;
    }

    public void setEmailError(String emailError) {
        this.emailError = emailError;
    }

    public String getPhoneError() {
        return phoneError;
    }

    public void setPhoneError(String phoneError) {
        this.phoneError = phoneError;
    }

    public String getBirthdayError() {
        return birthdayError;
    }

    public void setBirthdayError(String birthdayError) {
        this.birthdayError = birthdayError;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public String getConfirmError() {
        return confirmError;
    }

    public void setConfirmError(String confirmError) {
        this.confirmError = confirmError;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
