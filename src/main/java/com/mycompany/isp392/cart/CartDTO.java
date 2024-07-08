package com.mycompany.isp392.cart;





/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author TTNHAT
 */
public class CartDTO {
    private int cartID;
    private double totalPrice;
    private int custID;
    private int promotionID;
    private int status;
    public CartDTO() {
        
    }
    

    public CartDTO(int cartID, double totalPrice, int custID, int promotionID,int status) {
        this.cartID = cartID;
        this.totalPrice = totalPrice;
        this.custID = custID;
        this.promotionID = promotionID;
        this.status = status;
    }

    public int getCartID() {
        return cartID;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public int getCustID() {
        return custID;
    }

    public int getPromotionID() {
        return promotionID;
    }

    public int getStatus() {
        return status;
    }

    public void setCartID(int cartID) {
        this.cartID = cartID;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setCustID(int custID) {
        this.custID = custID;
    }

    public void setPromotionID(int promotionID) {
        this.promotionID = promotionID;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CartDTO{" + "cartID=" + cartID + ", totalPrice=" + totalPrice + ", custID=" + custID + '}';
    }
}
