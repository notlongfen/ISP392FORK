/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.isp392.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.mycompany.isp392.cart.CartDAO;
import com.mycompany.isp392.cart.CartDTO;
import com.mycompany.isp392.cart.CartDetailsDTO;
import com.mycompany.isp392.cart.CartError;
import com.mycompany.isp392.product.ProductDAO;
import com.mycompany.isp392.product.ProductDTO;
import com.mycompany.isp392.promotion.PromotionDAO;
import com.mycompany.isp392.promotion.PromotionDTO;
import com.mycompany.isp392.promotion.PromotionError;
import com.mycompany.isp392.user.UserDAO;
import com.mycompany.isp392.user.UserDTO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author notlongfen
 */
@WebServlet(name = "PromotionCheckerController", urlPatterns = {"/PromotionCheckerController"})
public class PromotionCheckerController extends HttpServlet {

    private static final String ERROR = "US_Checkout.jsp";
    private static final String SUCCESS = "US_Checkout.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession sesssion = request.getSession();
        String url = ERROR;
        try {
            UserDTO userDTO = (UserDTO) sesssion.getAttribute("LOGIN_USER");
            UserDAO userDAO = new UserDAO();
            PromotionDAO promotionDAO = new PromotionDAO();

            String promotionName = request.getParameter("promotionName");
            request.setAttribute("PROMOTION_NAME", promotionName);
            CartDTO cart = (CartDTO) sesssion.getAttribute("CART");
            CartDAO cartDAO = new CartDAO();
            if (cart == null) {
                cart = cartDAO.getCartByCustomerID(userDTO.getUserID());
//                request.setAttribute("CART_TOTAL_PRICE", cart);
                if (cart == null) {
                    CartError error = new CartError();
                    error.setError("Your cart is empty");
                    request.getRequestDispatcher(url).forward(request, response);
                }
            }
            int userPoint = userDAO.getCustomerByID(userDTO.getUserID()).getPoints();
            PromotionDTO promotionDTO = promotionDAO.getPromotionByName(promotionName);

            if (promotionDTO == null) {
                PromotionError pe = new PromotionError();
                pe.setConditionError("This promotion is not exist");
                request.setAttribute("PROMOTION_ERROR", pe);
                return;
            }

            int minPointToApplyCoupon = promotionDTO.getCondition();

            // Check if user point is enough to apply coupon
            if (userPoint >= minPointToApplyCoupon) {
                double percentage = (double) promotionDTO.getDiscountPer() / 100.0;
                // int point = userDAO.getCustomerByID(userDTO.getUserID()).getPoints() - 100;
                // userDAO.updateUserPoint(userDTO.getUserID(), point);
                double originalPrice = cart.getTotalPrice(); // Lưu lại giá gốc trước khi áp dụng khuyến mãi
                double newPrice = cart.getTotalPrice() - (cart.getTotalPrice() * percentage) + 40000;
                cart.setTotalPrice(newPrice);
                List<CartDetailsDTO> cartList = cartDAO.getCartItems(cart.getCartID());
                request.setAttribute("CART_CHECKOUT", cartList);
                request.setAttribute("CART_TOTAL_PRICE", originalPrice); // Đặt giá gốc vào thuộc tính request
                request.setAttribute("CART_FINAL_PRICE", cart.getTotalPrice()); // Đặt giá sau khuyến mãi vào thuộc tính request
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
        processRequest(request, response);
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
        processRequest(request, response);
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
