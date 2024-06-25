<%-- 
    Document   : login
    Created on : Jun 5, 2024, 8:43:43 AM
    Author     : Oscar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Login page</title>
        <meta http-equiv="Content_Type" content="width=device-width; charset=UTF-8">
    </head>
    <body>
        <div>Input your information</div>
        <form action="MainController" method="POST">
            Email <input type="text" name="email"/><br/>
            Password <input type="password" name="password" /><br/>
            <input type="submit" name="action" value="Login"/>
            <input type="submit" name="action" value="Sign_Up"/>
            <input type="reset" value="Reset"/>
            <input type="submit" name="action" value="Add_View"/>
            <input type="submit" name="action" value="Add_Brand_View"/>
            <input type="submit" name="action" value="Add_Category_View"/>
            <input type="submit" name="action" value="Add_Children_Category_View"/>
            <input type="submit" name="action" value="Search_Category_View"/>
            <input type="submit" name="action" value="Add_Brand_View"/>
            <input type="submit" name="action" value="Add_Manager_View"/>
            <input type="submit" name="action" value ="Add_Promo_View"/>
            <input type="submit" name="action" value ="Search_Order_View"/>
            <input type="submit" name="action" value ="View_Support"/>
            <input type="submit" name="action" value ="View privacy"/>
            
        </form>
        <a class="border m-5 p-3 rounded" href="https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://localhost:8080/ISP392/google-login&response_type=code
           &client_id=786115507775-obtimai0mtsb6b6fsudfv0629n9uc6oq.apps.googleusercontent.com&approval_prompt=force"><img src="https://img.icons8.com/color/16/000000/google-logo.png">Login With Google</a>

    </body>
</html>
