/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.isp392.order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.resource.cci.ResultSet;
import utils.DbUtils;

/**
 *
 * @author notlongfen
 */
public class OrderDAO {
    private static final String ADD_ORDER = "INSERT INTO Orders (status, total, orderDate, CustID, promotionID, CartID) VALUES (?,?,?,?,?,?)";
    private static final String GET_LAST_ORDER_ID = "SELECT MAX(orderID) FROM Orders";
    private static final String ADD_ORDER_DETAILS = "INSERT INTO OrderDetails (orderID, productID, quantity, price) VALUES (?,?,?,?)";
    public int getLastOrderId() throws SQLException{
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        int lastOrderId = 0;
        try {
            conn = DbUtils.getConnection();
            ptm = conn.prepareStatement(GET_LAST_ORDER_ID);
            rs = (ResultSet) ptm.executeQuery();
            if(rs.next()){
                lastOrderId = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();

        }finally{
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
        return lastOrderId + 1;
    }
    
    public OrderDTO insertOrder(OrderDTO order, int total, int custId, int promotionId, int cartId){
        OrderDTO orderDTO = null;
        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = DbUtils.getConnection();
            pstm = conn.prepareStatement(ADD_ORDER);
            pstm.setInt(1, 1);
            pstm.setInt(2, total);
            pstm.setDate(3, (java.sql.Date) order.getOrderDate());
            pstm.setInt(4, custId);
            pstm.setInt(5, promotionId);
            pstm.setInt(6, cartId);
            int row = pstm.executeUpdate();
            if (row > 0) {
                orderDTO = order;
            } 
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return orderDTO;
    }

    public OrderDetailsDTO insertOrderDetails(OrderDetailsDTO orderDetails){
        OrderDetailsDTO orderDetailsDTO = null;
        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = DbUtils.getConnection();
            pstm = conn.prepareStatement(ADD_ORDER);
            pstm.setInt(1, orderDetails.getOrderID());
            pstm.setInt(2, orderDetails.getProductID());
            pstm.setInt(3, orderDetails.getQuantity());
            pstm.setInt(4, orderDetails.getUnitPrice());
            int row = pstm.executeUpdate();
            if (row > 0) {
                orderDetailsDTO = orderDetails;
            } 
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderDetailsDTO;
    }
    
    
}
