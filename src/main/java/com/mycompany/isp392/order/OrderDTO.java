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
    private String status;
    private int total;
    private Date orderDate;
    private int custID;
    private int promotionID;
    private int cartID;
   
    public OrderDTO() {
    }

    public OrderDTO(int orderID, String status, int total, Date orderDate, int custID, int promotionID, int cartID) {
        this.orderID = orderID;
        this.status = status;
        this.total = total;
        this.orderDate = orderDate;
        this.custID = custID;
        this.promotionID = promotionID;
        this.cartID = cartID;
    }

    public int getOrderID() {
        return orderID;
    }

    public String getStatus() {
        return status;
    }

    public int getTotal() {
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

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTotal(int total) {
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

    @Override
    public String toString() {
        return "OrderDTO{" + "orderID=" + orderID + ", status=" + status + ", total=" + total + ", orderDate=" + orderDate + ", custID=" + custID + ", promotionID=" + promotionID + ", cartID=" + cartID + '}';
    }
}
