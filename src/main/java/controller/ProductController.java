package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bo.CategoryBO;
import bo.ProductBO;
import model.User;

@WebServlet("/ProductController")
public class ProductController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private void loadData(HttpServletRequest request) throws ServletException {
    	ProductBO productBO = new ProductBO();
    	CategoryBO categoryBO = new CategoryBO();
    	try {
//        	System.out.print(productBO.getAll());
//        	System.out.print(categoryBO.getAllCategoriesForDropdown());
            request.setAttribute("products", productBO.getAll());
            request.setAttribute("categories", categoryBO.getAllCategoriesForDropdown());
        } catch (Exception e) {
        	e.printStackTrace();
            request.setAttribute("msg", "Error loading data: " + e.getMessage());
        }
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("LoginController");
            return;
        }
        User user = (User) session.getAttribute("user");
        if (!user.isAdmin()) {
            response.sendRedirect("UserController");
            return;
        }
        loadData(request);
        request.getRequestDispatcher("admin_product.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}