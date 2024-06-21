package com.mycompany.isp392.controllers;

import java.io.IOException;
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

@WebServlet(name = "ForgotPasswordController", urlPatterns = { "/ForgotPasswordController" })
public class ForgotPasswordController extends HttpServlet {
    private static final String ERROR = "forgotPassword.jsp";
    private static final String SUCCESS = "verifyForgetPassword.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

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
                if (check) {
                    ForgetPasswordDTO dto = fpdao.getAllInfoByUserID(userID);
                    String subject = "Reset Password";
                    String messageBody = "Click the link below to reset your password: http://localhost:8080/ISP392/verifyForgetPassword.jsp?token="
                            + dto.getToken();

                    final String fromEmail = "micomicomun@gmail.com";
                    final String password = "ezox gkgv joqr mbwx";

                    Properties props = new Properties();
                    props.put("mail.smtp.auth", "true");
                    props.put("mail.smtp.starttls.enable", "true");
                    props.put("mail.smtp.host", "smtp.gmail.com");
                    props.put("mail.smtp.port", "587");

                    Session session = Session.getInstance(props,
                            new jakarta.mail.Authenticator() {
                                @Override
                                protected PasswordAuthentication getPasswordAuthentication() {
                                    return new PasswordAuthentication(fromEmail, password);
                                }
                            });

                    try {
                        Message message = new MimeMessage(session);
                        message.setFrom(new InternetAddress(fromEmail));
                        message.setRecipients(Message.RecipientType.TO,
                                InternetAddress.parse(email));
                        message.setSubject(subject);
                        message.setText(messageBody);

                        Transport.send(message);
                    } catch (Exception e) {
                        error.setError("Send email failed.");
                        e.printStackTrace();
                    }

                    url = SUCCESS;
                } else {
                    error.setError("Insert token failed.");
                    request.setAttribute("ERROR", error);
                }
            } else {
                error.setError("Email does not exist.");
                request.setAttribute("ERROR", error);
            }

        } catch (Exception e) {
            error.setError("Failed to send email");
            e.printStackTrace();
        } finally {
            response.sendRedirect(url);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
