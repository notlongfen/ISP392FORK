package com.mycompany.isp392.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 *
 * @author Oscar
 */
@WebServlet(name = "TermAndConditionController", urlPatterns = {"/TermAndConditionController"})
public class TermAndConditionController extends HttpServlet {

    private static final String ERROR = "US_SignUp.jsp";
    private static final String SUCCESS = "US_TermAndCondition.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = SUCCESS;
        String filePath = "D:\\Document\\FPT\\HK5_SU24\\ISP392\\ISP392\\Terms_and_Conditions.txt";
        StringBuilder contentBuilder = new StringBuilder();

        try (BufferedReader br = Files.newBufferedReader(Paths.get(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                contentBuilder.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            url = ERROR;
        } finally {
            String termAndConditionContent = contentBuilder.toString();
            request.setAttribute("termAndConditionContent", termAndConditionContent);
            System.out.println("Term and Condition Content: " + termAndConditionContent); // Debugging
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
