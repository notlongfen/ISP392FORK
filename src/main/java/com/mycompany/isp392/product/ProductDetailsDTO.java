package com.mycompany.isp392.product;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.sql.Date;

/**
 *
 * @author TTNHAT
 */
public class ProductDetailsDTO extends ProductDTO {

    private int productDetailsID; 
    private int productID;
    private String color;
    private String size;
    private int stockQuantity;
    private int price;
    private Date importDate;
    private String image;
    private int status;
    private String brandName;

    
    public ProductDetailsDTO() {
    }

    public ProductDetailsDTO(int productID, String color, String size, int stockQuantity, int price, Date importDate, String image, int status) {
        this.productID = productID;
        this.color = color;
        this.size = size;
        this.stockQuantity = stockQuantity;
        this.price = price;
        this.importDate = importDate;
        this.image = image;
        this.status = status;
    }

    public ProductDetailsDTO(int productDetailsID, int productID, String color, String size, int stockQuantity, int price, Date importDate, String image, int status) {
        this.productDetailsID = productDetailsID;
        this.productID = productID;
        this.color = color;
        this.size = size;
        this.stockQuantity = stockQuantity;
        this.price = price;
        this.importDate = importDate;
        this.image = image;
        this.status = status;
    }
    
    public ProductDetailsDTO(String productName, String image, int price) {
        super(productName);
        this.image = image;
        this.price = price;
    }
    
    public ProductDetailsDTO(String productName, String color, String size, int price) {
        super(productName);
        this.color=color;
        this.size=size;
        this.image = image;
        this.price = price;
    }

    public ProductDetailsDTO(int productID, String productName, String image, int price, String brandName) {
        super(productName);
        this.productID = productID;
        this.image = image;
        this.price = price;
        this.brandName = brandName;
    }

    public ProductDetailsDTO(String productName, String size) {
        super(productName);
        this.size = size;
        
    }

    public int getProductDetailsID() {
        return productDetailsID;
    }
    
    public int getProductID() {
        return productID;
    }

    public String getColor() {
        return color;
    }

    public String getSize() {
        return size;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public int getPrice() {
        return price;
    }

    public Date getImportDate() {
        return importDate;
    }

    public String getImage() {
        return image;
    }

    public int getStatus() {
        return status;
    }

    public void setProductDetailsID(int productDetailsID) {
        this.productDetailsID = productDetailsID;
    }
    
    public void setProductID(int productID) {
        this.productID = productID;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setImportDate(Date importDate) {
        this.importDate = importDate;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    @Override
    public String toString() {
        return "ProductDetailsDTO{" + "productDetailsID=" + productDetailsID + ", productID=" + productID + ", color=" + color + ", size=" + size + ", stockQuantity=" + stockQuantity + ", price=" + price + ", importDate=" + importDate + ", image=" + image + ", status=" + status + ", brandName=" + brandName + '}';
    }
}
