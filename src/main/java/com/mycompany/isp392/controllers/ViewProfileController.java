package com.mycompany.isp392.controllers;

import com.mycompany.isp392.user.CustomerDTO;
import com.mycompany.isp392.user.UserDAO;
import com.mycompany.isp392.user.UserDTO;
import com.mycompany.isp392.user.UserError;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
//import java.text.SimpleDateFormat;
//import java.util.Date;

/**
 *
 * @author 84773
 */
@WebServlet(urlPatterns = {"/ViewProfileController"})
public class ViewProfileController extends HttpServlet {

    private static final String SUCCESS = "US_MyProfile.jsp";
    private static final String ERROR = "US_SignIn.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        UserError error = new UserError();
        try {
            HttpSession session = request.getSession();
            UserDAO dao = new UserDAO();
            UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
            int custID = user.getUserID();
            if (user != null) {
                CustomerDTO cust = dao.getFullCustomerByID(custID);
                if (cust != null) {
                    session.setAttribute("custID", custID);
                    session.setAttribute("cust", cust);
                    url = SUCCESS;
                } else {
                    url = ERROR;
                }

            } else {
                error.setError("UNABLE TO GET USER INFORMATION !");
                request.setAttribute("USER_ERROR", error);
            }
        } catch (Exception e) {
            log("Error at ViewProfileController: " + e.toString());
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
