package com.mycompany.isp392.cart;



/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author TTNHAT
 */
public class CartDetailsDTO {
    private int cartID;    
    private int productID;

    public CartDetailsDTO() {
    }

    public CartDetailsDTO(int cartID, int productID) {
        this.cartID = cartID;
        this.productID = productID;
    }

    public int getCartID() {
        return cartID;
    }

    public int getProductID() {
        return productID;
    }

    public void setCartID(int cartID) {
        this.cartID = cartID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    @Override
    public String toString() {
        return "CartDetailsDTO{" + "cartID=" + cartID + ", productID=" + productID + '}';
    }
}
