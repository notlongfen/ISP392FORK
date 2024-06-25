package com.mycompany.isp392.product;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import utils.DbUtils;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductDAO {

    private static final String ADD_PRODUCT = "INSERT INTO Products(productName, description, NumberOfPurchasing, status, BrandID)VALUES(?,?,?,?,?)";
    private static final String EDIT_PRODUCT = "UPDATE Products SET productName=?, description=? WHERE productID=?";
    private static final String DELETE_PRODUCT = "UPDATE Products SET status = 0 WHERE productID=?";
    private static final String SELECT_PRODUCT = "SELECT productName, description, numberOfPurchasing, brandID FROM Products WHERE productID=? and status= 1";
    private static final String SEARCH_PRODUCT = "SELECT p.productID, p.productName, p.description, p.NumberOfPurchasing, p.status AS productStatus, p.BrandID, "
            + "pd.color, pd.size, pd.stockQuantity, pd.price, pd.importDate, pd.image, pd.status "
            + "FROM Products p LEFT JOIN ProductDetails pd ON p.productID = pd.ProductID "
            + "WHERE p.productName LIKE ? OR p.description LIKE ? OR pd.color LIKE ? OR pd.size LIKE ? OR pd.price LIKE ?";
    private static final String ADD_PRODUCT_DETAILS = "INSERT INTO ProductDetails (ProductID, color, size, stockQuantity, price, importDate, image, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String EDIT_PRODUCT_DETAILS = "UPDATE ProductDetails SET color=?, size=?, stockQuantity=?, price=?, importDate=?, image=?, status=? WHERE ProductID=? AND color = ? AND size = ?";
    private static final String DELETE_PRODUCT_DETAILS = "UPDATE ProductDetails SET status = 0 WHERE ProductID=? AND color = ? AND size= ?";
    private static final String GET_ALL_PRODUCTS = "SELECT * FROM Products WHERE status = 1";
    private static final String DELETE_PRODUCT_DETAIL = "UPDATE ProductDetails SET status = 0 WHERE ProductID=?";
    private static final String CHECK_PRODUCT = "SELECT productID FROM Products WHERE productName LIKE ?";

    public boolean addProduct(ProductDTO product) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(ADD_PRODUCT);
                ptm.setString(1, product.getProductName());
                ptm.setString(2, product.getDescription());
                ptm.setInt(3, product.getNumberOfPurchase());
                ptm.setInt(4, product.getStatus());
                ptm.setInt(5, product.getBrandID());
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

    public boolean addProductDetails(ProductDetailsDTO productDetails) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(ADD_PRODUCT_DETAILS);
                ptm.setInt(1, productDetails.getProductID());
                ptm.setString(2, productDetails.getColor());
                ptm.setString(3, productDetails.getSize());
                ptm.setInt(4, productDetails.getStockQuantity());
                ptm.setInt(5, productDetails.getPrice());
                ptm.setDate(6, productDetails.getImportDate());
                ptm.setString(7, productDetails.getImage());
                ptm.setInt(8, productDetails.getStatus());
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

    public boolean deleteProduct(int productID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DELETE_PRODUCT);
                ptm.setInt(1, productID);
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
                    product = new ProductDTO(productID, productName, description, numberOfPurchase, 1, brandID);
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

    public Map<ProductDTO, List<ProductDetailsDTO>> searchProducts(String searchText) throws SQLException {
        Map<ProductDTO, List<ProductDetailsDTO>> productMap = new HashMap<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SEARCH_PRODUCT);
                String searchPattern = "%" + searchText + "%";
                ptm.setString(1, searchPattern);
                ptm.setString(2, searchPattern);
                ptm.setString(3, searchPattern);
                ptm.setString(4, searchPattern);
                ptm.setString(5, searchPattern);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int productID = rs.getInt("productID");
                    String productName = rs.getString("productName");
                    String description = rs.getString("description");
                    int numberOfPurchase = rs.getInt("NumberOfPurchasing");
                    int status = rs.getInt("status");
                    int brandID = rs.getInt("BrandID");

                    ProductDTO product = new ProductDTO(productID, productName, description, numberOfPurchase, status, brandID);

                    String color = rs.getString("color");
                    String size = rs.getString("size");
                    int stockQuantity = rs.getInt("stockQuantity");
                    int price = rs.getInt("price");
                    Date importDate = rs.getDate("importDate");
                    String image = rs.getString("image");
                    int detailStatus = rs.getInt("status");

                    ProductDetailsDTO productDetails = new ProductDetailsDTO(productID, color, size, stockQuantity, price, importDate, image, detailStatus);

                    if (productMap.containsKey(product)) {
                        productMap.get(product).add(productDetails);
                    } else {
                        List<ProductDetailsDTO> detailsList = new ArrayList<>();
                        detailsList.add(productDetails);
                        productMap.put(product, detailsList);
                    }
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
        return productMap;
    }

    public boolean editProductDetails(ProductDetailsDTO productDetails, String color, String size) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(EDIT_PRODUCT_DETAILS);
                ptm.setString(1, productDetails.getColor());
                ptm.setString(2, productDetails.getSize());
                ptm.setInt(3, productDetails.getStockQuantity());
                ptm.setInt(4, productDetails.getPrice());
                ptm.setDate(5, productDetails.getImportDate());
                ptm.setString(6, productDetails.getImage());
                ptm.setInt(7, productDetails.getStatus());
                ptm.setInt(8, productDetails.getProductID());
                ptm.setString(9, color);
                ptm.setString(10, size);
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

    public boolean deleteProductDetail(int productID, String color, String size) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DELETE_PRODUCT_DETAILS);
                ptm.setInt(1, productID);
                ptm.setString(2, color);
                ptm.setString(3, size);

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

    public boolean deleteProductDetails(int productID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DELETE_PRODUCT_DETAIL);
                ptm.setInt(1, productID);
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

    public List<ProductDetailsDTO> getProductDetails(int productID) throws SQLException {
        List<ProductDetailsDTO> productDetailsList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        String query = "SELECT * FROM ProductDetails WHERE ProductID = ?";
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(query);
                ptm.setInt(1, productID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String color = rs.getString("color");
                    String size = rs.getString("size");
                    int stockQuantity = rs.getInt("stockQuantity");
                    int price = rs.getInt("price");
                    Date importDate = rs.getDate("importDate");
                    String image = rs.getString("image");
                    int status = rs.getInt("status");
                    productDetailsList.add(new ProductDetailsDTO(productID, color, size, stockQuantity, price, importDate, image, status));
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
        return productDetailsList;
    }

    public int getLatestProductID() throws SQLException {
        int latestProductID = -1;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        String query = "SELECT MAX(productID) AS latestProductID FROM Products";
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(query);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    latestProductID = rs.getInt("latestProductID");
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
        return latestProductID;
    }

    public List<ProductDTO> getAllProducts() throws SQLException {
        List<ProductDTO> products = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_ALL_PRODUCTS);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int productID = rs.getInt("ProductID");
                    String productName = rs.getString("productName");
                    String description = rs.getString("description");
                    int numberOfPurchasing = rs.getInt("numberOfPurchasing");
                    int status = rs.getInt("status");
                    int brandID = rs.getInt("brandID");
                    products.add(new ProductDTO(productID, productName, description, numberOfPurchasing, status, brandID));
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

    public boolean checkProductExists(String productName) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_PRODUCT);
                ptm.setString(1, productName);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    check = true;
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
        return check;
    }

}
