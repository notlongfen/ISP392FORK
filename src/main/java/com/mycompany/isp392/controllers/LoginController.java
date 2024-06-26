
package com.mycompany.isp392.controllers;

import com.mycompany.isp392.user.UserDAO;
import com.mycompany.isp392.user.UserDTO;
import io.github.cdimascio.dotenv.Dotenv;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "LoginController", urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet {

    private static final String ERROR = "US_SignIn.jsp";
    private static final int CUSTOMER = 4;
    private static final String CUSTOMER_PAGE = "customer.jsp";
    private static final int SYSTEM_MANAGER = 1;
    private static final int SHOP_MANAGER = 2;
    private static final int SHOP_STAFF = 3;
    private static final String SYSTEM_MANAGER_PAGE = "systemManager.jsp";
    private static final String SHOP_MANAGER_PAGE = "shopManager.jsp";
    private static final String SHOP_STAFF_PAGE = "shopStaff.jsp";
    private String clientID;
    public void initClientID() throws ServletException{
        Dotenv dotenv = Dotenv.configure().directory("/home/notlongfen/code/java/ISP392/.env").load();
        clientID = dotenv.get("GOOGLE_CLIENT_ID");
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        initClientID();
        request.setAttribute("CLIENT_ID", clientID);
        String url = ERROR;
        try {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            UserDAO dao = new UserDAO();
            UserDTO loginUser = dao.checkLogin(email, password);
            if (loginUser != null) {
                HttpSession session = request.getSession();
                session.setAttribute("LOGIN_USER", loginUser);
                int roleID = loginUser.getRoleID();
                switch (roleID) {
                    case CUSTOMER:
                        url = CUSTOMER_PAGE;
                        break;
                    case SYSTEM_MANAGER:
                        url = SYSTEM_MANAGER_PAGE;
                        break;
                    case SHOP_MANAGER:
                        url = SHOP_MANAGER_PAGE;
                        break;
                    case SHOP_STAFF:
                        url = SHOP_STAFF_PAGE;
                        break;
                    default:
                        request.setAttribute("ERROR", "YOUR ROLE IS NOT SUPPORTED !");
                        break;
                }
            } else {
                request.setAttribute("ERROR", "INCORRECT EMAIL OR PASSWORD !");
            }

        } catch (Exception e) {
            log("Error at LoginController: " + e.toString());
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
