/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.isp392.brand;

import java.util.List;

import utils.DbUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author notlongfen
 */
public class BrandDAO {
    private static final String SEARCH_BRAND = "SELECT * FROM Brands WHERE brandName LIKE ?";
    private static final String INSERT_BRAND = "INSERT INTO Brands(BrandName, status) VALUES(?,1)";
    private static final String UPDATE_BRAND = "UPDATE Brands SET BrandName=? WHERE BrandID=?";
    private static final String DEACTIVATE_BRAND = "UPDATE Brands SET status = 0 WHERE BrandID=?";
    private static final String SELECT_BRAND = "SELECT BrandID, BrandName, status FROM Brands WHERE BrandID=?";
   
    public List<BrandDTO> searchForBrand(String brandName) {
        List<BrandDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SEARCH_BRAND);
                ptm.setString(1, "%" + brandName + "%");
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int brandID = rs.getInt("brandID");
                    int status = rs.getInt("status"); 
                    
                    list.add(new BrandDTO(brandID, brandName, status));
                }
            }

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return list;
    }
      public boolean addBrand(String brandName) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(INSERT_BRAND);
                ptm.setString(1, brandName);
                check = ptm.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BrandDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public boolean updateBrand(String brandName, int brandID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_BRAND);
                ptm.setString(1, brandName);
                ptm.setInt(2, brandID);
                check = ptm.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BrandDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public boolean deleteBrand(int brandID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DEACTIVATE_BRAND);
                ptm.setInt(1, brandID);
                check = ptm.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BrandDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }
}
