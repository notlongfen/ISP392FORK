/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.isp392.cart;

/**
 *
 * @author TTNHAT
 */
public class CartError {
    private String quantityError;
    private String productError;
    private String error;

    public CartError() {
        this.quantityError = "";
        this.productError = "";
        this.error = error; 
    }

    public CartError(String quantityError, String productError, String error) {
        this.quantityError = quantityError;
        this.productError = productError;
        this.error = error;
    }

    public String getQuantityError() {
        return quantityError;
    }

    public String getProductError() {
        return productError;
    }

    public String getError() {
        return error;
    }

    public void setQuantityError(String quantityError) {
        this.quantityError = quantityError;
    }

    public void setProductError(String productError) {
        this.productError = productError;
    }

    public void setError(String error) {
        this.error = error;
    }
    
}
