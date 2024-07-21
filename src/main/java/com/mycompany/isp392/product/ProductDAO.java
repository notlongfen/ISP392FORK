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
import com.mycompany.isp392.category.CategoryDTO;
import com.mycompany.isp392.wishlist.WishlistDTO;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Locale;
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

//    private static final String EDIT_PRODUCT = "UPDATE Products SET productName=?, description=?, numberOfPurchasing=?, brandID=?, status = ? WHERE productID=?";
    private static final String EDIT_PRODUCT = "UPDATE Products SET productName=?, description=?, numberOfPurchasing=?, brandID=?, status = ? WHERE productID=?";
    private static final String DELETE_PRODUCT_CATEGORIES = "DELETE FROM ProductBelongtoCDCategories WHERE ProductID=?";
    private static final String ADD_PRODUCT_CATEGORY = "INSERT INTO ProductBelongtoCDCategories (ProductID, CDCategoryID) VALUES (?, ?)";
    private static final String GET_CATEGORIES_BY_PRODUCT_ID = "SELECT c.* FROM Categories c INNER JOIN ProductBelongtoCDCategories pbc ON c.categoryID = pbc.CDCategoryID WHERE pbc.ProductID = ?";
    private static final String GET_PRODUCT_DETAILS_BY_ID = "SELECT * FROM ProductDetails WHERE ProductDetailsID LIKE ? AND status = 1";
    private static final String GET_PRODUCT_INFO_TO_SENDMAIL = "SELECT p.productName, pd.color, pd.size, pd.price, pd.image FROM Orders o"
            + "INNER JOIN OrderDetails od ON o.OrderID = od.OrderID"
            + "INNER JOIN Products p ON od.ProductID = p.ProductID"
            + "INNER JOIN ProductDetails pd ON od.ProductDetailsID = pd.ProductDetailsID"
            + "WHERE o.OrderID = ?; ";
    private static final String GET_PRODUCTS_BY_PAGE = "SELECT * FROM Products ORDER BY productID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
    private static final String GET_TOTAL_PRODUCTS = "SELECT COUNT(*) AS total FROM Products";
    private static final String GET_PRODUCT_INFO_BY_CARTID = "SELECT * FROM Carts c"
            + "INNER JOIN CartDetails cd ON c.CartID = cd.CartID"
            + "INNER JOIN Products p ON cd.ProductID = p.ProductID"
            + "INNER JOIN ProductDetails pd ON cd.ProductDetailsID = pd.ProductDetailsID"
            + "WHERE c.CartID = ?; ";
    private static final String UPDATE_PRODUCT_NUMBER_OF_PURCHASING = "UPDATE Products SET numberOfPurchasing = numberOfPurchasing + ? WHERE productID = ?";
    private static final String GET_PRODUCT_DETAILS_BY_COLOR = "SELECT * FROM ProductDetails WHERE ProductID = ? AND Color = ? AND status = 1";
    private static final String GET_PRODUCT_DETAILS_ID = "SELECT * FROM ProductDetails WHERE ProductID = ? AND price = ? AND size LIKE ? AND Color LIKE ? AND status = 1";
    private static final String GET_PRODUCT_DETAILS_BY_PRODUCT_ID_COLOR_SIZE = "SELECT * FROM ProductDetails WHERE ProductID = ? AND color = ? AND size = ? AND status = 1";
    private static final String SEARCH_PRODUCT_LIST_BY_PRODUCTNAME = "SELECT * FROM Products WHERE productName LIKE ? AND status = 1";

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
                    int numberOfPurchasing = rs.getInt("NumberOfPurchasing");
                    int status = rs.getInt("status");

                    int brandID = rs.getInt("BrandID");
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

    public int getTotalQuantityByProductID(int productID) throws SQLException {
        int totalQuantity = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        String query = "SELECT SUM(stockQuantity) as totalQuantity FROM ProductDetails WHERE ProductID = ? AND status = 1";
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(query);
                ptm.setInt(1, productID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    totalQuantity = rs.getInt("totalQuantity");
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
        return totalQuantity;
    }

    public boolean updateNumberOfPurchasing(int productID, int totalQuantity) throws SQLException {
        boolean updated = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        String query = "UPDATE Products SET numberOfPurchasing = ? WHERE ProductID = ?";
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(query);
                ptm.setInt(1, totalQuantity);
                ptm.setInt(2, productID);
                updated = ptm.executeUpdate() > 0;
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
        return updated;
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
        try (Connection conn = DbUtils.getConnection(); PreparedStatement pst = conn.prepareStatement(GET_PRODUCTS_BY_PAGE)) {
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
                    productList.add(new ProductDTO(productID, productName, description, numberOfPurchase, status, brandID));
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
                    products.add(new ProductDetailsDTO(productName, color, size, price));
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
        try (Connection conn = DbUtils.getConnection(); PreparedStatement pst = conn.prepareStatement(GET_TOTAL_PRODUCTS); ResultSet rs = pst.executeQuery()) {
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

    public List<ProductDetailsDTO> getProductByCartID(int CartID) throws SQLException {
        List<ProductDetailsDTO> products = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_PRODUCT_INFO_BY_CARTID);
                ptm.setInt(1, CartID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String productName = rs.getString("productName");
                    String color = rs.getString("color");
                    int size = rs.getInt("size");
                    products.add(new ProductDetailsDTO(productName, color, size));
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

    public boolean updateProductNumberOfPurchasedItems(int productID, int quantity) throws SQLException {
        boolean result = false;
        Connection conn = null;
        PreparedStatement pstm = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                pstm = conn.prepareStatement(UPDATE_PRODUCT_NUMBER_OF_PURCHASING);
                pstm.setInt(1, quantity);
                pstm.setInt(2, productID);
                result = pstm.executeUpdate() > 0;
            }
        } catch (Exception ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DbUtils.closeConnection(conn, pstm);
        }
        return result;
    }

    public List<ProductDTO> getFilteredProducts(String[] brandFilters, String[] priceFilters, String[] categoryFilters, int page, int recordsPerPage) throws SQLException {
        List<ProductDTO> productList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        StringBuilder sql = new StringBuilder("SELECT DISTINCT p.* FROM Products p JOIN ProductDetails pd ON p.ProductID = pd.ProductID");

        if (categoryFilters != null && categoryFilters.length > 0) {
            sql.append(" JOIN ProductBelongtoCDCategories pbcc ON p.ProductID = pbcc.ProductID");
            sql.append(" JOIN ChildrenCategories cc ON pbcc.CDCategoryID = cc.CDCategoryID");
        }

        sql.append(" WHERE p.status = 1");

        if (brandFilters != null && brandFilters.length > 0) {
            sql.append(" AND p.BrandID IN (").append(String.join(",", brandFilters)).append(")");
        }

        if (priceFilters != null && priceFilters.length > 0) {
            sql.append(" AND (");
            for (int i = 0; i < priceFilters.length; i++) {
                if (i > 0) {
                    sql.append(" OR ");
                }
                switch (priceFilters[i]) {
                    case "0-2000000":
                        sql.append("pd.price < 2000000");
                        break;
                    case "2000000-5000000":
                        sql.append("pd.price BETWEEN 2000000 AND 5000000");
                        break;
                    case "5000000-10000000":
                        sql.append("pd.price BETWEEN 5000000 AND 10000000");
                        break;
                    case "10000000plus":
                        sql.append("pd.price > 10000000");
                        break;
                }
            }
            sql.append(")");
        }

        if (categoryFilters != null && categoryFilters.length > 0) {
            sql.append(" AND cc.ParentID IN (");
            for (int i = 0; i < categoryFilters.length; i++) {
                if (i > 0) {
                    sql.append(",");
                }
                sql.append(categoryFilters[i]);
            }
            sql.append(")");
        }

        sql.append(" ORDER BY p.ProductID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY");

        try {
            conn = DbUtils.getConnection();
            ptm = conn.prepareStatement(sql.toString());
            ptm.setInt(1, (page - 1) * recordsPerPage);
            ptm.setInt(2, recordsPerPage);
            rs = ptm.executeQuery();
            while (rs.next()) {
                int productID = rs.getInt("ProductID");
                String productName = rs.getString("ProductName");
                String description = rs.getString("Description");
                int numberOfPurchasing = rs.getInt("NumberOfPurchasing");
                int status = rs.getInt("Status");
                int brandID = rs.getInt("BrandID");

                ProductDTO product = new ProductDTO(productID, productName, description, numberOfPurchasing, status, brandID);
                productList.add(product);
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

    public List<ProductDTO> getFilteredProducts(String[] brandFilters, String[] priceFilters, String[] categoryFilters, String searchQuery, int page, int recordsPerPage) throws SQLException {
        List<ProductDTO> productList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        StringBuilder sql = new StringBuilder("SELECT DISTINCT p.* FROM Products p JOIN ProductDetails pd ON p.ProductID = pd.ProductID");

        if (categoryFilters != null && categoryFilters.length > 0) {
            sql.append(" JOIN ProductBelongtoCDCategories pbcc ON p.ProductID = pbcc.ProductID");
            sql.append(" JOIN ChildrenCategories cc ON pbcc.CDCategoryID = cc.CDCategoryID");
        }

        sql.append(" WHERE p.status = 1");

        if (brandFilters != null && brandFilters.length > 0) {
            sql.append(" AND p.BrandID IN (").append(String.join(",", brandFilters)).append(")");
        }

        if (priceFilters != null && priceFilters.length > 0) {
            sql.append(" AND (");
            for (int i = 0; i < priceFilters.length; i++) {
                if (i > 0) {
                    sql.append(" OR ");
                }
                switch (priceFilters[i]) {
                    case "0-2000000":
                        sql.append("pd.price < 2000000");
                        break;
                    case "2000000-5000000":
                        sql.append("pd.price BETWEEN 2000000 AND 5000000");
                        break;
                    case "5000000-10000000":
                        sql.append("pd.price BETWEEN 5000000 AND 10000000");
                        break;
                    case "10000000plus":
                        sql.append("pd.price > 10000000");
                        break;
                }
            }
            sql.append(")");
        }

        if (categoryFilters != null && categoryFilters.length > 0) {
            sql.append(" AND cc.ParentID IN (");
            for (int i = 0; i < categoryFilters.length; i++) {
                if (i > 0) {
                    sql.append(",");
                }
                sql.append(categoryFilters[i]);
            }
            sql.append(")");
        }

        if (searchQuery != null && !searchQuery.isEmpty()) {
            sql.append(" AND p.productName LIKE ?");
        }

        sql.append(" ORDER BY p.ProductID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY");

        try {
            conn = DbUtils.getConnection();
            ptm = conn.prepareStatement(sql.toString());

            int paramIndex = 1;
            if (searchQuery != null && !searchQuery.isEmpty()) {
                ptm.setString(paramIndex++, "%" + searchQuery + "%");
            }
            ptm.setInt(paramIndex++, (page - 1) * recordsPerPage);
            ptm.setInt(paramIndex, recordsPerPage);

            rs = ptm.executeQuery();
            while (rs.next()) {
                int productID = rs.getInt("ProductID");
                String productName = rs.getString("ProductName");
                String description = rs.getString("Description");
                int numberOfPurchasing = rs.getInt("NumberOfPurchasing");
                int status = rs.getInt("Status");
                int brandID = rs.getInt("BrandID");

                ProductDTO product = new ProductDTO(productID, productName, description, numberOfPurchasing, status, brandID);
                productList.add(product);
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

    public int getTotalFilteredProductsCount(String[] brandFilters, String[] priceFilters, String[] categoryFilters) throws SQLException {
        int totalProducts = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        StringBuilder sql = new StringBuilder("SELECT COUNT(DISTINCT p.ProductID) FROM Products p JOIN ProductDetails pd ON p.ProductID = pd.ProductID");

        if (categoryFilters != null && categoryFilters.length > 0) {
            sql.append(" JOIN ProductBelongtoCDCategories pbcc ON p.ProductID = pbcc.ProductID");
            sql.append(" JOIN ChildrenCategories cc ON pbcc.CDCategoryID = cc.CDCategoryID");
        }

        sql.append(" WHERE p.status = 1");

        if (brandFilters != null && brandFilters.length > 0) {
            sql.append(" AND p.BrandID IN (").append(String.join(",", brandFilters)).append(")");
        }

        if (priceFilters != null && priceFilters.length > 0) {
            sql.append(" AND (");
            for (int i = 0; i < priceFilters.length; i++) {
                if (i > 0) {
                    sql.append(" OR ");
                }
                switch (priceFilters[i]) {
                    case "0-2000000":
                        sql.append("pd.price < 2000000");
                        break;
                    case "2000000-5000000":
                        sql.append("pd.price BETWEEN 2000000 AND 5000000");
                        break;
                    case "5000000-10000000":
                        sql.append("pd.price BETWEEN 5000000 AND 10000000");
                        break;
                    case "10000000plus":
                        sql.append("pd.price > 10000000");
                        break;
                }
            }
            sql.append(")");
        }

        if (categoryFilters != null && categoryFilters.length > 0) {
            sql.append(" AND cc.ParentID IN (");
            for (int i = 0; i < categoryFilters.length; i++) {
                if (i > 0) {
                    sql.append(",");
                }
                sql.append(categoryFilters[i]);
            }
            sql.append(")");
        }

        try {
            conn = DbUtils.getConnection();
            ptm = conn.prepareStatement(sql.toString());
            rs = ptm.executeQuery();
            if (rs.next()) {
                totalProducts = rs.getInt(1);
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
        return totalProducts;
    }

    public int getTotalFilteredProductsCount(String[] brandFilters, String[] priceFilters, String[] categoryFilters, String searchQuery) throws SQLException {
        int totalProducts = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        StringBuilder sql = new StringBuilder("SELECT COUNT(DISTINCT p.ProductID) FROM Products p JOIN ProductDetails pd ON p.ProductID = pd.ProductID");

        if (categoryFilters != null && categoryFilters.length > 0) {
            sql.append(" JOIN ProductBelongtoCDCategories pbcc ON p.ProductID = pbcc.ProductID");
            sql.append(" JOIN ChildrenCategories cc ON pbcc.CDCategoryID = cc.CDCategoryID");
        }

        sql.append(" WHERE p.status = 1");

        if (brandFilters != null && brandFilters.length > 0) {
            sql.append(" AND p.BrandID IN (").append(String.join(",", brandFilters)).append(")");
        }

        if (priceFilters != null && priceFilters.length > 0) {
            sql.append(" AND (");
            for (int i = 0; i < priceFilters.length; i++) {
                if (i > 0) {
                    sql.append(" OR ");
                }
                switch (priceFilters[i]) {
                    case "0-2000000":
                        sql.append("pd.price < 2000000");
                        break;
                    case "2000000-5000000":
                        sql.append("pd.price BETWEEN 2000000 AND 5000000");
                        break;
                    case "5000000-10000000":
                        sql.append("pd.price BETWEEN 5000000 AND 10000000");
                        break;
                    case "10000000plus":
                        sql.append("pd.price > 10000000");
                        break;
                }
            }
            sql.append(")");
        }

        if (categoryFilters != null && categoryFilters.length > 0) {
            sql.append(" AND cc.ParentID IN (");
            for (int i = 0; i < categoryFilters.length; i++) {
                if (i > 0) {
                    sql.append(",");
                }
                sql.append(categoryFilters[i]);
            }
            sql.append(")");
        }

        if (searchQuery != null && !searchQuery.isEmpty()) {
            sql.append(" AND p.productName LIKE ?");
        }

        try {
            conn = DbUtils.getConnection();
            ptm = conn.prepareStatement(sql.toString());

            int paramIndex = 1;
            if (searchQuery != null && !searchQuery.isEmpty()) {
                ptm.setString(paramIndex++, "%" + searchQuery + "%");
            }

            rs = ptm.executeQuery();
            if (rs.next()) {
                totalProducts = rs.getInt(1);
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
        return totalProducts;
    }

    public List<ProductDetailsDTO> getProductDetailsByColor(int productID, String color) throws SQLException {
        List<ProductDetailsDTO> productDetailsList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_PRODUCT_DETAILS_BY_COLOR);
                ptm.setInt(1, productID);
                ptm.setString(2, color);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int productDetailsID = rs.getInt("ProductDetailsID");
                    String size = rs.getString("size");
                    int stockQuantity = rs.getInt("stockQuantity");
                    int price = rs.getInt("price");
                    Date importDate = rs.getDate("importDate");
                    String image = rs.getString("image");
                    int status = rs.getInt("status");
                    productDetailsList.add(new ProductDetailsDTO(productDetailsID, productID, color, size, stockQuantity, price, importDate, image, status));
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

    public Map<String, Map<String, Map<String, Object>>> getProductDetailsByProductID2(int productId) {
        Map<String, Map<String, Map<String, Object>>> colorSizeMap = new HashMap<>();
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(localeVN);

        try (Connection con = DbUtils.getConnection()) {
            String query = "SELECT color, size, price, image FROM ProductDetails WHERE ProductID = ?  and status = 1 and stockQuantity > 0";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, productId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String color = rs.getString("color");
                String size = rs.getString("size");
                int price = rs.getInt("price");
                String images = rs.getString("image");

                colorSizeMap.putIfAbsent(color, new HashMap<>());
                Map<String, Object> sizeDetails = new HashMap<>();
                sizeDetails.put("price", currencyFormatter.format(price));
                sizeDetails.put("images", images.split(";"));
                colorSizeMap.get(color).put(size, sizeDetails);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return colorSizeMap;
    }

//    public static String getProductPrice(int productPrice) {
//        NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
//        return formatter.format(productPrice);
//    }
    public static void main(String[] args) {
        ProductDAO dao = new ProductDAO();
        // Thay đổi productId tại đây để truy vấn các chi tiết sản phẩm khác nhau
        int productId = 9; // Thay đổi productId theo nhu cầu của bạn

        // Gọi hàm để lấy dữ liệu chi tiết sản phẩm
        Map<String, Map<String, Map<String, Object>>> detailsMap = dao.getProductDetailsByProductID2(17);

        // In ra kết quả để kiểm tra
        for (String color : detailsMap.keySet()) {
            System.out.println("Color: " + color);
            Map<String, Map<String, Object>> sizeMap = detailsMap.get(color);
            for (String size : sizeMap.keySet()) {
                System.out.println("  Size: " + size);
                Map<String, Object> sizeDetails = sizeMap.get(size);
                System.out.println("    Price: " + sizeDetails.get("price"));
                System.out.println("    Images: " + Arrays.toString((String[]) sizeDetails.get("images")));
            }
        }
    }

    public int getProductDetailsID(int productID, int price, String size, String color) throws SQLException {
        int id = -1;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_PRODUCT_DETAILS_ID);
                ptm.setInt(1, productID);
                ptm.setInt(2, price);
                ptm.setString(3, size);
                ptm.setString(4, color);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    id = rs.getInt("ProductDetailsID");
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
        return id;
    }

    public int getProductDetailsIDByProductIDColorSize(int productID, String color, String size) throws SQLException {
        int id = -1;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_PRODUCT_DETAILS_BY_PRODUCT_ID_COLOR_SIZE);
                ptm.setInt(1, productID);
                ptm.setString(2, color);
                ptm.setString(3, size);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    id = rs.getInt("ProductDetailsID");
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
        return id;
    }

    public List<ProductDTO> searchProductByName(String productName) {
        List<ProductDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                String sql = SEARCH_PRODUCT_LIST_BY_PRODUCTNAME;
                ptm = conn.prepareStatement(sql);
                ptm.setString(1, "%" + productName + "%");
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int productID = rs.getInt("ProductID");
                    String productName1 = rs.getString("ProductName");
                    String description = rs.getString("Description");
                    int numberOfPurchasing = rs.getInt("NumberOfPurchasing");
                    int status = rs.getInt("Status");
                    int brandID = rs.getInt("BrandID");
                    list.add(new ProductDTO(productID, productName1, description, numberOfPurchasing, status, brandID));
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
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return list;
    }

    public List<ProductDTO> getTop5ProductsByPurchases() throws ClassNotFoundException {
        List<ProductDTO> products = new ArrayList<>();
        String query = "SELECT top(5) productID, productName, description, numberOfPurchasing, status, brandID "
                + "FROM Products "
                + "WHERE status = 1 "
                + // Assuming status 1 means active
                "ORDER BY numberOfPurchasing DESC ";

        try (Connection conn = DbUtils.getConnection(); PreparedStatement ps = conn.prepareStatement(query); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int productID = rs.getInt("productID");
                String productName = rs.getString("productName");
                String description = rs.getString("description");
                int numberOfPurchase = rs.getInt("numberOfPurchasing");
                int status = rs.getInt("status");
                int brandID = rs.getInt("brandID");

                ProductDTO product = new ProductDTO(productID, productName, description, numberOfPurchase, status, brandID);
                products.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    public List<ProductDetailsDTO> getProductDetailsByProductIDAndPrice(int productID, String[] priceFilters) throws SQLException {
        List<ProductDetailsDTO> productDetailsList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        StringBuilder query = new StringBuilder("SELECT * FROM ProductDetails WHERE ProductID = ? AND status = 1");

        if (priceFilters != null && priceFilters.length > 0) {
            query.append(" AND (");
            for (int i = 0; i < priceFilters.length; i++) {
                if (i > 0) {
                    query.append(" OR ");
                }
                switch (priceFilters[i]) {
                    case "0-2000000":
                        query.append("price < 2000000");
                        break;
                    case "2000000-5000000":
                        query.append("price BETWEEN 2000000 AND 5000000");
                        break;
                    case "5000000-10000000":
                        query.append("price BETWEEN 5000000 AND 10000000");
                        break;
                    case "10000000plus":
                        query.append("price > 10000000");
                        break;
                }
            }
            query.append(")");
        }

        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(query.toString());
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

                    productDetailsList.add(new ProductDetailsDTO(productDetailID, productID, color, size, stockQuantity, price, importDate, image, status));
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

    public CategoryDTO getCategoryByProductID(int productID) throws SQLException {
        CategoryDTO category = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        String query = "SELECT c.CategoryID, c.CategoriesName FROM Categories c "
                + "INNER JOIN ProductBelongtoCDCategories pbcc ON c.CategoryID = pbcc.CDCategoryID "
                + "WHERE pbcc.ProductID = ?";

        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(query);
                ptm.setInt(1, productID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    int categoryID = rs.getInt("CategoryID");
                    String categoryName = rs.getString("CategoriesName");
                    category = new CategoryDTO(categoryID, categoryName);
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
        return category;
    }

}
