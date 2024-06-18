/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.isp392.order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import utils.DbUtils;

/**
 *
 * @author notlongfen
 */
public class OrderDAO {

    private static final String ADD_ORDER = "INSERT INTO Orders (status, total, orderDate, CustID, promotionID, CartID) VALUES (?,?,?,?,?,?)";
    private static final String GET_LAST_ORDER_ID = "SELECT MAX(orderID) FROM Orders";
    private static final String ADD_ORDER_DETAILS = "INSERT INTO OrderDetails (orderID, productID, quantity, unitPrice) VALUES (?,?,?,?)";
    private static final String SET_ORDER_ORDER = "Update Orders SET status = ? WHERE OrderID = ?";
    private static final String DELETE_ORDER = "";
    private static final String SEARCH_ORDERS = "SELECT * FROM Orders WHERE orderID LIKE ? OR orderDate LIKE ? OR total LIKE ? OR CustID LIKE ? OR CartID LIKE ?";
    private static final String UPDATE_ORDER_STATUS = "UPDATE Orders SET status = ? WHERE orderID = ?";
    public int getLastOrderId() throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        int lastOrderId = 0;
        try {
            conn = DbUtils.getConnection();
            ptm = conn.prepareStatement(GET_LAST_ORDER_ID);
            rs = (ResultSet) ptm.executeQuery();
            if (rs.next()) {
                lastOrderId = rs.getInt(1);
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
        return lastOrderId + 1;
    }

    public OrderDTO insertOrder(OrderDTO order, int total, int custId, int promotionId, int cartId) {
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
        } finally{
            try {
                if (pstm != null) {
                    pstm.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return orderDTO;
    }

    public OrderDetailsDTO insertOrderDetails(OrderDetailsDTO orderDetails) {
        OrderDetailsDTO orderDetailsDTO = null;
        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = DbUtils.getConnection();
            pstm = conn.prepareStatement(ADD_ORDER_DETAILS);
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
        }finally{
            try {
                if (pstm != null) {
                    pstm.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return orderDetailsDTO;
    }
    
    public boolean updateOrderStatus(int orderID,int status){
        Connection conn = null;
        PreparedStatement pstm = null;
        boolean check = false;
        try {
            conn = DbUtils.getConnection();
            pstm = conn.prepareStatement(SET_ORDER_ORDER);
            pstm.setInt(1, status);
            pstm.setInt(2, orderID);
            int row = pstm.executeUpdate();
            if (row > 0) {
                check = true;
            } 
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            try {
                if (pstm != null) {
                    pstm.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return check;
    }
    
    

   public List<OrderDTO> searchOrders(String searchText) throws SQLException, ClassNotFoundException {
        List<OrderDTO> orders = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SEARCH_ORDERS);
                ptm.setString(1, "%" + searchText + "%");
                ptm.setString(2, "%" + searchText + "%");
                ptm.setString(3, "%" + searchText + "%");
                ptm.setString(4, "%" + searchText + "%");
                ptm.setString(5, "%" + searchText + "%");
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int orderID = rs.getInt("orderID");
                    int status = rs.getInt("status");
                    int total = rs.getInt("total");
                    Date orderDate = rs.getDate("orderDate");
                    int custID = rs.getInt("CustID");
                    int promotionID = rs.getInt("promotionID");
                    int cartID = rs.getInt("CartID");
                    orders.add(new OrderDTO(orderID, status, total, orderDate, custID, promotionID, cartID));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e; // Rethrow the exception after logging it
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
        return orders;
    }
     public boolean updateOrderStatus(int orderID, int status) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement ptm = null;
        boolean updated = false;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_ORDER_STATUS);
                ptm.setInt(1, status);
                ptm.setInt(2, orderID);
                updated = ptm.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e; // Rethrow the exception after logging it
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return updated;
    }
}
