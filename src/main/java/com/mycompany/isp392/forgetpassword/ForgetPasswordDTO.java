/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.isp392.forgetpassword;

import java.sql.Date;

/**
 *
 * @author notlongfen
 */
public class ForgetPasswordDTO {
    private int resetID;
    private int userID;
    private String token;
    private Date expiredDate;
    private int status;

    public ForgetPasswordDTO() {
    }

    public ForgetPasswordDTO(int resetID, int userID, String token, Date expiredDate, int status) {
        this.resetID = resetID;
        this.userID = userID;
        this.token = token;
        this.expiredDate = expiredDate;
        this.status = status;
    }

    public int getResetID() {
        return resetID;
    }

    public void setResetID(int resetID) {
        this.resetID = resetID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Date expiredDate) {
        this.expiredDate = expiredDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
}
