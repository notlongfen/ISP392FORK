package com.mycompany.isp392.promotion;


import java.math.BigDecimal;
import java.sql.Date;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author TTNHAT
 */
public class PromotionDTO {
    private int promotionID;
    private String promotionName;
    private Date startDate;
    private Date endDate;
    private int discountPer;
    private String condition;

    public PromotionDTO() {
    }

    public PromotionDTO(int promotionID, String promotionName, Date startDate, Date endDate, int discountPer, String condition) {
        this.promotionID = promotionID;
        this.promotionName = promotionName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.discountPer = discountPer;
        this.condition = condition;
    }

    public int getPromotionID() {
        return promotionID;
    }

    public String getPromotionName() {
        return promotionName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public int getDiscountPer() {
        return discountPer;
    }

    public String getCondition() {
        return condition;
    }

    public void setPromotionID(int promotionID) {
        this.promotionID = promotionID;
    }

    public void setPromotionName(String promotionName) {
        this.promotionName = promotionName;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setDiscountPer(int discountPer) {
        this.discountPer = discountPer;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    @Override
    public String toString() {
        return "PromotionDTO{" + "promotionID=" + promotionID + ", promotionName=" + promotionName + ", startDate=" + startDate + ", endDate=" + endDate + ", discountPer=" + discountPer + ", condition=" + condition + '}';
    }
 }
