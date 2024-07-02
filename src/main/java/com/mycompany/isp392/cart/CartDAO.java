package com.mycompany.isp392.cart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.mycompany.isp392.product.*;
import utils.DbUtils;

/**
 *
 * @author TTNHAT
 */
public class CartDAO {

    private static final String CREATE_CART = "INSERT INTO Carts (CartID, totalPrice, CustID) VALUES (?, ?, ?)";
    private static final String ADD_CART_DETAILS = "INSERT INTO CartDetails (CartID, ProductID, ProductDetailsID, quantity, price) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE_CART = "UPDATE Carts SET totalPrice = (SELECT SUM(price) FROM CartDetails WHERE CartID = ?) WHERE CartID = ?";
    private static final String UPDATE_CART_DETAILS = "UPDATE CartDetails SET price = ?, quantity = ? WHERE CartID = ? and ProductDetailsID = ?";
    private static final String GET_CART_DETAILS = "SELECT * FROM CartDetails WHERE CartID = ? AND ProductDetailsID = ?";
    private static final String GET_CART_BY_CUSTOMER = "SELECT CartID FROM Carts WHERE custID = ?";
    private static final String GET_LATEST_CART_ID = "SELECT MAX(CartID) AS CartID FROM Carts";
    private static final String REMOVE_PRODUCT_FROM_CART = "DELETE FROM CartDetails WHERE CartID = ? AND ProductDetailsID = ?";
    private static final String GET_CART_ITEMS = "SELECT cd.CartID, cd.ProductID, cd.ProductDetailsID, cd.price, cd.quantity, "
            + "pd.color, pd.size, pd.stockQuantity, pd.price AS unitPrice, pd.importDate, pd.image, pd.status AS pdStatus, "
            + "p.productName, p.description, p.NumberOfPurchasing, p.status AS pStatus, p.BrandID "
            + "FROM CartDetails cd "
            + "JOIN ProductDetails pd ON cd.ProductDetailsID = pd.ProductDetailsID "
            + "JOIN Products p ON cd.ProductID = p.ProductID "
            + "WHERE cd.CartID = ?";
            private static final String UPDATE_CART_DETAILS_STATUS = "UPDATE CartDetails SET status = ? WHERE cartID = ? AND productID = ?";
    public boolean createCart(CartDTO cart) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        Statement stm = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                stm = conn.createStatement();
                stm.executeUpdate("SET IDENTITY_INSERT Carts ON");

                ptm = conn.prepareStatement(CREATE_CART);
                ptm.setInt(1, cart.getCartID());
                ptm.setDouble(2, cart.getTotalPrice());
                ptm.setInt(3, cart.getCustID());
                check = ptm.executeUpdate() > 0;

