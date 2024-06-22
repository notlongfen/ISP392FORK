<%-- 
    Document   : editCustomer
    Created on : Jun 19, 2024, 10:14:46 AM
    Author     : TTNHAT
--%>

<%@page import="com.mycompany.isp392.user.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Customer Page</title>
    </head>
    <body>
        <%
            UserDAO dao = new UserDAO();
            int userID = Integer.parseInt(request.getParameter("userID"));
            UserDTO user = dao.getUserByID(userID);
            CustomerDTO customer = dao.getCustomerByID(userID);
        %>
        <h2>Edit Customer Information</h2>
        <form action="MainController" method="POST">
            <input type="hidden" name="userID" value="<%= user.getUserID()%>" readonly=""/>
            Customer Name: <input type="text" name="userName" value="<%=user.getUserName()%>" required=""/><br/>
            <input type="hidden" name="oldEmail" value="<%=user.getEmail()%>" readonly=""/></br>
            Email: <input type="text" name="email" value="<%=user.getEmail()%>" required=""/></br>
            <input type="hidden" name="oldPhone" value="<%= user.getPhone()%>" readonly=""/></br>
            Phone: <input type="number" name="phone" value="<%= user.getPhone()%>" required=""/></br>
            Status: <select name="status"></br>
                <option value="0" <%= user.getStatus() == 0 ? "selected" : "" %>>Inactive</option>
                <option value="1" <%= user.getStatus() == 1 ? "selected" : "" %>>Active</option>
            </select>
            Points: <input type="number" name="points" value="<%=customer.getPoints()%>"required="" min="0"/></br>
            Birthday: <input type="date" name="birthday" value="<%= customer.getBirthday()%>" required=""/></br>
            City: <input type="text" name="city" value="<%= customer.getCity()%>" required=""/></br>
            District: <input type="text" name="district" value="<%= customer.getDistrict()%>" required=""/></br>
            Ward: <input type="text" name="ward" value="<%= customer.getWard()%>" required=""/></br>
            Address: <input type="text" name="address" value="<%= customer.getAddress()%>" required=""/></br>
            <input type="submit" name="action" value="EditCustomer" required=""/>
            <input type="reset" value="Reset" required=""/>
        </form>
        <%
            UserError userError = (UserError) request.getAttribute("EDIT_ERROR");
            if (userError != null) {
        %>
        <div style="color: red;">
            <p><%= userError.getUserNameError()%></p>
            <p><%= userError.getEmailError()%></p>
            <p><%= userError.getPhoneError()%></p>
            <p><%= userError.getBirthdayError()%></p>
            <p><%= userError.getPasswordError()%></p>
            <p><%= userError.getConfirmError()%></p>
            <p><%= userError.getError()%></p>
        </div>
        <%
            }
        %>
    </body>
</html>
