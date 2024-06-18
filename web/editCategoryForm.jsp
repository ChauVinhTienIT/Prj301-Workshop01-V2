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
        <title>Add A New Account</title>

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
                <h3>Update Category</h3>
                <div class="col-md-8">
                    <form action="category-manager?action=update" method="POST" accept-charset="UTF-8">
                        
                        
                        <input type="hidden" class="form-control" name="typeId" value = "${existCategory.getTypeId()}">
                        
                        <div class="form-group">
                            <label for="categoryName">Category Name:</label>
                            <input type="text" class="form-control" name="categoryName" value = "${existCategory.getCategoryName()}">
                        </div>
                        <div class="form-group">
                            <label for="memo">Memo:</label>
                            <input type="text" class="form-control" name="memo" value = "${existCategory.getMemo()}">
                        </div>
                        
                        <button type="submit" class="btn btn-default">Save</button>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
