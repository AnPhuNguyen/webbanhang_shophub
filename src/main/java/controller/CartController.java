// New: src/controller/CartController.java
package controller;

import bo.ProductBO;
import model.Product;
import model.User;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/CartController")
public class CartController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("LoginController");
            return;
        }
        User user = (User) session.getAttribute("user");
        if (user.isAdmin()) {
            response.sendRedirect("AdminController");
            return;
        }

        Map<Long, Integer> cart = (Map<Long, Integer>) session.getAttribute("cart");
        if (cart == null) {
            cart = new HashMap<>();
        }

        ProductBO productBO = new ProductBO();
        List<Map.Entry<Product, Integer>> cartItems = new ArrayList<>();
        try {
            for (Map.Entry<Long, Integer> entry : cart.entrySet()) {
                Product p = productBO.read(entry.getKey());
                if (p != null) {
                    cartItems.add(new AbstractMap.SimpleEntry<>(p, entry.getValue()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("cartItems", cartItems);
        request.getRequestDispatcher("cart.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}