/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.isp392.promotion;

/**
 *
 * @author TTNHAT
 */
public class PromotionError {

    private String promotionIDError;
    private String promotionNameError;
    private String startDateError;
    private String endDateError;
    private String discountPerError;
    private String conditionError;
    private String statusError;
    private String error;

    public PromotionError() {
        this.promotionIDError = "";
        this.promotionNameError = "";
        this.startDateError = "";
        this.endDateError = "";
        this.discountPerError = "";
        this.conditionError = "";
        this.statusError = "";
        this.error = "";
    }

    public PromotionError(String promotionIDError, String promotionNameError, String startDateError, String endDateError, String discountPerError, String conditionError, String statusError, String error) {
        this.promotionIDError = promotionIDError;
        this.promotionNameError = promotionNameError;
        this.startDateError = startDateError;
        this.endDateError = endDateError;
        this.discountPerError = discountPerError;
        this.conditionError = conditionError;
        this.statusError = statusError;
        this.error = error;
    }

    public String getPromotionIDError() {
        return promotionIDError;
    }

    public String getPromotionNameError() {
        return promotionNameError;
    }

    public String getStartDateError() {
        return startDateError;
    }

    public String getEndDateError() {
        return endDateError;
    }

    public String getDiscountPerError() {
        return discountPerError;
    }

    public String getConditionError() {
        return conditionError;
    }

    public String getError() {
        return error;
    }

    public String getStatusError() {
        return statusError;
    }
    
    public void setPromotionIDError(String promotionIDError) {
        this.promotionIDError = promotionIDError;
    }

    public void setPromotionNameError(String promotionNameError) {
        this.promotionNameError = promotionNameError;
    }

    public void setStartDateError(String startDateError) {
        this.startDateError = startDateError;
    }

    public void setEndDateError(String endDateError) {
        this.endDateError = endDateError;
    }

    public void setDiscountPerError(String discountPerError) {
        this.discountPerError = discountPerError;
    }

    public void setConditionError(String conditionError) {
        this.conditionError = conditionError;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setStatusError(String statusError) {
        this.statusError = statusError;
    }
}
