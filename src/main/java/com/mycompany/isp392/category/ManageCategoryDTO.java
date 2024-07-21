package com.mycompany.isp392.category;

import java.sql.Timestamp;
import java.util.List;

public class ManageCategoryDTO {
    public int categories;
    public int CDCategoryID;
    public int empID;
    public List<String> oldField;
    public List<String> newField; 
    public String loadNewField; 
    public String loadOldField; 
    public String action;
    public Timestamp changeDate;
    
    public ManageCategoryDTO() {
    }

    public ManageCategoryDTO(int categories, int empID, List<String> oldField, List<String> newField, String loadNewField, String loadOldField, String action, Timestamp changeDate) {
        this.categories = categories;
        this.empID = empID;
        this.oldField = oldField;
        this.newField = newField;
        this.loadNewField = loadNewField;
        this.loadOldField = loadOldField;
        this.action = action;
        this.changeDate = changeDate;
    }

    public ManageCategoryDTO(int categories, int empID, List<String> oldField, List<String> newField, String action, Timestamp changeDate) {
        this.categories = categories;
        this.empID = empID;
        this.oldField = oldField;
        this.newField = newField;
        this.action = action;
        this.changeDate = changeDate;
    }

    public ManageCategoryDTO(int categories, int empID, List<String> oldField, List<String> newField, String action) {
        this.categories = categories;
        this.empID = empID;
        this.oldField = oldField;
        this.newField = newField;
        this.action = action;
    }
    
    public ManageCategoryDTO(int CDCategoryID, int empID, List<String> oldField, List<String> newField, String action, boolean isCD) {
        this.CDCategoryID = CDCategoryID;
        this.empID = empID;
        this.oldField = oldField;
        this.newField = newField;
        this.action = action;
    }

    public ManageCategoryDTO(int categories, int empID, String loadOldField, String loadNewField,  String action, Timestamp changeDate) {
        this.categories = categories;
        this.empID = empID;
        this.loadNewField = loadNewField;
        this.loadOldField = loadOldField;
        this.action = action;
        this.changeDate = changeDate;
    }
    
    
    
    public int getCategories() {
        return categories;
    }

    public void setCategories(int categories) {
        this.categories = categories;
    }

    public int getCDCategoryID() {
        return CDCategoryID;
    }

    public void setCDCategoryID(int CDCategoryID) {
        this.CDCategoryID = CDCategoryID;
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
