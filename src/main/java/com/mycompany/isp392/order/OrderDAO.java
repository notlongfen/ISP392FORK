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
import java.security.Timestamp;
import java.util.HashMap;
import java.util.Map;

import utils.DbUtils;

/**
 *
 * @author notlongfen
 */
public class OrderDAO {

    private static final String ADD_ORDER = "INSERT INTO Orders (status, total, orderDate, CustID, promotionID, CartID, userName, city, district, ward, address, phone, note) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final String GET_LAST_ORDER_ID = "SELECT MAX(orderID) FROM Orders";
    private static final String ADD_ORDER_DETAILS = "INSERT INTO OrderDetails (productDetailsID, orderID, productID, quantity, unitPrice) VALUES (?,?,?,?,?)";
    private static final String SEARCH_ORDERS = "SELECT * FROM Orders WHERE orderID = ?";
    private static final String EDIT_ORDER_STATUS = "UPDATE Orders SET status = ? WHERE orderID = ?";
    private static final String GET_ORDER_INFO_BY_ORDERID = "SELECT * FROM Orders WHERE orderID = ?";
    private static final String GET_LIST_ORDER_DETAIL_INFO_BY_ORDERID = "SELECT * FROM OrderDetails WHERE orderID = ?";
    private static final String GET_ALL_ORDERS = "SELECT * FROM Orders";
    private static final String VIEW_ORDER = "SELECT * FROM Orders o JOIN OrderDetails od ON o.OrderID = od.OrderID JOIN ProductDetails pd ON pd.ProductDetailsID = od.ProductDetailsID WHERE custID = ?";
    private static final String VIEW_PD_IN_ORDER = "SELECT * FROM ProductDetails pd JOIN Products p ON pd.ProductID = p.ProductID WHERE p.ProductID = ?";
    private static final String VIEW_CATE_OF_PRODUCT = "SELECT *,  cdc.CategoriesName AS CDCategoryName FROM ProductBelongtoCDCategories pc JOIN Categories c ON pc.CDCategoryID = c.CategoryID JOIN ChildrenCategories cdc ON c.CategoryID = cdc.ParentID WHERE pc.ProductID = ? ";
    private static final String CANCEL_ORDER = "UPDATE Orders SET status = 4 WHERE orderID = ? AND status NOT IN (0, 2, 3)";
    private static final String GET_TOTAL_INCOME_TODAY = "SELECT SUM(total) AS totalIncome FROM Orders WHERE orderDate = CAST(GETDATE() AS DATE) AND status = 4";
    private static final String GET_TOTAL_INCOME_YESTERDAY = "SELECT SUM(total) AS totalIncome FROM Orders WHERE orderDate = DATEADD(DAY, -1, CAST(GETDATE() AS DATE)) AND status = 4";
    private static final String GET_NUMBER_OF_ORDERS_TODAY = "SELECT COUNT(orderID) AS numberOfOrders FROM Orders WHERE orderDate = CAST(GETDATE() AS DATE) AND status = 4";
    private static final String GET_NUMBER_OF_ORDERS_YESTERDAY = "SELECT COUNT(orderID) AS numberOfOrders FROM Orders WHERE orderDate = DATEADD(DAY, -1, CAST(GETDATE() AS DATE)) AND status = 4";
    private static final String GET_ORDERS_BY_USERS = "SELECT * FROM Orders WHERE CustID = ?";
    private static final String GET_FIRST_PRODUCT_IN_ORDER = "SELECT od.*, p.productName, pd.size, pd.image, cc.CategoriesName "
            + "FROM OrderDetails od "
            + "JOIN ProductDetails pd ON pd.ProductDetailsID = od.ProductDetailsID "
            + "JOIN Products p ON p.ProductID = pd.ProductID "
            + "JOIN ProductBelongtoCDCategories pbtc ON pbtc.ProductID = p.ProductID "
            + "JOIN ChildrenCategories cc ON cc.CDCategoryID = pbtc.CDCategoryID "
            + "WHERE od.OrderID = ?";
    private static final String GET_TOTAL_QUANTITY_BY_ORDER = "SELECT SUM(quantity) AS totalQuantity FROM OrderDetails WHERE OrderID = ?";
    private static final String GET_ALL_ORDER_STATUS_BY_ORDERID = "SELECT FieldNew, ChangeDate FROM ManageOrders WHERE OrderID =?";
    private static final String GET_PRODUCT_DETAILS_BY_ORDERID = "SELECT p.productName,pd.image, pd.size, pd.color FROM ProductDetails pd "
            + "INNER JOIN Products p ON p.ProductID=pd.ProductID "
            + "INNER JOIN OrderDetails od ON pd.ProductDetailsID = od.ProductDetailsID "
            + "WHERE od.OrderID=? ;";
    private static final String GET_TOTAL_PRICE_IN_CART_BY_ORDERID = "SELECT c.totalPrice FROM Orders o JOIN Carts C ON o.CartID = C.CartID WHERE o.OrderID=?;";
    
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
                    String cateName = rs.getString("CDCategoryName");
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

