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
    private String description;
    private int status;
    private String image;

    public PromotionDTO() {
    }

    public PromotionDTO(int promotionID, String promotionName, Date startDate, Date endDate, int discountPer, int condition, String description, int status) {
        this.promotionID = promotionID;
        this.promotionName = promotionName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.discountPer = discountPer;
        this.condition = condition;
        this.description = description;
        this.status = status;
    }

    public PromotionDTO(int promotionID, String promotionName, Date startDate, Date endDate, int discountPer, int condition, String description, int status, String image) {
        this.promotionID = promotionID;
        this.promotionName = promotionName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.discountPer = discountPer;
        this.condition = condition;
        this.description = description;
        this.status = status;
        this.image = image;
    }
    

    public PromotionDTO(int promotionID, String promotionName, Date startDate, Date endDate, int discountPer, String description, int condition) {
        this.promotionID = promotionID;
        this.promotionName = promotionName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.discountPer = discountPer;
        this.description = description;
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

    public int getCondition() {
        return condition;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    @Override
    public String toString() {
        return "PromotionDTO{" + "promotionID=" + promotionID + ", promotionName=" + promotionName + ", startDate=" + startDate + ", endDate=" + endDate + ", discountPer=" + discountPer + ", condition=" + condition + ", status=" + status + '}';
    }
}
