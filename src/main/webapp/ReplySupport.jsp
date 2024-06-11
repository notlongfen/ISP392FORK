<%-- 
    Document   : ReplySupport
    Created on : Jun 9, 2024, 7:16:47 PM
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
        <h1>Hello World!</h1>
        <form action="MainController" method="post">
            <label for="toEmail"> To Email: </label>
            <input type="email" name="toEmail">
            <label for="subject"> Subject: </label>
            <input type="text" name="subject">
            <label for="content"> Content: </label>
            <textarea name="content"></textarea>
            <input type="submit" value="Send_Email" name="action">
        </form>
    </body>
</html>
