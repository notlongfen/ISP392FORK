package com.mycompany.isp392.controllers;

import com.mycompany.isp392.promotion.ManagePromotionDTO;
import com.mycompany.isp392.user.EmployeeDTO;
import com.mycompany.isp392.user.ManageUserDTO;
import com.mycompany.isp392.user.UserDAO;
import com.mycompany.isp392.user.UserDTO;
import com.mycompany.isp392.user.UserError;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;
import utils.DbUtils;

@WebServlet(name = "AddEmployeeController", urlPatterns = {"/AddEmployeeController"})
public class AddEmployeeController extends HttpServlet {

    private static final String ERROR = "AD_AddEmployees.jsp";
    private static final String SUCCESS = "SearchUserController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        UserDAO dao = new UserDAO();
        UserError userError = new UserError();
        boolean checkValidation = true;

        try {
            String userName = request.getParameter("userName");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            int phone = Integer.parseInt(request.getParameter("phone"));
            int roleID = Integer.parseInt(request.getParameter("roleID"));

            if (dao.checkEmailExists(email) != -1) {
                userError.setEmailError("Email already exists.");
                checkValidation = false;
            }

            if (dao.checkPhoneExists(phone)) {
                userError.setPhoneError("Phone number already exists.");
                checkValidation = false;
            }

            if (String.valueOf(phone).length() != 9 || String.valueOf(phone).length() != 10) {
                userError.setPhoneError("Phone number must be 9 or 10 digits.");
                checkValidation = false;
            }

            if (checkValidation) {
                String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
                UserDTO newUser = new UserDTO(userName, email, hashedPassword, roleID, phone, 1);
                boolean checkAddManager = dao.addEmployee(newUser);

                if (checkAddManager) {
                    List<String> newField = new ArrayList<>();
                    EmployeeDTO empl = dao.getLastEmployeeByID();
                    UserDTO user = (UserDTO) request.getSession().getAttribute("LOGIN_USER");
                    int empID = user.getUserID();
                    newField.add("Name: " + userName);
                    newField.add("Email: " + email);
                    newField.add("Password: " + password);
                    newField.add("Phone: " + String.valueOf(phone));
                    newField.add("RoleID: " + String.valueOf(roleID));


                    ManageUserDTO manage = new ManageUserDTO(empl.getEmpID(), empID, new ArrayList<>(), newField, "Add");
                    DbUtils.addCheckLogToDB("SuperviseEmployees", "UserID", manage);
                    request.setAttribute("SUCCESS_MESSAGE", "EMPLOYEE ADDED SUCCESSFULLY !");
                    url = SUCCESS;
                } else {
                    userError.setError("UNABLE TO ADD EMPLOYEE TO DATABASE !");
                    request.setAttribute("USER_ERROR", userError);
                }
            } else {
                request.setAttribute("USER_ERROR", userError);
            }

        } catch (Exception e) {
            log("Error at AddEmployeeController: " + e.toString());
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
