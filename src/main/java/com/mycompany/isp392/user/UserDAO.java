package com.mycompany.isp392.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import utils.DbUtils;

public class UserDAO {

    private static final String LOGIN = "SELECT UserID, userName, roleID, phone, status, password FROM Users WHERE email = ? AND status = 1";
    private static final String CHECK_EMAIL = "SELECT UserID FROM Users WHERE email = ?";
    private static final String CHECK_PHONE = "SELECT UserID FROM Users WHERE phone = ?";
    private static final String ADD_USER = "INSERT INTO Users(userName, roleID, email, password, phone, status) VALUES(?, ?, ?, ?, ?, 1)";
    private static final String ADD_MANAGER = "INSERT INTO Employees(EmpID, position) VALUES(?, ?)";
    private static final String ADD_CUSTOMER = "INSERT INTO Customers(CustID, points, birthday, province_city, district, ward, detailAddress) VALUES(?, 0, ?, ?, ?, ?, ?)";
    private static final String GET_LAST_USER_ID = "SELECT MAX(UserID) AS LastUserID FROM Users";
    private static final String UPDATE_USER_PASSWORD = "UPDATE Users SET password = ? WHERE email = ?";
    private static final String GET_USER_INFO_BY_USERID = "SELECT * FROM Users WHERE UserID = ?";
    private static final String SEARCH_USER = "SELECT * FROM Users WHERE userName LIKE ?";
    
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
                rs = ptm.executeQuery();
                if (rs.next()) {
                    String hashedPassword = rs.getString("password");
                    if (BCrypt.checkpw(password, hashedPassword)) {
                        int UserID = rs.getInt("UserID");
                        String userName = rs.getString("userName");
                        int roleID = rs.getInt("roleID");
                        int phone = rs.getInt("phone");
                        int status = rs.getInt("status");
                        user = new UserDTO(UserID, userName, email, password, roleID, phone, status);
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

    public int checkEmailExists(String email) throws SQLException {
        int check = -1;
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
                    check = rs.getInt("userID");
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

    public boolean addAccount(UserDTO user, CustomerDTO customer) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptmUser = null;
        PreparedStatement ptmCustomer = null;
        ResultSet rs = null;

        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptmUser = conn.prepareStatement(ADD_USER, PreparedStatement.RETURN_GENERATED_KEYS);
                ptmUser.setString(1, user.getUserName());
                ptmUser.setInt(2, user.getRoleID());
                ptmUser.setString(3, user.getEmail());
                ptmUser.setString(4, user.getPassword());
                ptmUser.setInt(5, user.getPhone());
                int affectedRows = ptmUser.executeUpdate();

                if (affectedRows > 0) {
                    rs = ptmUser.getGeneratedKeys();
                    if (rs.next()) {
                        int userId = rs.getInt(1);
                        ptmCustomer = conn.prepareStatement(ADD_CUSTOMER);
                        ptmCustomer.setInt(1, userId);
                        ptmCustomer.setDate(2, (java.sql.Date) customer.getBirthday());
                        ptmCustomer.setString(3, customer.getCity());
                        ptmCustomer.setString(4, customer.getDistrict());
                        ptmCustomer.setString(5, customer.getWard());
                        ptmCustomer.setString(6, customer.getAddress());
                        check = ptmCustomer.executeUpdate() > 0;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptmUser != null) {
                ptmUser.close();
            }
            if (ptmCustomer != null) {
                ptmCustomer.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public boolean addManager(UserDTO user, EmployeeDTO employee) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptmUser = null;
        PreparedStatement ptmEmployee = null;
        ResultSet rs = null;

        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptmUser = conn.prepareStatement(ADD_USER, PreparedStatement.RETURN_GENERATED_KEYS);
                ptmUser.setString(1, user.getUserName());
                ptmUser.setInt(2, user.getRoleID());
                ptmUser.setString(3, user.getEmail());
                ptmUser.setString(4, user.getPassword());
                ptmUser.setInt(5, user.getPhone());
                int affectedRows = ptmUser.executeUpdate();

                if (affectedRows > 0) {
                    rs = ptmUser.getGeneratedKeys();
                    if (rs.next()) {
                        int userId = rs.getInt(1);
                        ptmEmployee = conn.prepareStatement(ADD_MANAGER);
                        ptmEmployee.setInt(1, userId);
                        ptmEmployee.setString(2, employee.getPosition());
                        check = ptmEmployee.executeUpdate() > 0;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptmUser != null) {
                ptmUser.close();
            }
            if (ptmEmployee != null) {
                ptmEmployee.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public boolean resetPassword(String email, String newPassword) {
        Connection conn = null;
        PreparedStatement ptm = null;
        boolean result = false;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_USER_PASSWORD);
                ptm.setString(1, newPassword);
                ptm.setString(2, email);
                result = ptm.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ptm != null) {
                    ptm.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public UserDTO getUserInfoByUserID(int userID) {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        UserDTO user = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_USER_INFO_BY_USERID);
                ptm.setInt(1, userID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    String userName = rs.getString("userName");
                    String email = rs.getString("email");
                    int phone = rs.getInt("phone");
                    int roleID = rs.getInt("roleID");
                    int status = rs.getInt("status");
                    user = new UserDTO(userName, email, phone, roleID, status);
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
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return user;
    }
    
    public List<UserDTO> searchListUser(String search) throws SQLException {
        List<UserDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SEARCH_USER);
                ptm.setString(1, "%" + search + "%");
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int userID = rs.getInt("UserID");
                    String userName = rs.getString("userName");
                    String email = rs.getString("email");
                    String password = "*******";
                    int roleID = rs.getInt("roleID");
                    int phone = rs.getInt("phone");
                    int status = rs.getInt("status");
                    list.add(new UserDTO(userID, userName, email, password, roleID, phone, status));
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
