/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.isp392.support;

import com.mycompany.isp392.product.ProductDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.DbUtils;

/**
 *
 * @author notlongfen
 */
public class SupportDAO {
    private static final String GET_LAST_SUPPORTID = "SELECT * FROM Supports";
    private static final String SEARCH_SUPPORT = "SELECT * FROM ";
    private static final String UPDATE_SUPPORT_STATUS = "UPDATE Supports SET status = ? WHERE supportID = ?";
    
    
    public int getLastSupportId() throws SQLException {
        int lastSupportId = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_LAST_SUPPORTID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    lastSupportId = rs.getInt("SupportID");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return lastSupportId + 1;
    }
    
    public String supportStatusUpdate(int custID){
        Connection conn = null;
        PreparedStatement ptm = null;
        String supportID = "";
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_SUPPORT_STATUS);
                ptm.setString(1, "Done");
                ptm.setInt(2, custID);
                ptm.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return supportID;
    }
    
    public List<SupportDTO> searchSupport(String searchText) throws SQLException {
        List<ProductDTO> products = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SEARCH_SUPPORT);
                ptm.setString(1, "%" + searchText + "%");
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int productID = rs.getInt("productID");
                    String name = rs.getString("productName");
                    String description = rs.getString("description");
                    int numberOfPurchase = rs.getInt("numberOfPurchase");
                    int brandID = rs.getInt("brandID");
                    products.add(new ProductDTO(productID, name, description, numberOfPurchase, 0, brandID));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return products;
    }
}
