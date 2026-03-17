// New: src/controller/AddToCartServlet.java
package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/AddToCartServlet")
public class AddToCartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long productId = Long.parseLong(request.getParameter("productId"));
        HttpSession session = request.getSession();
        Map<Long, Integer> cart = (Map<Long, Integer>) session.getAttribute("cart");
        if (cart == null) {
            cart = new HashMap<>();
        }
        cart.put(productId, cart.getOrDefault(productId, 0) + 1);
        session.setAttribute("cart", cart);
        response.sendRedirect("UserController");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}