<%-- File: admin_product.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>ShopHub - Manage Products</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        .product-img { max-width: 80px; max-height: 80px; object-fit: cover; border-radius: 8px; }
        @media (max-width: 768px) {
            .table-responsive { overflow-x: auto; }
        }
    </style>
</head>
<body>
    <jsp:include page="navbar_admin.jsp" />

    <div class="container mt-4">
        <h2>Manage Products</h2>

        <c:if test="${not empty sessionScope.msg}">
            <div class="alert ${msg.contains('Error') ? 'alert-danger' : 'alert-success'} alert-dismissible fade show">
                ${sessionScope.msg}
                <button type="button" class="close" data-dismiss="alert">&times;</button>
            </div>
            <c:remove var="msg" scope="session"/>
        </c:if>

        <button class="btn btn-success mb-3" data-toggle="modal" data-target="#productModal" onclick="resetProductModal('add')">
            Add New Product
        </button>

        <div class="table-responsive">
            <table class="table table-striped table-bordered table-hover">
                <thead class="thead-dark">
                    <tr>
                        <th>#</th>
                        <th>ID</th>
                        <th>Image</th>
                        <th>Name</th>
                        <th>Category</th>
                        <th>Price (₫)</th>
                        <th>Stock</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${products}" var="p" varStatus="loop">
                        <tr>
                            <td>${loop.index + 1}</td>
                            <td>${p.productId}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${not empty p.imageUrl}">
                                        <img src="${p.imageUrl}" alt="${p.productName}" class="product-img">
                                    </c:when>
                                    <c:otherwise>
                                        No Image
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>${p.productName}</td>
                            <td>
                                <c:set var="displayCatId" value="${p.categoryId != -1 ? p.categoryId : null}" />
                                <c:choose>
                                    <c:when test="${displayCatId == null}">
                                        Uncategorized
                                    </c:when>
                                    <c:otherwise>
                                        ${categoryMap[displayCatId]} (ID: ${displayCatId})
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td><fmt:formatNumber value="${p.price}" type="number" groupingUsed="true"/></td>
                            <td>${p.stockQuantity}</td>
                            <td>
                                <button class="btn btn-sm btn-primary" data-toggle="modal" data-target="#productModal"
                                        onclick="fillProductModal('edit', ${p.productId}, '${p.productName.replace("'", "\\'")}', 
                                        '${p.productDescription == null ? "" : p.productDescription.replace("'", "\\'")}', 
                                        ${p.price}, ${p.stockQuantity}, '${p.imageUrl == null ? "" : p.imageUrl}', 
                                        ${displayCatId == null ? '' : displayCatId})">Edit</button>
                                <button class="btn btn-sm btn-warning" data-toggle="modal" data-target="#stockModal"
                                        onclick="setStockModal(${p.productId}, '${p.productName.replace("'", "\\'")}')">Stock</button>
                                <button class="btn btn-sm btn-danger" onclick="deleteProduct(${p.productId})">Delete</button>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <!-- Product Modal -->
    <jsp:include page="admin_product_productModal.jsp"></jsp:include>
	<jsp:include page="admin_product_stockModal.jsp"></jsp:include>
    

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        function resetProductModal(mode) {
            document.getElementById('productAction').value = 'add';
            document.getElementById('productModalTitle').innerText = 'Add Product';
            document.getElementById('productId').value = '';
            document.getElementById('productName').value = '';
            document.getElementById('productDesc').value = '';
            document.getElementById('productPrice').value = '';
            document.getElementById('productStock').value = '';
            document.getElementById('productImageUrl').value = '';
            document.getElementById('productCategory').value = '';
        }

        function fillProductModal(mode, id, name, desc, price, stock, img, catId) {
            document.getElementById('productAction').value = 'edit';
            document.getElementById('productModalTitle').innerText = 'Edit Product';
            document.getElementById('productId').value = id;
            document.getElementById('productName').value = name;
            document.getElementById('productDesc').value = desc || '';
            document.getElementById('productPrice').value = price;
            document.getElementById('productStock').value = stock;
            document.getElementById('productImageUrl').value = img || '';
            document.getElementById('productCategory').value = catId || '';
        }

        function setStockModal(id, name) {
            document.getElementById('stockProductId').value = id;
            document.getElementById('stockProductName').innerText = 'Product: ' + name;
            document.getElementById('stockQuantity').value = '';
        }

        function deleteProduct(id) {
            if (confirm('Are you sure you want to delete this product?')) {
                let form = document.createElement('form');
                form.method = 'post';
                form.action = 'ProductActionServlet';
                let a = document.createElement('input'); a.name = 'action'; a.value = 'delete'; a.type = 'hidden';
                let i = document.createElement('input'); i.name = 'id'; i.value = id; i.type = 'hidden';
                form.appendChild(a); form.appendChild(i);
                document.body.appendChild(form);
                form.submit();
            }
        }
    </script>
</body>
</html>