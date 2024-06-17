package com.mycompany.isp392.controllers;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MainController extends HttpServlet {

    private static final String WELCOME = "login.jsp";

    private static final String LOGIN = "Login";
    private static final String LOGIN_CONTROLLER = "LoginController";

    private static final String REGISTER_PAGE = "Sign_Up";
    private static final String REGISTER_PAGE_VIEW = "signup.jsp";

    private static final String ADD_MANAGER_PAGE = "Add_Manager_View";
    private static final String ADD_MANAGER_PAGE_VIEW = "addManager.jsp";
    private static final String ADD_MANAGER_CONTROLLER = "AddManagerController";
    private static final String ADD_MANAGER = "Add_Manager";

    private static final String ADD_PRODUCT_PAGE = "Add_View";
    private static final String ADD_PRODUCT_PAGE_VIEW = "product.jsp";

    private static final String ADD_PRODUCT = "Ađd_Product";
    private static final String ADD_PRODUCT_CONTROLLER = "AddProductController";

    private static final String ADD_BRAND_PAGE = "Add_Brand_View";
    private static final String ADD_BRAND_PAGE_VIEW = "brand.jsp";

    private static final String ADD_BRAND = "Add_Brand";
    private static final String ADD_BRAND_CONTROLLER = "AddBrandController";

    private static final String SEARCH_BRAND = "Search_Brand";
    private static final String SEARCH_BRAND_CONTROLLER = "SearchBrandController";

    private static final String DELETE_BRAND = "Delete_Brand";
    private static final String DELETE_BRAND_CONTROLLER = "DeleteBrandController";

    private static final String EDIT_BRAND = "Edit_Brand";
    private static final String EDIT_BRAND_CONTROLLER = "UpdateBrandController";

    private static final String REGISTER = "Sign_In";
    private static final String REGISTER_CONTROLLER = "RegisterController";

    private static final String SENDMAIL = "Send_Email";
    private static final String SEND_EMAIL_CONTROLLER = "SendMailServlet";

    private static final String SEARCH_SUPPORT_PAGE = "View_Support";
    private static final String SEARCH_SUPPORT_PAGE_VIEW = "support.jsp";
    private static final String SEARCH_SUPPORT= "Search support";
    private static final String SEARCH_SUPPORT_CONTROLLER = "SearchSupportController";

    private static final String SEARCH_PROMOTION = "Search promotion";
    private static final String SEARCH_PROMOTION_CONTROLLER = "SearchPromotionController";

    private static final String EDIT_PROMOTION = "EditPromotion";
    private static final String EDIT_PROMOTION_PAGE = "CopyPromotionController";
    private static final String SAVE_EDIT_PROMOTION = "SaveEditPromotion";
    private static final String EDIT_PROMOTION_CONTROLLER = "EditPromotionController";

    private static final String DELETE_PROMOTION = "DeletePromotion";
    private static final String DELETE_PROMOTION_CONTROLLER = "DeletePromotionController";

    //temp
    private static final String ADD_CATEGORY_PAGE = "Add_Category_View";
    private static final String ADD_CATEGORY_PAGE_VIEW = "addCategory.jsp";
    private static final String ADD_CATEGORY = "Add_Category";
    private static final String ADD_CATEGORY_CONTROLLER = "AddCategoryController";

    //temp 
    private static final String ADD_CHILDREN_CATEGORY_PAGE = "Add_Children_Category_View";
    private static final String ADD_CHILDREN_CATEGORY_PAGE_VIEW = "addChildrenCategory.jsp";

    private static final String ADD_CHILDREN_CATEGORY = "Add_Children_Category";
    private static final String ADD_CHILDREN_CATEGORY_CONTROLLER = "AddChildrenCategoryController";

    private static final String SEARCH_CATEGORY_PAGE = "Search_Category_View";
    private static final String SEARCH_PAGE_VIEW = "SearchCategory.jsp";
    private static final String SEARCH_CATEGORY = "Search_Category";
    private static final String SEARCH_CATEGORY_CONTROLLER = "SearchCategoryController";

    private static final String EDIT_CATEGORY = "Edit_Category";
    private static final String EDIT_CATEGORY_CONTROLLER = "EditCategoryController";

    private static final String EDIT_CHILDRENCATEGORY = "Edit_ChildrenCategory";
    private static final String EDIT_CHILDRENCATEGORY_CONTROLLER = "EditChildrenCategoryController";

    private static final String ADD_PROMOTION_PAGE = "Add_Promo_View";
    private static final String ADD_PROMOTION_PAGE_VIEW = "addPromotion.jsp";

    private static final String ADD_PROMOTION = "Add_Promotion";
    private static final String ADD_PROMOTION_CONTROLLER = "AddPromotionController";

    private static final String SEARCH_ORDER_PAGE = "Search_Order_View";
    private static final String SEARCH_ORDER_VIEW = "order.jsp";
    private static final String SEARCH_ORDER = "Search_Order";
    private static final String SEARCH_ORDER_CONTROLLER = "SearchOrderController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = WELCOME;
        try {
            String action = request.getParameter("action");
            if (LOGIN.equals(action)) {
                url = LOGIN_CONTROLLER;
            } else if (REGISTER_PAGE.equals(action)) {
                url = REGISTER_PAGE_VIEW;
            } else if (REGISTER.equals(action)) {
                url = REGISTER_CONTROLLER;
            } else if (SENDMAIL.equals(action)) {
                url = SEND_EMAIL_CONTROLLER;
            } else if (ADD_PRODUCT.equals(action)) {
                url = ADD_PRODUCT_CONTROLLER;
            } else if (SEARCH_SUPPORT_PAGE.equals(action)) {
                url = SEARCH_SUPPORT_PAGE_VIEW;
            } else if (SEARCH_SUPPORT.equals(action)) {
                url = SEARCH_SUPPORT_CONTROLLER;
            } else if (ADD_PRODUCT_PAGE.equals(action)) {
                url = ADD_PRODUCT_PAGE_VIEW;
            } else if (ADD_BRAND_PAGE.equals(action)) {
                url = ADD_BRAND_PAGE_VIEW;
            } else if (ADD_BRAND.equals(action)) {
                url = ADD_BRAND_CONTROLLER;
            } else if (SEARCH_BRAND.equals(action)) {
                url = SEARCH_BRAND_CONTROLLER;
            } else if (DELETE_BRAND.equals(action)) {
                url = DELETE_BRAND_CONTROLLER;
            } else if (EDIT_BRAND.equals(action)) {
                url = EDIT_BRAND_CONTROLLER;
            } else if (ADD_PROMOTION_PAGE.equals(action)) {
                url = ADD_PROMOTION_PAGE_VIEW;
            } else if (ADD_PROMOTION.equals(action)) {
                url = ADD_PROMOTION_CONTROLLER;
            } else if (SEARCH_PROMOTION.equals(action)) {
                url = SEARCH_PROMOTION_CONTROLLER;
            } else if (EDIT_PROMOTION.equals(action)) {
                url = EDIT_PROMOTION_PAGE;
            } else if (SAVE_EDIT_PROMOTION.equals(action)) {
                url = EDIT_PROMOTION_CONTROLLER;
            } else if (DELETE_PROMOTION.equals(action)) {
                url = DELETE_PROMOTION_CONTROLLER;
            } else if (ADD_CATEGORY_PAGE.equals(action)) {
                url = ADD_CATEGORY_PAGE_VIEW;
            } else if (ADD_CATEGORY.equals(action)) {
                url = ADD_CATEGORY_CONTROLLER;
            } else if (ADD_CHILDREN_CATEGORY_PAGE.equals(action)) {
                url = ADD_CHILDREN_CATEGORY_PAGE_VIEW;
            } else if (ADD_CHILDREN_CATEGORY.equals(action)) {
                url = ADD_CHILDREN_CATEGORY_CONTROLLER;
            } else if (SEARCH_CATEGORY_PAGE.equals(action)) {
                url = SEARCH_PAGE_VIEW;
            } else if (SEARCH_CATEGORY.equals(action)) {
                url = SEARCH_CATEGORY_CONTROLLER;
            } else if (ADD_MANAGER.equals(action)) {
                url = ADD_MANAGER_CONTROLLER;
            } else if (ADD_MANAGER_PAGE.equals(action)) {
                url = ADD_MANAGER_PAGE_VIEW;
            } else if (EDIT_CHILDRENCATEGORY.equals(action)) {
                url = EDIT_CHILDRENCATEGORY_CONTROLLER;
            } else if (EDIT_CATEGORY.equals(action)) {
                url = EDIT_CATEGORY_CONTROLLER;
            } else if (SEARCH_ORDER_PAGE.equals(action)) {
                url = SEARCH_ORDER_VIEW;
            } else if (SEARCH_ORDER.equals(action)) {
                url = SEARCH_ORDER_CONTROLLER;
            }
        } catch (Exception e) {
            log("error at MainController: " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the
    // + sign on the left to edit the code.">
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
