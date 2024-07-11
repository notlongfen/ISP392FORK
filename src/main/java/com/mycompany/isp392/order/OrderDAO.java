/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.isp392.order;

import com.mycompany.isp392.category.ChildrenCategoryDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.sql.ResultSet;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import com.mycompany.isp392.user.UserDTO;
import com.mycompany.isp392.order.*;
import com.mycompany.isp392.product.ProductDetailsDTO;

import utils.DbUtils;

/**
 *
 * @author notlongfen
 */
public class OrderDAO {

    private static final String ADD_ORDER = "INSERT INTO Orders (status, total, orderDate, CustID, promotionID, CartID, userName, city, district, ward, addresss, phone) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final String GET_LAST_ORDER_ID = "SELECT MAX(orderID) FROM Orders";
    private static final String ADD_ORDER_DETAILS = "INSERT INTO OrderDetails (productDetailsID, orderID, productID, quantity, unitPrice) VALUES (?,?,?,?,?)";
    private static final String SEARCH_ORDERS = "SELECT * FROM Orders WHERE orderID LIKE ? OR orderDate LIKE ? OR total LIKE ? OR CustID LIKE ? OR CartID LIKE ?";
    private static final String EDIT_ORDER_STATUS = "UPDATE Orders SET status = ? WHERE orderID = ?";
    private static final String GET_ORDER_INFO_BY_ORDERID = "SELECT * FROM Orders WHERE orderID = ?";
    private static final String GET_LIST_ORDER_DETAIL_INFO_BY_ORDERID = "SELECT * FROM OrderDetails WHERE orderID = ?";
    private static final String GET_ALL_ORDERS = "SELECT * FROM Orders";
    private static final String VIEW_ORDER = "SELECT * FROM Orders o JOIN OrderDetails od ON o.OrderID = od.OrderID JOIN ProductDetails pd ON pd.ProductDetailsID = od.ProductDetailsID WHERE custID = ?";
    private static final String VIEW_PD_IN_ORDER = "SELECT * FROM ProductDetails pd JOIN Products p ON pd.ProductID = p.ProductID WHERE ProductID = ?";
    private static final String VIEW_CATE_OF_PRODUCT = "SELECT * FROM ProductBelongtoCDCategories pc JOIN Categories c ON pc.CDCategoryID = c.CategoryID JOIN ChildrenCategories cdc ON c.CategoryID = cdc.ParentID WHERE pc.ProductID = ? "; 
    private static final String CANCEL_ORDER = "UPDATE Orders SET status = 4 WHERE orderID = ? AND status NOT IN (0, 2, 3)";
    
    
    public boolean cancelOrder(int orderID) throws ClassNotFoundException {
        Connection conn = null;
        PreparedStatement ptm = null;
        boolean isCanceled = false;
        try {
            conn = DbUtils.getConnection();
            ptm = conn.prepareStatement(CANCEL_ORDER);
            ptm.setInt(1, orderID);
            int row = ptm.executeUpdate();
            if (row > 0) {
                isCanceled = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ptm != null) {
                    ptm.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return isCanceled;
    }
    
    public List<ChildrenCategoryDTO> viewCateOfProduct(int productID) throws ClassNotFoundException, SQLException {
        List<ChildrenCategoryDTO> cateList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(VIEW_CATE_OF_PRODUCT);
                ptm.setInt(1, productID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String cateName = rs.getString("categoryName");
                    cateList.add(new ChildrenCategoryDTO(cateName));
                }
            }
        } catch (SQLException e) {
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
        return cateList;
    }
    
    
    public List<OrderDetailsDTO> viewOrder(int custID) throws ClassNotFoundException, SQLException {
        List<OrderDetailsDTO> orderList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(VIEW_ORDER);
                ptm.setInt(1, custID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int orderID = rs.getInt("orderID");
                    int status = rs.getInt("status");
                    int total = rs.getInt("total");
                    Date orderDate = rs.getDate("orderDate");
                    int promotionID = rs.getInt("promotionID");
                    int cartID = rs.getInt("cartID");
                    String userName = rs.getString("userName");
                    String city = rs.getString("city");
                    String district = rs.getString("district");
                    String ward = rs.getString("ward");
                    String address = rs.getString("address");
                    int phone = rs.getInt("phone");
                    String note = rs.getString("note");
                    int productDetailsID = rs.getInt("productDetailsID");
                    int productID = rs.getInt("productID");
                    int quantity = rs.getInt("quantity");
                    int unitPrice = rs.getInt("unitPrice");
                    orderList.add(new OrderDetailsDTO(productDetailsID, orderID, productID, quantity, unitPrice, status, total, orderDate, promotionID, cartID, userName, city, district, ward, address, phone, note));
                }
            }
        } catch (SQLException e) {
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
        return orderList;
    }
    
