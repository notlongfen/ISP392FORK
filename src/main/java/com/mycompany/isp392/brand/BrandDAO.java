package com.mycompany.isp392.brand;

import java.sql.Date;
import java.util.List;
import utils.DbUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Timestamp;
import java.util.Arrays;

public class BrandDAO {

    private static final String SEARCH_BRAND = "SELECT * FROM Brands WHERE brandName LIKE ?";
    private static final String SEARCH_BRAND_BY_ID = "SELECT * FROM Brands WHERE brandID LIKE ?";
    private static final String INSERT_BRAND = "INSERT INTO Brands(brandName, image, status) VALUES(?,?,1)";
    private static final String UPDATE_BRAND = "UPDATE Brands SET brandName=?, image=?, status=? WHERE brandID=?";
    private static final String DELETE_BRAND = "UPDATE Brands SET status = 0 WHERE brandID=?";
    private static final String CHECK_BRAND = "SELECT brandID FROM Brands WHERE brandName LIKE ? AND brandID != ?";
    private static final String CHECK_BRAND_NAME = "SELECT brandID FROM Brands WHERE brandName LIKE ? ";
    private static final String GET_ALL_BRANDS = "SELECT * FROM Brands";
//    private static final String GET_BRAND = "SELECT * FROM Brands WHERE brandID=?";
    private static final String GET_BRAND = "SELECT * FROM Brands WHERE BrandID=?";
    private static final String ADD_MANAGE_BRAND = "INSERT INTO ManageBrands(BrandID, EmpID, FieldNew, FieldOld, Action) values (?,?,?,?,?)";
    private static final String GET_MANAGE_BRAND = "SELECT * FROM ManageBrands";


    public List<BrandDTO> searchForBrand(String brandName) {
        List<BrandDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SEARCH_BRAND);
                ptm.setString(1, "%" + brandName + "%");
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int brandID = rs.getInt("brandID");
                    String name = rs.getString("brandName");
                    String image = rs.getString("image");
                    int status = rs.getInt("status");
                    list.add(new BrandDTO(brandID, name, image, status));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, ptm, rs);
        }
        return list;
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
                    String name = rs.getString("brandName");
                    String image = rs.getString("image");
                    int status = rs.getInt("status");
                    list.add(new BrandDTO(brandID, name, image, status));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, ptm, rs);
        }
        return list;
    }

    public boolean addBrand(String brandName, String brandImage) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(INSERT_BRAND);
                ptm.setString(1, brandName);
                ptm.setString(2, brandImage);
                check = ptm.executeUpdate() > 0;
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, ptm, null);
        }
        return check;
    }

    public boolean updateBrand(String brandName, String brandImage, int brandID, int status) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_BRAND);
                ptm.setString(1, brandName);
                ptm.setString(2, brandImage);
                ptm.setInt(3, status);
                ptm.setInt(4, brandID);
                check = ptm.executeUpdate() > 0;
            }
        } catch (SQLException | ClassNotFoundException e) {
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

    public boolean deleteBrand(int brandID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DELETE_BRAND);
                ptm.setInt(1, brandID);
                check = ptm.executeUpdate() > 0;
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, ptm, null);
        }
        return check;
    }

    public int deleteBrand1(int brandID) throws SQLException {
        int newStatus = -1;
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DELETE_BRAND);
                ptm.setInt(1, brandID);
                check = ptm.executeUpdate() > 0;
                if (check) {
                    ptm = conn.prepareStatement(GET_BRAND);
                    ptm.setInt(1, brandID);
                    rs = ptm.executeQuery();
                    if (rs.next()) {
                        newStatus = rs.getInt("status");
                    }
                }

            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
        }
        return newStatus;
    }

    public boolean checkBrandExists(String brandName, int brandID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_BRAND);
                ptm.setString(1, brandName);
                ptm.setInt(2, brandID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    check = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, ptm, rs);
        }
        return check;
    }

    public boolean checkBrandNameExists(String brandName) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_BRAND_NAME);
                ptm.setString(1, brandName);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    check = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, ptm, rs);
        }
        return check;
    }

    public List<BrandDTO> getAllBrands() throws SQLException {
        List<BrandDTO> brands = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_ALL_BRANDS);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int brandID = rs.getInt("brandID");
                    String brandName = rs.getString("brandName");
                    String brandImage = rs.getString("image");
                    int status = rs.getInt("status");
                    brands.add(new BrandDTO(brandID, brandName, brandImage, status));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, ptm, rs);
        }
        return brands;
    }

    public BrandDTO getSpecificBrand(String brandID) throws SQLException {
        BrandDTO brand = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_BRAND);
                ptm.setString(1, brandID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    String brandName = rs.getString("BrandName");
                    String brandImage = rs.getString("image");
                    int status = rs.getInt("status");
                    brand = new BrandDTO(Integer.parseInt(brandID), brandName, brandImage, status);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, ptm, rs);
        }
        return brand;
    }

    private void closeResources(Connection conn, PreparedStatement ptm, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (ptm != null) {
            try {
                ptm.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public String getBrandImagePath(int brandID) throws SQLException {
        String imagePath = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        String query = "SELECT image FROM Brands WHERE brandID = ?";
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(query);
                ptm.setInt(1, brandID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    imagePath = rs.getString("image");
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
        return imagePath;
    }

    public boolean addManageBrand(ManageBrandDTO manage) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(ADD_MANAGE_BRAND);
                ptm.setInt(1, manage.getBrandID());
                ptm.setInt(2, manage.getEmpID());
                ptm.setString(3, String.join(",", manage.getNewField())); // Chuyển đổi danh sách thành chuỗi
                ptm.setString(4, String.join(",", manage.getOldField())); // Chuyển đổi danh sách thành chuỗi
                ptm.setString(5, manage.getAction());
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

    public List<ManageBrandDTO> getManageBrand() throws SQLException {
        List<ManageBrandDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_MANAGE_BRAND);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int brandID = rs.getInt("BrandID");
                    int empID = rs.getInt("EmpID");
                    String oldField = rs.getString("FieldOld");
                    String newField = rs.getString("FieldNew");
                    String action = rs.getString("Action");
                    Timestamp changeDate = rs.getTimestamp("ChangeDate");

                    list.add(new ManageBrandDTO(brandID, empID, oldField, newField, action, changeDate));
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

}
