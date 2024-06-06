/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.isp392.wishlist;

/**
 *
 * @author Oscar
 */
public class WishlistDTO {
    private int wishlistID;
    private int custID;
    private int productID;

    public WishlistDTO() {
    }

    public WishlistDTO(int wishlistID, int custID, int productID) {
        this.wishlistID = wishlistID;
        this.custID = custID;
        this.productID = productID;
    }

    public int getWishlistID() {
        return wishlistID;
    }

    public void setWishlistID(int wishlistID) {
        this.wishlistID = wishlistID;
    }

    public int getCustID() {
        return custID;
    }

    public void setCustID(int custID) {
        this.custID = custID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }
    
    
}
