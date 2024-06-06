package com.mycompany.isp392.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import utils.DbUtils;

public class UserDAO {

    private static final String LOGIN = "SELECT UserID, userName, roleID, phone, status FROM Users WHERE email = ? AND password = ? ";
    private static final String CHECK_EMAIL = "SELECT UserID FROM Users WHERE email = ?";
    private static final String CHECK_PHONE = "SELECT UserID FROM Users WHERE phone = ?";
    private static final String INSERT_USER = "INSERT INTO Users(userName, roleID, email, password, phone, status) VALUES(?, ?, ?, ?, ?, ?)";
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

        return lastUserId +1;
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

    public boolean insert(CustomerDTO customer) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptmUser = null;
        PreparedStatement ptmCustomer = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptmUser = conn.prepareStatement(INSERT_USER);
                ptmUser.setInt(1, customer.getUserID());
                ptmUser.setString(2, customer.getUserName());
                ptmUser.setInt(3, customer.getRoleID());
                ptmUser.setString(4, customer.getEmail());
                ptmUser.setString(5, customer.getPassword());
                ptmUser.setInt(6, customer.getPhone());
                ptmUser.setBoolean(7, customer.getStatus());

                ptmCustomer = conn.prepareStatement(INSERT_CUSTOMER);
                ptmCustomer.setDate(1, (java.sql.Date) new Date(customer.getBirthday().getTime()));
                ptmCustomer.setString(2, customer.getCity());
                ptmCustomer.setString(3, customer.getDistrict());
                ptmCustomer.setString(4, customer.getWard());
                ptmCustomer.setString(5, customer.getAddress());
                check = ptmUser.executeUpdate() > 0 ? true : false;

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptmCustomer != null) {
                ptmCustomer.close();
            }
            if (ptmUser!= null) {
                ptmUser.close();
            }
            if (conn != null) {
                conn.close();
            }

        }
        return check;
    }
}