                stm.executeUpdate("SET IDENTITY_INSERT Carts OFF");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return check;
    }

    public boolean addToCart(CartDetailsDTO details) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptmAdd = null;
        PreparedStatement ptmUpdate = null;

        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                conn.setAutoCommit(false);
                //Add product details to CartDetails
                ptmAdd = conn.prepareStatement(ADD_CART_DETAILS);
                ptmAdd.setInt(1, details.getCartID());
                ptmAdd.setInt(2, details.getProductID());
                ptmAdd.setInt(3, details.getProductDetailsID());
                ptmAdd.setInt(4, details.getQuantity());
                ptmAdd.setInt(5, details.getPrice());
                boolean checkAdd = ptmAdd.executeUpdate() > 0;

                //Update Cart
                ptmUpdate = conn.prepareStatement(UPDATE_CART);
                ptmUpdate.setInt(1, details.getCartID());
                ptmUpdate.setInt(2, details.getCartID());
                boolean checkUpdate = ptmUpdate.executeUpdate() > 0;

                //Check both statements
                if (checkAdd && checkUpdate) {
                    conn.commit();
                    check = true;
                } else {
                    conn.rollback();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptmAdd != null) {
                ptmAdd.close();
            }
            if (ptmUpdate != null) {
                ptmUpdate.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public boolean updateCartDetails(CartDetailsDTO details) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptmCart = null;
        PreparedStatement ptmCartDetails = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                conn.setAutoCommit(false);
                //update cart details
                ptmCartDetails = conn.prepareStatement(UPDATE_CART_DETAILS);
                ptmCartDetails.setInt(1, details.getPrice());
                ptmCartDetails.setInt(2, details.getQuantity());
                ptmCartDetails.setInt(3, details.getCartID());
                ptmCartDetails.setInt(4, details.getProductDetailsID());
                boolean checkCartDetails = ptmCartDetails.executeUpdate() > 0;

                //update cart
                ptmCart = conn.prepareStatement(UPDATE_CART);
                ptmCart.setInt(1, details.getCartID());
                ptmCart.setInt(2, details.getCartID());
                boolean checkCart = ptmCart.executeUpdate() > 0;

                //check both statements
                if (checkCart && checkCartDetails) {
                    conn.commit();
                    check = true;
                } else {
                    conn.rollback();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptmCart != null) {
                ptmCart.close();
            }
            if (ptmCartDetails != null) {
                ptmCartDetails.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public CartDetailsDTO getCartDetails(int cartID, int productDetailsID) throws SQLException {
        CartDetailsDTO details = null;
        ResultSet rs = null;
        PreparedStatement ptm = null;
        Connection conn = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_CART_DETAILS);
                ptm.setInt(1, cartID);
                ptm.setInt(2, productDetailsID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    int productID = rs.getInt("ProductID");
                    int quantity = rs.getInt("quantity");
                    int price = rs.getInt("price");
                    details = new CartDetailsDTO(cartID, productID, productDetailsID, quantity, price);
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

    public List<CartDetailsDTO> getCartItems(int cartID) throws SQLException {
        List<CartDetailsDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_CART_ITEMS);
                ptm.setInt(1, cartID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int productID = rs.getInt("ProductID");
                    int productDetailsID = rs.getInt("ProductDetailsID");
                    int price = rs.getInt("price");
                    int quantity = rs.getInt("quantity");
                    ProductDTO product = new ProductDTO(
                            productID,
                            rs.getString("productName"),
                            rs.getString("description"),
                            rs.getInt("NumberOfPurchasing"),
                            rs.getInt("pStatus"),
                            rs.getInt("BrandID")
                    );
                    ProductDetailsDTO productDetails = new ProductDetailsDTO(
                            productDetailsID,
                            productID,
                            rs.getString("color"),
                            rs.getString("size"),
                            rs.getInt("stockQuantity"),
                            rs.getInt("unitPrice"),
                            rs.getDate("importDate"),
                            rs.getString("image"),
                            rs.getInt("pdStatus")
                    );
                    CartDetailsDTO cartDetails = new CartDetailsDTO(cartID, productID, productDetailsID, quantity, price, product, productDetails);
                    list.add(cartDetails);
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

    public int getCartByCustomer(int custID) throws SQLException {
        int cartID = -1;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_CART_BY_CUSTOMER);
                ptm.setInt(1, custID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    cartID = rs.getInt("CartID");
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
        return cartID;
    }

    public int getLatestCartID() throws SQLException {
        int latestCartID = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_LATEST_CART_ID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    latestCartID = rs.getInt("CartID");
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
        return latestCartID;
    }

    public boolean removeProductFromCart(int cartID, int productDetailsID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptmRemove = null;
        PreparedStatement ptmUpdate = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                conn.setAutoCommit(false);
                ptmRemove = conn.prepareStatement(REMOVE_PRODUCT_FROM_CART);
                ptmRemove.setInt(1, cartID);
                ptmRemove.setInt(2, productDetailsID);
                boolean checkRemove = ptmRemove.executeUpdate() > 0;

                ptmUpdate = conn.prepareStatement(UPDATE_CART);
                ptmUpdate.setInt(1, cartID);
                ptmUpdate.setInt(2, cartID);
                boolean checkUpdate = ptmUpdate.executeUpdate() > 0;

                if (checkRemove && checkUpdate) {
                    conn.commit();
                    check = true;
                } else {
                    conn.rollback();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptmRemove != null) {
                ptmRemove.close();
            }
            if (ptmUpdate != null) {
                ptmUpdate.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public boolean updateCartDetailsStaus(int cartID, int productID, int status) {
        Connection conn = null;
        PreparedStatement pstm = null;
        boolean check = false;
        try {
            conn = DbUtils.getConnection();
            pstm = conn.prepareStatement(UPDATE_CART_DETAILS_STATUS);
            pstm.setInt(1, status);
            pstm.setInt(2, cartID);
            pstm.setInt(3, productID);
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
}
