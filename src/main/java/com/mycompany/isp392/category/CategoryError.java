/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.isp392.category;

/**
 *
 * @author TTNHAT
 */
public class CategoryError {

    private String categoryIDError;
    private String categoryNameError;
    private String descriptionError;
    private String categoryStatusError;
    private String cdCategoryIDError;
    private String cdCategoryNameError;
    private String parentIDError;
    private String cdCategoryStatusError;
    private String error;

    public CategoryError() {
        this.categoryIDError = "";
        this.categoryNameError = "";
        this.descriptionError = "";
        this.categoryStatusError = "";
        this.cdCategoryIDError = "";
        this.cdCategoryNameError = "";
        this.parentIDError = "";
        this.cdCategoryStatusError = "";
        this.error = error;

    }

    public CategoryError(String categoryIDError, String categoryNameError, String descriptionError, String categoryStatusError, String cdCategoryIDError, String cdCategoryNameError, String parentIDError, String cdCategoryStatusError, String error) {
        this.categoryIDError = categoryIDError;
        this.categoryNameError = categoryNameError;
        this.descriptionError = descriptionError;
        this.categoryStatusError = categoryStatusError;
        this.cdCategoryIDError = cdCategoryIDError;
        this.cdCategoryNameError = cdCategoryNameError;
        this.parentIDError = parentIDError;
        this.cdCategoryStatusError = cdCategoryStatusError;
        this.error = error;
    }

    public String getCategoryIDError() {
        return categoryIDError;
    }

    public String getCategoryNameError() {
        return categoryNameError;
    }

    public String getDescriptionError() {
        return descriptionError;
    }

    public String getCategoryStatusError() {
        return categoryStatusError;
    }

    public String getCdCategoryIDError() {
        return cdCategoryIDError;
    }

    public String getCdCategoryNameError() {
        return cdCategoryNameError;
    }

    public String getParentIDError() {
        return parentIDError;
    }

    public String getCdCategoryStatusError() {
        return cdCategoryStatusError;
    }

    public String getError() {
        return error;
    }

    public void setCategoryIDError(String categoryIDError) {
        this.categoryIDError = categoryIDError;
    }

    public void setCategoryNameError(String categoryNameError) {
        this.categoryNameError = categoryNameError;
    }

    public void setDescriptionError(String descriptionError) {
        this.descriptionError = descriptionError;
    }

    public void setCategoryStatusError(String categoryStatusError) {
        this.categoryStatusError = categoryStatusError;
    }

    public void setCdCategoryIDError(String cdCategoryIDError) {
        this.cdCategoryIDError = cdCategoryIDError;
    }

    public void setCdCategoryNameError(String cdCategoryNameError) {
        this.cdCategoryNameError = cdCategoryNameError;
    }

    public void setParentIDError(String parentIDError) {
        this.parentIDError = parentIDError;
    }

    public void setCdCategoryStatusError(String cdCategoryStatusError) {
        this.cdCategoryStatusError = cdCategoryStatusError;
    }

    public void setError(String error) {
        this.error = error;
    }
}