    private static final String GET_TOTAL_INCOME_THIS_MONTH = "SELECT SUM(total) AS totalIncome FROM Orders  WHERE YEAR(orderDate) = YEAR(GETDATE()) AND MONTH(orderDate) = MONTH(GETDATE()) AND status=3;";
    private static final String NUMBER_OF_ORDERS_THIS_MONTH = "SELECT COUNT(OrderID) AS numberOfOrder FROM Orders  WHERE YEAR(orderDate) = YEAR(GETDATE()) AND MONTH(orderDate) = MONTH(GETDATE()) AND status=3;";

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
                orderDTO = new OrderDTO(dao.getLastOrderId() - 1, 1, total, init, custId, promotionId, cartId, userName,
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
                ptm.setString(1,  searchText );
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
                    String note = rs.getString("note");
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
                    int status = rs.getInt("status");
                    Date orderDate = rs.getDate("orderDate");
                    String userName = rs.getString("userName");
                    String city = rs.getString("city");
                    String district = rs.getString("district");
                    String ward = rs.getString("ward");
                    String address = rs.getString("address");
                    int phone = rs.getInt("phone");
                    String note = rs.getString("note");
                    order = new OrderDTO(orderID, status, total, orderDate, userName, city, district, ward, address, phone, note);
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

    public int getMonthIncome() {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        int totalIncome = 0;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_TOTAL_INCOME_THIS_MONTH);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    totalIncome = rs.getInt("totalIncome");
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
        return totalIncome;
    }

    public int getNumberOfCanceledOrder() {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        int numberOfOrder = 0;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(NUMBER_OF_ORDERS_THIS_MONTH);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    numberOfOrder = rs.getInt("numberOfOrder");
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
        return numberOfOrder;
    }

