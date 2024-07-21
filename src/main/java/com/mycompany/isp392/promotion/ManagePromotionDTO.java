/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.isp392.promotion;

import java.security.Timestamp;
import java.util.List;

/**
 *
 * @author PC
 */
public class ManagePromotionDTO {
    private int promotionID;
    private int empID;
    private List<String> oldField;
    private List<String> newField;
    private String loadNewField;
    private String loadOldField;
    private String action;
    private Timestamp changeDate;

    public ManagePromotionDTO(int promotionID, int empID, List<String> oldField, List<String> newField, String action) {
        this.promotionID = promotionID;
        this.empID = empID;
        this.oldField = oldField;
        this.newField = newField;
        this.action = action;
    }

    public int getPromotionID() {
        return promotionID;
    }

    public void setPromotionID(int promotionID) {
        this.promotionID = promotionID;
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
    
    
}
