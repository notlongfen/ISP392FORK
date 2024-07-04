/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.isp392.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.mycompany.isp392.cart.CartDAO;
import com.mycompany.isp392.cart.CartDTO;
import com.mycompany.isp392.cart.CartDetailsDTO;
import com.mycompany.isp392.cart.CartError;
import com.mycompany.isp392.order.OrderDAO;
import com.mycompany.isp392.order.OrderDTO;
import com.mycompany.isp392.order.OrderDetailsDTO;
import com.mycompany.isp392.product.ProductDTO;
import com.mycompany.isp392.product.ProductDetailsDTO;
import com.mycompany.isp392.promotion.PromotionDAO;
import com.mycompany.isp392.user.UserDTO;

/**
 *
 * @author notlongfen
 */
@WebServlet(name = "CheckoutController", urlPatterns = {"/CheckoutController"})
public class CheckoutController extends HttpServlet {
    private static final String NOT_LOGED_IN = "US_SignIn.jsp";
    private static final String ERROR = "US_Checkout.jsp";

    
    
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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CheckoutController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CheckoutController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        String url = ERROR;
        HttpSession session = request.getSession();
        CartDTO cart = (CartDTO) session.getAttribute("cart");
        CartDAO cartDAO = new CartDAO();
        UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
        if(cart == null){
                cart = cartDAO.getCartByCustomerID(user.getUserID());
                if(cart == null){
                    url = ERROR;
                    request.getRequestDispatcher(url).forward(request, response);
                    CartError error = new CartError();
                    error.setError("Your cart is empty");
                }
        }
        // List<ProductDetailsDTO> products = (List<ProductDetailsDTO>)
        // session.getAttribute("LIST_PRODUCT");
        String promotionIDS = request.getParameter("promotionID");
        int promotionID = 0;
        if(promotionIDS != null){
            promotionID = Integer.parseInt(promotionIDS);
        }
        if(user == null){
            url = NOT_LOGED_IN;
            request.getRequestDispatcher(url).forward(request, response);
        }
        try {
            String name = request.getParameter("name");
            String ward = request.getParameter("ward");
            String district = request.getParameter("district");
            String city = request.getParameter("city");
            String address = request.getParameter("address");
            String note = request.getParameter("note");
            int phone = Integer.parseInt(request.getParameter("phone"));
            PromotionDAO promotionDAO = new PromotionDAO();
            List<CartDetailsDTO> cartDetails = cartDAO.getCartItems(cart.getCartID());
            double percentage = promotionDAO.getPromotionByID(promotionID).getDiscountPer() / 100;
            cart.setTotalPrice(cart.getTotalPrice() - (cart.getTotalPrice() * percentage));

            OrderDAO orderDAO = new OrderDAO();
            OrderDTO order = orderDAO.insertOrder(cart.getTotalPrice(), user.getUserID(), promotionID, cart.getCartID(), name, city, district, ward, address, phone, note);
            if(order != null){
                // OrderDetailsDTO odDTO = new OrderDetailsDTO(order.getOrderID(), cart.prod, promotionID, phone); //not done
                // OrderDetailsDTO orderDetailsDTO = orderDAO.insertOrderDetails()
                for(CartDetailsDTO cartDetail : cartDetails){
//                    OrderDetailsDTO orderDetailsDTO = new OrderDetailsDTO(order.getOrderID(), cartDetail.getProductID(), cartDetail.getQuantity(), cartDetail.getPrice());
//                    orderDAO.insertOrderDetails(orderDetailsDTO);
                    productDAO.updateQuantittyAfterCheckout(cartDetail.getProductID(), cartDetail.getQuantity());
                }
                session.removeAttribute("cart");
                //boolean check = cartDAO.updateCartDetails();
            }


        } catch (Exception e) {
            // TODO: handle exception
        }finally{
            request.getRequestDispatcher(url).forward(request, response);
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
