/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.isp392.user;

/**
 *
 * @author Oscar
 */
public class EmployeeDTO extends UserDTO {
    private String position;

    public EmployeeDTO() {
    }

    public EmployeeDTO(String position) {
        this.position = position;
    }

    public EmployeeDTO(String position, int UserID, String userName, String email, String password, int roleID, int phone, boolean status) {
        super(UserID, userName, email, password, roleID, phone, status);
        this.position = position;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
    
    
}


