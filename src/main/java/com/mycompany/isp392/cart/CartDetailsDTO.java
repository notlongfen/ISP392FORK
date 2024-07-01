package com.mycompany.isp392.cart;

import com.mycompany.isp392.product.*;

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
    private int productDetailsID;
    private int quantity;
    private int price;

    ProductDTO product;
    ProductDetailsDTO productDetails;

    public CartDetailsDTO() {
    }

    public CartDetailsDTO(int cartID, int productID, int productDetailsID, int quantity, int price) {
        this.cartID = cartID;
        this.productID = productID;
        this.productDetailsID = productDetailsID;
        this.quantity = quantity;
        this.price = price;
    }

    public CartDetailsDTO(int cartID, int productID, int productDetailsID, int quantity, int price, ProductDTO product, ProductDetailsDTO productDetails) {
        this.cartID = cartID;
        this.productID = productID;
        this.productDetailsID = productDetailsID;
        this.quantity = quantity;
        this.price = price;
        this.product = product;
        this.productDetails = productDetails;
    }

    public int getCartID() {
        return cartID;
    }

    public int getProductID() {
        return productID;
    }

    public int getProductDetailsID() {
        return productDetailsID;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getPrice() {
        return price;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public ProductDetailsDTO getProductDetails() {
        return productDetails;
    }

    public void setCartID(int cartID) {
        this.cartID = cartID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public void setProductDetailsID(int productDetailsID) {
        this.productDetailsID = productDetailsID;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "CartDetailsDTO{" + "cartID=" + cartID + ", productID=" + productID + ", productDetailsID=" + productDetailsID + ", quantity=" + quantity + ", price=" + price + '}';
    }
}
