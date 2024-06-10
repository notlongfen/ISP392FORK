package com.mycompany.isp392.category;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author TTNHAT
 */
public class ChildrenCategoryDTO {
    private int cdCategoryID;
    private String categoryName;
    private int parentID;
    private int status;

    public ChildrenCategoryDTO() {
    }

    public ChildrenCategoryDTO(int cdCategoryID, String categoryName, int parentID, int status) {
        this.cdCategoryID = cdCategoryID;
        this.categoryName = categoryName;
        this.parentID = parentID;
        this.status = status;
    }

    public int getCdCategoryID() {
        return cdCategoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public int getParentID() {
        return parentID;
    }

    public int getStatus() {
        return status;
    }

    public void setCdCategoryID(int cdCategoryID) {
        this.cdCategoryID = cdCategoryID;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setParentID(int parentID) {
        this.parentID = parentID;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ChildrenCategoryDTO{" + "cdCategoryID=" + cdCategoryID + ", categoryName=" + categoryName + ", parentID=" + parentID + ", status=" + status + '}';
    }
}
