/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.isp392.support;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.DbUtils;

/**
 *
 * @author notlongfen
 */
public class SupportDAO {

    private static final String GET_LAST_SUPPORTID = "SELECT * FROM Supports";
    private static final String SEARCH_SUPPORT = "SELECT *  FROM Supports s \n"
            + "INNER JOIN Customers c ON s.CustID = c.CustID\n"
            + "INNER JOIN Users u ON c.CustID = u.UserID\n"
            + "WHERE u.userName like ?";
    private static final String UPDATE_SUPPORT_STATUS = "UPDATE Supports SET status = ? WHERE supportID = ?";
    private static final String GET_SUPPORT_INFO_BASED_ON_SUPPORTID = "SELECT * FROM Supports s \n"
            + "INNER JOIN Customers c ON s.CustID = c.CustID\n"
            + "INNER JOIN Users u ON c.CustID = u.UserID\n"
            + "WHERE s.SupportID = ?";
    private static final String GET_ALL_SUPPORT_INFO = "SELECT * FROM Supports \n";
    private static final String ADD_SUPPORT_HISTORY = "INSERT INTO  ProcessSupports (EmpID, SupportID, responseMessage, title, responseDate) VALUES (?, ?, ?,?, GETDATE());";
    private static final String GET_SUPPORT_PROCESS_INFO_BY_SUPPORT_ID = "SELECT * FROM ProcessSupports WHERE SupportID = ?";
    private static final String ADD_SUPPORT = "INSERT INTO Supports (status, requestDate, requestMessage, CustID, title) VALUES (1, GETDATE(), ?, (SELECT CustID FROM Customers WHERE CustID = (SELECT CustID FROM Users WHERE email = ?)), ?)";

    public int getLastSupportId() throws SQLException {
        int lastSupportId = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;

        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_LAST_SUPPORTID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    lastSupportId = rs.getInt("SupportID");
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

        return lastSupportId + 1;
    }

    public String supportStatusUpdate(int spID) {
        Connection conn = null;
        PreparedStatement ptm = null;
        String supportID = "Not Yet";
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE_SUPPORT_STATUS);
                ptm.setInt(1, 0);
                ptm.setInt(2, spID);
                ptm.executeUpdate();
                supportID = "Done";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return supportID;
    }

    public List<SupportDTO> searchSupport(String searchText) throws SQLException {
        List<SupportDTO> supports = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SEARCH_SUPPORT);
                ptm.setString(1, "%" + searchText + "%");
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int supportID = rs.getInt("supportID");
                    int status = rs.getInt("status");
                    Date requestDate = rs.getDate("requestDate");
                    String requestMessage = rs.getString("requestMessage");
                    int custID = rs.getInt("custID");
                    supports.add(new SupportDTO(supportID, status, requestDate, requestMessage, custID));
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
        return supports;
    }

    public SupportDTO getSupportInfo(int supportID) {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        SupportDTO support = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_SUPPORT_INFO_BASED_ON_SUPPORTID);
                ptm.setInt(1, supportID);
                int id = supportID;
                rs = ptm.executeQuery();
                if (rs.next()) {
                    int status = rs.getInt("status");
                    String title = rs.getString("title");
                    Date requestDate = rs.getDate("requestDate");
                    String requestMessage = rs.getString("requestMessage");
                    int custID = rs.getInt("custID");
                    support = new SupportDTO(supportID, status, title, requestDate, requestMessage, custID);
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
        return support;
    }

    public List<SupportDTO> getAllSupport() throws SQLException {
        List<SupportDTO> supports = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_ALL_SUPPORT_INFO);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int supportID = rs.getInt("supportID");
                    int status = rs.getInt("status");
                    Date requestDate = rs.getDate("requestDate");
                    String requestMessage = rs.getString("requestMessage");
                    int custID = rs.getInt("custID");
                    supports.add(new SupportDTO(supportID, status, requestDate, requestMessage, custID));
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
        return supports;
    }

    public ProcessSupportDTO addReplyHistory(ProcessSupportDTO psp) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(ADD_SUPPORT_HISTORY);
                ptm.setInt(1, psp.getEmpID());
                ptm.setInt(2, psp.getSupportID());
                ptm.setString(3, psp.getResponseMessage());
                ptm.setString(4, psp.getTitle());
                ptm.executeUpdate();
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
        return psp;
    }

    public ProcessSupportDTO getInfoProcessSupport(int supportID) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        ProcessSupportDTO pdto = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_SUPPORT_PROCESS_INFO_BY_SUPPORT_ID);
                ptm.setInt(1, supportID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    int empID = rs.getInt("EmpID");
                    String responseMessage = rs.getString("responseMessage");
                    Date responseDate = rs.getDate("responseDate");
                    pdto = new ProcessSupportDTO(empID, supportID, responseMessage, responseDate);
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
        return pdto;
    }

    public boolean insertToSupport(String customerEmail, String title, String content) {
        Connection conn = null;
        PreparedStatement ptm = null;
        boolean result = false;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(ADD_SUPPORT);
                ptm.setString(1, content);
                ptm.setString(2, customerEmail);
                ptm.setString(3, title);
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
}
