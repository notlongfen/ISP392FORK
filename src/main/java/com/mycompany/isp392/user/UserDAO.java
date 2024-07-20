package com.mycompany.isp392.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import jakarta.ws.rs.GET;
import utils.DbUtils;

public class UserDAO {

    private static final String LOGIN = "SELECT UserID, userName, roleID, phone, status, password FROM Users WHERE email = ? AND status = 1";
    private static final String CHECK_EMAIL = "SELECT UserID FROM Users WHERE email = ?";
    private static final String CHECK_PHONE = "SELECT UserID FROM Users WHERE phone = ?";
    private static final String ADD_USER = "INSERT INTO Users(userName, roleID, email, password, phone, status) VALUES(?, ?, ?, ?, ?, 1)";
    private static final String ADD_EMPLOYEE = "INSERT INTO Employees(EmpID) VALUES(?)";
    private static final String ADD_CUSTOMER = "INSERT INTO Customers(CustID, points, birthday, province_city, district, ward, detailAddress) VALUES(?, 0, ?, ?, ?, ?, ?)";
    private static final String GET_LAST_USER_ID = "SELECT MAX(UserID) AS LastUserID FROM Users";
    private static final String UPDATE_USER_PASSWORD = "UPDATE Users SET password = ? WHERE email = ?";
    private static final String UPDATE_CUSTOMER_STATUS = "UPDATE Users SET status = ? WHERE userID = ?";
    private static final String GET_USER_INFO_BY_USERID = "SELECT * FROM Users WHERE UserID = ?";
    private static final String SEARCH_USER = "SELECT * FROM Users WHERE userName LIKE ?";
    private static final String GET_ALL_USERS = "SELECT * FROM Users";
    private static final String EDIT_USER = "UPDATE Users SET userName = ?, roleID = ?, email = ?, password = ?, phone = ?, status = ? WHERE UserID = ?";
    private static final String EDIT_USER_NO_PASS = "UPDATE Users SET userName = ?, roleID = ?, email = ?, phone = ?, status = ? WHERE UserID = ?";
    private static final String EDIT_CUSTOMER = "UPDATE Customers SET points = ?, birthday = ?, province_city = ?, district = ?, ward = ?, detailAddress = ? WHERE CustID = ?";
    private static final String GET_USER_BY_ID = "SELECT * FROM Users WHERE UserID = ?";
    private static final String GET_CUSTOMER_BY_ID = "SELECT * FROM Customers WHERE CustID = ?";
    private static final String GET_ALL_INFO_CUSTOMER_BY_ID = "SELECT * FROM Users u INNER JOIN Customers c ON u.UserID = c.CustID WHERE CustID = ?";
    private static final String GET_EMPLOYEE_BY_ID = "SELECT * FROM Employees WHERE EmpID = ?";
    private static final String DELETE_USER = "UPDATE Users SET status = 0 WHERE UserID = ?";
    private static final String GET_USER_INFO_BASED_ON_SUPPORTID = "SELECT * FROM Supports s \n"
            + "INNER JOIN Customers c ON s.CustID = c.CustID\n"
            + "INNER JOIN Users u ON c.CustID = u.UserID\n"
            + "WHERE s.SupportID = ?";
    private static final String GET_ALL_USER_SUPPORT_INFO = "SELECT * FROM Supports s \n"
            + "INNER JOIN Customers c ON s.CustID = c.CustID\n"
            + "INNER JOIN Users u ON c.CustID = u.UserID\n";
    private static final String GET_CUSTOMER_INFO = "SELECT * FROM Customers WHERE CustID = ?";
    private static final String GET_FULL_CUSTOMER_BY_ID = "SELECT * FROM Users u JOIN Customers c ON u.UserID = c.CustID WHERE c.CustID=?";
    private static final String UPDATE_USER_PROFILE = "UPDATE Users SET userName = ?, email = ?, phone = ? WHERE UserID = ?";
    private static final String UPDATE_CUSTOMER_PROFILE = "UPDATE Customers SET birthday = ?, province_city = ?, district = ?, ward = ?, detailAddress = ? WHERE CustID = ?";
    private static final String GET_USER_INFO_BASED_ON_ORDER_ID = "SELECT * FROM Orders o \n"
            + "INNER JOIN Customers c ON o.CustID = c.CustID\n"
            + "INNER JOIN Users u ON c.CustID = u.UserID\n"
            + "WHERE o.OrderID = ?";

    private static final String UPDATE_EMP_ROLE_STATUS = "UPDATE Users SET roleID = ?, status = ? WHERE UserID = ?";
    private static final String GET_HASHED_PASSWORD = "SELECT password FROM Users WHERE UserID = ?";
    private static final String GET_USER_GOOGLE_BY_EMAIL = "SELECT * FROM Users WHERE email = ?";

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
        int check = -1; // -1 means email does not exist
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

