<%-- 
    Document   : listAccount.jsp
    Created on : Jun 22, 2024, 4:48:55 PM
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
                                <h2>Manager <b>Accounts</b></h2>
                            </div>
                            <div class="col-sm-6">
                                <a href="account-manager?action=new" class="btn btn-success" data-toggle="modal"><i class="material-icons">&#xE147;</i> <span>Add New Employee</span></a>
                            </div>
                        </div>
                    </div>
                    <table class="table table-striped table-hover">
                        <thead>
                            <tr>
                                <th>Account</th>
                                <th>Pass</th>
                                <th>Full Name</th>
                                <th>BirthDay</th>
                                <th>Gender</th>
                                <th>Phone</th>
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
                                        <td>${account.getFullName()}</td>
                                        <td>${account.getBirthDay()}</td>
                                        <td>${account.isGender()?"Male":"Female"}</td>
                                        <td>${account.getPhone()}</td>
                                        <td>${account.getRoleInSystem()}</td>

                                        <td>
                                            <a href="account-manager?action=edit&account=${account.getAccount()}" class="edit" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i></a>
                                            <a href="account-manager?action=delete&account=${account.getAccount()}" class="delete" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i></a>
                                            <a href="account-manager?action=changeStatus&account=${account.getAccount()}">${account.isIsUse()?"Deactivate":"Activate"}</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                        </tbody>
                    </table>
                </div>
            </div>        
        </div>

</html>
