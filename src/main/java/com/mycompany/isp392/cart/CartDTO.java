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
    private int quantity;
    private int custID;

    public CartDTO() {
    }

    public CartDTO(int cartID, double totalPrice, int quantity, int custID) {
        this.cartID = cartID;
        this.totalPrice = totalPrice;
        this.quantity = quantity;
        this.custID = custID;
    }

    public int getCartID() {
        return cartID;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getCustID() {
        return custID;
    }

    public void setCartID(int cartID) {
        this.cartID = cartID;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setCustID(int custID) {
        this.custID = custID;
    }


    @Override
    public String toString() {
        return "CartDTO{" + "cartID=" + cartID + ", totalPrice=" + totalPrice + ", quantity=" + quantity + ", custID=" + custID + '}';
    }
}
