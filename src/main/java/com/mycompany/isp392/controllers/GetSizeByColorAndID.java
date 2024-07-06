package com.mycompany.isp392.controllers;

import com.google.gson.Gson;
import com.mycompany.isp392.product.ProductDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

public class GetSizeByColorAndID extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");

        try {
            int productID = Integer.parseInt(request.getParameter("productID"));
            String color = request.getParameter("color");

            ProductDAO productDAO = new ProductDAO();
            List<String> sizes = productDAO.getSizesByProductIDandColor(productID, color);

            if (sizes != null) {
                Gson gson = new Gson();
                String json = gson.toJson(sizes);

                try (PrintWriter out = response.getWriter()) {
                    out.print(json);
                }
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND); // Return 404 Not Found if sizes is null
                response.getWriter().write("Sizes not found for the specified product and color.");
            }
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Return 400 Bad Request error if NumberFormatException occurs
            response.getWriter().write("Error converting data. Please provide a valid product ID.");
            e.printStackTrace();
        } catch (SQLException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // Return 500 Internal Server Error if SQLException occurs
            response.getWriter().write("Error querying data from the database.");
            e.printStackTrace();
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
        return "Get Sizes by Color and Product ID";
    }
}
