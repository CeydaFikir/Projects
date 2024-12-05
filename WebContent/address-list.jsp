<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>Address List</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
    <!-- Navbar -->
    <nav class="navbar navbar-expand-lg navbar-dark" style="background-color: #6a4c9c;">
        <a class="navbar-brand" href="#">Address Management</a>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <a class="nav-link" href="new">Add New Address</a>
                </li>
            </ul>
        </div>
    </nav>

    <div class="container mt-5">
        <h2 class="text-center" style="color: #6a4c9c;">List of Addresses</h2>
        <div class="table-responsive">
            <table class="table table-bordered table-striped">
                <thead class="thead-dark">
                    <tr>
                        <th>ID</th>
                        <th>Address Code</th>
                        <th>Address Type</th>
                        <th>Address</th>
                        <th>District</th>
                        <th>Zipcode</th>
                        <th>City</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="address" items="${listAddress}">
                        <tr>
                            <td><c:out value="${address.id}" /></td>
                            <td><c:out value="${address.addressCode}" /></td>
                            <td><c:out value="${address.addressType}" /></td>
                            <td><c:out value="${address.address}" /></td>
                            <td><c:out value="${address.district}" /></td>
                            <td><c:out value="${address.zipcode}" /></td>
                            <td><c:out value="${address.city}" /></td>
                            <td>
                                <a href="edit?id=<c:out value='${address.id}' />" class="btn btn-warning btn-sm">Edit</a>
                                <a href="delete?id=<c:out value='${address.id}' />" class="btn btn-danger btn-sm" 
                                   onclick="return confirm('Are you sure you want to delete this address?');">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${empty listAddress}">
                        <tr>
                            <td colspan="8" class="text-center">No addresses found</td>
                        </tr>
                    </c:if>
                </tbody>
            </table>
        </div>
    </div>

    <!-- Footer -->
    <footer class="bg-dark text-white text-center py-3" style="position: fixed; bottom: 0; width: 100%;">
        <p>Address Management System © 2024</p>
    </footer>

    <!-- Bootstrap JS & jQuery -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.0.7/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
