

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
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/listAccount.css"/>    
        <script src="${pageContext.request.contextPath}/static/js/listAccount.js"></script>
    </head>
    <body>
        <%@include file = "../header.jspf"%>
        <div class="container-xl">
            <div class="table-responsive">
                <div class="table-wrapper">
                    <div class="table-title">
                        <div class="row">
                            <div class="col-sm-6">
                                <h2>Category <b>Manager</b></h2>
                            </div>
                            <div class="col-sm-6">
                                <a href="category-manager?action=new" class="btn btn-success" data-toggle="modal"><i class="material-icons">&#xE147;</i> <span>Add New Category</span></a>
                            </div>
                        </div>
                    </div>
                    <table class="table table-striped table-hover">
                        <thead>
                            <tr>
                                <th>Type ID</th>
                                <th>Category Name</th>
                                <th>Memo</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
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
                                        <a href="${editUrl}" class="edit" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i></a> 
                                        <a href="${deleteUrl}"class="delete" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i></a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>        
        </div>

    </body>
</html>
