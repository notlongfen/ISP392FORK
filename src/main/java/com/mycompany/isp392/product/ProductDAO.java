package com.mycompany.isp392.product;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import utils.DbUtils;
import java.sql.ResultSet;
import java.util.List;


import com.mycompany.isp392.brand.BrandDTO;

public class ProductDAO {

    private static final String ADD_PRODUCT = "INSERT INTO Products(productName, description, NumberOfPurchasing, status, BrandID)VALUES(?,?,?,?,?)";
    private static final String DELETE_PRODUCT = "UPDATE Products SET status = 0 WHERE productID=?";
    private static final String SELECT_PRODUCT = "SELECT productName, description, numberOfPurchasing, brandID FROM Products WHERE productID=?";
    private static final String SEARCH_PRODUCT = "SELECT productID, productName, description, NumberOfPurchasing, status, BrandID "
            + "FROM Products "
            + "WHERE productName LIKE ? OR description LIKE ?";
    private static final String ADD_PRODUCT_DETAILS = "INSERT INTO ProductDetails (ProductID, color, size, stockQuantity, price, importDate, image, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String EDIT_PRODUCT_DETAILS = "UPDATE ProductDetails SET color=?, size=?, stockQuantity=?, price=?, importDate=?, image=?, status=? WHERE ProductID=? AND color = ? AND size = ?";
    private static final String DELETE_PRODUCT_DETAILS = "UPDATE ProductDetails SET status = 0 WHERE ProductID=? AND color = ? AND size= ?";
    private static final String GET_ALL_PRODUCTS = "SELECT * FROM Products";
    private static final String DELETE_PRODUCT_DETAIL = "UPDATE ProductDetails SET status = 0 WHERE ProductID=?";
    private static final String CHECK_PRODUCT = "SELECT productID FROM Products WHERE productName LIKE ? and productID != ?";
       private static final String CHECK_PRODUCT_NAME = "SELECT productID FROM Products WHERE productName LIKE ?";
    private static final String SEARCH_BRAND_BY_ID = "SELECT * FROM Brands WHERE brandID LIKE ?";
    private static final String SELECT__WISHLIST = "SELECT wd.ProductID, p.productName, pd.image, b.BrandName, pd.price "
            + "FROM Wishlists w JOIN WishlistDetails wd ON w.WishlistID = wd.WishlistID JOIN Products p ON wd.ProductID = p.ProductID JOIN ProductDetails pd ON p.ProductID = pd.ProductID JOIN  Brands b ON p.BrandID = b.BrandID "
            + "WHERE w.CustID = ? ";
    private static final String DELETE__WISHLIST = "DELETE FROM WishlistDetails "
            + "WHERE WishlistID = (SELECT WishlistID FROM Wishlists WHERE CustID = ?) "
            + "AND ProductID = ?";
   private static final String EDIT_PRODUCT = "UPDATE Products SET productName=?, description=?, numberOfPurchasing=?, brandID=? WHERE productID=?";
    private static final String DELETE_PRODUCT_CATEGORIES = "DELETE FROM ProductBelongtoCDCategories WHERE ProductID=?";
    private static final String ADD_PRODUCT_CATEGORY = "INSERT INTO ProductBelongtoCDCategories (ProductID, CDCategoryID) VALUES (?, ?)";
    private static final String GET_CATEGORIES_BY_PRODUCT_ID = "SELECT c.* FROM Categories c INNER JOIN ProductBelongtoCDCategories pbc ON c.categoryID = pbc.CDCategoryID WHERE pbc.ProductID = ?";

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

