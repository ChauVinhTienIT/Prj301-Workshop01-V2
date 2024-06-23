<%-- 
    Document   : user-form.jsp
    Created on : Jun 17, 2024, 7:09:34 PM
    Author     : Lenovo
--%>

<%@page import="dao.CategoryDao"%>
<%@page import="model.Category"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
                <h3>Update Product</h3>
                <div class="col-md-8">
                    <form action="product-manager?action=update" method="POST" enctype="multipart/form-data" accept-charset="UTF-8">
                        
                        <div class="form-group">
                            <label for="productId">Product Id:</label>
                            <input type="text" class="form-control" name="productId" value="${existProduct.getProductId()}">
                        </div>
                        <div class="form-group">
                            <label for="productName">Product Name:</label>
                            <input type="text" class="form-control" name="productName" value="${existProduct.getProductName()}">
                        </div>
                        
                        
                        <div class="form-group">
                            <label for="productImage">Product Image:</label><br>
                            <img src=".${existProduct.getProductImage()}" width="200" height="200">
                            <input type = "file" name = "image" size = "50" /><br>
                        </div>

                        <div class="form-group">
                            <label for="brief">Brief:</label>
                            <input type="text" class="form-control" name="brief" value="${existProduct.getBrief()}">
                        </div>
                        
                        
                        <div class="form-group">
                            <c:set value="<%= new CategoryDao().listAll()%>" var="cateList"></c:set>
                            <label for="typeId">Type:</label>
                            <select class="form-control" name="typeId">
                                <c:forEach items="${cateList}" var="category">
                                    <option value="${category.getTypeId()}" ${existProduct.getTypeId()==category.getTypeId()?"check":""}> ${category.getCategoryName()}</option>
                                </c:forEach>
                            </select>

                        </div>
                        
                        
                        <div class="form-group">
                            <label for="unit">Unit:</label>
                            <select class="form-control" name="unit">
                                <option value="1" ${existProduct.getUnit().equals(existProduct.getUnit())?"check":""}>Cái</option>
                                <option value="2" ${existProduct.getUnit().equals(existProduct.getUnit())?"check":""}>Chiếc</option>
                                <option value="3" ${existProduct.getUnit().equals(existProduct.getUnit())?"check":""}>Bộ</option>
                            </select>
                        </div>
                        
                        <div class="form-group">
                            <label for="price">Price:</label>
                            <input type="number" min="0" class="form-control" name="price" value="${existProduct.getPrice()}">
                        </div>
                        
                        <div class="form-group">
                            <label for="discount">Discount</label>
                            <input type="number" min="0" max="100" class="form-control" name="discount" value="${existProduct.getDiscount()}">
                        </div>
                        
                        <button type="submit" class="btn btn-primary">Add Product</button>
                    </form>

                </div>
            </div>
        </div>
    </body>
</html>
