

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Category List</title>
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
            <h2>Category List</h2>
            <a href="category-manager?action=new">Add New Category</a>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Type ID</th>
                        <th>Category Name</th>
                        <th>Memo</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:if test="${categoryList != null}">
                        <c:forEach items="${categoryList}" var="category">
                            <tr>
                                <td>${category.getTypeId()}</td>
                                <td>${category.getCategoryName()}</td>
                                <td>${category.getMemo()}</td>
                                <td>
                                    

                                    <c:url value = "category-manager" var = "editUrl">
                                        <c:param name = "action" value = "edit"/>
                                        <c:param name = "typeId" value = "${category.getTypeId()}"/>
                                    </c:url>
                                    <c:url value = "category-manager" var = "deleteUrl">
                                        <c:param name = "action" value = "delete"/>
                                        <c:param name = "typeId" value = "${category.getTypeId()}"/>
                                    </c:url>
                                    <a href="${editUrl}">Edit</a> 
                                    &nbsp;&nbsp;&nbsp;&nbsp; 
                                    <a href="${deleteUrl}">Delete</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:if>
                </tbody>
            </table>
        </div>
    </body>
</html>
