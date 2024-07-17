/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.isp392.brand;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC
 */
public class ManageBrandDTO {
    public int brandID;
    public int empID;
    public List<String> oldField;
    public List<String> newField; 
    public String loadNewField; 
    public String loadOldField; 
    public String action;
    public Timestamp changeDate;

    public ManageBrandDTO() {
    }

    public ManageBrandDTO(int brandID, int empID, List<String> oldField, List<String> newField, String loadNewField, String loadOldField, String action, Timestamp changeDate) {
        this.brandID = brandID;
        this.empID = empID;
        this.oldField = oldField;
        this.newField = newField;
        this.loadNewField = loadNewField;
        this.loadOldField = loadOldField;
        this.action = action;
        this.changeDate = changeDate;
    }

    public ManageBrandDTO(int brandID, int empID, List<String> oldField, List<String> newField, String action, Timestamp changeDate) {
        this.brandID = brandID;
        this.empID = empID;
        this.oldField = oldField;
        this.newField = newField;
        this.action = action;
        this.changeDate = changeDate;
    }

    public ManageBrandDTO(int brandID, int empID, List<String> oldField, List<String> newField, String action) {
        this.brandID = brandID;
        this.empID = empID;
        this.oldField = oldField;
        this.newField = newField;
        this.action = action;
    }

    public ManageBrandDTO(int brandID, int empID, String loadOldField, String loadNewField,  String action, Timestamp changeDate) {
        this.brandID = brandID;
        this.empID = empID;
        this.loadNewField = loadNewField;
        this.loadOldField = loadOldField;
        this.action = action;
        this.changeDate = changeDate;
    }

    public ManageBrandDTO(int brandID, int empID, String newField, String action){
        this.brandID = brandID;
        this.empID = empID;
        this.loadNewField = newField;
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

    public List<String> getOldField() {
        return oldField;
    }

    public void setOldField(List<String> oldField) {
        this.oldField = oldField;
    }

    public List<String> getNewField() {
        return newField;
    }

    public void setNewField(List<String> newField) {
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

    public String getLoadNewField() {
        return loadNewField;
    }

    public void setLoadNewField(String loadNewField) {
        this.loadNewField = loadNewField;
    }

    public String getLoadOldField() {
        return loadOldField;
    }

    public void setLoadOldField(String loadOldField) {
        this.loadOldField = loadOldField;
    }

    
    
    @Override
    public String toString() {
        return "ManageBrandDTO{" + "brandID=" + brandID + ", empID=" + empID + ", oldField=" + oldField + ", newField=" + newField + ", action=" + action + ", changeDate=" + changeDate + '}';
    }


    
    
}
