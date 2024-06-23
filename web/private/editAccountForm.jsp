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
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Account Manager</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
    </head>
    <body>
        <%@include file = "../header.jspf"%>
        <div class="container">
            <div class="row">
                <h3>Update Account</h3>
                <div class="col-md-8">
                    <form action="account-manager?action=update" method="POST" accept-charset="UTF-8">

                        <div class="form-group">
                            <label for="accountName">Account Name:</label>
                            <input type="text" class="form-control" name="account" value="<c:out value='${existAccount.getAccount()}' />" readonly/>
                        </div>
                        <div class="form-group">
                            <label for="pwd">Password:</label>
                            <input type="password" class="form-control" name="password" value="<c:out value='${existAccount.getPass()}' />" disabled>
                        </div>
                        <div class="form-group">
                            <label for="lastName">Last Name:</label>
                            <input type="text" class="form-control" name="lastName" value="${existAccount.getLastName()}">
                        </div>
                        <div class="form-group">
                            <label for="firstName">First Name:</label>
                            <input type="text" class="form-control" name="firstName" value="${existAccount.getFirstName()}">
                        </div>

                        <div class="form-group">
                            <label for="phone">Phone Number:</label>
                            <input type="text" class="form-control" name="phone" value="${existAccount.getPhone()}">
                        </div>

                        <div class="form-group">
                            <label for="birthDay">Birth Day:</label>
                            <input type="date" class="form-control" name="birthDay" value="${existAccount.getBirthDay()}">
                        </div>

                        <div class="form-group">
                            <label for="gender">Gender:</label></br>
                            <input type="radio" name="gender" value="1" checked>Male
                            <input type="radio" name="gender" value="0" >Female
                        </div>

                        <div class="form-group">
                            <label for="roleInSystem">Role in system:</label>
                            <select class="form-control" name="roleInSystem" >
                                <option value="1">Administrator</option>
                                <option value="2">Staff</option>
                            </select>
                        </div>

                        <div class="checkbox">
                            <label><input type="checkbox" name="isUse" value="1">Is Active</label>
                        </div>

                        <button type="submit" class="btn btn-primary">Submit</button>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
