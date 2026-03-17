<%-- File: purchase_history.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>ShopHub - Purchase History</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
    <jsp:include page="navbar_user.jsp" />
    <div class="container mt-5">
        <h1>Purchase History</h1>
        
        <c:if test="${not empty sessionScope.msg}">
            <div class="alert alert-success alert-dismissible fade show">
                ${sessionScope.msg}
                <button type="button" class="close" data-dismiss="alert">&times;</button>
            </div>
            <c:remove var="msg" scope="session"/>
        </c:if>
        
        <c:forEach var="detail" items="${orderDetails}">
            <div class="card mb-4">
                <div class="card-header">
                    Order ID: ${detail.order.orderId} - Date: <fmt:formatDate value="${detail.order.createdAt}" pattern="yyyy-MM-dd HH:mm:ss" />
                </div>
                <div class="card-body">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>Product</th>
                                <th>Price</th>
                                <th>Quantity</th>
                                <th>Total</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="item" items="${detail.items}">
                                <tr>
                                    <td>${item.product.productName}</td>
                                    <td>$${item.product.price}</td>
                                    <td>${item.quantity}</td>
                                    <td>$${item.product.price * item.quantity}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </c:forEach>
    </div>
</body>
</html>