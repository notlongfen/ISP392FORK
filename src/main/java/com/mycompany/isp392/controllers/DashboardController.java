package com.mycompany.isp392.controllers;

import com.mycompany.isp392.order.OrderDAO;
import com.mycompany.isp392.product.ProductDAO;
import com.mycompany.isp392.product.ProductDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "DashboardController", urlPatterns = {"/DashboardController"})
public class DashboardController extends HttpServlet {

    private static final String ERROR = "error.jsp"; // Define an error page if needed
    private static final String DASHBOARD_PAGE = "AD_Statistic.jsp"; // Your dashboard JSP page

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = ERROR;
        try {
            OrderDAO orderDAO = new OrderDAO();
            ProductDAO productDAO = new ProductDAO();

            // Fetch daily and monthly data
            int totalIncomeToday = orderDAO.getTotalIncomeToday();
            int totalIncomeYesterday = orderDAO.getTotalIncomeYesterday();
            int numberOfOrdersToday = orderDAO.getNumberOfOrdersToday();
            int numberOfOrdersYesterday = orderDAO.getNumberOfOrdersYesterday();
            List<Integer> monthlyIncomes = orderDAO.getMonthlyIncomes();
            List<Integer> monthlyOrders = orderDAO.getMonthlyOrders();

            // Fetch top 5 products based on number of purchases
            List<ProductDTO> top5Products = productDAO.getTop5ProductsByPurchases();

            // Fetch order counts by status
            Map<Integer, Integer> orderCountByStatus = orderDAO.getOrderCountByStatus();

            int pendingOrders = orderCountByStatus.getOrDefault(1, 0);
            int inProgressOrders = orderCountByStatus.getOrDefault(2, 0);
            int deliveringOrders = orderCountByStatus.getOrDefault(3, 0);
            int completedOrders = orderCountByStatus.getOrDefault(4, 0);
            int canceledOrders = orderCountByStatus.getOrDefault(5, 0);

            // Set attributes for the JSP
            request.setAttribute("totalIncomeToday", totalIncomeToday);
            request.setAttribute("totalIncomeYesterday", totalIncomeYesterday);
            request.setAttribute("numberOfOrdersToday", numberOfOrdersToday);
            request.setAttribute("numberOfOrdersYesterday", numberOfOrdersYesterday);
            request.setAttribute("monthlyIncomes", monthlyIncomes);
            request.setAttribute("monthlyOrders", monthlyOrders);
            request.setAttribute("top5Products", top5Products);
            request.setAttribute("pendingOrders", pendingOrders);
            request.setAttribute("inProgressOrders", inProgressOrders);
            request.setAttribute("deliveringOrders", deliveringOrders);
            request.setAttribute("completedOrders", completedOrders);
            request.setAttribute("canceledOrders", canceledOrders);

            url = DASHBOARD_PAGE;
        } catch (Exception e) {
            log("Error at DashboardController: " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Dashboard Controller";
    }
}
