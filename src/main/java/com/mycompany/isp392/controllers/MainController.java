package com.mycompany.isp392.controllers;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MainController extends HttpServlet {

    private static final String WELCOME = "US_SignIn.jsp";

    private static final String LOGIN = "Login";
    private static final String LOGIN_CONTROLLER = "LoginController";

    private static final String REGISTER_PAGE = "Sign_Up";
    private static final String REGISTER_PAGE_VIEW = "US_SignUp.jsp";
    private static final String REGISTER = "Sign In";
    private static final String REGISTER_CONTROLLER = "RegisterController";

    private static final String ADD_EMPLOYEE_PAGE = "Add_Employee_View";
    private static final String ADD_EMPLOYEE_PAGE_VIEW = "addEmployee.jsp";
    private static final String ADD_EMPLOYEE_CONTROLLER = "AddEmployeeController";
    private static final String ADD_EMPLOYEE = "Add_Employee";

    private static final String ADD_PRODUCT_PAGE = "Add_View";
    private static final String ADD_PRODUCT_PAGE_VIEW = "product.jsp";

    private static final String ADD_PRODUCT = "Ađd_Product";
    private static final String ADD_PRODUCT_CONTROLLER = "AddProductController";
    
    private static final String SEARCH_PRODUCT = "Search_Product";
    private static final String SEARCH_PRODUCT_CONTROLLER = "SearchProductController";
    

    private static final String ADD_BRAND_PAGE = "Add_Brand_View";
    private static final String ADD_BRAND_PAGE_VIEW = "brand.jsp";

    private static final String ADD_BRAND = "Add_Brand";
    private static final String ADD_BRAND_CONTROLLER = "AddBrandController";
    
    private static final String GET_BRANDS_PAGE = "Manage_Brands_Page";
    private static final String GET_BRANDS_PAGE_VIEW = "GetBrandsController";
    
    private static final String EDIT_BRAND_PAGE = "Edit_Brand_Page";
    private static final String EDIT_BRAND_PAGE_VIEW = "GetSpecificBrandController";
    
    private static final String SEARCH_BRAND = "Search_Brand";
    private static final String SEARCH_BRAND_CONTROLLER = "SearchBrandController";

    private static final String DELETE_BRAND = "Delete_Brand";
    private static final String DELETE_BRAND_CONTROLLER = "DeleteBrandController";

    private static final String EDIT_BRAND = "Edit_Brand";
    private static final String EDIT_BRAND_CONTROLLER = "UpdateBrandController";

    private static final String SENDMAIL = "Send_Email";
    private static final String SEND_EMAIL_CONTROLLER = "SendMailServlet";

    private static final String SEARCH_SUPPORT_PAGE = "View_Support";
    private static final String SEARCH_SUPPORT_PAGE_VIEW = "support.jsp";
    private static final String SEARCH_SUPPORT = "Search Support";
    private static final String SEARCH_SUPPORT_CONTROLLER = "SearchSupportController";

    private static final String SEARCH_PROMOTION = "Search promotion";
    private static final String SEARCH_PROMOTION_CONTROLLER = "SearchPromotionController";

    private static final String EDIT_PROMOTION = "EditPromotion";
    private static final String EDIT_PROMOTION_PAGE = "CopyPromotionController";
    private static final String SAVE_EDIT_PROMOTION = "SaveEditPromotion";
    private static final String EDIT_PROMOTION_CONTROLLER = "EditPromotionController";

    private static final String DELETE_PROMOTION = "DeletePromotion";
    private static final String DELETE_PROMOTION_CONTROLLER = "DeletePromotionController";

    private static final String ADD_CATEGORY_PAGE = "Add_Category_View";
    private static final String ADD_CATEGORY_PAGE_VIEW = "addCategory.jsp";
    private static final String ADD_CATEGORY = "Add_Category";
    private static final String ADD_CATEGORY_CONTROLLER = "AddCategoryController";

    private static final String ADD_CHILDREN_CATEGORY_PAGE = "Add_Children_Category_View";
    private static final String ADD_CHILDREN_CATEGORY_PAGE_VIEW = "addChildrenCategory.jsp";
    private static final String ADD_CHILDREN_CATEGORY = "Add_Children_Category";
    private static final String ADD_CHILDREN_CATEGORY_CONTROLLER = "AddChildrenCategoryController";

    private static final String ADD_PROMOTION_PAGE = "Add_Promo_View";
    private static final String ADD_PROMOTION_PAGE_VIEW = "addPromotion.jsp";
    private static final String ADD_PROMOTION = "Add_Promotion";
    private static final String ADD_PROMOTION_CONTROLLER = "AddPromotionController";

    private static final String DELETE_USER = "DeleteUser";
    private static final String DELETE_USER_CONTROLLER = "DeleteUserController";

    private static final String EDIT_CATEGORY = "Edit_Category";
    private static final String EDIT_CATEGORY_CONTROLLER = "EditCategoryController";

    private static final String EDIT_CHILDRENCATEGORY = "Edit_ChildrenCategory";
    private static final String EDIT_CHILDRENCATEGORY_CONTROLLER = "EditChildrenCategoryController";

    private static final String DELETE_CATEGORY = "Delete_Category";
    private static final String DELETE_CATEGORY_CONTROLLER = "DeleteCategoryController";

    private static final String DELETE_CHILDREN_CATEGORY = "Delete_ChildrenCategory";
    private static final String DELETE_CHILDREN_CATEGORY_CONTROLLER = "DeleteChildrenCategoryController";

    private static final String SEARCH_CATEGORY_PAGE = "Search_Category_View";
    private static final String SEARCH_PAGE_VIEW = "SearchCategory.jsp";
    private static final String SEARCH_CATEGORY = "Search_Category";
    private static final String SEARCH_CATEGORY_CONTROLLER = "SearchCategoryController";

    private static final String SEARCH_ORDER_PAGE = "Search_Order_View";
    private static final String SEARCH_ORDER_VIEW = "order.jsp";
    private static final String SEARCH_ORDER = "Search_Order";
    private static final String SEARCH_ORDER_CONTROLLER = "SearchOrderController";

    private static final String FORGOT_PASSWORD_CONTROLLER = "ForgotPasswordController";
    private static final String FORGOT_PASSWORD_PAGE = "Forgot_Password";

    private static final String EDIT_ORDER = "Edit_Order";
    private static final String EDIT_ORDER_CONTROLLER = "UpdateOrderController";

    private static final String VERIFY_TOKEN = "Verify_Token";
    private static final String VERIFY_TOKEN_CONTROLLER = "VerifyToken";

    private static final String SEARCH_USER = "SearchUser";
    private static final String SEARCH_USER_CONTROLLER = "SearchUserController";

    private static final String EDIT_CUSTOMER_PAGE = "EditCustomerPage";
    private static final String EDIT_CUSTOMER_PAGE_VIEW = "editCustomer.jsp";

    private static final String EDIT_CUSTOMER = "EditCustomer";
    private static final String EDIT_CUSTOMER_CONTROLLER = "EditCustomerController";

    private static final String EDIT_EMPLOYEE_PAGE = "EditEmployeePage";
    private static final String EDIT_EMPLOYEE_PAGE_VIEW = "editEmployee.jsp";

    private static final String EDIT_EMPLOYEE = "EditEmployee";
    private static final String EDIT_EMPLOYEE_CONTROLLER = "EditEmployeeController";

    private static final String GET_PRODUCT_PAGE = "Add_Product_Details_Page";
    private static final String GET_PRODUCT_PAGE_VIEW = "GetProductsController";
    

    private static final String SEARCH_PRODUCT_PAGE = "Search_Product_Page";
    private static final String SEARCH_PRODUCT_PAGE_VIEW = "product.jsp";
    
    private static final String ADD_PRODUCT_DETAILS = "Add_Product_Details";
    private static final String ADD_PRODUCT_DETAILS_CONTROLLER = "AddProductDetailsController";
    
    
    private static final String EDIT_PRODUCT = "Edit_Product";
    private static final String EDIT_PRODUCT_CONTROLLER = "EditProductController";
    
        private static final String EDIT_PRODUCT_DETAILS = "Edit_Product_Details";
    private static final String EDIT_PRODUCT_DETAILS_CONTROLLER = "EditProductDetailsController";
    
    private static final String DELETE_PRODUCT = "Delete_Product";
    private static final String DELETE_PRODUCT_CONTROLLER = "DeleteProductController";
    
    private static final String DELETE_DETAILS = "Delete_Detail";
    private static final String DELETE_DETAILS_CONTROLLER = "DeleteProductDetailsController";
    
    private static final String VIEW_PRIVACY = "View privacy";
    private static final String VIEW_PRIVACY_CONTROLLER = "ViewPrivacyController";


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
            } else if (SEARCH_PRODUCT.equals(action)) {
                url = SEARCH_PRODUCT_CONTROLLER;
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
            } else if (ADD_EMPLOYEE.equals(action)) {
                url = ADD_EMPLOYEE_CONTROLLER;
            } else if (ADD_EMPLOYEE_PAGE.equals(action)) {
                url = ADD_EMPLOYEE_PAGE_VIEW;
            } else if (EDIT_CHILDRENCATEGORY.equals(action)) {
                url = EDIT_CHILDRENCATEGORY_CONTROLLER;
            } else if (EDIT_CATEGORY.equals(action)) {
                url = EDIT_CATEGORY_CONTROLLER;
            } else if (SEARCH_ORDER_PAGE.equals(action)) {
                url = SEARCH_ORDER_VIEW;
            } else if (SEARCH_ORDER.equals(action)) {
                url = SEARCH_ORDER_CONTROLLER;
            } else if (DELETE_CATEGORY.equals(action)) {
                url = DELETE_CATEGORY_CONTROLLER;
            } else if (DELETE_CHILDREN_CATEGORY.equals(action)) {
                url = DELETE_CHILDREN_CATEGORY_CONTROLLER;
            } else if (EDIT_ORDER.equals(action)) {
                url = EDIT_ORDER_CONTROLLER;
            } else if (FORGOT_PASSWORD_PAGE.equals(action)) {
                url = FORGOT_PASSWORD_CONTROLLER;
            } else if (VERIFY_TOKEN.equals(action)) {
                url = VERIFY_TOKEN_CONTROLLER;
            } else if (SEARCH_USER.equals(action)) {
                url = SEARCH_USER_CONTROLLER;
            } else if (EDIT_CUSTOMER_PAGE.equals(action)) {
                url = EDIT_CUSTOMER_PAGE_VIEW;
            } else if (EDIT_CUSTOMER.equals(action)) {
                url = EDIT_CUSTOMER_CONTROLLER;
            } else if (EDIT_EMPLOYEE_PAGE.equals(action)) {
                url = EDIT_EMPLOYEE_PAGE_VIEW;
            } else if (EDIT_EMPLOYEE.equals(action)) {
                url = EDIT_EMPLOYEE_CONTROLLER;
            } else if (GET_PRODUCT_PAGE.equals(action)) {
                url = GET_PRODUCT_PAGE_VIEW;
            } else if (SEARCH_PRODUCT.equals(action)) {
                url = SEARCH_PRODUCT_CONTROLLER;
            } else if (DELETE_PRODUCT.equals(action)) {
                url = DELETE_PRODUCT_CONTROLLER;
            } else if (EDIT_PRODUCT.equals(action)) {
                url = EDIT_PRODUCT_CONTROLLER;
            } else if (ADD_PRODUCT_DETAILS.equals(action)) {
                url = ADD_PRODUCT_DETAILS_CONTROLLER;
            }else if (SEARCH_PRODUCT_PAGE.equals(action)) {
                url = SEARCH_PRODUCT_PAGE_VIEW;
            }else if (DELETE_DETAILS.equals(action)) {
                url = DELETE_DETAILS_CONTROLLER;
            }else if (EDIT_PRODUCT_DETAILS.equals(action)) {
                url = EDIT_PRODUCT_DETAILS_CONTROLLER;
            }else if (GET_BRANDS_PAGE.equals(action)) {
                url = GET_BRANDS_PAGE_VIEW;
            }else if (EDIT_BRAND_PAGE.equals(action)) {
                url = EDIT_BRAND_PAGE_VIEW;
            } else if (DELETE_USER.equals(action)) {
                url = DELETE_USER_CONTROLLER;
            } else if (VIEW_PRIVACY.equals(action)) {
                url = VIEW_PRIVACY_CONTROLLER;
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
