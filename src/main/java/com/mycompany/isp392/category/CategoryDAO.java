/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.isp392.category;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.DbUtils;

/**
 *
 * @author TTNHAT
 */
public class CategoryDAO {

    private static final String ADD_CATEGORY = "INSERT INTO Categories (CategoryID, CategoriesName, Description, status) VALUES (?, ?, ?, ?)";
    private static final String ADD_CHILDREN_CATEGORY = "INSERT INTO ChildrenCategories (CDCategoryID, CategoriesName, ParentID, status) VALUES (?,?,?,?)";
    private static final String DELETE_CATEGORY = "UPDATE Categories SET status = 0 WHERE status = 1 AND CategoryID LIKE ?";
    private static final String DELETE_CHILDREN_CATEGORY = "UPDATE ChildrenCategories SET status = 0 WHERE status = 1 AND CDCategoryID LIKE ?";
    private static final String GET_LATEST_CATEGORY_ID = "SELECT MAX(CategoryID) AS CategoryID FROM Categories";
    private static final String GET_LATEST_CHILDREN_CATEGORY_ID = "SELECT MAX(CDCategoryID) AS CDCategoryID FROM ChildrenCategories";
    private static final String GET_LIST_CATEGORY = "SELECT * FROM Categories";
    private static final String GET_ACTIVE_CATEGORY = "SELECT * FROM Categories WHERE status = 1";
    private static final String SEARCH_CATEGORIES = "SELECT * FROM Categories WHERE CategoriesName LIKE ? OR description LIKE ?";
    private static final String SEARCH_CHILDREN_CATEGORIES = "SELECT * FROM ChildrenCategories WHERE parentID = ?";
    private static final String UPDATE_CATEGORY = "UPDATE Categories SET CategoriesName = ?, Description = ? WHERE CategoryID = ?";
    private static final String UPDATE_CHILDREN_CATEGORY = "UPDATE ChildrenCategories SET CategoriesName = ? WHERE CDCategoryID = ?";
    private static final String CHECK_CATEGORY_DUPLICATE = "SELECT * FROM Categories WHERE CategoriesName LIKE ?";
    private static final String CHECK_CHILDREN_CATEGORY_DUPLICATE = "SELECT * FROM ChildrenCategories WHERE CategoriesName LIKE ? AND ParentID LIKE ?";
    private static final String CHECK_PARENT_ID = "SELECT * FROM  Categories WHERE CategoryID LIKE ? AND status = 1";
    private static final String GET_CHILDREN_CATEGORIES = "SELECT * FROM ChildrenCategories WHERE status = 1 AND ParentID LIKE ?";
    private static final String ADD_PRODUCT_CATEGORY = "INSERT INTO ProductBelongtoCDCategories(ProductID, CDCategoryID) VALUES(?, ?)";
    private static final String GET_LIST_CDCATEGORY = "SELECT * FROM ChildrenCategories where status = 1";

    public boolean addCategory(CategoryDTO category) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        Statement stm = null;
        boolean check = false;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                stm = conn.createStatement();
                stm.executeUpdate("SET IDENTITY_INSERT Categories ON");

                ptm = conn.prepareStatement(ADD_CATEGORY);
                ptm.setInt(1, category.getCategoryID());
                ptm.setString(2, category.getCategoryName());
                ptm.setString(3, category.getDescription());
                ptm.setInt(4, category.getStatus());
                check = ptm.executeUpdate() > 0;

                stm.executeUpdate("SET IDENTITY_INSERT Categories OFF");
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

    public boolean addChildrenCategory(ChildrenCategoryDTO cdCategory) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        Statement stm = null;
        boolean check = false;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                stm = conn.createStatement();
                stm.executeUpdate("SET IDENTITY_INSERT ChildrenCategories ON");

                ptm = conn.prepareStatement(ADD_CHILDREN_CATEGORY);
                ptm.setInt(1, cdCategory.getCdCategoryID());
                ptm.setString(2, cdCategory.getCategoryName());
                ptm.setInt(3, cdCategory.getParentID());
                ptm.setInt(4, cdCategory.getStatus());
                check = ptm.executeUpdate() > 0;

                stm.executeUpdate("SET IDENTITY_INSERT ChildrenCategories OFF");
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

