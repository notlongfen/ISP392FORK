/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.isp392.forgetpassword;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;

import utils.DbUtils;

/**
 *
 * @author notlongfen
 */
public class ForgetPasswordDAO {
    private static final String CREATE_TOKEN = "INSERT INTO ForgetPassword (userID, token, expiredDate, tokenStatus) VALUES (?, ?, GETDATE() +1, 1)";
    private static final String CHECK_TOKEN = "SELECT * FROM ForgetPassword WHERE token = ? AND expiredDate > GETDATE() AND tokenStatus = 1";
    private static final String GET_ALL_INFO = "SELECT TOP 1 * FROM ForgetPassword WHERE userID = ? AND tokenStatus = 1 ORDER BY ResetID DESC";
    private static final String INVALIDATE_TOKEN = "UPDATE ForgetPassword SET tokenStatus = 0 WHERE token = ?";

    public String createToken() {
        return UUID.randomUUID().toString();
    }

    public boolean insertToken(int userID) {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        boolean result = false;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CREATE_TOKEN);
                ptm.setInt(1, userID);
                ptm.setString(2, createToken());
                ptm.executeUpdate();
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            DbUtils.closeConnection(conn, ptm, rs);
        }
        return result;
    }

    public ForgetPasswordDTO checkToken(String token) {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        ForgetPasswordDTO dto = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_TOKEN);
                ptm.setString(1, token);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    dto = new ForgetPasswordDTO(rs.getInt("ResetID"),rs.getInt("userID"), rs.getString("token"), rs.getDate("expiredDate"), rs.getInt("tokenStatus"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            DbUtils.closeConnection(conn, ptm, rs);
        }
        return dto;
    }

    public ForgetPasswordDTO getAllInfoByUserID(int userID) {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        ForgetPasswordDTO dto = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_ALL_INFO);
                ptm.setInt(1, userID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    dto = new ForgetPasswordDTO(rs.getInt("ResetID"),rs.getInt("userID"), rs.getString("token"), rs.getDate("expiredDate"), rs.getInt("tokenStatus"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            DbUtils.closeConnection(conn, ptm, rs);
        }
        return dto;
    }

    public ForgetPasswordDTO getAllInfoByToken(String token) {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        ForgetPasswordDTO dto = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_TOKEN);
                ptm.setString(1, token);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    dto = new ForgetPasswordDTO(rs.getInt("ResetID"),rs.getInt("userID"), rs.getString("token"), rs.getDate("expiredDate"), rs.getInt("tokenStatus"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            DbUtils.closeConnection(conn, ptm, rs);
        }
        return dto;
    }

    public boolean invalidateToken(String token) {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        boolean result = false;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(INVALIDATE_TOKEN);
                ptm.setString(1, token);
                ptm.executeUpdate();
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            DbUtils.closeConnection(conn, ptm, rs);
        }
        return result;
    }
    
}
