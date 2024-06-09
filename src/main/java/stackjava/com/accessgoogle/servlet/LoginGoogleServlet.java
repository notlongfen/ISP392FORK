/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package stackjava.com.accessgoogle.servlet;

import com.mycompany.isp392.user.UserDAO;
import com.mycompany.isp392.user.UserDTO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import stackjava.com.accessgoogle.common.GooglePojo;
import stackjava.com.accessgoogle.common.GoogleUtils;

/**
 *
 * @author notlongfen
 */
@WebServlet(name = "LoginGoogleServlet", urlPatterns = {"/ISP392/google-login"})
public class LoginGoogleServlet extends HttpServlet {
private static final long serialVersionUID = 1L;
  public LoginGoogleServlet() {
    super();
  }
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String code = request.getParameter("code");
    HttpSession session = request.getSession();
    
    if (code == null || code.isEmpty()) {
      RequestDispatcher dis = request.getRequestDispatcher("login.jsp");
      dis.forward(request, response);
    } else {
      String accessToken = GoogleUtils.getToken(code);
      GooglePojo googlePojo = GoogleUtils.getUserInfo(accessToken);
      request.setAttribute("id", googlePojo.getId());
      request.setAttribute("name", googlePojo.getName());
      request.setAttribute("email", googlePojo.getEmail());
      String id = googlePojo.getId();
      String email = googlePojo.getEmail();
      UserDAO dao = new UserDAO();
      UserDTO dto = null;
        try {
            dto = new UserDTO(dao.getLastUserId(),email, email, "***", 1, 0, true);
        } catch (SQLException ex) {
            Logger.getLogger(LoginGoogleServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
      session.setAttribute("LOGIN_USER", dto);
      System.out.println(dto);
      RequestDispatcher dis = request.getRequestDispatcher("admin.jsp");
      dis.forward(request, response);
    }
  }
}
