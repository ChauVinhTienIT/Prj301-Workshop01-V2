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
                <h3>Add New Product</h3>
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
                        
                        <button type="submit" class="btn btn-default">Add Product</button>
                    </form>

                </div>
            </div>
        </div>
    </body>
</html>