    public List<ProductDetailsDTO> viewProductDetailsInOrder(int productID) throws ClassNotFoundException, SQLException {
        List<ProductDetailsDTO> productList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(VIEW_PD_IN_ORDER);
                ptm.setInt(1, productID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String size = rs.getString("size");
                    String productName = rs.getString("productName");
                    productList.add(new ProductDetailsDTO(productName, size));
                }
            }
        } catch (SQLException e) {
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
        return productList;
    }
    

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

    public OrderDTO insertOrder(double total, int custId, int promotionId, int cartId, String userName, String city,
            String district, String ward, String address, int phone, String note) {
        OrderDTO orderDTO = null;
        Connection conn = null;
        PreparedStatement pstm = null;
        OrderDAO dao = new OrderDAO();

        try {
            Date init = Date.valueOf(LocalDate.now());
            conn = DbUtils.getConnection();
            pstm = conn.prepareStatement(ADD_ORDER);
            pstm.setInt(1, 1);
            pstm.setDouble(2, total);
            pstm.setDate(3, init);
            pstm.setInt(4, custId);
            pstm.setInt(5, promotionId);
            pstm.setInt(6, cartId);
            pstm.setString(7, userName);
            pstm.setString(8, city);
            pstm.setString(9, district);
            pstm.setString(10, ward);
            pstm.setString(11, address);
            pstm.setInt(12, phone);
            pstm.setString(13, note);
            int row = pstm.executeUpdate();
            if (row > 0) {
                orderDTO = new OrderDTO(dao.getLastOrderId(), 1, total, init, custId, promotionId, cartId, userName,
                        city, district, ward, address, phone, note);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
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
            pstm.setInt(1, orderDetails.getProductDetailsID());
            pstm.setInt(2, orderDetails.getOrderID());
            pstm.setInt(3, orderDetails.getProductID());
            pstm.setInt(4, orderDetails.getQuantity());
            pstm.setInt(5, orderDetails.getUnitPrice());
            int row = pstm.executeUpdate();
            if (row > 0) {
                orderDetailsDTO = orderDetails;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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

    public boolean editOrderStatus(int orderID, int status) {
        Connection conn = null;
        PreparedStatement pstm = null;
        boolean check = false;
        try {
            conn = DbUtils.getConnection();
            pstm = conn.prepareStatement(EDIT_ORDER_STATUS);
            pstm.setInt(1, status);
            pstm.setInt(2, orderID);
            int row = pstm.executeUpdate();
            if (row > 0) {
                check = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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
                    String userName = rs.getString("userName");
                    String city = rs.getString("city");
                    String district = rs.getString("district");
                    String ward = rs.getString("ward");
                    String address = rs.getString("address");
                    int phone = rs.getInt("phone");
                    String note = rs.getString(phone);
                    orders.add(new OrderDTO(orderID, status, total, orderDate, custID, promotionID, cartID, userName,
                            city, district, ward, address, phone, note));
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

    public List<OrderDetailsDTO> getListOrderDetailsByOrderID(int orderID) {
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<OrderDetailsDTO> listOrderDetails = new ArrayList<>();
        try {
            conn = DbUtils.getConnection();
            pstm = conn.prepareStatement(GET_LIST_ORDER_DETAIL_INFO_BY_ORDERID);
            pstm.setInt(1, orderID);
            rs = pstm.executeQuery();
            while (rs.next()) {
                int productDetailsID = rs.getInt("productDetailsID");
                int productID = rs.getInt("productID");
                int quantity = rs.getInt("quantity");
                int unitPrice = rs.getInt("unitPrice");
                listOrderDetails.add(new OrderDetailsDTO(productDetailsID, orderID, productID, quantity, unitPrice));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
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
        return listOrderDetails;
    }

    public List<OrderDTO> getAllOrder() throws SQLException {
        List<OrderDTO> orders = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_ALL_ORDERS);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int orderID = rs.getInt("orderID");
                    int status = rs.getInt("status");
                    Date orderDate = rs.getDate("orderDate");
                    int total = rs.getInt("total");
                    int custID = rs.getInt("custID");
                    orders.add(new OrderDTO(orderID, status, total, orderDate, custID));

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
        return orders;
    }

    public OrderDTO getOrderInfo(int orderID) {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        OrderDTO order = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_ORDER_INFO_BY_ORDERID);
                ptm.setInt(1, orderID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    double total = rs.getDouble("total");
                    int promotionID = rs.getInt("promotionID");
                    Date orderDate = rs.getDate("orderDate");
                    String userName = rs.getString("userName");
                    String city = rs.getString("city");
                    String district = rs.getString("district");
                    String ward = rs.getString("ward");
                    String address = rs.getString("address");
                    int phone = rs.getInt("phone");
                    String note = rs.getString("note");
                    order = new OrderDTO(orderID, total, orderDate, userName, city, district, ward, address, phone, note);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ptm != null) {
                    ptm.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return order;
    }

}
