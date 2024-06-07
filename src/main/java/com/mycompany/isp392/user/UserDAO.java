package com.mycompany.isp392.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import utils.DbUtils;

public class UserDAO {

    private static final String LOGIN = "SELECT UserID, userName, roleID, phone, status FROM Users WHERE email = ? AND password = ? AND status =1";
    private static final String CHECK_EMAIL = "SELECT UserID FROM Users WHERE email = ?";
    private static final String CHECK_PHONE = "SELECT UserID FROM Users WHERE phone = ?";
    private static final String INSERT_USER = "INSERT INTO Users(userName, roleID, email, password, phone, status) VALUES(?, ?, ?, ?, ?, true)";
    private static final String INSERT_CUSTOMER = "INSERT INTO Customers(CustID, points, birthday, province_city, district, ward, detailAddress) VALUES(?, 0, ?, ?, ?, ?, ?)";
    private static final String GET_LAST_USER_ID = "SELECT MAX(UserID) AS LastUserID FROM Users";

    public UserDTO checkLogin(String email, String password) throws SQLException {
        UserDTO user = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(LOGIN);
                ptm.setString(1, email);
                ptm.setString(2, password);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    int UserID = rs.getInt("UserID");
                    String userName = rs.getString("userName");
                    int roleID = rs.getInt("roleID");
                    int phone = rs.getInt("phone");
                    boolean status = rs.getBoolean("status");
                    user = new UserDTO(UserID, userName, email, password, roleID, phone, status);
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
        return user;
    }

    public int getLastUserId() throws SQLException {
        int lastUserId = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_LAST_USER_ID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    lastUserId = rs.getInt("LastUserID");
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

        return lastUserId + 1;
    }

    public boolean checkEmailExists(String email) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_EMAIL);
                ptm.setString(1, email);
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

    public boolean checkPhoneExists(int phone) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_PHONE);
                ptm.setInt(1, phone);
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

    public boolean insertUser(CustomerDTO customer) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptmUser = null;
        PreparedStatement ptmCustomer = null;
        try {
            conn = DbUtils.getConnection();

            ptmUser = conn.prepareStatement(INSERT_USER);
            ptmUser.setString(1, customer.getUserName());
            ptmUser.setInt(2, customer.getRoleID());
            ptmUser.setString(3, customer.getEmail());
            ptmUser.setString(4, customer.getPassword());
            ptmUser.setInt(5, customer.getPhone());
            check = ptmUser.executeUpdate() > 0 ? true : false;

            ptmCustomer = conn.prepareStatement(INSERT_CUSTOMER);
            ptmCustomer.setInt(1, customer.getUserID());
            ptmCustomer.setDate(2, new java.sql.Date(customer.getBirthday().getTime()));
            ptmCustomer.setString(3, customer.getCity());
            ptmCustomer.setString(4, customer.getDistrict());
            ptmCustomer.setString(5, customer.getWard());
            ptmCustomer.setString(6, customer.getAddress());
            check = ptmCustomer.executeUpdate() > 0 ? true : false;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptmCustomer != null) {
                ptmCustomer.close();
            }
            if (ptmUser != null) {
                ptmUser.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }
}
