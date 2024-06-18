<%-- 
    Document   : accountList
    Created on : Jun 18, 2024, 3:39:34 PM
    Author     : Lenovo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product List</title>
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <!-- jQuery library -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

        <!-- Latest compiled JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="static/css/style.css">
    </head>
    <body>
        <%@include file = "header.jspf"%>
        <div class="container">
            <h2>Product List</h2>
            <a href="product-manager?action=new">Add New Account</a>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Product Id</th>
                        <th>Product Name</th>
                        <th>Product Image</th>
                        <th>Brief</th>
                        <th>Posted Date</th>
                        <th>Type Id</th>
                        <th>Account</th>
                        <th>Unit</th>
                        <th>Price</th>
                        <th>Discount</th>
                    </tr>
                </thead>
                <tbody>
                    <c:if test="${productList != null}">
                        <c:forEach items="${productList}" var="product">
                            <tr>
                                <td>${product.getProductId()}</td>
                                <td>${product.getProductName()}</td>
                                <td><img scr="" width="50" height="50"></td>
                                <td>${product.getBrief()}</td>
                                <td>${product.getPostedDate()}</td>
                                <td>${product.getTypeId()}</td>
                                <td>${product.getAccount()}</td>
                                <td>${product.getUnit()}</td>
                                <td>${product.getPrice()}</td>
                                <td>${product.getDiscount()}</td>
                                <td>
                                    <a href="account-manager?action=edit&account=<c:out value='${account.getAccount()}' />">Edit</a> 
                                    &nbsp;&nbsp;&nbsp;&nbsp; 
                                    <a href="account-manager?action=delete&account=<c:out value='${account.getAccount()}'/>">Delete</a>
                                    &nbsp;&nbsp;&nbsp;&nbsp; 
                                    <a href="account-manager?action=changeStatus&account=<c:out value='${account.getAccount()}' />">${account.isIsUse()?"Deactivate":"Activate"}</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:if>
                </tbody>
            </table>
        </div>
    </body>
</html>
