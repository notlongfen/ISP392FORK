/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.isp392.support;

import java.sql.Date;

public class ProcessSupportDTO {
    private int empID;
    private int supportID;
    private String responseMessage;
    private String title;
    private Date responseDate;

    public ProcessSupportDTO() {
    }

    public ProcessSupportDTO(int empID, int supportID, String responseMessage, Date responseDate) {
        this.empID = empID;
        this.supportID = supportID;
        this.responseMessage = responseMessage;
        this.responseDate = responseDate;
    }
    
    public ProcessSupportDTO(int empID, int supportID, String responseMessage,String title, Date responseDate) {
        this.empID = empID;
        this.supportID = supportID;
        this.responseMessage = responseMessage;
        this.title = title;
        this.responseDate = responseDate;
    }

    public int getEmpID() {
        return empID;
    }

    public void setEmpID(int empID) {
        this.empID = empID;
    }

    public int getSupportID() {
        return supportID;
    }

    public void setSupportID(int supportID) {
        this.supportID = supportID;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getResponseDate() {
        return responseDate;
    }

    public void setResponseDate(Date responseDate) {
        this.responseDate = responseDate;
    }
    
    
}
