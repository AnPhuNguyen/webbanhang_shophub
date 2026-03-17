<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- Stock Modal -->
    <div class="modal fade" id="stockModal" tabindex="-1" role="dialog">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Manage Stock</h5>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>
                <form action="ProductActionServlet" method="post">
                    <div class="modal-body">
                        <input type="hidden" name="action" value="stock">
                        <input type="hidden" id="stockProductId" name="productId">
                        <div class="form-group">
                            <label id="stockProductName"></label>
                        </div>
                        <div class="form-group">
                            <label for="stockQuantity">Quantity</label>
                            <input type="number" class="form-control" id="stockQuantity" name="quantity" min="1" required>
                        </div>
                        <div class="form-group">
                            <label>Action</label><br>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio" name="stockAction" id="import" value="import" checked>
                                <label class="form-check-label" for="import">Import (Add)</label>
                            </div>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio" name="stockAction" id="export" value="export">
                                <label class="form-check-label" for="export">Export (Subtract)</label>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                        <button type="submit" class="btn btn-primary">Apply</button>
                    </div>
                </form>
            </div>
        </div>
    </div>