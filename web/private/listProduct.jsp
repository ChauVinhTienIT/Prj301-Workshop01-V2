<%-- 
    Document   : accountList
    Created on : Jun 18, 2024, 3:39:34 PM
    Author     : Lenovo
--%>

<%@page import="dao.CategoryDao"%>
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
                                <h2>Product <b>Manager</b></h2>
                            </div>
                            <div class="col-sm-6">
                                <a href="product-manager?action=new" class="btn btn-success" data-toggle="modal"><i class="material-icons">&#xE147;</i> <span>Add New Product</span></a>
                            </div>
                        </div>
                    </div>
                    <table class="table table-striped table-hover">
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
                                <th>Action</th>
                            </tr>
                        </thead>
                        <c:if test="${productList != null}">
                            <c:set value="<%= new CategoryDao()%>" var="cateDao"></c:set>
                            <c:forEach items="${productList}" var="product">
                                <tr>
                                    <td>${product.getProductId()}</td>
                                    <td>${product.getProductName()}</td>
                                    <td><img src=".${product.getProductImage()}" width="100" height="100" class="rounded mx-auto d-block"></td>
                                    <td>${product.getBrief()}</td>
                                    <td>${product.getPostedDate()}</td>
                                    <td>${cateDao.getObjectById(product.getTypeId()).getCategoryName()}</td>
                                    <td>${product.getAccount()}</td>
                                    <td>${product.getUnit()}</td>
                                    <td>${product.getPrice()}</td>
                                    <td>${product.getDiscount()}</td>
                                    <td>
                                        <a href="product-manager?action=edit&productId=${product.getProductId()}" class="edit" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i></a> 
                                        <a href="product-manager?action=delete&productId=${product.getProductId()}" class="delete" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i></a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:if>
                    </table>
                </div>
            </div>        
        </div>

    </body>
</html>
