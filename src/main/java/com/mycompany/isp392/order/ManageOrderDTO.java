/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.isp392.order;

import java.security.Timestamp;
import java.util.List;

/**
 *
 * @author notlongfen
 */
public class ManageOrderDTO {
    private int orderID;
    private int empID;
    private List<String> oldField;
    private List<String> newField;
    private String loadOldField;
    private String loadNewField;
    private String action;
    private Timestamp changeDate;

    public ManageOrderDTO() {
    }

    public ManageOrderDTO(int orderID, int empID, List<String> oldField, List<String> newField, String loadOldField, String loadNewField, String action, Timestamp changeDate) {
        this.orderID = orderID;
        this.empID = empID;
        this.oldField = oldField;
        this.newField = newField;
        this.loadOldField = loadOldField;
        this.loadNewField = loadNewField;
        this.action = action;
        this.changeDate = changeDate;
    }

    public ManageOrderDTO(int orderID, int empID, List<String> oldField, List<String> newField, String action, Timestamp changeDate) {
        this.orderID = orderID;
        this.empID = empID;
        this.oldField = oldField;
        this.newField = newField;
        this.action = action;
        this.changeDate = changeDate;
    }

    public ManageOrderDTO(int orderID, int empID, String newField, String action){
        this.orderID = orderID;
        this.empID = empID;
        this.loadNewField = newField;
        this.action = action;
    }

    public ManageOrderDTO(int orderID, int empID, List<String> oldField, List<String> newField, String action) {
        this.orderID = orderID;
        this.empID = empID;
        this.oldField = oldField;
        this.newField = newField;
        this.action = action;
    }
    
    public int getEmpID() {
        return empID;
    }

    public void setEmpID(int empID) {
        this.empID = empID;
    }
  

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
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

    public String getLoadOldField() {
        return loadOldField;
    }

    public void setLoadOldField(String loadOldField) {
        this.loadOldField = loadOldField;
    }

    public String getLoadNewField() {
        return loadNewField;
    }

    public void setLoadNewField(String loadNewField) {
        this.loadNewField = loadNewField;
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
