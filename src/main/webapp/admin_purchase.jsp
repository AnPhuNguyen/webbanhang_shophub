<%-- File: admin_purchase.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>ShopHub - Manage Purchases</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <jsp:include page="navbar_admin.jsp" />

    <div class="container mt-4">
        <h2>Manage Purchases</h2>

        <c:if test="${not empty sessionScope.msg}">
            <div class="alert ${msg.contains('Error') ? 'alert-danger' : 'alert-success'} alert-dismissible fade show">
                ${sessionScope.msg}
                <button type="button" class="close" data-dismiss="alert">&times;</button>
            </div>
            <c:remove var="msg" scope="session"/>
        </c:if>

        <div class="table-responsive">
            <table class="table table-striped table-bordered">
                <thead class="thead-dark">
                    <tr>
                        <th>Order ID</th>
                        <th>User</th>
                        <th>Created At</th>
                        <th>Total Amount</th>
                        <th>State</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${purchasedOrders}" var="order">
                        <tr>
                            <td>${order.orderId}</td>
                            <td>${order.userName}</td>
                            <td><fmt:formatDate value="${order.createdAt}" pattern="dd/MM/yyyy HH:mm"/></td>
                            <td><fmt:formatNumber value="${order.totalAmount}" type="currency" currencySymbol="₫"/></td>
                            <td>
                                <span class="badge ${order.processed ? 'badge-success' : 'badge-warning'}">
                                    ${order.processed ? 'Completed' : 'Pending'}
                                </span>
                            </td>
                            <td>
                                <c:if test="${!order.processed}">
                                    <button class="btn btn-sm btn-primary" onclick="processOrder(${order.orderId})">
                                        Process
                                    </button>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <script>
        function processOrder(orderId) {
            if (confirm('Are you sure you want to process this order? This will mark it as completed.')) {
                let form = document.createElement('form');
                form.method = 'post';
                form.action = 'PurchaseActionServlet';
                let inputAction = document.createElement('input');
                inputAction.type = 'hidden';
                inputAction.name = 'action';
                inputAction.value = 'process';
                let inputId = document.createElement('input');
                inputId.type = 'hidden';
                inputId.name = 'orderId';
                inputId.value = orderId;
                form.appendChild(inputAction);
                form.appendChild(inputId);
                document.body.appendChild(form);
                form.submit();
            }
        }
    </script>
</body>
</html>