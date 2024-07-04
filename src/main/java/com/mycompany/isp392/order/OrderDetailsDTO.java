package com.mycompany.isp392.order;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author TTNHAT
 */
public class OrderDetailsDTO {

    private int productDetailsID;
    private int orderID;
    private int productID;
    private int quantity;
    private int unitPrice;

    public OrderDetailsDTO() {
    }

    public OrderDetailsDTO(int productDetailsID, int orderID, int productID, int quantity, int unitPrice) {
        this.productDetailsID = productDetailsID;
        this.orderID = orderID;
        this.productID = productID;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }
    
    public OrderDetailsDTO(int productDetailsID, int orderID, int quantity, int unitPrice) {
        this.productDetailsID = productDetailsID;
        this.orderID = orderID;
        this.productID = productID;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public int getProductDetailsID() {
        return productDetailsID;
    }

    public void setProductDetailsID(int productDetailsID) {
        this.productDetailsID = productDetailsID;
    }

    public int getOrderID() {
        return orderID;
    }

    public int getProductID() {
        return productID;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "OrderDetailsDTO{" + "orderID=" + orderID + ", productID=" + productID + ", quantity=" + quantity + ", unitPrice=" + unitPrice + '}';
    }

}
