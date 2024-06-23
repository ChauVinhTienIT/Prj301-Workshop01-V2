<%-- 
    Document   : login
    Created on : Jun 9, 2024, 1:50:22 PM
    Author     : Lenovo
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        
    </head>
    <body>
        <div style="text-align: center">
            <h1>Login</h1>
            <form action="user-authentication?action=login" method="POST">
                <label for="accountName">Account:</label>
                <input name="accountName" size="30"  required/>
                <br><br>
                <label for="password">Password:</label>
                <input type="password" name="password" size="30" required />
                <br>${message}
                <br><br>           
                <button type="submit">Login</button>
            </form>
        </div>
    </body>
</html>
