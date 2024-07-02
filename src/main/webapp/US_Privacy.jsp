<%-- 
    Document   : privacy
    Created on : Jun 24, 2024, 2:20:49 PM
    Author     : Oscar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Privacy Page</title>
    </head>
    <body>
        <div>
            <%= request.getAttribute("privacyContent") %>
        </div>
    </body>
</html>