    public List<Integer> getMonthlyIncomes() throws ClassNotFoundException {
        List<Integer> monthlyIncomes = new ArrayList<>();
        String query = "SELECT MONTH(orderDate) as month, SUM(total) as totalIncome FROM Orders WHERE YEAR(orderDate) = YEAR(GETDATE()) AND status = 4 GROUP BY MONTH(orderDate)";

        try (Connection conn = DbUtils.getConnection(); PreparedStatement ps = conn.prepareStatement(query); ResultSet rs = ps.executeQuery()) {

            for (int i = 1; i <= 12; i++) {
                monthlyIncomes.add(0);
            }

            while (rs.next()) {
                int month = rs.getInt("month");
                int totalIncome = rs.getInt("totalIncome");
                monthlyIncomes.set(month - 1, totalIncome);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return monthlyIncomes;
    }

    // Method to fetch monthly orders
    public List<Integer> getMonthlyOrders() throws ClassNotFoundException {
        List<Integer> monthlyOrders = new ArrayList<>();
        String query = "SELECT MONTH(orderDate) as month, COUNT(orderID) as orderCount FROM Orders WHERE YEAR(orderDate) = YEAR(GETDATE()) AND status = 4 GROUP BY MONTH(orderDate)";

        try (Connection conn = DbUtils.getConnection(); PreparedStatement ps = conn.prepareStatement(query); ResultSet rs = ps.executeQuery()) {

            for (int i = 1; i <= 12; i++) {
                monthlyOrders.add(0);
            }

            while (rs.next()) {
                int month = rs.getInt("month");
                int orderCount = rs.getInt("orderCount");
                monthlyOrders.set(month - 1, orderCount);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return monthlyOrders;
    }

    public int getTotalIncomeToday() throws ClassNotFoundException {
        int totalIncome = 0;
        try (Connection conn = DbUtils.getConnection(); PreparedStatement ps = conn.prepareStatement(GET_TOTAL_INCOME_TODAY); ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                totalIncome = rs.getInt("totalIncome");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalIncome;
    }

    // Method to fetch total income for yesterday
    public int getTotalIncomeYesterday() throws ClassNotFoundException {
        int totalIncome = 0;
        try (Connection conn = DbUtils.getConnection(); PreparedStatement ps = conn.prepareStatement(GET_TOTAL_INCOME_YESTERDAY); ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                totalIncome = rs.getInt("totalIncome");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalIncome;
    }

    // Method to fetch number of orders for today
    public int getNumberOfOrdersToday() throws ClassNotFoundException {
        int numberOfOrders = 0;
        try (Connection conn = DbUtils.getConnection(); PreparedStatement ps = conn.prepareStatement(GET_NUMBER_OF_ORDERS_TODAY); ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                numberOfOrders = rs.getInt("numberOfOrders");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return numberOfOrders;
    }

    // Method to fetch number of orders for yesterday
    public int getNumberOfOrdersYesterday() throws ClassNotFoundException {
        int numberOfOrders = 0;
        try (Connection conn = DbUtils.getConnection(); PreparedStatement ps = conn.prepareStatement(GET_NUMBER_OF_ORDERS_YESTERDAY); ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                numberOfOrders = rs.getInt("numberOfOrders");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return numberOfOrders;
    }
    private static final String GET_ORDER_COUNT_BY_STATUS = "SELECT status, COUNT(orderID) as orderCount FROM Orders WHERE status IN (1, 2, 3, 4, 5) GROUP BY status";

    // Method to fetch order counts by status
    public Map<Integer, Integer> getOrderCountByStatus() throws ClassNotFoundException {
        Map<Integer, Integer> orderCountByStatus = new HashMap<>();
        try (Connection conn = DbUtils.getConnection(); PreparedStatement ps = conn.prepareStatement(GET_ORDER_COUNT_BY_STATUS); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int status = rs.getInt("status");
                int count = rs.getInt("orderCount");
                orderCountByStatus.put(status, count);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderCountByStatus;
    }

    public List<OrderDTO> getOrdersByUser(int custID) throws SQLException {
        List<OrderDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_ORDERS_BY_USERS);
                ptm.setInt(1, custID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int orderID = rs.getInt("OrderID");
                    int status = rs.getInt("status");
                    double total = rs.getDouble("total");
                    Date orderDate = rs.getDate("orderDate");
                    int promotionID = rs.getInt("promotionID");
                    int cartID = rs.getInt("CartID");
                    String userName = rs.getString("userName");
                    String city = rs.getString("city");
                    String district = rs.getString("district");
                    String ward = rs.getString("ward");
                    String address = rs.getString("address");
                    int phone = rs.getInt("phone");
                    String note = rs.getString("phone");
                    OrderDTO order = new OrderDTO(orderID, status, total, orderDate, custID, promotionID, cartID, userName, city, district, ward, address, phone, note);
                    list.add(order);
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
        return list;
    }

    public OrderDetailsDTO getFirstProductInOrder(int orderID) throws SQLException {
        OrderDetailsDTO details = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_FIRST_PRODUCT_IN_ORDER);
                ptm.setInt(1, orderID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int productID = rs.getInt("ProductID");
                    int productDetailsID = rs.getInt("ProductDetailsID");
                    int quantity = rs.getInt("quantity");
                    int unitPrice = rs.getInt("unitPrice");
                    String productName = rs.getString("productName");
                    String size = rs.getString("size");
                    String image = rs.getString("image");
                    String category = rs.getString("CategoriesName");
                    details = new OrderDetailsDTO(productDetailsID, orderID, productID, quantity, unitPrice, productName, size, image, category);
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
        return details;
    }

    public int getTotalQuantityByOrder(int orderID) throws SQLException {
        int total = -1;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_TOTAL_QUANTITY_BY_ORDER);
                ptm.setInt(1, orderID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    total = rs.getInt("totalQuantity");
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
        return total;
    }

    public OrderDTO getRecentOrderOfCustomer(int userID){
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        OrderDTO order = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement("SELECT * FROM Orders WHERE CustID = ? ORDER BY OrderID DESC");
                ptm.setInt(1, userID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    int orderID = rs.getInt("OrderID");
                    int status = rs.getInt("status");
                    double total = rs.getDouble("total");
                    Date orderDate = rs.getDate("orderDate");
                    int promotionID = rs.getInt("promotionID");
                    int cartID = rs.getInt("CartID");
                    String userName = rs.getString("userName");
                    String city = rs.getString("city");
                    String district = rs.getString("district");
                    String ward = rs.getString("ward");
                    String address = rs.getString("address");
                    int phone = rs.getInt("phone");
                    String note = rs.getString("note");
                    order = new OrderDTO(orderID, status, total, orderDate, userID, promotionID, cartID, userName, city, district, ward, address, phone, note);
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
    
    public List<OrderDetailsDTO> getLastOrderDetails(int orderID){
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        List<OrderDetailsDTO> list = new ArrayList<>();
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement("SELECT * FROM OrderDetails od JOIN Orders o ON od.OrderID = o.OrderID WHERE o.orderID = ? ORDER BY od.OrderID DESC");
                ptm.setInt(1, orderID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int productDetailsID = rs.getInt("ProductDetailsID");
                    int productID = rs.getInt("ProductID");
                    int quantity = rs.getInt("quantity");
                    int unitPrice = rs.getInt("unitPrice");
                    list.add(new OrderDetailsDTO(productDetailsID, orderID, productID, quantity, unitPrice));
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
        return list;
        
    }

    public List<ManageOrderDTO> getListOrderStatusByOrderID(int orderID) {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        List<ManageOrderDTO> list = new ArrayList<>();
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_ALL_ORDER_STATUS_BY_ORDERID);
                ptm.setInt(1, orderID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String loadNewField = rs.getString("FieldNew");
                    java.sql.Timestamp changeDate = rs.getTimestamp("ChangeDate");
                    list.add(new ManageOrderDTO(orderID, loadNewField, changeDate));
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
        return list;
    }
    
    public List<OrderDetailsDTO> getOrderItems(int orderID) throws SQLException {
        List<OrderDetailsDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_PRODUCT_DETAILS_BY_ORDERID);
                ptm.setInt(1, orderID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String productName = rs.getString("productName");
                    String size = rs.getString("size");
                    String image = rs.getString("image");
                    String color = rs.getString("color");
                    list.add(new OrderDetailsDTO(orderID, productName, size, image, color));
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
        return list;
    }
    
    public double getTotalPriceInCartByOrderID(int orderID) throws SQLException {
        double totalPrice = 0.0;
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement ptm = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_TOTAL_PRICE_IN_CART_BY_ORDERID);
                ptm.setInt(1, orderID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    totalPrice = rs.getDouble("totalPrice");
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
        return totalPrice;
    }
}
