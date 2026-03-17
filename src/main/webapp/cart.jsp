<%-- File: cart.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>ShopHub - Cart</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
    <jsp:include page="navbar_user.jsp" />

    <div class="container mt-5">
        <h1 class="mb-4">Your Cart</h1>

        <c:if test="${not empty sessionScope.msg}">
            <div class="alert alert-danger alert-dismissible fade show">
                ${sessionScope.msg}
                <button type="button" class="close" data-dismiss="alert">&times;</button>
            </div>
            <c:remove var="msg" scope="session"/>
        </c:if>

        <c:choose>
            <c:when test="${empty cartItems}">
                <div class="alert alert-info text-center">
                    Your cart is empty. <a href="UserController">Continue shopping</a>
                </div>
            </c:when>
            <c:otherwise>
                <div class="table-responsive">
                    <table class="table table-striped table-hover align-middle">
                        <thead class="thead-dark">
                            <tr>
                                <th>Product</th>
                                <th>Price</th>
                                <th>Quantity</th>
                                <th>Line Total</th>
                                <th class="text-center">Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:set var="grandTotal" value="0" />
                            <c:forEach var="item" items="${cartItems}">
                                <c:set var="lineTotal" value="${item.key.price * item.value}" />
                                <c:set var="grandTotal" value="${grandTotal + lineTotal}" />
                                <tr>
                                    <td>
                                        <strong>${item.key.productName}</strong>
                                        <c:if test="${not empty item.key.imageUrl}">
                                            <br><img src="${item.key.imageUrl}" alt="${item.key.productName}" class="img-fluid mt-2" style="max-height: 80px;">
                                        </c:if>
                                    </td>
                                    <td>$<fmt:formatNumber value="${item.key.price}" pattern="#,##0" /></td>
                                    <td>${item.value}</td>
                                    <td>$<fmt:formatNumber value="${lineTotal}" pattern="#,##0" /></td>
                                    <td class="text-center">
                                        <a href="UpdateCartServlet?action=increase&productId=${item.key.productId}" class="btn btn-sm btn-success">+</a>
                                        <a href="UpdateCartServlet?action=decrease&productId=${item.key.productId}" class="btn btn-sm btn-warning mx-1">-</a>
                                        <a href="UpdateCartServlet?action=remove&productId=${item.key.productId}" class="btn btn-sm btn-danger">Remove</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                        <tfoot>
                            <tr class="table-info font-weight-bold">
                                <td colspan="3" class="text-right">Grand Total:</td>
                                <td>$<fmt:formatNumber value="${grandTotal}" pattern="#,##0" /></td>
                                <td></td>
                            </tr>
                        </tfoot>
                    </table>
                </div>

                <div class="text-right mt-4">
                    <a href="UpdateCartServlet?action=clear" class="btn btn-warning mr-3" onclick="return confirm('Clear entire cart?')">Clear Cart</a>
                    <a href="PurchaseCartServlet" class="btn btn-success btn-lg" onclick="return confirm('Confirm purchase of $${grandTotal} total?')">Purchase Now</a>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</body>
</html>