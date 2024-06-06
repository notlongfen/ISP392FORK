/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.isp392.user;

import java.util.Date;

public class CustomerDTO extends UserDTO{
    private int points;
    private Date birthday;
    private String city;
    private String district;
    private String ward;
    private String address;

    public CustomerDTO() {
    }

    public CustomerDTO(int points, Date birthday, String city, String district, String ward, String address) {
        this.points = points;
        this.birthday = birthday;
        this.city = city;
        this.district = district;
        this.ward = ward;
        this.address = address;
    }

    public CustomerDTO(int UserID, String userName, String email, String password, int roleID, int phone, boolean status, int points, Date birthday, String city, String district, String ward, String address) {
        super(UserID, userName, email, password, roleID, phone, status);
        this.points = points;
        this.birthday = birthday;
        this.city = city;
        this.district = district;
        this.ward = ward;
        this.address = address;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
