package com.mycompany.isp392.controllers;

import com.mycompany.isp392.brand.*;
import com.mycompany.isp392.user.UserDTO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeleteBrandController extends HttpServlet {

    private static final String ERROR = "brand.jsp";
    private static final String SUCCESS = "GetBrandsController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        BrandError brandError = new BrandError();
        try {
            HttpSession session = request.getSession();
            UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
            int empID = user.getUserID();
            int brandID = Integer.parseInt(request.getParameter("id"));
            int oldStatus = Integer.parseInt(request.getParameter("status"));
            String action = request.getParameter("delete");
            BrandDAO brandDAO = new BrandDAO();
            int newStatus = brandDAO.deleteBrand1(brandID);

            if (newStatus != -1) {
                List<String> oldList = new ArrayList<>();
                List<String> newList = new ArrayList<>();
                oldList.add(String.valueOf(oldStatus));
                newList.add(String.valueOf(newStatus));
                ManageBrandDTO manage = new ManageBrandDTO(brandID, empID, oldList, newList, action);
                boolean checkAdd = brandDAO.addManageBrand(manage);
                request.setAttribute("SUCCESS_MESSAGE", "BRAND DELETED SUCCESSFULLY !");
                url = SUCCESS;
            } else {
                brandError.setError("UNABLE TO DELETE BRAND !");
                request.setAttribute("BRAND_ERROR", brandError);
            }
        } catch (SQLException e) {
            log("Error at DeleteBrandController: " + e.toString());
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
        return "Short description";
    }
}
