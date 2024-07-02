/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.isp392.support;

import java.sql.Date;

public class SupportDTO {
    private int supportID;
    private int status;
    private String title;
    private Date requestDate;
    private String requestMessage;
    private int custID;

    public SupportDTO() {
    }

    public SupportDTO(int supportID, int status, Date requestDate, String requestMessage, int custID) {
        this.supportID = supportID;
        this.status = status;
        this.requestDate = requestDate;
        this.requestMessage = requestMessage;
        this.custID = custID;
    }
    
    public SupportDTO(int supportID, int status, String title, Date requestDate, String requestMessage, int custID) {
        this.supportID = supportID;
        this.status = status;
        this.title = title;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
