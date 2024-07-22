package com.mycompany.isp392.order;

import java.sql.Date;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author TTNHAT
 */
public class OrderDTO {

    private int orderID;
    private int status;
    private double total;
    private Date orderDate;
    private int custID;
    private int promotionID;
    private int cartID;
    private String userName;
    private String city;
    private String district;
    private String ward;
    private String address;
    private int phone;
    private String note;

    public OrderDTO() {
    }

    public OrderDTO(int orderID, int status, double total, Date orderDate, int promotionID, int cartID, String userName, String city, String district, String ward, String address, int phone, String note) {
        this.orderID = orderID;
        this.status = status;
        this.total = total;
        this.orderDate = orderDate;
        this.promotionID = promotionID;
        this.cartID = cartID;
        this.userName = userName;
        this.city = city;
        this.district = district;
        this.ward = ward;
        this.address = address;
        this.phone = phone;
        this.note = note;
    }

    public OrderDTO(int orderID, int status, double total, Date orderDate, int custID, int promotionID, int cartID, String userName, String city, String district, String ward, String address, int phone, String note) {
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
    }

    public OrderDTO(int orderID, int status, double total, Date orderDate, int custID) {
        this.orderID = orderID;
        this.status = status;
        this.total = total;
        this.orderDate = orderDate;
        this.custID = custID;
    }

    OrderDTO(int orderID, double total, Date orderDate, String userName, String city, String district, String ward, String address, int phone, String note) {
        this.orderID = orderID;
        this.total = total;
        this.orderDate = orderDate;
        this.userName = userName;
        this.city = city;
        this.district = district;
        this.ward = ward;
        this.address = address;
        this.phone = phone;
        this.note = note;
    }

    OrderDTO(int orderID, int status, double total, Date orderDate, String userName, String city, String district, String ward, String address, int phone, String note) {
        this.orderID = orderID;
        this.status = status;
        this.total = total;
        this.orderDate = orderDate;
        this.userName = userName;
        this.city = city;
        this.district = district;
        this.ward = ward;
        this.address = address;
        this.phone = phone;
        this.note = note;
    }

    public int getOrderID() {
        return orderID;
    }

    public int getStatus() {
        return status;
    }

    public double getTotal() {
        return total;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public int getCustID() {
        return custID;
    }

    public int getPromotionID() {
        return promotionID;
    }

    public int getCartID() {
        return cartID;
    }

    public String getUserName() {
        return userName;
    }

    public String getCity() {
        return city;
    }

    public String getDistrict() {
        return district;
    }

    public String getWard() {
        return ward;
    }

    public String getAddress() {
        return address;
    }

    public int getPhone() {
        return phone;
    }

    public String getNote() {
        return note;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public void setCustID(int custID) {
        this.custID = custID;
    }

    public void setPromotionID(int promotionID) {
        this.promotionID = promotionID;
    }

    public void setCartID(int cartID) {
        this.cartID = cartID;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getStatusDescription() {
        switch (this.status) {
            case 0:
                return "Cancelled";
            case 1:
                return "Pending";
            case 2:
                return "In Processing";
            case 3:
                return "Delivering";
            default:
                return "Completed";
        }
    }

    @Override
    public String toString() {
        return "OrderDTO{" + "orderID=" + orderID + ", status=" + status + ", total=" + total + ", orderDate=" + orderDate + ", custID=" + custID + ", promotionID=" + promotionID + ", cartID=" + cartID + '}';
    }
    
   
}
