/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.isp392.controllers;

import com.mycompany.isp392.product.ProductDAO;
import com.mycompany.isp392.product.ProductDetailsDTO;
import com.mycompany.isp392.user.UserDTO;
import com.mycompany.isp392.wishlist.WishlistDAO;
import com.mycompany.isp392.wishlist.WishlistDTO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author PC
 */
@WebServlet(name = "AddWishlistController", urlPatterns = {"/AddWishlistController"})
public class AddWishlistController extends HttpServlet {

    private static final String ERROR = "US_index.jsp";
    private static final String SUCCESS = "WishlistController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            WishlistDAO dao = new WishlistDAO();
            UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
            int cusID = user.getUserID();
            int productID = Integer.parseInt(request.getParameter("productID"));
            int productDetailID = Integer.parseInt(request.getParameter("productDetailID"));


            boolean customerExists = dao.checkCustomerExists(cusID);
            boolean addWishlist = false;

            if (customerExists) {
                addWishlist = dao.updateWishlistStatus(cusID, 1);
            } else {
                addWishlist = dao.addToWishlist(cusID);
            }

            if (addWishlist) {
                WishlistDTO wishlist = dao.SelectWishlist(cusID);
                int wishlistID = wishlist.getWishlistID();

                boolean wishlistDetailExists = dao.checkWishlistDetailExists(wishlistID, productID);
                boolean addWishlistDetail = false;

                if (wishlistDetailExists) {
                    addWishlistDetail = dao.updateWishlistDetailStatus(wishlistID, productID, productDetailID ,1);
                } else {
                    addWishlistDetail = dao.addToWishlistDetail(wishlistID, productID, productDetailID);
                }

                if (addWishlistDetail) {
                    url = SUCCESS;
                }
            }

        } catch (Exception e) {
            log("Error at SearchController: " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
