// New: src/controller/UpdateCartServlet.java
package controller;

import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/UpdateCartServlet")
public class UpdateCartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        long productId = request.getParameter("productId") != null ? Long.parseLong(request.getParameter("productId")) : 0;
        HttpSession session = request.getSession();
        Map<Long, Integer> cart = (Map<Long, Integer>) session.getAttribute("cart");
        if (cart != null && action != null) {
            if ("increase".equals(action)) {
                cart.put(productId, cart.getOrDefault(productId, 0) + 1);
            } else if ("decrease".equals(action)) {
                int qty = cart.getOrDefault(productId, 0);
                if (qty > 1) {
                    cart.put(productId, qty - 1);
                } else {
                    cart.remove(productId);
                }
            } else if ("remove".equals(action)) {
                cart.remove(productId);
            } else if ("clear".equals(action)) {
                cart.clear();
            }
            session.setAttribute("cart", cart);
        }
        response.sendRedirect("CartController");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}