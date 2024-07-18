/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.isp392.controllers;


import com.mycompany.isp392.cart.CartDAO;
import com.mycompany.isp392.cart.CartDTO;
import com.mycompany.isp392.cart.CartDetailsDTO;
import com.mycompany.isp392.cart.CartError;
import com.mycompany.isp392.user.UserDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;



/**
 *
 * @author TTNHAT
 */
@WebServlet(name = "ViewCartController", urlPatterns = {"/ViewCartController"})
public class ViewCartController extends HttpServlet {

    private static final String ERROR = "HomePageController";
    private static final String SUCCESS = "US_Cart.jsp";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession session = request.getSession();
        UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
        if (loginUser == null) {
            response.sendRedirect("US_SignIn.jsp");
            return;
        }
        String url = ERROR;
        CartDAO dao = new CartDAO();
        CartError error = new CartError();           
        boolean checkValidation = true;
        try {
            int custID = loginUser.getUserID();
            int cartID = dao.getCartIDByCustomer(custID);
            if (cartID == -1) {
                cartID = dao.getLatestCartID() + 1;
                CartDTO cart = new CartDTO(cartID, 0, custID, 1, 1);
                boolean checkCart = dao.createCart(cart);
                if (!checkCart) {
                    error.setError("Unable to create new cart");
                    checkValidation = false;
                }
            }
            double totalPrice = dao.getTotalPrice(cartID);
            
            if(checkValidation){
                List<CartDetailsDTO> cartDetails = dao.getCartItems(cartID);
                request.setAttribute("CART", cartDetails);
                request.setAttribute("TOTAL_PRICE", totalPrice);
                url = SUCCESS;
            } else {      
                request.setAttribute("CART_ERROR", error);
            }
        } catch (Exception e) {
            log("Error at ViewCartController: " + e.toString());
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
