/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.isp392.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import com.mycompany.isp392.forgetpassword.ForgetPasswordDAO;
import com.mycompany.isp392.forgetpassword.ForgetPasswordDTO;
import com.mycompany.isp392.forgetpassword.ForgetPasswordErrors;
import com.mycompany.isp392.user.EmployeeDTO;
import com.mycompany.isp392.user.UserDAO;

import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author notlongfen
 */
@WebServlet(name = "ForgotPasswordController", urlPatterns = { "/ForgotPasswordController" })
public class ForgotPasswordController extends HttpServlet {
    private static final String ERROR = "forgotPassword.jsp";
    private static final String SUCCESS = "verifyForgetPassword.jsp";

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ForgotPasswordController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ForgotPasswordController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        String url = ERROR;
        ForgetPasswordErrors error = new ForgetPasswordErrors();
        try {
            String email = request.getParameter("email");
            UserDAO dao = new UserDAO();
            ForgetPasswordDAO fpdao = new ForgetPasswordDAO();
            int userID = dao.checkEmailExists(email);
            if (userID != -1) {
                request.setAttribute("USER_ID", userID);
                boolean check = fpdao.insertToken(userID);
                // Check if insert successfully
                if (check) {
                    ForgetPasswordDTO dto = fpdao.getAllInfoByUserID(userID);
                    String subject = "Reset Password";
                    String messageBody = "Click the link below to reset your password: http://localhost:8080/ISP392/verifyForgetPassword.jsp?token="
                            + dto.getToken();
                    // Sender's email and password
                    final String fromEmail = "micomicomun@gmail.com";
                    final String password = "ezox gkgv joqr mbwx";

                    // Set up mail server properties
                    Properties props = new Properties();
                    props.put("mail.smtp.auth", "true");
                    props.put("mail.smtp.starttls.enable", "true");
                    props.put("mail.smtp.host", "smtp.gmail.com");
                    props.put("mail.smtp.port", "587");

                    // Get the Session object
                    Session session = Session.getInstance(props,
                            new jakarta.mail.Authenticator() {
                                protected PasswordAuthentication getPasswordAuthentication() {
                                    return new PasswordAuthentication(fromEmail, password);
                                }
                            });

                    try {
                        
                        // Create a default MimeMessage object
                        Message message = new MimeMessage(session);

                        // Set From: header field of the header
                        message.setFrom(new InternetAddress(fromEmail));

                        // Set To: header field of the header
                        message.setRecipients(Message.RecipientType.TO,
                                InternetAddress.parse(email));

                        // Set Subject: header field
                        message.setSubject(subject);

                        // Now set the actual message
                        message.setText(messageBody);

                        // Send message
                        Transport.send(message);
                    } catch (Exception e) {
                        error.setError("Send email failed.");
                        e.printStackTrace();
                    }

                    url = SUCCESS;
                }else{
                    error.setError("Insert token failed.");
                    request.setAttribute("ERROR", error);
                }
            }else{
                error.setError("Email does not exist.");
                request.setAttribute("ERROR", error);
            }

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            response.sendRedirect(url);
        }
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