    public boolean addEmployee(UserDTO user) throws SQLException {
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
                        ptmEmployee = conn.prepareStatement(ADD_EMPLOYEE);
                        ptmEmployee.setInt(1, userId);
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
                String hashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());
                ptm = conn.prepareStatement(UPDATE_USER_PASSWORD);
                ptm.setString(1, hashedPassword);
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

    public boolean updateCustomerStatus(int userID, int status) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_CUSTOMER_STATUS);
                ptm.setInt(1, status);
                ptm.setInt(2, userID);
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

    public boolean deleteUser(int UserID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DELETE_USER);
                ptm.setInt(1, UserID);
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
    
        public int deleteUser1(int UserID) throws SQLException {
        int newStatus = -1;
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DELETE_USER);
                ptm.setInt(1, UserID);
                check = ptm.executeUpdate() > 0;
                if (check) {
                    ptm = conn.prepareStatement(GET_USER_BY_ID);
                    ptm.setInt(1, UserID);
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

    public List<UserDTO> getAllUsers() throws SQLException {
        List<UserDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_ALL_USERS);
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

    public boolean editUserAndCustomer(UserDTO user, CustomerDTO customer) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptmUser = null;
        PreparedStatement ptmCustomer = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                // Disable auto-commit mode
                conn.setAutoCommit(false);

                // Update User
                ptmUser = conn.prepareStatement(EDIT_USER_NO_PASS);
                ptmUser.setString(1, user.getUserName());
                ptmUser.setInt(2, user.getRoleID());
                ptmUser.setString(3, user.getEmail());
                ptmUser.setInt(4, user.getPhone());
                ptmUser.setInt(5, user.getStatus());
                ptmUser.setInt(6, user.getUserID());
                boolean userUpdated = ptmUser.executeUpdate() > 0;

                // Update Customer
                ptmCustomer = conn.prepareStatement(EDIT_CUSTOMER);
                ptmCustomer.setInt(1, customer.getPoints());
                ptmCustomer.setDate(2, (Date) customer.getBirthday());
                ptmCustomer.setString(3, customer.getCity());
                ptmCustomer.setString(4, customer.getDistrict());
                ptmCustomer.setString(5, customer.getWard());
                ptmCustomer.setString(6, customer.getAddress());
                ptmCustomer.setInt(7, customer.getCustID());
                boolean customerUpdated = ptmCustomer.executeUpdate() > 0;

                // Check both updates
                if (userUpdated && customerUpdated) {
                    // Commit the transaction if both updates are successful
                    conn.commit();
                    check = true;
                } else {
                    // Rollback the transaction if any update fails
                    conn.rollback();
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

    public boolean editEmployee(UserDTO user) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(EDIT_USER);
                ptm.setString(1, user.getUserName());
                ptm.setInt(2, user.getRoleID());
                ptm.setString(3, user.getEmail());
                ptm.setString(4, user.getPassword());
                ptm.setInt(5, user.getPhone());
                ptm.setInt(6, user.getStatus());
                ptm.setInt(7, user.getUserID());
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

    public UserDTO getUserByID(int UserID) throws SQLException {
        UserDTO user = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_USER_BY_ID);
                ptm.setInt(1, UserID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    String userName = rs.getString("userName");
                    String email = rs.getString("email");
                    String password = rs.getString("password");
                    int roleID = rs.getInt("roleID");
                    int phone = rs.getInt("phone");
                    int status = rs.getInt("status");
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

    public CustomerDTO getCustomerByID(int CustID) throws SQLException {
        CustomerDTO customer = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_CUSTOMER_BY_ID);
                ptm.setInt(1, CustID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    int points = rs.getInt("points");
                    Date birthday = rs.getDate("birthday");
                    String city = rs.getString("province_city");
                    String district = rs.getString("district");
                    String ward = rs.getString("ward");
                    String address = rs.getString("detailAddress");
                    customer = new CustomerDTO(CustID, points, birthday, city, district, ward, address);
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
        return customer;
    }
    
    public CustomerDTO getAllInfoCustomerByID(int CustID) throws SQLException {
        CustomerDTO customer = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_ALL_INFO_CUSTOMER_BY_ID);
                ptm.setInt(1, CustID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    String name = rs.getString("userName");
                    String email = rs.getString("email");
                    int roleID = rs.getInt("roleID");
                    int phone = rs.getInt("phone");
                    int points = rs.getInt("points");
                    Date birthday = rs.getDate("birthday");
                    String city = rs.getString("province_city");
                    String district = rs.getString("district");
                    String ward = rs.getString("ward");
                    String address = rs.getString("detailAddress");
                    customer = new CustomerDTO(name, email, phone, roleID,  points, birthday, city, district, ward, address);
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
        return customer;
    }

    public EmployeeDTO getEmployeeByID(int EmpID) throws SQLException {
        EmployeeDTO employee = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_EMPLOYEE_BY_ID);
                ptm.setInt(1, EmpID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    employee = new EmployeeDTO(EmpID);
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
        return employee;
    }

    public UserDTO getUserInfo(int supportID) {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        UserDTO user = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_USER_INFO_BASED_ON_SUPPORTID);
                ptm.setInt(1, supportID);
                int id = supportID;
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int userID = rs.getInt("UserID");
                    String userName = rs.getString("userName");
                    String email = rs.getString("email");
                    int phone = rs.getInt("phone");
                    user = new UserDTO(userID, userName, email, phone);
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

    public List<UserDTO> getAllUserSupport() throws SQLException {
        List<UserDTO> users = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_ALL_USER_SUPPORT_INFO);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int userID = rs.getInt("UserID");
                    String userName = rs.getString("userName");
                    String email = rs.getString("email");
                    int phone = rs.getInt("phone");
                    users.add(new UserDTO(userID, userName, email, phone));
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
        return users;
    }

    public CustomerDTO getCustomerInfo(int CustID) throws SQLException {
        CustomerDTO customer = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_CUSTOMER_INFO);
                ptm.setInt(1, CustID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    int points = rs.getInt("points");
                    Date birthday = rs.getDate("birthday");
                    String city = rs.getString("province_city");
                    String district = rs.getString("district");
                    String ward = rs.getString("ward");
                    String address = rs.getString("detailAddress");
                    customer = new CustomerDTO(CustID, points, birthday, city, district, ward, address);
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
        return customer;
    }

    public int updateUserPoint(int userID, int point) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        int result = 0;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement("UPDATE Customers SET points = ? WHERE CustID = ?");
                ptm.setInt(1, point);
                ptm.setInt(2, userID);
                result = ptm.executeUpdate();
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
        return result;
    }

    public CustomerDTO getFullCustomerByID(int CustID) throws SQLException {
        CustomerDTO customer = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_FULL_CUSTOMER_BY_ID);
                ptm.setInt(1, CustID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    String userName = rs.getString("userName");
                    String email = rs.getString("email");
                    int phone = rs.getInt("phone");
                    int roleID = rs.getInt("roleID");
                    int points = rs.getInt("points");
                    Date birthday = rs.getDate("birthday");
                    String city = rs.getString("province_city");
                    String district = rs.getString("district");
                    String ward = rs.getString("ward");
                    String address = rs.getString("detailAddress");
                    customer = new CustomerDTO(userName, email, phone, roleID, points, birthday, city, district, ward,
                            address);
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
        return customer;
    }

    public boolean updateUserAndCustomer(CustomerDTO customer) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptmUser = null;
        PreparedStatement ptmCustomer = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                // Disable auto-commit mode
                conn.setAutoCommit(false);

                // Update User
                ptmUser = conn.prepareStatement(UPDATE_USER_PROFILE);
                ptmUser.setString(1, customer.getUserName());
                ptmUser.setString(2, customer.getEmail());
                ptmUser.setInt(3, customer.getPhone());
                ptmUser.setInt(4, customer.getUserID());
                boolean userUpdated = ptmUser.executeUpdate() > 0;

                // Update Customer
                ptmCustomer = conn.prepareStatement(UPDATE_CUSTOMER_PROFILE);
                ptmCustomer.setDate(1, (Date) customer.getBirthday());
                ptmCustomer.setString(2, customer.getCity());
                ptmCustomer.setString(3, customer.getDistrict());
                ptmCustomer.setString(4, customer.getWard());
                ptmCustomer.setString(5, customer.getAddress());
                ptmCustomer.setInt(6, customer.getUserID());
                boolean customerUpdated = ptmCustomer.executeUpdate() > 0;

                // Check both updates
                if (userUpdated && customerUpdated) {
                    // Commit the transaction if both updates are successful
                    conn.commit();
                    check = true;
                } else {
                    // Rollback the transaction if any update fails
                    conn.rollback();
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

    public UserDTO getUserInfoBasedOnOrderID(int orderID) {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        UserDTO user = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_USER_INFO_BASED_ON_ORDER_ID);
                ptm.setInt(1, orderID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    int userID = rs.getInt("UserID");
                    String userName = rs.getString("userName");
                    String email = rs.getString("email");
                    int phone = rs.getInt("phone");
                    user = new UserDTO(userID, userName, email, phone);
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

    public boolean updateEmpRoleAndStatus(int userID, int roleID, int status) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_EMP_ROLE_STATUS);
                ptm.setInt(1, roleID);
                ptm.setInt(2, status);
                ptm.setInt(3, userID);
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

    public String getHashedPassword(int userID) throws SQLException {
        String hashedPassword = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_HASHED_PASSWORD);
                ptm.setInt(1, userID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    hashedPassword = rs.getString("password");
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
        return hashedPassword;
    }

    public UserDTO getUserGoogleInfo(String email) {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        UserDTO user = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_USER_GOOGLE_BY_EMAIL);
                ptm.setString(1, email);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    int userID = rs.getInt("UserID");
                    String userName = rs.getString("userName");
                    int roleID = rs.getInt("roleID");
                    int phone = rs.getInt("phone");
                    int status = rs.getInt("status");
                    user = new UserDTO(userID, userName, email, "", roleID, phone, status);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
}
