/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.isp392.support;

import java.sql.Date;

/**
 *
 * @author Oscar
 */
public class SupportError {
    private String supportIDError;
    private String statusError;
    private String requestDateError;
    private String requestMessageError;
    private String custIDError;
    private String empIDError;
    private String responseMessageError;
    private String responseDateError;
    private String error;

    public SupportError(String supportIDError, String statusError, String requestDateError, String requestMessageError, String custIDError, String empIDError, String responseMessageError, String responseDateError, String error) {
        this.supportIDError = supportIDError;
        this.statusError = statusError;
        this.requestDateError = requestDateError;
        this.requestMessageError = requestMessageError;
        this.custIDError = custIDError;
        this.empIDError = empIDError;
        this.responseMessageError = responseMessageError;
        this.responseDateError = responseDateError;
        this.error = error;
    }

    public SupportError() {
    }

    public String getSupportIDError() {
        return supportIDError;
    }

    public void setSupportIDError(String supportIDError) {
        this.supportIDError = supportIDError;
    }

    public String getStatusError() {
        return statusError;
    }

    public void setStatusError(String statusError) {
        this.statusError = statusError;
    }

    public String getRequestDateError() {
        return requestDateError;
    }

    public void setRequestDateError(String requestDateError) {
        this.requestDateError = requestDateError;
    }

    public String getRequestMessageError() {
        return requestMessageError;
    }

    public void setRequestMessageError(String requestMessageError) {
        this.requestMessageError = requestMessageError;
    }

    public String getCustIDError() {
        return custIDError;
    }

    public void setCustIDError(String custIDError) {
        this.custIDError = custIDError;
    }

    public String getEmpIDError() {
        return empIDError;
    }

    public void setEmpIDError(String empIDError) {
        this.empIDError = empIDError;
    }

    public String getResponseMessageError() {
        return responseMessageError;
    }

    public void setResponseMessageError(String responseMessageError) {
        this.responseMessageError = responseMessageError;
    }

    public String getResponseDateError() {
        return responseDateError;
    }

    public void setResponseDateError(String responseDateError) {
        this.responseDateError = responseDateError;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
    
    
}
