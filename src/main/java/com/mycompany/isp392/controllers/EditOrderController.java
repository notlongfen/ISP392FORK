
package com.mycompany.isp392.controllers;

import com.mycompany.isp392.cart.CartDAO;
import com.mycompany.isp392.order.ManageOrderDTO;
import com.mycompany.isp392.order.OrderDAO;
import com.mycompany.isp392.order.OrderDTO;
import com.mycompany.isp392.order.OrderDetailsDTO;
import com.mycompany.isp392.product.ManageProductDTO;
import com.mycompany.isp392.product.ProductDAO;
import com.mycompany.isp392.product.ProductDetailsDTO;
import com.mycompany.isp392.promotion.PromotionDAO;
import com.mycompany.isp392.user.CustomerDTO;
import com.mycompany.isp392.user.UserDAO;
import com.mycompany.isp392.user.UserDTO;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.DbUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@WebServlet(name = "EditOrderController", urlPatterns = { "/EditOrderController" })
public class EditOrderController extends HttpServlet {

    private static final String ERROR = "GetOrderInforController";
    private static final String SUCCESS = "GetOrderListController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            int orderID = Integer.parseInt(request.getParameter("orderID"));
            int status = Integer.parseInt(request.getParameter("status"));

            int oldStatus = Integer.parseInt(request.getParameter("oldStatus"));

            UserDTO user = (UserDTO) request.getSession().getAttribute("LOGIN_USER");

            HashMap<Integer, Integer> priceAndPoint = new HashMap<>();
            priceAndPoint.put(500_000, 10);
            priceAndPoint.put(1_000_000, 20);

            OrderDAO orderDAO = new OrderDAO();
            PromotionDAO promotionDAO = new PromotionDAO();
            UserDAO userDAO = new UserDAO();
            CartDAO cartDAO = new CartDAO();

            boolean check = orderDAO.editOrderStatus(orderID, status);
            if (check) {
                url = "SendMailServlet";
                request.setAttribute("REQFROM", "Update_Order_Status");
                request.setAttribute("MESSAGE", "Order status updated successfully! With status" + status);
                request.setAttribute("status", status);
                request.setAttribute("orderID", orderID);
                request.getRequestDispatcher(url).include(request, response);

                // update user point if have promotion , update product quantity, update order
                // details
                OrderDTO orderDTO = orderDAO.getOrderInfo(orderID);
                List<OrderDetailsDTO> listOrderDetails = orderDAO.getListOrderDetailsByOrderID(orderID);

                int promotionID = orderDTO.getPromotionID();
                double percentage = promotionDAO.getPromotionByID(promotionID).getDiscountPer() / 100.0;

                if (percentage != 0) { // if have promotion
                    double total = (orderDTO.getTotal() - 40_000) / (1 - percentage);
                    int point = 0;
                    CustomerDTO customer = userDAO.getCustomerByID(orderDTO.getCustID());
                    int userPointBefore = customer.getPoints();

                    while (total >= 0) {
                        if (total >= 1_000_000) {
                            total /= 1_000_000;
                            point = (int) total * priceAndPoint.get(1_000_000);

                        } else if (total >= 500_000) {
                            total /= 500_000;
                            point = (int) total * priceAndPoint.get(500_000);
                        } else {
                            break;
                        }
                    }
                    int finalUserPoint = userDAO.updateUserPoint(orderDTO.getCustID(), userPointBefore - point + 100);
                    System.out.println("finalUserPoint: " + finalUserPoint);
                }

                if (status == 3) { //delivery // TODO: NEED CHECK
                    for (OrderDetailsDTO orderDetailsDTO : listOrderDetails) {
                        ProductDAO productDAO = new ProductDAO();
                        ProductDetailsDTO productDetailsDTO = productDAO.getProductDetailsByID(orderDetailsDTO.getProductDetailsID());

                        int productDetailID = productDetailsDTO.getProductDetailsID();
                        // int quantity = productDetailsDTO.getStockQuantity() - orderDetailsDTO.getQuantity(); WRONG CAUSE THE DATABASE DAO
                        productDAO.updateQuantittyAfterCheckout(orderDetailsDTO.getProductID(), productDetailID, orderDetailsDTO.getQuantity());
                    }
                }

                List<String> oldList = new ArrayList<>();
                List<String> newList = new ArrayList<>();

                if (oldStatus != status) {
                    oldList.add("Status: " + String.valueOf(oldStatus));
                    newList.add("Status: " + String.valueOf(status));
                }

                if (oldList.size() > 0 && newList.size() > 0) {
                    ManageOrderDTO manageOrder = null;

                    if (oldStatus == 1 && status == 0) {
                        manageOrder = new ManageOrderDTO(orderID, user.getUserID(), oldList, newList, "Delete");
                    } else {
                        manageOrder = new ManageOrderDTO(orderID, user.getUserID(), oldList, newList, "Edit");
                    }
                    DbUtils.addCheckLogToDB("ManageOrders", "OrderID", manageOrder);

                }

            }
            if (check) {
                request.setAttribute("SUCCESS_MESSAGE", "Order status updated successfully!");
                url = SUCCESS;
            }
        } catch (Exception e) {
            log("Error at UpdateOrderController: " + e.toString());
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
