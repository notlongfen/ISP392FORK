
package com.mycompany.isp392.brand;

public class BrandDTO {
    private int brandID;
    private String brandName;
    private String image;
    private int status;

    public BrandDTO(int brandID, String brandName, String image, int status) {
        this.brandID = brandID;
        this.brandName = brandName;
        this.image = image;
        this.status = status;
    }

    public BrandDTO() {
    }

    public int getBrandID() {
        return brandID;
    }

    public void setBrandID(int brandID) {
        this.brandID = brandID;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
}
