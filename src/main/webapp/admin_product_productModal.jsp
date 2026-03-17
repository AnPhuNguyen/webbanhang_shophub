<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<div class="modal fade" id="productModal" tabindex="-1" role="dialog">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="productModalTitle">Add Product</h5>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>
                <form action="ProductActionServlet" method="post">
                    <div class="modal-body">
                        <input type="hidden" id="productAction" name="action" value="add">
                        <input type="hidden" id="productId" name="id">
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <label for="productName">Name *</label>
                                <input type="text" class="form-control" id="productName" name="name" required>
                            </div>
                            <div class="form-group col-md-6">
                                <label for="productPrice">Price *</label>
                                <input type="number" class="form-control" id="productPrice" name="price" min="0" required>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="productDesc">Description</label>
                            <textarea class="form-control" id="productDesc" name="description" rows="3"></textarea>
                        </div>
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <label for="productStock">Stock Quantity *</label>
                                <input type="number" class="form-control" id="productStock" name="stock" min="0" required>
                            </div>
                            <div class="form-group col-md-6">
                                <label for="productCategory">Category (currently cannot be uncategorized)</label>
                                <select class="form-control" id="productCategory" name="categoryId">
                                    <option value="">-- Uncategorized --</option>
                                    <c:forEach items="${categories}" var="cat">
                                        <option value="${cat.categoryId}">${cat.categoryName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="productImageUrl">Image URL</label>
                            <input type="url" class="form-control" id="productImageUrl" name="imageUrl" placeholder="https://example.com/image.jpg">
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