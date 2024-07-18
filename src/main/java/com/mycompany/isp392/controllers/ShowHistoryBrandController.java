/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package com.mycompany.isp392.controllers;

import com.mycompany.isp392.brand.BrandDAO;
import com.mycompany.isp392.brand.BrandDTO;
import com.mycompany.isp392.brand.ManageBrandDTO;
import com.mycompany.isp392.product.ManageProductDTO;
import com.mycompany.isp392.user.ManageUserDTO;
import com.mycompany.isp392.user.UserDTO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import utils.DbUtils;

/**
 *
 * @author PC
 */
@WebServlet(name="ShowHistoryBrandController", urlPatterns={"/ShowHistoryBrandController"})
public class ShowHistoryBrandController extends HttpServlet {
    
    private static final String ERROR = "AD_HistoryManage.jsp";
    private static final String SUCCESS = "AD_HistoryManage.jsp";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();

//            List<ManageBrandDTO> manage = dao.getManageBrand();

            List<ManageBrandDTO> manage = (List<ManageBrandDTO>) DbUtils.getCheckLogFromDB("ManageBrands", "BrandID", ManageBrandDTO.class);
            List<ManageProductDTO> manageProduct = (List<ManageProductDTO>) DbUtils.getCheckLogFromDB("ManageProducts", "ProductID", ManageProductDTO.class);
            List<ManageProductDTO> manageProductDetails = (List<ManageProductDTO>) DbUtils.getCheckLogFromDB("ManageProductDetails", "ProductDetailsID", ManageProductDTO.class);
            // List<UserDTO> manageUser = (List<UserDTO>) DbUtils.getCheckLogFromDB("ManageUsers", "UserID", UserDTO.class);

            session.setAttribute("manageProduct", manageProduct);
            session.setAttribute("manageBrand", manage);
            session.setAttribute("manageProductDetails", manageProductDetails);

            List<ManageBrandDTO> brand = (List<ManageBrandDTO>) DbUtils.getCheckLogFromDB("ManageBrands", "BrandID", ManageBrandDTO.class);
            session.setAttribute("manageBrand", brand);
            
            List<ManageUserDTO> employee = (List<ManageUserDTO>) DbUtils.getCheckLogFromDB("SuperviseEmployees", "UserID", ManageUserDTO.class);
            session.setAttribute("manageEmployee", employee);
            
            List<ManageUserDTO> customer = (List<ManageUserDTO>) DbUtils.getCheckLogFromDB("SuperviseCustomers", "UserID", ManageUserDTO.class);
            session.setAttribute("manageCustomer", customer);
            

        } catch (Exception e) {
            log("Error at SearchController: " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
