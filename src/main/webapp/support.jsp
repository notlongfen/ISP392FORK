<%-- 
    Document   : support
    Created on : Jun 10, 2024, 6:02:03 PM
    Author     : notlongfen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="MainController" method="POST">
            <input type="text" name="search" placeholder="Search for support request">
            <input type="submit"name="action" value="Search support">
        </form>
        
    </body>
</html>
