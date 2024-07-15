/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.isp392.wishlist;

import com.mycompany.isp392.product.ProductDetailsDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.DbUtils;

/**
 *
 * @author PC
 */
public class WishlistDAO {

    private static final String SELECT__WISHLIST = "SELECT DISTINCT P.ProductID, WD.ProductDetailsID, P.productName, PD.price, PD.image, B.BrandName\n"
            + "FROM \n"
            + "    Wishlists W JOIN WishlistDetails WD ON W.WishlistID = WD.WishlistID JOIN Products P ON WD.ProductID = P.ProductID JOIN  ProductDetails PD ON WD.ProductDetailsID = PD.ProductDetailsID JOIN  Brands B ON P.BrandID = B.BrandID\n"
            + "WHERE \n"
            + "    W.CustID = ? AND WD.status = 1;";
    private static final String DELETE__WISHLIST = "UPDATE WishlistDetails SET status = 0 WHERE WishlistID IN ( SELECT WishlistID FROM Wishlists WHERE CustID = ?) AND ProductDetailsID = ? AND ProductID = ?";
    private static final String ADD__WISHLIST = "insert into Wishlists(CustID) values (?)";
    private static final String SHOW__WISHLIST = "select * from Wishlists where CustID = ?";
    private static final String ADD__WISHLISTDETAIl = "insert into WishlistDetails (WishlistID,ProductID, ProductDetailsID, status) values (?,?,?,?)  ";

    public boolean addToWishlist(int custID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(ADD__WISHLIST);
                ptm.setInt(1, custID);
                check = ptm.executeUpdate() > 0;

            }
        } catch (ClassNotFoundException | SQLException e) {
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

    public WishlistDTO SelectWishlist(int custID) throws SQLException {
        WishlistDTO wishlist = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SHOW__WISHLIST);
                ptm.setInt(1, custID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    int wID = rs.getInt("WishlistID");
//                    int cID = rs.getInt("CustID");
                    wishlist = new WishlistDTO(wID, custID);
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
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
        return wishlist;
    }

    public boolean addToWishlistDetail(int wishlistID, int productID, int productDetailID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(ADD__WISHLISTDETAIl);
                ptm.setInt(1, wishlistID);
                ptm.setInt(2, productID);
                ptm.setInt(3, productDetailID);
                ptm.setInt(4, 1);
                check = ptm.executeUpdate() > 0;

            }
        } catch (ClassNotFoundException | SQLException e) {
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

    public List<ProductDetailsDTO> showWishlist(int cusID) throws SQLException {
        List<ProductDetailsDTO> products = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SELECT__WISHLIST);
                ptm.setInt(1, cusID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int productID = rs.getInt("ProductID");
                    int productDetailID = rs.getInt("ProductDetailsID");
                    String name = rs.getString("productName");
                    int price = rs.getInt("price");
                    String images = rs.getString("image");
                    String brandName = rs.getString("BrandName");
                    products.add(new ProductDetailsDTO(productDetailID, productID, name, images, price, brandName));
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

    public boolean deleteWishlist(int cusID, int productID, int productDetailID) throws SQLException {
        boolean result = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(DELETE__WISHLIST);
                stm.setInt(1, cusID);
                stm.setInt(2, productDetailID);
                stm.setInt(3, productID);
                int value = stm.executeUpdate();
                result = value > 0 ? true : false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return result;
    }

    public boolean areAllWishlistDetailsZero(int custID) throws SQLException, ClassNotFoundException {
        boolean allZero = true;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DbUtils.getConnection();
            String sql = "SELECT COUNT(*) FROM WishlistDetails wd INNER JOIN Wishlists w ON wd.wishlistID = w.wishlistID WHERE w.custID = ? AND wd.status <> 0";
            ptm = conn.prepareStatement(sql);
            ptm.setInt(1, custID);
            rs = ptm.executeQuery();
            if (rs.next()) {
                allZero = rs.getInt(1) == 0;
            }
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
        return allZero;
    }

    public boolean checkCustomerExists(int cusID) throws SQLException, ClassNotFoundException {
        boolean exists = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DbUtils.getConnection();
            String sql = "SELECT COUNT(*) FROM Wishlists WHERE custID = ?";
            ptm = conn.prepareStatement(sql);
            ptm.setInt(1, cusID);
            rs = ptm.executeQuery();
            if (rs.next()) {
                exists = rs.getInt(1) > 0;
            }
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
        return exists;
    }

    public boolean updateWishlistStatus(int cusID, int status) throws SQLException, ClassNotFoundException {
        boolean updated = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DbUtils.getConnection();
            String sql = "UPDATE Wishlists SET status = ? WHERE custID = ?";
            ptm = conn.prepareStatement(sql);
            ptm.setInt(1, status);
            ptm.setInt(2, cusID);
            updated = ptm.executeUpdate() > 0;
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

    public boolean updateWishlistDetailStatus(int wishlistID, int productID, int productDetailID, int status) throws SQLException, ClassNotFoundException {
        boolean updated = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DbUtils.getConnection();
            String sql = "UPDATE WishlistDetails SET status = ? WHERE WishlistID = ? AND ProductDetailsID = ? AND ProductID = ? AND status = 0";
            ptm = conn.prepareStatement(sql);
            ptm.setInt(1, status);
            ptm.setInt(2, wishlistID);
            ptm.setInt(3, productDetailID);
            ptm.setInt(4, productID);
            updated = ptm.executeUpdate() > 0;
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

    public boolean checkWishlistDetailExists(int wishlistID, int productID) throws SQLException, ClassNotFoundException {
        boolean exists = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DbUtils.getConnection();
            String sql = "SELECT COUNT(*) FROM WishlistDetails WHERE WishlistID = ? AND ProductDetailsID = ?";
            ptm = conn.prepareStatement(sql);
            ptm.setInt(1, wishlistID);
            ptm.setInt(2, productID);
            rs = ptm.executeQuery();
            if (rs.next()) {
                exists = rs.getInt(1) > 0;
            }
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
        return exists;
    }
}
