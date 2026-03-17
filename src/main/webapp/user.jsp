<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>ShopHub - User Dashboard</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
    <jsp:include page="navbar_user.jsp" />
    <div class="container mt-5">
        <h1>Welcome, ${user.accountName}</h1>
        
        <!-- Search bar -->
        <form action="UserController" method="get">
            <div class="input-group mb-3">
                <input type="text" class="form-control" placeholder="Search products by name" name="search" value="${param.search}">
                <div class="input-group-append">
                    <button class="btn btn-outline-secondary" type="submit">Search</button>
                </div>
            </div>
        </form>
        
        <!-- Category filter -->
        <div class="mb-3">
            <label for="category">Filter by category:</label>
            <select id="category" class="form-control" onchange="window.location.href='UserController?category=' + this.value">
                <option value="">All</option>
                <option value="0" ${param.category == '0' ? 'selected' : ''}>Uncategorized</option>
                <c:forEach items="${categories}" var="cat">
                    <option value="${cat.categoryId}" ${param.category == cat.categoryId ? 'selected' : ''}>${cat.categoryName}</option>
                </c:forEach>
            </select>
        </div>
        
        <div class="row">
            <c:forEach items="${products}" var="p">
                <div class="col-md-4 mb-4">
                    <div class="card h-100">
                        <c:if test="${not empty p.imageUrl}">
                            <img src="${p.imageUrl}" class="card-img-top" alt="${p.productName}" style="height: 200px; object-fit: cover;">
                        </c:if>
                        <div class="card-body d-flex flex-column">
                            <h5 class="card-title">${p.productName}</h5>
                            <p class="card-text">${p.productDescription}</p>
                            <p class="card-text">Price: $${p.price}</p>
                            <p class="card-text">Stock: ${p.stockQuantity}</p>
                            <a href="AddToCartServlet?productId=${p.productId}" class="btn btn-primary mt-auto">Add to Cart</a>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</body>
</html>