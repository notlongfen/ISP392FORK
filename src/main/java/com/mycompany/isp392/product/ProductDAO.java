package com.mycompany.isp392.product;

import com.mycompany.isp392.brand.BrandDTO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.DbUtils;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import com.mycompany.isp392.brand.BrandDTO;
import com.mycompany.isp392.wishlist.WishlistDTO;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductDAO {

    private static final String ADD_PRODUCT = "INSERT INTO Products(productName, description, NumberOfPurchasing, status, BrandID)VALUES(?,?,?,?,?)";
    private static final String DELETE_PRODUCT = "UPDATE Products SET status = 0 WHERE productID=?";
    private static final String SELECT_PRODUCT = "SELECT productName, description, numberOfPurchasing, brandID, status FROM Products WHERE productID=?";
    private static final String SEARCH_PRODUCT = "SELECT DISTINCT "
            + "p.productID, "
            + "p.productName, "
            + "p.description, "
            + "p.NumberOfPurchasing, "
            + "p.status, "
            + "p.BrandID, "
            + "c.CategoriesName AS parentCategoryName, "
            + "cc.CategoriesName AS childCategoryName "
            + "FROM Products p "
            + "LEFT JOIN ProductBelongtoCDCategories pbcc ON p.productID = pbcc.ProductID "
            + "LEFT JOIN Categories c ON pbcc.CDCategoryID = c.categoryID "
            + "LEFT JOIN ChildrenCategories cc ON c.categoryID = cc.ParentID "
            + "WHERE p.productName LIKE ? "
            + "OR p.description LIKE ? "
            + "OR c.CategoriesName LIKE ? "
            + "OR cc.CategoriesName LIKE ?";
    private static final String ADD_PRODUCT_DETAILS = "INSERT INTO ProductDetails (ProductID, color, size, stockQuantity, price, importDate, image, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String EDIT_PRODUCT_DETAILS = "UPDATE ProductDetails SET color=?, size=?, stockQuantity=?, price=?, importDate=?, image=?, status=? WHERE ProductDetailsID=?";
    private static final String DELETE_PRODUCT_DETAILS = "UPDATE ProductDetails SET status = 0 WHERE ProductDetailsID=?";
    private static final String GET_ALL_PRODUCTS = "SELECT * FROM Products";
    private static final String DELETE_PRODUCT_DETAIL = "UPDATE ProductDetails SET status = 0 WHERE ProductID=?";
    private static final String CHECK_PRODUCT = "SELECT productID FROM Products WHERE productName LIKE ? and productID != ?";
    private static final String CHECK_PRODUCT_NAME = "SELECT productID FROM Products WHERE productName LIKE ?";
    private static final String SEARCH_BRAND_BY_ID = "SELECT * FROM Brands WHERE brandID LIKE ?";

    private static final String SELECT_CATEGORY_PRODUCT = "SELECT * FROM Products p JOIN ProductDetails pd ON p.ProductID = pd.ProductID JOIN ProductBelongtoCategories pbc ON p.ProductID = pbc.ProductID "
            + "JOIN Categories c ON pbc.Categories = c.CategoryID WHERE c.CategoriesName = ?";

    private static final String SELECT__WISHLIST = "SELECT wd.ProductID, p.productName, pd.image, b.BrandName, pd.price "
            + "FROM Wishlists w JOIN WishlistDetails wd ON w.WishlistID = wd.WishlistID JOIN Products p ON wd.ProductID = p.ProductID JOIN ProductDetails pd ON p.ProductID = pd.ProductID JOIN  Brands b ON p.BrandID = b.BrandID "
            + "WHERE w.CustID = ? ";
    private static final String DELETE__WISHLIST = "DELETE FROM WishlistDetails "
            + "WHERE WishlistID = (SELECT WishlistID FROM Wishlists WHERE CustID = ?) "
            + "AND ProductID = ?";

//    private static final String EDIT_PRODUCT = "UPDATE Products SET productName=?, description=?, numberOfPurchasing=?, brandID=?, status = ? WHERE productID=?";
    private static final String EDIT_PRODUCT = "UPDATE Products SET productName=?, description=?, numberOfPurchasing=?, brandID=?, status = ? WHERE productID=?";

    private static final String DELETE_PRODUCT_CATEGORIES = "DELETE FROM ProductBelongtoCDCategories WHERE ProductID=?";
    private static final String ADD_PRODUCT_CATEGORY = "INSERT INTO ProductBelongtoCDCategories (ProductID, CDCategoryID) VALUES (?, ?)";
    private static final String GET_CATEGORIES_BY_PRODUCT_ID = "SELECT c.* FROM Categories c INNER JOIN ProductBelongtoCDCategories pbc ON c.categoryID = pbc.CDCategoryID WHERE pbc.ProductID = ?";
    private static final String GET_PRODUCT_DETAILS_BY_ID = "SELECT * FROM ProductDetails WHERE ProductDetailsID LIKE ? AND status = 1";

    private static final String ADD__WISHLIST = "insert into Wishlists(CustID) values (?)";
    private static final String SHOW__WISHLIST = "select * from Wishlists where CustID = ?";
    private static final String ADD__WISHLISTDETAIl = "insert into WishlistDetails (WishlistID,ProductID) values (?,?) ";
    private static final String GET_PRODUCT_INFO_TO_SENDMAIL = "SELECT p.productName, pd.color, pd.size, pd.price, pd.image FROM Orders o" +
                                                                "INNER JOIN OrderDetails od ON o.OrderID = od.OrderID" +
                                                                "INNER JOIN Products p ON od.ProductID = p.ProductID" +
                                                                "INNER JOIN ProductDetails pd ON od.ProductDetailsID = pd.ProductDetailsID" +
                                                                "WHERE o.OrderID = ?; ";
    private static final String GET_PRODUCTS_BY_PAGE = "SELECT * FROM Products ORDER BY productID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
    private static final String GET_TOTAL_PRODUCTS = "SELECT COUNT(*) AS total FROM Products";

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

    public ProductDetailsDTO getProductDetailsByID(int productDetailsID) throws SQLException {
        ProductDetailsDTO details = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_PRODUCT_DETAILS_BY_ID);
                ptm.setInt(1, productDetailsID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    int productID = rs.getInt("ProductID");
                    String color = rs.getString("color");
                    String size = rs.getString("size");
                    int stockQuantity = rs.getInt("stockQuantity");
                    int price = rs.getInt("price");
                    Date importDate = rs.getDate("importDate");
                    String image = rs.getString("image");
                    int status = rs.getInt("status");
                    details = new ProductDetailsDTO(productDetailsID, productID, color, size, stockQuantity, price,
                            importDate, image, status);
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

    public boolean editProduct(int productID, String productName, String description, int numberOfPurchasing, int brandID, String[] categoryIDs, int status) throws SQLException, Exception {
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
                ptm.setInt(5, status);
                ptm.setInt(6, productID);
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
                    int numberOfPurchase = rs.getInt("numberOfPurchasing");
                    int brandID = rs.getInt("brandID");
                    int status = rs.getInt("status");
                    product = new ProductDTO(productID, productName, description, numberOfPurchase, status, brandID);
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
                ptm.setInt(1, brandID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String Name = rs.getString("brandName");
                    int status = rs.getInt("status");
                    String image = rs.getString("image");
                    list.add(new BrandDTO(brandID, Name, image, status));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean editProductDetails(int productDetailID, int stockQuantity, int price, Date importDate, String image,
            int detailStatus) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                String sql = "UPDATE ProductDetails SET stockQuantity=?, price=?, importDate=?, image=?, status=? WHERE productDetailsID=?";
                ptm = conn.prepareStatement(sql);
                ptm.setInt(1, stockQuantity);
                ptm.setInt(2, price);
                ptm.setDate(3, importDate);
                ptm.setString(4, image);
                ptm.setInt(5, detailStatus);
                ptm.setInt(6, productDetailID);
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

    public boolean deleteProductDetail(int productDetailID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DELETE_PRODUCT_DETAILS);
                ptm.setInt(1, productDetailID);
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
                    // No product details found for the given productID, so no need to delete
                    // anything
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
                    int productDetailID = rs.getInt("ProductDetailsID");
                    String color = rs.getString("color");
                    String size = rs.getString("size");
                    int stockQuantity = rs.getInt("stockQuantity");
                    int price = rs.getInt("price");
                    Date importDate = rs.getDate("importDate");
                    String image = rs.getString("image");
                    int status = rs.getInt("status");
                    productDetailsList.add(new ProductDetailsDTO(productDetailID, productID, color, size, stockQuantity,
                            price, importDate, image, status));
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
                ptm.setString(3, searchPattern);
                ptm.setString(4, searchPattern);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int productID = rs.getInt("productID");
                    String productName = rs.getString("productName");
                    String description = rs.getString("description");
                    int numberOfPurchasing = rs.getInt("NumberOfPurchasing");
                    int status = rs.getInt("status");
                    int brandID = rs.getInt("BrandID");

                    ProductDTO product = new ProductDTO(productID, productName, description, numberOfPurchasing, status,
                            brandID);
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
                ptm = conn.prepareStatement(SELECT_CATEGORY_PRODUCT);

                ptm.setString(1, gender);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int productID = rs.getInt("ProductID");
                    String name = rs.getString("productName");
                    int price = rs.getInt("price");
                    String images = rs.getString("image");
                    products.add(new ProductDetailsDTO(productID, name, images, price, ""));
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

    public ProductDetailsDTO selectProductDetailByID(int productDetailID) throws SQLException {
        ProductDetailsDTO productDetail = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        String query = "SELECT * FROM ProductDetails WHERE ProductDetailsID = ?";
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(query);
                ptm.setInt(1, productDetailID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    int productID = rs.getInt("ProductID");
                    String color = rs.getString("color");
                    String size = rs.getString("size");
                    int stockQuantity = rs.getInt("stockQuantity");
                    int price = rs.getInt("price");
                    Date importDate = rs.getDate("importDate");
                    String image = rs.getString("image");
                    int status = rs.getInt("status");
                    productDetail = new ProductDetailsDTO(productID, color, size, stockQuantity, price, importDate,
                            image, status);
                    productDetail.setProductDetailsID(productDetailID);
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
        return productDetail;
    }

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

    public boolean checkDuplicateProductDetail(int productID, String color, String size) throws SQLException {
        boolean isDuplicate = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        String query = "SELECT 1 FROM ProductDetails WHERE ProductID = ? AND color = ? AND size = ?";
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(query);
                ptm.setInt(1, productID);
                ptm.setString(2, color);
                ptm.setString(3, size);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    isDuplicate = true;
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
        return isDuplicate;
    }

    public Map<Integer, Date> getLatestImportDates() throws SQLException {
        Map<Integer, Date> latestImportDates = new HashMap<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        String sql = "SELECT productID, MAX(importDate) AS latestImportDate FROM ProductDetails GROUP BY productID";

        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(sql);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int productID = rs.getInt("productID");
                    Date latestImportDate = rs.getDate("latestImportDate");
                    latestImportDates.put(productID, latestImportDate);
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
        return latestImportDates;
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

    public boolean updateQuantittyAfterCheckout(int productID, int quantity) throws SQLException {
        boolean result = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                String query = "UPDATE ProductDetails SET stockQuantity = stockQuantity - ? WHERE ProductID = ?";
                stm = conn.prepareStatement(query);
                stm.setInt(1, quantity);
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

    public boolean addToWishlistDetail(int wishlistID, int productID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(ADD__WISHLISTDETAIl);
                ptm.setInt(1, wishlistID);
                ptm.setInt(2, productID);
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
    public List<ProductDetailsDTO> getAllProductDetails() throws SQLException {
        String sql = "SELECT * FROM ProductDetails";
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        List<ProductDetailsDTO> productDetailsList = new ArrayList<>();
        try {
            conn = DbUtils.getConnection();
            ptm = conn.prepareStatement(sql);
            rs = ptm.executeQuery();
            while (rs.next()) {
                int productDetailsID = rs.getInt("ProductDetailsID");
                int productID = rs.getInt("ProductID");
                String color = rs.getString("color");
                String size = rs.getString("size");
                int stockQuantity = rs.getInt("stockQuantity");
                int price = rs.getInt("price");
                Date importDate = rs.getDate("importDate");
                String image = rs.getString("image");
                int status = rs.getInt("status");
                ProductDetailsDTO productDetails = new ProductDetailsDTO(productDetailsID, productID, color, size, stockQuantity, price, importDate, image, status);

                productDetailsList.add(productDetails);
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

    public List<ProductDTO> getAllProductPags(int pageNumber, int rowsPerPage) throws SQLException {
        List<ProductDTO> products = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                // Calculate the offset
                int offset = (pageNumber - 1) * rowsPerPage;

                // Prepare the SQL query with pagination
                String sql = "SELECT * FROM ("
                        + "  SELECT *, ROW_NUMBER() OVER (ORDER BY ProductID) AS RowNumber"
                        + "  FROM Products"
                        + ") AS OrderedProducts"
                        + " WHERE RowNumber BETWEEN ? AND ?";

                ptm = conn.prepareStatement(sql);
                ptm.setInt(1, offset + 1); // Start row number
                ptm.setInt(2, offset + rowsPerPage + offset); // End row number

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

    public int getTotalProductCount() throws SQLException {
        int count = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT COUNT(*) AS total FROM Products";
                ptm = conn.prepareStatement(sql);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    count = rs.getInt("total");
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
        return count;
    }

    public ProductDTO getProductByID(int productID) throws SQLException {
        ProductDTO product = null;
        String sql = "SELECT * FROM Products WHERE ProductID = ?";
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DbUtils.getConnection(); // Lấy kết nối đến CSDL
            if (conn != null) {
                ptm = conn.prepareStatement(sql);
                ptm.setInt(1, productID);
                rs = ptm.executeQuery();

                if (rs.next()) {
                    // Lấy thông tin chi tiết sản phẩm từ ResultSet
                    String productName = rs.getString("productName");
                    String description = rs.getString("description");
                    int numberOfPurchasing = rs.getInt("NumberOfPurchasing");
                    int status = rs.getInt("status");
                    int brandID = rs.getInt("BrandID");

                    product = new ProductDTO(productID, productName, description, numberOfPurchasing, status, brandID);
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

    public List<ProductDetailsDTO> getProductDetailsByProductID(int productID) throws SQLException {
        List<ProductDetailsDTO> productDetailsList = new ArrayList<>();
        String sql = "SELECT * FROM ProductDetails WHERE ProductID = ?";
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DbUtils.getConnection(); // Đảm bảo kết nối đến cơ sở dữ liệu
            ptm = conn.prepareStatement(sql);
            ptm.setInt(1, productID);
            rs = ptm.executeQuery();
            while (rs.next()) {
                int productDetailsID = rs.getInt("ProductDetailsID");
                String color = rs.getString("color");
                String size = rs.getString("size");
                int stockQuantity = rs.getInt("stockQuantity");
                int price = rs.getInt("price");
                Date importDate = rs.getDate("importDate");
                String image = rs.getString("image");
                int status = rs.getInt("status");

                // Tạo đối tượng ProductDetailsDTO từ dữ liệu lấy được
                ProductDetailsDTO productDetail = new ProductDetailsDTO(productDetailsID, productID, color, size, stockQuantity, price, importDate, image, status);
                productDetailsList.add(productDetail);
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

    public List<String> getSizesByProductIDandColor(int productID, String color) throws SQLException {
        List<String> sizes = new ArrayList<>();
        String query = "SELECT DISTINCT Size FROM ProductDetails WHERE ProductID = ? AND Color = ?";
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(query);
                ptm.setInt(1, productID);
                ptm.setString(2, color);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String size = rs.getString("Size");
                    sizes.add(size); // Add size to the list
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
        return sizes;
    }

    public List<ProductDTO> getProductsByPriceRange(String priceRange) throws SQLException {
        List<ProductDTO> productList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        String sql = "SELECT p.ProductID, p.ProductName, p.BrandID, p.Description, p.NumberOfPurchasing, p.Status "
                + "FROM Products p "
                + "JOIN ProductDetails pd ON p.ProductID = pd.ProductID "
                + "WHERE ";

        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                if ("0-100".equals(priceRange)) {
                    sql += "pd.Price < 100";
                } else if ("100-200".equals(priceRange)) {
                    sql += "pd.Price BETWEEN 100 AND 200";
                } else if ("200plus".equals(priceRange)) {
                    sql += "pd.Price > 200";
                } else {
                    throw new IllegalArgumentException("Invalid price range: " + priceRange);
                }
                ptm = conn.prepareStatement(sql);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int productID = rs.getInt("ProductID");
                    String productName = rs.getString("ProductName");
                    int brandID = rs.getInt("BrandID");
                    String description = rs.getString("Description");
                    int numberOfPurchasing = rs.getInt("NumberOfPurchasing");
                    int status = rs.getInt("Status");
                    ProductDTO product = new ProductDTO(productID, productName, description, numberOfPurchasing, status, brandID);
                    productList.add(product);
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
        return productList;
    }
     // Method to get products by page
    public List<ProductDTO> getProductsByPage(int page, int entriesPerPage) throws SQLException {
        List<ProductDTO> productList = new ArrayList<>();
        try (Connection conn = DbUtils.getConnection();
             PreparedStatement pst = conn.prepareStatement(GET_PRODUCTS_BY_PAGE)) {
            int offset = (page - 1) * entriesPerPage;
            pst.setInt(1, offset);
            pst.setInt(2, entriesPerPage);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    int productID = rs.getInt("productID");
                    String productName = rs.getString("productName");
                    String description = rs.getString("description");
                    int brandID = rs.getInt("brandID");
                    int status = rs.getInt("status");
                    int numberOfPurchase = rs.getInt("numberOfPurchasing");
                    productList.add(new ProductDTO(productID, productName, description, brandID, status, numberOfPurchase));
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return productList;
    }

    public List<ProductDetailsDTO> getProductDetailsByProductList(List<ProductDTO> products) throws SQLException {
        List<ProductDetailsDTO> productDetails = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        if (products == null || products.isEmpty()) {
            return productDetails;
        }

        StringBuilder productIds = new StringBuilder();
        for (ProductDTO product : products) {
            productIds.append(product.getProductID()).append(",");
        }
        // Remove the last comma
        productIds.setLength(productIds.length() - 1);

        String sql = "SELECT * FROM ProductDetails WHERE ProductID IN (" + productIds.toString() + ")";

        try {
            conn = DbUtils.getConnection();
            ptm = conn.prepareStatement(sql);
            rs = ptm.executeQuery();
            while (rs.next()) {
                ProductDetailsDTO detail = new ProductDetailsDTO();
                detail.setProductDetailsID(rs.getInt("ProductDetailsID"));
                detail.setProductID(rs.getInt("ProductID"));
                detail.setColor(rs.getString("color"));
                detail.setSize(rs.getString("size"));
                detail.setStockQuantity(rs.getInt("stockQuantity"));
                detail.setPrice(rs.getInt("price"));
                detail.setImportDate(rs.getDate("importDate"));
                detail.setImage(rs.getString("image"));
                detail.setStatus(rs.getInt("status"));
                // Set other fields as needed
                productDetails.add(detail);
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
        return productDetails;
    }

    public List<ProductDTO> getProductByBrand(int brandID) throws SQLException {
        List<ProductDTO> productList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM Products WHERE BrandID = ?";
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(sql);
                ptm.setInt(1, brandID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int productID = rs.getInt("ProductID");
                    String productName = rs.getString("ProductName");
                    String description = rs.getString("Description");
                    int numberOfPurchasing = rs.getInt("NumberOfPurchasing");
                    int status = rs.getInt("Status");

                    ProductDTO product = new ProductDTO(productID, productName, description, numberOfPurchasing, status, brandID);
                    productList.add(product);
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
        return productList;
    }

    public List<ProductDetailsDTO> getProductDetailsByBrand(int brandID) throws SQLException {
        List<ProductDetailsDTO> productDetailsList = new ArrayList<>();
        String sql = "SELECT pd.* FROM ProductDetails pd INNER JOIN Products p ON pd.ProductID = p.ProductID WHERE p.BrandID = ?";
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(sql);
                ptm.setInt(1, brandID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    ProductDetailsDTO detail = new ProductDetailsDTO();
                    detail.setProductDetailsID(rs.getInt("ProductDetailsID"));
                    detail.setProductID(rs.getInt("ProductID"));
                    detail.setColor(rs.getString("color"));
                    detail.setSize(rs.getString("size"));
                    detail.setStockQuantity(rs.getInt("stockQuantity"));
                    detail.setPrice(rs.getInt("price"));
                    detail.setImportDate(rs.getDate("importDate"));
                    detail.setImage(rs.getString("image"));
                    detail.setStatus(rs.getInt("status"));

                    productDetailsList.add(detail);
                }
            }
        } catch (Exception e) {
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

    public static void main(String[] args) throws SQLException {
        ProductDAO dao = new ProductDAO();
//        List<ProductDTO> allProducts = dao.getAllProductPags(1, 10);
//        for (ProductDTO allProduct : allProducts) {
//            System.out.println(allProduct);
//        }

//        int total = dao.getTotalProductCount();
//        System.out.println("Total product is: " + total);
//
//        List<ProductDetailsDTO> lists = dao.getProductDetailsByProductID(3);
//        for (ProductDetailsDTO list : lists) {
//            System.out.println(list);
//        }
        String color = "Black";
        int productID = 3;
//       
        List<ProductDetailsDTO> products = dao.getProductDetailsByBrand(3);
        for (ProductDTO product : products) {
            System.out.println(product);
        }

    }

    
    public List<ProductDetailsDTO> getProductInfoToSendMail(int orderID) throws SQLException {
        List<ProductDetailsDTO> products = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_PRODUCT_INFO_TO_SENDMAIL);
                ptm.setInt(1, orderID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String productName = rs.getString("productName");
                    String color = rs.getString("color");
                    String size = rs.getString("size");
                    int price = rs.getInt("price");
                    products.add(new ProductDetailsDTO(productName,color,size,price));
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
    // Method to get total number of products
    public int getTotalProducts() throws SQLException {
        int total = 0;
        try (Connection conn = DbUtils.getConnection();
             PreparedStatement pst = conn.prepareStatement(GET_TOTAL_PRODUCTS);
             ResultSet rs = pst.executeQuery()) {
            if (rs.next()) {
                total = rs.getInt("total");
            }
        } catch (Exception ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return total;
    }
       public List<ProductDTO> searchProductsByPage(String searchText, int page, int entriesPerPage) throws SQLException {
        List<ProductDTO> productList = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int start = (page - 1) * entriesPerPage;
        try {
            con = DbUtils.getConnection();
            if (con != null) {
                String sql = "SELECT * FROM Products WHERE productName LIKE ? ORDER BY productID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
                ps = con.prepareStatement(sql);
                ps.setString(1, "%" + searchText + "%");
                ps.setInt(2, start);
                ps.setInt(3, entriesPerPage);
                rs = ps.executeQuery();
                while (rs.next()) {
                    int productID = rs.getInt("productID");
                    String productName = rs.getString("productName");
                    int brandID = rs.getInt("brandID");
                    String description = rs.getString("description");
                    int status = rs.getInt("status");
                    int numberOfPurchase = rs.getInt("numberOfPurchasing");
                    productList.add(new ProductDTO(productID, productName, description, numberOfPurchase, status, brandID));
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbUtils.closeConnection(con, ps, rs);
        }
        return productList;
    }

    public int getTotalSearchProducts(String searchText) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int totalProducts = 0;
        try {
            con = DbUtils.getConnection();
            if (con != null) {
                String sql = "SELECT COUNT(*) FROM Products WHERE productName LIKE ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, "%" + searchText + "%");
                rs = ps.executeQuery();
                if (rs.next()) {
                    totalProducts = rs.getInt(1);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbUtils.closeConnection(con, ps, rs);
        }
        return totalProducts;
    }
}
