/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.isp392.product;

import java.security.Timestamp;
import java.util.List;

/**
 *
 * @author notlongfen
 */
public class ManageProductDTO {
    private int productID;
    private int empID;
    private List<String> oldField;
    private List<String> newField;
    private String loadNewField;
    private String loadOldField;
    private String action;
    private Timestamp changeDate;

    public ManageProductDTO() {
    }

    public ManageProductDTO(int productID, int empID, List<String> oldField, List<String> newField, String loadNewField, String loadOldField, String action, Timestamp changeDate) {
        this.productID = productID;
        this.empID = empID;
        this.oldField = oldField;
        this.newField = newField;
        this.loadNewField = loadNewField;
        this.loadOldField = loadOldField;
        this.action = action;
        this.changeDate = changeDate;
    }

    public ManageProductDTO(int productID, int empID, List<String> oldField, List<String> newField, String action, Timestamp changeDate) {
        this.productID = productID;
        this.empID = empID;
        this.oldField = oldField;
        this.newField = newField;
        this.action = action;
        this.changeDate = changeDate;
    }

    public ManageProductDTO(int productID, int empID, List<String> oldField, List<String> newField, String action) {
        this.productID = productID;
        this.empID = empID;
        this.oldField = oldField;
        this.newField = newField;
        this.action = action;
    }

    public ManageProductDTO(int productID, int empID, String loadOldField, String loadNewField, String action, Timestamp changeDate) {
        this.productID = productID;
        this.empID = empID;
        this.loadNewField = loadNewField;
        this.loadOldField = loadOldField;
        this.action = action;
        this.changeDate = changeDate;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
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
