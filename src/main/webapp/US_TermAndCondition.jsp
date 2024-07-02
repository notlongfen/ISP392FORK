<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Term And Condition Page</title>
    </head>
    <body>
        <div>
            <%
                String termAndConditionContent = (String) request.getAttribute("termAndConditionContent");
                if (termAndConditionContent != null) {
                    out.print(termAndConditionContent);
                } else {
                    out.print("Terms and conditions content is not available.");
                }
            %>
        </div>
    </body>
</html>