     public boolean editProduct(int productID, String productName, String description, int numberOfPurchasing, int brandID, String[] categoryIDs) throws SQLException, Exception {
        Connection conn = null;
        PreparedStatement ptm = null;
        boolean check = false;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                conn.setAutoCommit(false);
                ptm = conn.prepareStatement(EDIT_PRODUCT);
                ptm.setString(1, productName);
                ptm.setString(2, description);
                ptm.setInt(3, numberOfPurchasing);
                ptm.setInt(4, brandID);
                ptm.setInt(5, productID);
                check = ptm.executeUpdate() > 0;

                if (check) {
                    ptm = conn.prepareStatement(DELETE_PRODUCT_CATEGORIES);
                    ptm.setInt(1, productID);
                    ptm.executeUpdate();

                    if (categoryIDs != null) {
                        ptm = conn.prepareStatement(ADD_PRODUCT_CATEGORY);
                        for (String categoryID : categoryIDs) {
                            ptm.setInt(1, productID);
                            ptm.setInt(2, Integer.parseInt(categoryID));
                            ptm.executeUpdate();
                        }
                    }
                }
                conn.commit();
            }
        } catch (Exception e) {
            if (conn != null) {
                conn.rollback();
            }
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
                    int numberOfPurchase = rs.getInt("numberOfPurchasing");
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

    public List<BrandDTO> searchBrandByBrandID(int brandID) {
        List<BrandDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SEARCH_BRAND_BY_ID);
                // FIXME: Should I add the "%" here at the end of the brandID?
                ptm.setInt(1, brandID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String Name = rs.getString("brandName");
                    int status = rs.getInt("status");

                    list.add(new BrandDTO(brandID, Name, status));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
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
        ResultSet rs = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                // First, check if there are any product details for the given productID
                String checkQuery = "SELECT 1 FROM ProductDetails WHERE ProductID = ?";
                ptm = conn.prepareStatement(checkQuery);
                ptm.setInt(1, productID);
                rs = ptm.executeQuery();

                if (!rs.next()) {
                    // No product details found for the given productID, so no need to delete anything
                    return true; // Consider this a successful operation
                }

                // If there are product details, proceed to delete them
                ptm = conn.prepareStatement(DELETE_PRODUCT_DETAIL);
                ptm.setInt(1, productID);
                check = ptm.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            check = false; // Set check to false if an exception occurs
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

    public boolean checkProductExists(String productName, int productID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_PRODUCT);
                ptm.setString(1, productName);
                 ptm.setInt(2, productID);
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
     public boolean checkProductExistsOnlyName(String productName) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_PRODUCT_NAME);
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

    public List<ProductDTO> searchProducts(String searchText) throws SQLException {
        List<ProductDTO> products = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        String searchPattern = "%" + searchText + "%";

        String searchQuery = SEARCH_PRODUCT;

        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(searchQuery);
                ptm.setString(1, searchPattern);
                ptm.setString(2, searchPattern);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int productID = rs.getInt("productID");
                    String productName = rs.getString("productName");
                    String description = rs.getString("description");
                    int numberOfPurchasing = rs.getInt("NumberOfPurchasing");
                    int status = rs.getInt("status");
                    int brandID = rs.getInt("BrandID");

                    ProductDTO product = new ProductDTO(productID, productName, description, numberOfPurchasing, status, brandID);
                    products.add(product);
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

    public List<ProductDetailsDTO> CateProducts(String gender) throws SQLException {
        List<ProductDetailsDTO> products = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                //ptm = conn.prepareStatement(SELECT_CATEGORY_PRODUCT);
                ptm.setString(1, gender);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String name = rs.getString("productName");
                    int price = rs.getInt("price");
                    String images = rs.getString("image");
                    products.add(new ProductDetailsDTO(name, images, price));
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
                    String name = rs.getString("productName");
                    int price = rs.getInt("price");
                    String images = rs.getString("image");
                    String brandName = rs.getString("BrandName");
                    products.add(new ProductDetailsDTO(productID, name, images, price, brandName));
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

    public boolean deleteWishlist(int cusID, int productID) throws SQLException {
        boolean result = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                stm = conn.prepareStatement(DELETE__WISHLIST);
                stm.setInt(1, cusID);
                stm.setInt(2, productID);
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
}
