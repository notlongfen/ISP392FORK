/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.isp392.brand;

import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author PC
 */
public class ManageBrandDTO {
    public int brandID;
    public int empID;
    public String oldField;
    public String newField;
    public String action;
    public Timestamp changeDate;

    public ManageBrandDTO() {
    }

    public ManageBrandDTO(int brandID, int empID, String oldField, String newField, String action, Timestamp changeDate) {
        this.brandID = brandID;
        this.empID = empID;
        this.oldField = oldField;
        this.newField = newField;
        this.action = action;
        this.changeDate = changeDate;
    }


    
    public ManageBrandDTO(int brandID, int empID, String oldField, String newField, String action) {
        this.brandID = brandID;
        this.empID = empID;
        this.oldField = oldField;
        this.newField = newField;
        this.action = action;
    }

    public int getBrandID() {
        return brandID;
    }

    public void setBrandID(int brandID) {
        this.brandID = brandID;
    }

    public int getEmpID() {
        return empID;
    }

    public void setEmpID(int empID) {
        this.empID = empID;
    }

    public String getOldField() {
        return oldField;
    }

    public void setOldField(String oldField) {
        this.oldField = oldField;
    }

    public String getNewField() {
        return newField;
    }

    public void setNewField(String newField) {
        this.newField = newField;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Timestamp getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(Timestamp changeDate) {
        this.changeDate = changeDate;
    }

    @Override
    public String toString() {
        return "ManageBrandDTO{" + "brandID=" + brandID + ", empID=" + empID + ", oldField=" + oldField + ", newField=" + newField + ", action=" + action + ", changeDate=" + changeDate + '}';
    }


    
    
}
