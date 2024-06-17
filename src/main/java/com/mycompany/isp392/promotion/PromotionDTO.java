package com.mycompany.isp392.promotion;


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
    private int condition;
    private int status;

    public PromotionDTO() {
    }

    public PromotionDTO(int promotionID, String promotionName, Date startDate, Date endDate, int discountPer, int condition, int status) {
        this.promotionID = promotionID;
        this.promotionName = promotionName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.discountPer = discountPer;
        this.condition = condition;
        this.status=status;
    }

    PromotionDTO(int promotionID, String promotionName, Date startDate, Date endDate, int discountPer, int condition) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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

    public int getCondition() {
        return condition;
    }

    public int getStatus() {
        return status;
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

    public void setCondition(int condition) {
        this.condition = condition;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "PromotionDTO{" + "promotionID=" + promotionID + ", promotionName=" + promotionName + ", startDate=" + startDate + ", endDate=" + endDate + ", discountPer=" + discountPer + ", condition=" + condition + ", status=" + status + '}';
    }
 }