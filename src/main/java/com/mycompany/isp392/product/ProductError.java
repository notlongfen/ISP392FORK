/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.isp392.product;

/**
 *
 * @author TTNHAT
 */
public class ProductError {
    
    private String productIDError;
    private String productNameError;
    private String descriptionError;
    private String numberOfPurchaseError;
    private String productStatusError;
    private String brandIDError;
    private String colorError;
    private String sizeError;
    private String stockQuantityError;
    private String priceError;
    private String importDateError;
    private String imageError;
    private String detailStatusError;
    private String error;
    
    public ProductError(){
        this.productNameError = "";
        this.productIDError = "";
        this.descriptionError = "";
        this.numberOfPurchaseError = "";
        this.productStatusError = "";
        this.brandIDError = "";
        this.colorError = "";
        this.sizeError = "";
        this.stockQuantityError = "";
        this.priceError = "";
        this.importDateError = "";
        this.imageError = "";
        this.detailStatusError = "";
        this.error = error;
    }

    public ProductError(String productIDError, String productNameError, String descriptionError, String numberOfPurchaseError, String productStatusError, String brandIDError, String colorError, String sizeError, String stockQuantityError, String priceError, String importDateError, String imageError, String detailStatusError, String error) {
        this.productIDError = productIDError;
        this.productNameError = productNameError;
        this.descriptionError = descriptionError;
        this.numberOfPurchaseError = numberOfPurchaseError;
        this.productStatusError = productStatusError;
        this.brandIDError = brandIDError;
        this.colorError = colorError;
        this.sizeError = sizeError;
        this.stockQuantityError = stockQuantityError;
        this.priceError = priceError;
        this.importDateError = importDateError;
        this.imageError = imageError;
        this.detailStatusError = detailStatusError;
        this.error = error;
    }

    public String getProductIDError() {
        return productIDError;
    }

    public String getProductNameError() {
        return productNameError;
    }

    public String getDescriptionError() {
        return descriptionError;
    }

    public String getNumberOfPurchaseError() {
        return numberOfPurchaseError;
    }

    public String getProductStatusError() {
        return productStatusError;
    }

    public String getBrandIDError() {
        return brandIDError;
    }

    public String getColorError() {
        return colorError;
    }

    public String getSizeError() {
        return sizeError;
    }

    public String getStockQuantityError() {
        return stockQuantityError;
    }

    public String getPriceError() {
        return priceError;
    }

    public String getImportDateError() {
        return importDateError;
    }

    public String getImageError() {
        return imageError;
    }

    public String getDetailStatusError() {
        return detailStatusError;
    }

    public String getError() {
        return error;
    }

    public void setProductIDError(String productIDError) {
        this.productIDError = productIDError;
    }

    public void setProductNameError(String productNameError) {
        this.productNameError = productNameError;
    }

    public void setDescriptionError(String descriptionError) {
        this.descriptionError = descriptionError;
    }

    public void setNumberOfPurchaseError(String numberOfPurchaseError) {
        this.numberOfPurchaseError = numberOfPurchaseError;
    }

    public void setProductStatusError(String productStatusError) {
        this.productStatusError = productStatusError;
    }

    public void setBrandIDError(String brandIDError) {
        this.brandIDError = brandIDError;
    }

    public void setColorError(String colorError) {
        this.colorError = colorError;
    }

    public void setSizeError(String sizeError) {
        this.sizeError = sizeError;
    }

    public void setStockQuantityError(String stockQuantityError) {
        this.stockQuantityError = stockQuantityError;
    }

    public void setPriceError(String priceError) {
        this.priceError = priceError;
    }

    public void setImportDateError(String importDateError) {
        this.importDateError = importDateError;
    }

    public void setImageError(String imageError) {
        this.imageError = imageError;
    }

    public void setDetailStatusError(String detailStatusError) {
        this.detailStatusError = detailStatusError;
    }

    public void setError(String error) {
        this.error = error;
    }

   
}