    public boolean deleteCategory(int categoryID) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        boolean check = false;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DELETE_CATEGORY);
                ptm.setInt(1, categoryID);
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

    public boolean deleteChildrenCategory(int cdCategoryID) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        boolean check = false;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DELETE_CHILDREN_CATEGORY);
                ptm.setInt(1, cdCategoryID);
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

    public int getLatestCategoryID() throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        int latestCategoryID = 0;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_LATEST_CATEGORY_ID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    latestCategoryID = rs.getInt("CategoryID");
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
        return latestCategoryID;
    }

    public int getLatestCdCategoryID() throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        int latestCdCategoryID = 0;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_LATEST_CHILDREN_CATEGORY_ID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    latestCdCategoryID = rs.getInt("CDCategoryID");
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
        return latestCdCategoryID;
    }

    public List<CategoryDTO> getListCategory() throws Exception {
        List<CategoryDTO> list = new ArrayList<CategoryDTO>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_LIST_CATEGORY);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int categoryID = rs.getInt("CategoryID");
                    String categoryName = rs.getString("CategoriesName");
                    String description = rs.getString("Description");
                    list.add(new CategoryDTO(categoryID, categoryName, description, 1));
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

    public List<CategoryDTO> getActiveCategory() throws Exception {
        List<CategoryDTO> list = new ArrayList<CategoryDTO>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_ACTIVE_CATEGORY);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int categoryID = rs.getInt("CategoryID");
                    String categoryName = rs.getString("CategoriesName");
                    String description = rs.getString("Description");
                    int status = rs.getInt("status");
                    list.add(new CategoryDTO(categoryID, categoryName, description, status));
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

    public List<ChildrenCategoryDTO> getListCDCategory() throws Exception {
        List<ChildrenCategoryDTO> list = new ArrayList<ChildrenCategoryDTO>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_LIST_CDCATEGORY);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int categoryID = rs.getInt("CDCategoryID");
                    String categoryName = rs.getString("CategoriesName");
                    int parentID = rs.getInt("ParentID");
                    int status = rs.getInt("status");
                    list.add(new ChildrenCategoryDTO(categoryID, categoryName, parentID, status));
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

    public List<CategoryDTO> searchCategories(String searchText) throws SQLException, ClassNotFoundException {
        List<CategoryDTO> categories = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SEARCH_CATEGORIES);
                ptm.setString(1, "%" + searchText + "%");
                ptm.setString(2, "%" + searchText + "%");
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int categoryID = rs.getInt("categoryID");
                    String categoryName = rs.getString("CategoriesName");
                    String description = rs.getString("description");
                    int status = rs.getInt("status");
                    categories.add(new CategoryDTO(categoryID, categoryName, description, status));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Log and possibly rethrow if needed outside
            throw e;
        } finally {
            // Ensure resources are closed to prevent leaks
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
        return categories;
    }

    public List<ChildrenCategoryDTO> searchChildrenCategories(int parentID) throws SQLException, ClassNotFoundException {
        List<ChildrenCategoryDTO> childrenCategories = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SEARCH_CHILDREN_CATEGORIES);
                ptm.setInt(1, parentID);  // Set the parentID to fetch children categories
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int cdCategoryID = rs.getInt("CDCategoryID");
                    String categoryName = rs.getString("CategoriesName");
                    int status = rs.getInt("status");
                    childrenCategories.add(new ChildrenCategoryDTO(cdCategoryID, categoryName, parentID, status));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Log and possibly rethrow if needed outside
            throw e;
        } finally {
            // Ensure resources are closed to prevent leaks
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
        return childrenCategories;
    }

    public boolean updateCategory(int categoryID, String newName, String newDescription) throws SQLException, ClassNotFoundException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_CATEGORY);
                ptm.setString(1, newName);
                ptm.setString(2, newDescription);
                ptm.setInt(3, categoryID);
                check = ptm.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e; // It's a good practice to rethrow the exception after logging it.
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

    public boolean updateChildrenCategory(int cdCategoryID, String newName) throws SQLException, ClassNotFoundException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_CHILDREN_CATEGORY);
                ptm.setString(1, newName);
                ptm.setInt(2, cdCategoryID);
                check = ptm.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e; // It's a good practice to rethrow the exception after logging it.
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

    public boolean checkCategoryDuplicate(String categoryName) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_CATEGORY_DUPLICATE);
                ptm.setString(1, categoryName);
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

    public boolean checkChildrenCategoryDuplicate(String categoryName, int parentID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_CHILDREN_CATEGORY_DUPLICATE);
                ptm.setString(1, categoryName);
                ptm.setInt(2, parentID);
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

    public boolean checkParentID(int parentID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_PARENT_ID);
                ptm.setInt(1, parentID);
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

    public List<ChildrenCategoryDTO> getChildrenCategories(int parentID) throws SQLException {
        List<ChildrenCategoryDTO> list = new ArrayList<ChildrenCategoryDTO>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_CHILDREN_CATEGORIES);
                ptm.setInt(1, parentID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int cdCategoryID = rs.getInt("CDCategoryID");
                    String categoryName = rs.getString("CategoriesName");
                    list.add(new ChildrenCategoryDTO(cdCategoryID, categoryName, parentID, 1));
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

    public boolean addProductCategory(int productID, int categoryID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(ADD_PRODUCT_CATEGORY);
                ptm.setInt(1, productID);
                ptm.setInt(2, categoryID);
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

    private static final String GET_CATEGORIES_BY_PRODUCT_ID = "SELECT c.CDCategoryID, c.CategoriesName FROM ChildrenCategories c "
            + "INNER JOIN ProductBelongtoCDCategories pbc ON c.CDCategoryID = pbc.CDCategoryID "
            + "WHERE pbc.ProductID = ?";

    public List<CategoryDTO> getCategoriesByProductID(int productID) throws SQLException {
        List<CategoryDTO> categories = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_CATEGORIES_BY_PRODUCT_ID);
                ptm.setInt(1, productID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int categoryID = rs.getInt("CDCategoryID");
                    String categoryName = rs.getString("CategoriesName");
                    categories.add(new CategoryDTO(categoryID, categoryName));
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
        return categories;
    }

}
