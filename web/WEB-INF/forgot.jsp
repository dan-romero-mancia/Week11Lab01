<%-- 
    Document   : forgot
    Created on : Nov 20, 2017, 8:18:42 AM
    Author     : 752039
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Forgot Password</title>
    </head>
    <body>
        <h1>Forgot Password</h1>
        <form action="forgot" method="POST">
            <div>Email Address: <input type="text" name="email"></div>
            <input type="submit" value="Submit">
        </form>
        ${message}
    </body>
</html>
