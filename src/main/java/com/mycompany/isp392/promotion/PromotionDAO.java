/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.isp392.promotion;

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
 * @author Oscar
 */
public class PromotionDAO {

    private static final String ADD_PROMOTION = "INSERT INTO Promotions (PromotionID, promotionName, startDate, endDate, discountPer, condition, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String DELETE_PROMOTION = "UPDATE Promotions SET status = 0 WHERE status = 1 AND promotionID LIKE ?";
    private static final String SEARCH_PROMOTION = "SELECT promotionID, promotionName, startDate, endDate, discountPer, condition, status FROM Promotions WHERE promotionName LIKE ?";
    private static final String EDIT_PROMOTION = "UPDATE Promotions Set promotionName=?, startDate=?, endDate=?, discountPer=?, condition=? status=? WHERE promotionID = ?";
    private static final String GET_LATEST_PROMOTION_ID = "SELECT MAX(PromotionID) AS PromotionID FROM Promotions";
    private final static String CHECK_PROMOTION_DUPLICATE = "SELECT * FROM Promotions WHERE promotionName LIKE ?";
    private static final String GET_PROMOTION_BY_ID = "SELECT promotionID, promotionName, startDate, endDate, discountPer, condition FROM Promotions WHERE promotionID = ?";

    public boolean addPromotion(PromotionDTO promotion) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        Statement stm = null;
        boolean check = false;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                stm = conn.createStatement();
                stm.executeUpdate("SET IDENTITY_INSERT Promotions ON");

                ptm = conn.prepareStatement(ADD_PROMOTION);
                ptm.setInt(1, promotion.getPromotionID());
                ptm.setString(2, promotion.getPromotionName());
                ptm.setDate(3, promotion.getStartDate());
                ptm.setDate(4, promotion.getEndDate());
                ptm.setInt(5, promotion.getDiscountPer());
                ptm.setInt(6, promotion.getCondition());
                ptm.setInt(7, promotion.getStatus());
                check = ptm.executeUpdate() > 0;

                stm.executeUpdate("SET IDENTITY_INSERT Promotions OFF");
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

    public boolean deletePromotion(int promotionID) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        boolean check = false;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DELETE_PROMOTION);
                ptm.setInt(1, promotionID);
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

    public List<PromotionDTO> searchPromotion(String searchText) throws SQLException {
        List<PromotionDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SEARCH_PROMOTION);
                ptm.setString(1, "%" + searchText + "%");
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int promotionID = rs.getInt("promotionID");
                    String promotionName = rs.getString("promotionName");
                    Date startDate = rs.getDate("startDate");
                    Date endDate = rs.getDate("endDate");
                    int discountPer = rs.getInt("discountPer");
                    int condition = rs.getInt("condition");
                    int status = rs.getInt("status");
                    list.add(new PromotionDTO(promotionID, promotionName, startDate, endDate, discountPer, condition, status));
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

    public boolean editPromotion(PromotionDTO promotion) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;

        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(EDIT_PROMOTION);
                ptm.setString(1, promotion.getPromotionName());
                ptm.setDate(2, promotion.getStartDate());
                ptm.setDate(3, promotion.getEndDate());
                ptm.setInt(4, promotion.getDiscountPer());
                ptm.setInt(5, promotion.getCondition());
                ptm.setInt(6, promotion.getStatus());
                check = ptm.executeUpdate() > 0 ? true : false;
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

    public int getLatestPromotionID() throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        int latestPromotionID = 0;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_LATEST_PROMOTION_ID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    latestPromotionID = rs.getInt("PromotionID");
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
        return latestPromotionID;
    }

    public boolean checkPromotionDuplicate(String promotionName) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_PROMOTION_DUPLICATE);
                ptm.setString(1, promotionName);
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

    public PromotionDTO getPromotionByID(int promotionID) throws SQLException {
        PromotionDTO promotion = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DbUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_PROMOTION_BY_ID);
                ptm.setInt(1, promotionID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    String promotionName = rs.getString("promotionName");
                    Date startDate = rs.getDate("startDate");
                    Date endDate = rs.getDate("endDate");
                    int discountPer = rs.getInt("discountPer");
                    int condition = rs.getInt("condition");
                    promotion = new PromotionDTO(promotionID, promotionName, startDate, endDate, discountPer, condition);
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
        return promotion;
    }
}
