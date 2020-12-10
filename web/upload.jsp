<%-- 
    Document   : upload
    Created on : Oct 25, 2020, 1:25:11 PM
    Author     : Aleks
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    // распознавание русского алфавита на JSP.
    request.setCharacterEncoding("UTF-8");
    response.setContentType("text/html;charset=UTF-8");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Просмотр данных!</h1>
        <form action="UploadServlet" method="POST" enctype="multipart/form-data">
            
            <input type="text" name ="path" value="" /></BR></BR>
            <input type="file" name="fileToRead" size="63" /></BR></BR>
            <input type="submit" value="Отправить" />
            
            
            
        </form>
    </body>
</html>
