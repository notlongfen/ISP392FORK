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

    public CartDTO() {
        
    }
    

    public CartDTO(int cartID, double totalPrice, int custID) {
        this.cartID = cartID;
        this.totalPrice = totalPrice;
        this.custID = custID;
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

    public void setCartID(int cartID) {
        this.cartID = cartID;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setCustID(int custID) {
        this.custID = custID;
    }


    @Override
    public String toString() {
        return "CartDTO{" + "cartID=" + cartID + ", totalPrice=" + totalPrice + ", custID=" + custID + '}';
    }
}
