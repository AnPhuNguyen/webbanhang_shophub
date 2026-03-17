// 5. ProductActionServlet.java – add doGet and shared logic
package controller;

import bo.ProductBO;
import model.Product;
import bo.CategoryBO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/ProductActionServlet")
public class ProductActionServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ProductBO productBO = new ProductBO();
//    private CategoryBO categoryBO = new CategoryBO();

//    private void loadData(HttpServletRequest request) throws ServletException {
//        try {
////        	System.out.print(productBO.getAll());
////        	System.out.print(categoryBO.getAllCategoriesForDropdown());
//            request.setAttribute("products", productBO.getAll());
//            request.setAttribute("categories", categoryBO.getAllCategoriesForDropdown());
//        } catch (Exception e) {
//            request.setAttribute("msg", "Error loading data: " + e.getMessage());
//        }
//    }
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        loadData(request);
//        request.getRequestDispatcher("admin_product.jsp").forward(request, response);
//    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        String msg = null;

        try {
            if ("add".equals(action) || "edit".equals(action)) {
                Product p = new Product();
                p.setProductId("edit".equals(action) ? Long.parseLong(request.getParameter("id")) : 0);
                p.setProductName(request.getParameter("name"));
                p.setProductDescription(request.getParameter("description"));
                p.setPrice(Integer.parseInt(request.getParameter("price")));
                p.setStockQuantity(Integer.parseInt(request.getParameter("stock")));
                p.setImageUrl(request.getParameter("imageUrl"));

                String catIdStr = request.getParameter("categoryId");
                p.setCategoryId(catIdStr == null || catIdStr.isEmpty() ? null : Long.parseLong(catIdStr));

                if ("add".equals(action)) {
                    productBO.create(p);
                    msg = "Product added successfully";
                } else {
                    productBO.update(p);
                    msg = "Product updated successfully";
                }
            } else if ("delete".equals(action)) {
                productBO.delete(Long.parseLong(request.getParameter("id")));
                msg = "Product deleted successfully";
            } else if ("stock".equals(action)) {
                long id = Long.parseLong(request.getParameter("productId"));
                int qty = Integer.parseInt(request.getParameter("quantity"));
                boolean isPlus = "import".equals(request.getParameter("stockAction"));
                productBO.updateStock(id, qty, isPlus);
                msg = isPlus ? "Stock imported successfully" : "Stock exported successfully";
            }
        } catch (Exception e) {
            msg = "Error: " + e.getMessage();
        }

        request.getSession().setAttribute("msg", msg);
        response.sendRedirect("ProductController"); // redirect to doGet
    }
}