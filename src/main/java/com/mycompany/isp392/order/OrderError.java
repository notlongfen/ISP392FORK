/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.isp392.order;

import java.sql.Date;

/**
 *
 * @author Oscar
 */
public class OrderError {

    private String orderID;
    private String status;
    private String total;
    private String orderDate;
    private String custID;
    private String promotionID;
    private String cartID;
    private String userName;
    private String city;
    private String district;
    private String ward;
    private String address;
    private String phone;
    private String note;
    private String productDetailsID;
    private String productID;
    private String quantity;
    private String unitPrice;
    private String error;

    public OrderError() {
    }

    public OrderError(String orderID, String status, String total, String orderDate, String custID, String promotionID, String cartID, String userName, String city, String district, String ward, String address, String phone, String note, String productDetailsID, String productID, String quantity, String unitPrice, String error) {
        this.orderID = orderID;
        this.status = status;
        this.total = total;
        this.orderDate = orderDate;
        this.custID = custID;
        this.promotionID = promotionID;
        this.cartID = cartID;
        this.userName = userName;
        this.city = city;
        this.district = district;
        this.ward = ward;
        this.address = address;
        this.phone = phone;
        this.note = note;
        this.productDetailsID = productDetailsID;
        this.productID = productID;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.error = error;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getCustID() {
        return custID;
    }

    public void setCustID(String custID) {
        this.custID = custID;
    }

    public String getPromotionID() {
        return promotionID;
    }

    public void setPromotionID(String promotionID) {
        this.promotionID = promotionID;
    }

    public String getCartID() {
        return cartID;
    }

    public void setCartID(String cartID) {
        this.cartID = cartID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getProductDetailsID() {
        return productDetailsID;
    }

    public void setProductDetailsID(String productDetailsID) {
        this.productDetailsID = productDetailsID;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
    
}
