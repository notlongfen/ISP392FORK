/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package stackjava.com.accessgoogle.servlet;

import com.mycompany.isp392.user.CustomerDTO;
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
  private static final String ERROR = "US_SignIn.jsp";
  private static final String SUCCESS = "US_index.jsp";

private static final long serialVersionUID = 1L;
  public LoginGoogleServlet() {
    super();
  }
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String url = ERROR;
    String code = request.getParameter("code");
    HttpSession session = request.getSession();
    
    if (code == null || code.isEmpty()) {
      RequestDispatcher dis = request.getRequestDispatcher("US_SignIn.jsp");
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
      UserDTO userDTO = null;
      CustomerDTO customerDTO = null;
        try {
            if(dao.checkEmailExists(email) == -1){
              userDTO = new UserDTO(dao.getLastUserId(),email, email, "***", 4, 0, 1);
              customerDTO = new CustomerDTO(dao.getLastUserId(), null, "", "", "", "");
              boolean checkAddUserAndCustomer = dao.addAccount(userDTO, customerDTO);
              if(checkAddUserAndCustomer){
                session.setAttribute("LOGIN_USER", userDTO);
                System.out.println(userDTO);
                url = SUCCESS;
              }
            }else{
              session.setAttribute("LOGIN_USER", userDTO);
                System.out.println(userDTO);
                url = SUCCESS;
            }

        } catch (SQLException ex) {
            Logger.getLogger(LoginGoogleServlet.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
          RequestDispatcher dis = request.getRequestDispatcher(url);
          dis.forward(request, response);
        }
    }
  }
}
