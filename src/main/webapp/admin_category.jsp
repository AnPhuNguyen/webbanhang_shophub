<%-- File: admin_category.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="bo.CategoryBO, java.util.List, model.Category" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>ShopHub - Manage Categories</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <jsp:include page="navbar_admin.jsp" />

    <div class="container mt-4">
        <h2>Manage Categories</h2>

        <c:if test="${not empty sessionScope.msg}">
            <div class="alert alert-info alert-dismissible fade show">
                ${sessionScope.msg}
                <button type="button" class="close" data-dismiss="alert">&times;</button>
            </div>
            <c:remove var="msg" scope="session"/>
        </c:if>

        <button class="btn btn-success mb-3" data-toggle="modal" data-target="#categoryModal" onclick="resetModal('add')">
            Add New Category
        </button>

        <table class="table table-striped table-bordered">
            <thead class="thead-dark">
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Products</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <%
                    CategoryBO bo = new CategoryBO();
                    List<Category> list = bo.getAllWithCount();
                    pageContext.setAttribute("categories", list);
                %>
                <c:forEach items="${categories}" var="cat">
                    <tr>
                        <td>${cat.categoryId}</td>
                        <td>${cat.categoryName}</td>
                        <td>${cat.productCount}</td>
                        <td>
                            <c:if test="${cat.categoryId >= 0}">
                                <button class="btn btn-sm btn-primary" data-toggle="modal" data-target="#categoryModal"
                                        onclick="fillModal('edit', ${cat.categoryId}, '${cat.categoryName}')">Edit</button>
                                <button class="btn btn-sm btn-danger" onclick="deleteCategory(${cat.categoryId})">Delete</button>
                            </c:if>
                           
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <!-- Modal -->
    <div class="modal fade" id="categoryModal" data-backdrop="static">
        <div class="modal-dialog">
            <div class="modal-content">
                <form action="CategoryActionServlet" method="post">
                    <div class="modal-header">
                        <h5 class="modal-title" id="modalTitle">Add Category</h5>
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div>
                    <div class="modal-body">
                        <input type="hidden" name="action" id="action">
                        <input type="hidden" name="id" id="categoryId">
                        <div class="form-group">
                            <label>Category Name</label>
                            <input type="text" class="form-control" name="name" id="categoryName" required>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                        <button type="submit" class="btn btn-primary">Save</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        function resetModal(mode) {
            document.getElementById('action').value = 'add';
            document.getElementById('modalTitle').innerText = 'Add Category';
            document.getElementById('categoryName').value = '';
            document.getElementById('categoryId').value = '';
        }
        function fillModal(mode, id, name) {
            document.getElementById('action').value = 'edit';
            document.getElementById('modalTitle').innerText = 'Edit Category';
            document.getElementById('categoryId').value = id;
            document.getElementById('categoryName').value = name;
        }
        function deleteCategory(id) {
            if (confirm('Delete this category? Products will become Uncategorized.')) {
                let form = document.createElement('form');
                form.method = 'post';
                form.action = 'CategoryActionServlet';
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