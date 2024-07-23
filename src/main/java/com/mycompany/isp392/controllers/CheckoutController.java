/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.isp392.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import utils.DbUtils;

import com.mycompany.isp392.cart.*;
import com.mycompany.isp392.order.*;
import com.mycompany.isp392.product.*;
import com.mycompany.isp392.promotion.*;
import com.mycompany.isp392.user.*;

/**
 *
 * @author notlongfen
 */
@WebServlet(name = "CheckoutController", urlPatterns = { "/CheckoutController" })
public class CheckoutController extends HttpServlet {
    private static final String NOT_LOGED_IN = "US_SignIn.jsp";
    private static final String ERROR = "US_Checkout.jsp";
    private static final String SUCCESS = "ViewUSOrderDetailsController";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        HttpSession session = request.getSession();
        CartDTO cart = (CartDTO) request.getAttribute("CART_INFO");
        CartDAO cartDAO = new CartDAO();
        UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");

        // List<ProductDetailsDTO> products = (List<ProductDetailsDTO>)
        // session.getAttribute("LIST_PRODUCT");
        if (cart == null) {
            cart = cartDAO.getCartByCustomerID(user.getUserID());
            if (cart == null) {
                url = ERROR;
                CartError error = new CartError();
                error.setError("Your cart is empty");
                request.getRequestDispatcher(url).forward(request, response);
            }
        }
        
        String promotionName = request.getParameter("promotionName");
        if (promotionName == "") {
            promotionName = "NOSALE";
        }

