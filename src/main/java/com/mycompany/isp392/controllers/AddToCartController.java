/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.isp392.controllers;


import com.mycompany.isp392.cart.*;
import com.mycompany.isp392.product.*;
import com.mycompany.isp392.user.*;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


/**
 *
 * @author TTNHAT
 */
@WebServlet(name = "AddToCartController", urlPatterns = {"/AddToCartController"})
public class AddToCartController extends HttpServlet {
    
    private static final String ERROR = "productDetails.jsp";
    private static final String SUCCESS = "productDetails.jsp";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        CartDAO cartDao = new CartDAO();
        CartError error = new CartError();
        ProductDAO productDao = new ProductDAO();
        
        HttpSession session = request.getSession();
        UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
        if (loginUser == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        try{
            boolean checkValidation = true;
            int custID = Integer.parseInt(request.getParameter("custID"));
            int productID = Integer.parseInt(request.getParameter("productID"));
            int productDetailsID = Integer.parseInt(request.getParameter("productDetailsID"));
            int unitPrice = Integer.parseInt(request.getParameter("unitPrice"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            int price = quantity * unitPrice;
            int cartID = cartDao.getCartIDByCustomer(custID);
            
            //check if customer have cart already, if not, create new one
            if(cartID ==-1){
                cartID = cartDao.getLatestCartID() + 1;
                CartDTO cart = new CartDTO(cartID, 0, custID, 0,1);
                boolean checkCart = cartDao.createCart(cart);
                if(!checkCart){
                    error.setError("Unable to create new cart");
                    checkValidation = false;
                }
            }
            //check product stock and status
            ProductDetailsDTO productDetails = productDao.getProductDetailsByID(productDetailsID);
            if(quantity > productDetails.getStockQuantity()){
                error.setQuantityError("There's not enough products available");
                checkValidation = false;
            }
            if(productDetails.getStatus()!=1){
                error.setProductError("This product is not available");
                checkValidation = false;
            }
            
//            if(checkValidation){
//                CartDetailsDTO details = new CartDetailsDTO(cartID, productID, productDetailsID, price, quantity);
//                boolean checkCartDetails = cartDao.addToCart(details);
//                if(checkCartDetails){
//                    url=SUCCESS;
//                }else{
//                    error.setError("Unable to update database");
//                    request.setAttribute("CART_ERROR", error);
//                }
//            }else{
//                request.setAttribute("CART_ERROR", error);
//            }

            if(checkValidation){
                CartDetailsDTO existingDetails = cartDao.getCartDetails(cartID, productDetailsID);
                if(existingDetails != null){
                    int newQuantity = existingDetails.getQuantity() + quantity;
                    int newPrice = newQuantity * unitPrice;
                    CartDetailsDTO newDetails = new CartDetailsDTO(cartID, productID, productDetailsID, newQuantity, newPrice);
                    boolean updateCartDetails = cartDao.updateCartDetails(newDetails);
                    if(updateCartDetails){
                        url = SUCCESS;
                    } else {
                        error.setError("Unable to update database");
                        request.setAttribute("CART_ERROR",error);
                    }
                } else {
                    CartDetailsDTO details = new CartDetailsDTO(cartID, productID, productDetailsID, quantity, price);
                    boolean checkCartDetails = cartDao.addToCart(details);
                    if(checkCartDetails){
                        url=SUCCESS;
                    }else{
                        error.setError("Unable to update database");
                        request.setAttribute("CART_ERROR", error);
                    }
                }
            }else{
                request.setAttribute("CART_ERROR", error);
            }
        }catch (Exception e) {
            log("Error at AddToCartController: " + e.toString());
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
