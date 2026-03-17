// Updated: src/controller/UserController.java
package controller;

import model.User;
import bo.ProductBO;
import bo.CategoryBO;
import model.Product;
import model.Category;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/UserController")
public class UserController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UserController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            if (user.isAdmin()) {
                response.sendRedirect("AdminController");
            } else {
                loadData(request);
                request.getRequestDispatcher("user.jsp").forward(request, response);
            }
        } else {
            response.sendRedirect("LoginController");
        }
    }

    private void loadData(HttpServletRequest request) throws ServletException {
        ProductBO productBO = new ProductBO();
        CategoryBO categoryBO = new CategoryBO();
        try {
            String search = request.getParameter("search");
            String catStr = request.getParameter("category");
            Long categoryId = (catStr != null && !catStr.isEmpty()) ? (catStr.equals("0") ? null : Long.parseLong(catStr)) : null;
            
            List<Product> products;
            if (search != null && !search.trim().isEmpty()) {
                products = productBO.searchByName(search);
            } else if (categoryId != null) {
                products = productBO.getByCategory(categoryId);
            } else {
                products = productBO.getAll();
            }
            
            request.setAttribute("products", products);
            request.setAttribute("categories", categoryBO.getAllCategoriesForDropdown());
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg", "Error loading data: " + e.getMessage());
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}