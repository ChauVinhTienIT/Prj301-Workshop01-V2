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
        <title>Account List</title>
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <!-- jQuery library -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

        <!-- Latest compiled JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    </head>
    <body>
        <%@include file = "header.jspf"%>
        <div class="container">
            <h2>Account List</h2>
            <a href="account-manager?action=insert">Add New Account</a>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Account</th>
                        <th>Pass</th>
                        <th>Last Name</th>
                        <th>First name</th>
                        <th>BirthDay</th>
                        <th>Gender</th>
                        <th>Phone</th>
                        <th>Is Use</th>
                        <th>Role</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:if test="${accList != null}">
                        <c:forEach items="${accList}" var="account">
                            <tr>
                                <td>${account.getAccount()}</td>
                                <td>${account.getPass()}</td>
                                <td>${account.getLastName()}</td>
                                <td>${account.getFirstName()}</td>
                                <td>${account.getBirthDay()}</td>
                                <td>${account.isGender() == true?"Male":"Female"}</td>
                                <td>${account.getPhone()}</td>
                                <td>${account.isIsUse()}</td>
                                <td>${account.getRoleInSystem()}</td>
                                <td><a href="account-manager?action=edit&account=<c:out value='${account.getAccount()}'/>">Edit</a> &nbsp;&nbsp;&nbsp;&nbsp; <a href="account-manager?action=delete&account=<c:out value='${account.getAccount()}' />">Delete</a></td>
                            </tr>
                        </c:forEach>
                    </c:if>
                </tbody>
            </table>
        </div>
    </body>
</html>
