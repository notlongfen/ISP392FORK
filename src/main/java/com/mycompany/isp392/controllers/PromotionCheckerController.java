/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.isp392.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.mycompany.isp392.cart.*;
import com.mycompany.isp392.product.*;
import com.mycompany.isp392.promotion.*;
import com.mycompany.isp392.user.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author notlongfen
 */
@WebServlet(name = "PromotionCheckerController", urlPatterns = {"/PromotionCheckerController"})
public class PromotionCheckerController extends HttpServlet {

    private static final String ERROR = "US_Checkout.jsp";
    private static final String SUCCESS = "US_Checkout.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession sesssion = request.getSession();
        String url = ERROR;
        UserDTO userDTO = (UserDTO) sesssion.getAttribute("LOGIN_USER");
        CustomerDTO customer = (CustomerDTO) sesssion.getAttribute("CUST");
        UserDAO userDAO = new UserDAO();
        PromotionDAO promotionDAO = new PromotionDAO();

        String userName = request.getParameter("name");
        String phoneNumber = request.getParameter("phone");
        String address = request.getParameter("address");
        String ward = request.getParameter("ward");
        String district = request.getParameter("district");
        String city = request.getParameter("city");
        String note = request.getParameter("note");

        String promotionName = request.getParameter("promotionName");
        request.setAttribute("PROMOTION_NAME", promotionName);
        CartDTO cart = (CartDTO) sesssion.getAttribute("CART");

        CartDAO cartDAO = new CartDAO();
        List<CartDetailsDTO> cartList = null;
        double originalPrice = 0.0;
        PromotionDTO promotion = null;
        double newPrice = 0.0;

        try {
            if (cart == null) {
                cart = cartDAO.getCartByCustomerID(userDTO.getUserID());
                cartList = cartDAO.getCartItems(cart.getCartID());
                originalPrice = cart.getTotalPrice();
//                newPrice = cart.getTotalPrice() + 40000;
//                cart.setTotalPrice(newPrice);
//               
//                request.setAttribute("CART_TOTAL_PRICE", cart);
                promotion = promotionDAO.getPromotionByName(promotionName);
                if (promotion == null) {
                    PromotionError pe = new PromotionError();
                    pe.setConditionError("This promotion is not exist");
                    request.setAttribute("PROMOTION_ERROR", pe);
                    return;
                }
                cart.setPromotionID(promotion.getPromotionID());
                boolean checkUpdate = cartDAO.updateCartPromotion(cart.getPromotionID(), cart.getCartID());
                if (!checkUpdate) {
                    request.setAttribute("UPDATE_ERROR", "Cannot update promotionID in cart");
                    return;
                }
                if (cart == null) {
                    CartError error = new CartError();
                    error.setError("Your cart is empty");
                    request.getRequestDispatcher(url).forward(request, response);
                }
            }
            int userPoint = userDAO.getCustomerByID(userDTO.getUserID()).getPoints();

            if (promotion == null) {
                PromotionError pe = new PromotionError();
                pe.setConditionError("This promotion is not exist");
                request.setAttribute("PROMOTION_ERROR", pe);
                return;
            }

            int minPointToApplyCoupon = promotion.getCondition();

            // Check if user point is enough to apply coupon
            if (userPoint >= minPointToApplyCoupon) {
                double percentage = (double) promotion.getDiscountPer() / 100.0;
                // int point = userDAO.getCustomerByID(userDTO.getUserID()).getPoints() - 100;
                // userDAO.updateUserPoint(userDTO.getUserID(), point);
                newPrice = cart.getTotalPrice() - (cart.getTotalPrice() * percentage);
                cart.setTotalPrice(newPrice);
                // boolean checkUpdate = cartDAO.updateCartNewPrice(cart.getTotalPrice(), cart.getCartID());
                // if (!checkUpdate) {
                //     request.setAttribute("UPDATE_ERROR", "Cannot update total price in cart");
                //     return;
                // }
                request.setAttribute("CARTAFTERPROMOTION", cart);
                url = SUCCESS;
            } else {
                PromotionError pe = new PromotionError();
                pe.setConditionError("Your membership point does not reach the condition of this promotion " + "("
                        + minPointToApplyCoupon + " points)" + "Your point: " + userPoint + " points");
                request.setAttribute("PROMOTION_ERROR", pe);
                return;
            }
        } catch (Exception e) {
            log("ERROR at PromotionCheckerController: " + e.getMessage());
        } finally {
            request.setAttribute("name", userName);
            request.setAttribute("phoneNumber", phoneNumber);
            request.setAttribute("address", address);
            request.setAttribute("ward", ward);
            request.setAttribute("district", district);
            request.setAttribute("city", city);
            request.setAttribute("note", note);
            request.setAttribute("CART_CHECKOUT", cartList);
            request.setAttribute("CART_TOTAL_PRICE", originalPrice); // Đặt giá gốc vào thuộc tính request
            request.setAttribute("CART_FINAL_PRICE", cart.getTotalPrice()); // Đặt giá sau khuyến mãi vào thuộc tính request
            request.getRequestDispatcher(url).forward(request, response);
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the
    // + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(PromotionCheckerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(PromotionCheckerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
