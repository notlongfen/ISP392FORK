package com.mycompany.isp392.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.DbUtils;


/**
 * Data Access Object for handling database operations related to products.
 */
public class ProductDAO {
    private static final String ADD_PRODUCT = "INSERT INTO Products(productID, productName, description, numberOfPurchase, status, brandID) VALUES(?,?,?,?,?,?)";
    private static final String EDIT_PRODUCT = "UPDATE Products SET productName=?, description=? WHERE productID=?";
    private static final String DELETE_PRODUCT = "UPDATE Products SET status = -1 WHERE productID=?";
    private static final String SELECT_PRODUCT = "SELECT productName, description, numberOfPurchase, brandID FROM Products WHERE productID=? and status=0";
    private static final String SEARCH_PRODUCT = "SELECT productName, description, numberOfPurchase, brandID FROM Products WHERE productName like ? and status=0";
    public boolean addProduct(ProductDTO product) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(ADD_PRODUCT);
                ptm.setInt(1, product.getProductID());
                ptm.setString(2, product.getProductName());
                ptm.setString(3, product.getDescription());
                ptm.setInt(4, product.getNumberOfPurchase());
                ptm.setInt(5, product.getStatus());
                ptm.setInt(6, product.getBrandID());
                check = ptm.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    public boolean editProduct(String productID, String newName, String newDescription) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(EDIT_PRODUCT);
                ptm.setString(1, newName); // Set new product name
                ptm.setString(2, newDescription); // Set new description
                ptm.setString(3, productID); // Where clause, identifying which product to update

                check = ptm.executeUpdate() > 0; // Check if any rows were actually updated
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    public boolean deleteProduct(String productID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DELETE_PRODUCT);
                ptm.setString(1, productID);
                check = ptm.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    public ProductDTO selectProduct(int productID) throws SQLException {
        ProductDTO product = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SELECT_PRODUCT);
                ptm.setInt(1, productID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    String productName = rs.getString("productName");
                    String description = rs.getString("description");
                    int numberOfPurchase = rs.getInt("numberOfPurchase");
                    int brandID = rs.getInt("brandID");
                    product = new ProductDTO(productID, productName, description, numberOfPurchase, 0, brandID);
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
        return product;
    }
    
     public List<ProductDTO> searchProducts(String productName) throws SQLException {
        List<ProductDTO> products = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SEARCH_PRODUCT);
                ptm.setString(1, "%" + productName + "%"); 
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
