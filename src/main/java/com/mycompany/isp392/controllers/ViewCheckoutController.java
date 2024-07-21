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
import com.mycompany.isp392.promotion.PromotionDAO;
import com.mycompany.isp392.promotion.PromotionDTO;
import com.mycompany.isp392.user.*;

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
@WebServlet(name = "ViewCheckoutController", urlPatterns = {"/ViewCheckoutController"})
public class ViewCheckoutController extends HttpServlet {

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
        String url = ERROR;
        HttpSession session = request.getSession();

        try {
            UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
            CartDTO cart = (CartDTO) request.getAttribute("CART");
            if (cart == null) {
                CartDAO cartDAO = new CartDAO();
                cart = cartDAO.getCartByCustomerID(user.getUserID());
            }
            ProductDAO productDAO = new ProductDAO();
            CartDAO cartDAO = new CartDAO();
            PromotionDAO promotionDAO = new PromotionDAO();
//            List<ProductDetailsDTO> productList = productDAO.getProductByCartID(cart.getCartID());
            List<CartDetailsDTO> cartList = cartDAO.getCartItems(cart.getCartID());
            PromotionDTO promotionDTO = promotionDAO.getPromotionByID(cart.getPromotionID());
            request.setAttribute("PROMOTION", promotionDTO);
//            request.setAttribute("PRODUCT_LIST", productList);
//            session.setAttribute("CUST", user);
            request.setAttribute("CART_CHECKOUT", cartList);
            request.setAttribute("CART_INFO", cart);

        } catch (Exception e) {
            // TODO: handle exception
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
