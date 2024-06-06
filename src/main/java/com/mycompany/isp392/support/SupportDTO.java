/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.isp392.support;

import java.util.Date;

public class SupportDTO {
    private int supportID;
    private boolean status;
    private Date requestDate;
    private String requestMessage;
    private int custID;

    public SupportDTO() {
    }

    public SupportDTO(int supportID, boolean status, Date requestDate, String requestMessage, int custID) {
        this.supportID = supportID;
        this.status = status;
        this.requestDate = requestDate;
        this.requestMessage = requestMessage;
        this.custID = custID;
    }

    public int getSupportID() {
        return supportID;
    }

    public void setSupportID(int supportID) {
        this.supportID = supportID;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public String getRequestMessage() {
        return requestMessage;
    }

    public void setRequestMessage(String requestMessage) {
        this.requestMessage = requestMessage;
    }

    public int getCustID() {
        return custID;
    }

    public void setCustID(int custID) {
        this.custID = custID;
    }

    
    
}
