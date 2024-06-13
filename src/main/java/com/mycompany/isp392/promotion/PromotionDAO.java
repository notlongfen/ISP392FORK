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
import java.util.ArrayList;
import java.util.List;
import utils.DbUtils;

/**
 *
 * @author Oscar
 */
public class PromotionDAO {

    private static final String SEARCH_PROMOTION = "SELECT promotionID, promotionName, startDate, endDate, discountPer, condition FROM Promotions WHERE promotionName LIKE ?";
    private static final String EDIT_PROMOTION = "UPDATE Promotions Set promotionName=?, startDate=?, endDate=?, discountPer=?, condition=? WHERE promotionID = ?";

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
                    list.add(new PromotionDTO(promotionID, promotionName, startDate, endDate, discountPer, condition));
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
                ptm.setInt(3, promotion.getDiscountPer());
                ptm.setInt(3, promotion.getCondition());
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
}
