<%-- 
    Document   : US_RequestSupport
    Created on : Jul 5, 2024, 11:23:07 AM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <button id="openFormButton"><i class='fas fa-pen'></i></button>

            <div id="popupForm" class="form-popup">
                <form class="form-container">
                    <h4 style="text-align: center">PLEASE FILL IN THE BLANKS TO REQUEST SUPPORT</h4>
                    <label for="email"><b>Email</b></label>
                    <input type="text" id="email" name="email" required>

                    <label for="content"><b>Content</b></label>
                    <textarea id="content" name="content" required></textarea>

                    <button type="submit" class="btn">Send</button>
                    <button type="button" class="btn cancel" id="closeFormButton">Close</button>
                </form>
            </div>
    </body>
</html>