        if (cart.getPromotionID() == 0) {
            cart.setPromotionID(3);
        }
        if (user == null) {
            url = NOT_LOGED_IN;
            request.getRequestDispatcher(url).forward(request, response);
        }
        try {
            UserDTO userDTO = (UserDTO) session.getAttribute("LOGIN_USER");
            UserDAO userDAO = new UserDAO();
            // PromotionDAO promotionDAO = new PromotionDAO();
            String name = request.getParameter("name");
            String ward = request.getParameter("ward");
            String district = request.getParameter("district");
            String city = request.getParameter("city");
            String address = request.getParameter("address");
            String note = request.getParameter("note");
            int phone = Integer.parseInt(request.getParameter("phone"));
            double finalPrice = Double.parseDouble(request.getParameter("finalPrice"));

            List<CartDetailsDTO> cartDetails = cartDAO.getCartItems(cart.getCartID());
            // int userPoint = userDAO.getCustomerByID(userDTO.getUserID()).getPoints();
            // int minPointToApplyCoupon =
            // promotionDAO.getPromotionByID(promotionID).getCondition();

            // // Check if user point is enough to apply coupon
            // if (userPoint >= minPointToApplyCoupon) {
            // double percentage =
            // promotionDAO.getPromotionByID(promotionID).getDiscountPer() / 100;
            // int point = userDAO.getCustomerByID(userDTO.getUserID()).getPoints() - 100;
            // userDAO.updateUserPoint(userDTO.getUserID(), point);
            // cart.setTotalPrice(cart.getTotalPrice() - (cart.getTotalPrice() *
            // percentage));

            // } else {
            // PromotionError pe = new PromotionError();
            // pe.setConditionError("Your membership point does not reach the condition of
            // this promotion " + "("
            // + minPointToApplyCoupon + " points)" + "Your point: " + userPoint + "
            // points");
            // request.setAttribute("PROMOTION_ERROR", pe);
            // return;
            // }
            PromotionDAO promotionDAO = new PromotionDAO();
            PromotionDTO promotionInCart = promotionDAO.getPromotionByID(cart.getPromotionID());
            String promotionNameInCart = promotionInCart.getPromotionName();
            double percentage = promotionInCart.getDiscountPer() / 100.0;
            // if (!promotionName.equals(promotionNameInCart)) {
            //     PromotionError pe = new PromotionError();
            //     pe.setConditionError("You should apply your current voucher first or retype the voucher code");
            //     request.setAttribute("PROMOTION_ERROR", pe);
            //     return;
            // } else if (promotionName.equals(promotionNameInCart) && promotionInCart.getDiscountPer() != 0) {
            if(percentage != 0){ //Check if promotion is applied
                int pointa = userDAO.getCustomerByID(userDTO.getUserID()).getPoints() - 100;
                userDAO.updateUserPoint(userDTO.getUserID(), pointa);
                cart.setTotalPrice(cart.getTotalPrice() - (cart.getTotalPrice() * percentage) );
            }
            // }

            // Add Order
            OrderDAO orderDAO = new OrderDAO();
            ProductDAO productDAO = new ProductDAO();
            OrderDTO order = orderDAO.insertOrder(finalPrice, user.getUserID(),
                    promotionInCart.getPromotionID(), cart.getCartID(),
                    name, city, district, ward, address, phone, note);

            if (order != null) {
                // OrderDetailsDTO odDTO = new OrderDetailsDTO(order.getOrderID(), cart.prod,
                // promotionID, phone); //not done
                // OrderDetailsDTO orderDetailsDTO = orderDAO.insertOrderDetails()

                // FIXME: NEED CHECK
                for (CartDetailsDTO cartDetail : cartDetails) { // Add order details 
                    OrderDetailsDTO orderDetailsDTO = new OrderDetailsDTO(cartDetail.getProductDetailsID(),
                            order.getOrderID(), cartDetail.getProductID(), cartDetail.getQuantity(),
                            cartDetail.getPrice());
                    orderDAO.insertOrderDetails(orderDetailsDTO);
                    // productDAO.updateQuantittyAfterCheckout(cartDetail.getProductID(), cartDetail.getQuantity());
                }

                session.removeAttribute("cart");
                boolean check = cartDAO.updateCartStaus(cart.getCartID(), 0);
                // Update user point
                HashMap<Integer, Integer> priceAndPoint = new HashMap<>();
                priceAndPoint.put(500_000, 10);
                priceAndPoint.put(1_000_000, 20);

                double total = cart.getTotalPrice();
                int points = userDAO.getCustomerByID(userDTO.getUserID()).getPoints();

                if (check) {
                    while (total >= 0) {
                        if (total >= 1_000_000) {
                            points += priceAndPoint.get(1_000_000);
                            total -= 1_000_000;
                        } else if (total >= 500_000) {
                            points += priceAndPoint.get(500_000);
                            total -= 500_000;
                        } else {
                            break;
                        }
                    }

                    int finalUserPoint = userDAO.updateUserPoint(userDTO.getUserID(), points);

                    if (finalUserPoint != 0) {
                        // Update best sellet attribute
                        // for (CartDetailsDTO cartDetail : cartDetails) { // TODO: NEED CHECK
                        //     productDAO.updateProductNumberOfPurchasedItems(cartDetail.getProductID(),
                        //             cartDetail.getQuantity());
                        // }
                        // List<String> newList = new ArrayList<>();
                        // newList.add(String.valueOf(order.getOrderID()));
                        // newList.add(String.valueOf(order.getTotal()));
                        // newList.add(String.valueOf(order.getPromotionID()));
                        // newList.add(String.valueOf(order.getCartID()));
                        // newList.add(order.getUserName());
                        // newList.add(order.getCity());
                        // newList.add(order.getDistrict());
                        // newList.add(order.getWard());
                        // newList.add(order.getAddress());
                        // newList.add(String.valueOf(order.getPhone()));
                        // newList.add(order.getNote());
                        
                        // ManageOrderDTO manageOrder = new ManageOrderDTO(order.getOrderID(), user.getUserID(), new ArrayList<>(), newList,"checkout");
                        // DbUtils.addCheckLogToDB("ManageOrders", "orderID", manageOrder);
                        url = "SendMailServlet?email=" + user.getEmail() + "&orderID=" + order.getOrderID() + "&action=checkout";
                        request.getRequestDispatcher(url).include(request, response);
                        if(request.getAttribute("ERROR_SEND_MAIL") != null) {
                            url = ERROR;
                            return;
                        }
                        url = SUCCESS;
                    }
                }
            }

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the
    // + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
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
