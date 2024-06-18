<%-- 
    Document   : user-form.jsp
    Created on : Jun 17, 2024, 7:09:34 PM
    Author     : Lenovo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add New Account</title>

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
            <div class="row">
                <h3>Add New Account</h3>
                <div class="col-md-8">
                    <form action="account-manager?action=insert" method="POST" accept-charset="UTF-8">

                        <div class="form-group">
                            <label for="accountName">Account Name:</label>
                            <input type="text" class="form-control" name="accountName">
                        </div>
                        <div class="form-group">
                            <label for="pwd">Password:</label>
                            <input type="password" class="form-control" name="password">
                        </div>
                        <div class="form-group">
                            <label for="lastName">Last Name:</label>
                            <input type="text" class="form-control" name="lastName">
                        </div>
                        <div class="form-group">
                            <label for="firstName">First Name:</label>
                            <input type="text" class="form-control" name="firstName">
                        </div>

                        <div class="form-group">
                            <label for="phone">Phone Number:</label>
                            <input type="text" class="form-control" name="phone">
                        </div>

                        <div class="form-group">
                            <label for="birthDay">Birth Day:</label>
                            <input type="date" class="form-control" name="birthDay">
                        </div>

                        <div class="form-group">
                            <label for="gender">Gender:</label></br>
                            <input type="radio" name="gender" value="1" checked>Male
                            <input type="radio" name="gender" value="0" >Female
                        </div>

                        <div class="form-group">
                            <label for="roleInSystem">Role in system:</label>
                            <select class="form-control" name="roleInSystem">
                                <option value="1">Administrator</option>
                                <option value="2">Staff</option>
                            </select>
                        </div>

                        <div class="checkbox">
                            <label><input type="checkbox" name="isUse" value="1">Is Active</label>
                        </div>

                        <button type="submit" class="btn btn-default">Submit</button>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
