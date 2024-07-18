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
public class OrderDetailsDTO extends OrderDTO {

    private int productDetailsID;
    private int orderID;
    private int productID;
    private int quantity;
    private int unitPrice;
    
    private String productName;
    private String size;
    private String image;
    private String category;
    

    public OrderDetailsDTO() {
    }
    
    

    public OrderDetailsDTO(int productDetailsID, int orderID, int productID, int quantity, int unitPrice) {
        this.productDetailsID = productDetailsID;
        this.orderID = orderID;
        this.productID = productID;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public OrderDetailsDTO(int productDetailsID, int orderID, int productID, int quantity, int unitPrice, int status, double total, Date orderDate, int custID, int promotionID, int cartID, String userName, String city, String district, String ward, String address, int phone, String note) {
        super(orderID, status, total, orderDate, custID, promotionID, cartID, userName, city, district, ward, address, phone, note);
        this.productDetailsID = productDetailsID;
        this.productID = productID;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }
    public OrderDetailsDTO(int productDetailsID, int orderID, int productID, int quantity, int unitPrice, int status, double total, Date orderDate, int promotionID, int cartID, String userName, String city, String district, String ward, String address, int phone, String note) {
        super(orderID, status, total, orderDate, promotionID, cartID, userName, city, district, ward, address, phone, note);
        this.productDetailsID = productDetailsID;
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

    public OrderDetailsDTO(int productDetailsID, int orderID, int productID, int quantity, int unitPrice, String productName, String size, String image, String category) {
        this.productDetailsID = productDetailsID;
        this.orderID = orderID;
        this.productID = productID;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.productName = productName;
        this.size = size;
        this.image = image;
        this.category = category;
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

    public String getProductName() {
        return productName;
    }

    public String getSize() {
        return size;
    }

    public String getImage() {
        return image;
    }

    public String getCategory() {
        return category;
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
