<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>Address Management</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link rel="stylesheet" href="css/styles.css">
    <script>
        function validateForm() {
            let addressCode = document.forms["addressForm"]["addressCode"].value;
            let addressType = document.forms["addressForm"]["addressType"].value;
            let address = document.forms["addressForm"]["address"].value;
            let city = document.forms["addressForm"]["city"].value;
            if (!addressCode || !addressType || !address || !city) {
                alert("Address Code, Address Type, Address, and City are mandatory fields!");
                return false;
            }
        }
    </script>
</head>
<body>
    <!-- Navbar -->
    <nav class="navbar navbar-expand-lg navbar-dark" style="background-color: #6a4c9c;">
        <a class="navbar-brand" href="#">Address Management</a>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <a class="nav-link" href="list">View Address List</a>
                </li>
            </ul>
        </div>
    </nav>

    <!-- Main Content -->
    <div class="container mt-5">
        <h2 class="text-center" style="color: #6a4c9c;"> 
            <c:if test="${address != null}">
                Edit Address
            </c:if>
            <c:if test="${address == null}">
                Add New Address
            </c:if>
        </h2>
        <form name="addressForm" action="${address != null ? 'update' : 'insert'}" method="post" onsubmit="return validateForm()">
            <c:if test="${address != null}">
                <input type="hidden" name="id" value="<c:out value='${address.id}' />">
            </c:if>
            
            <!-- Address Code -->
            <div class="form-group">
                <label for="addressCode">Address Code</label>
                <input type="text" class="form-control" id="addressCode" name="addressCode"
                       value="<c:out value='${address.addressCode}' />" required>
            </div>
            
            <!-- Address Type -->
            <div class="form-group">
                <label for="addressType">Address Type</label>
                <select class="form-control" id="addressType" name="addressType" required>
                    <option value="" disabled ${address == null ? 'selected' : ''}>Select Address Type</option>
                    <option value="Invoice" ${address != null && address.addressType == 'Invoice' ? 'selected' : ''}>Invoice</option>
                    <option value="Delivery" ${address != null && address.addressType == 'Delivery' ? 'selected' : ''}>Delivery</option>
                    <option value="Buyer" ${address != null && address.addressType == 'Buyer' ? 'selected' : ''}>Buyer</option>
                    <option value="Other" ${address != null && address.addressType == 'Other' ? 'selected' : ''}>Other</option>
                </select>
            </div>
            
            <!-- Address -->
            <div class="form-group">
                <label for="address">Address</label>
                <textarea class="form-control" id="address" name="address" rows="3" required>
                    <c:out value="${address.address}" />
                </textarea>
            </div>
            
            <!-- District -->
            <div class="form-group">
                <label for="district">District</label>
                <input type="text" class="form-control" id="district" name="district"
                       value="<c:out value='${address.district}' />">
            </div>
            
            <!-- Zipcode -->
            <div class="form-group">
                <label for="zipcode">Zipcode</label>
                <input type="text" class="form-control" id="zipcode" name="zipcode"
                       value="<c:out value='${address.zipcode}' />">
            </div>
            
            <!-- City -->
            <div class="form-group">
                <label for="city">City</label>
                <input type="text" class="form-control" id="city" name="city"
                       value="<c:out value='${address.city}' />" required>
            </div>

            <!-- Submit Button -->
            <button type="submit" class="btn btn-primary btn-block">
                <c:if test="${address != null}">
                    Update Address
                </c:if>
                <c:if test="${address == null}">
                    Add Address
                </c:if>
            </button>
        </form>
    </div>

    <!-- Footer -->
    <footer class="bg-dark text-white text-center py-3">
        <p>Address Management System © 2024</p>
    </footer>

    <!-- Bootstrap JS & jQuery -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.0.7/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
