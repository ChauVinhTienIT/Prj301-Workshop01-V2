<%-- 
    Document   : error
    Created on : Jun 18, 2024, 3:35:05 PM
    Author     : Lenovo
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<title>Error</title>
</head>
<body>
    <center>
        <h1>Error</h1>
        <h2>${exception.getMessage()}<br/> </h2>
    </center>
</body>
</html>