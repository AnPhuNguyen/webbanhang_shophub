// New: src/controller/PurchaseCartServlet.java
package controller;

import bo.OrderBO;
import bo.ProductBO;
import model.Product;
import model.User;

import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/PurchaseCartServlet")
public class PurchaseCartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Map<Long, Integer> cart = (Map<Long, Integer>) session.getAttribute("cart");
        if (cart == null || cart.isEmpty()) {
            session.setAttribute("msg", "Cart is empty");
            response.sendRedirect("CartController");
            return;
        }

        ProductBO productBO = new ProductBO();
        try {
            for (Map.Entry<Long, Integer> entry : cart.entrySet()) {
                Product p = productBO.read(entry.getKey());
                if (p.getStockQuantity() < entry.getValue()) {
                    session.setAttribute("msg", "Insufficient stock for " + p.getProductName());
                    response.sendRedirect("CartController");
                    return;
                }
            }

            User user = (User) session.getAttribute("user");
            OrderBO orderBO = new OrderBO();
            long orderId = orderBO.createOrder(user.getAccountId());

            for (Map.Entry<Long, Integer> entry : cart.entrySet()) {
                orderBO.addOrderItem(orderId, entry.getKey(), entry.getValue());
                productBO.updateStock(entry.getKey(), entry.getValue(), false);
            }

            cart.clear();
            session.setAttribute("cart", cart);
            session.setAttribute("msg", "Purchase successful");
            response.sendRedirect("PurchaseHistoryController");
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("msg", "Error during purchase: " + e.getMessage());
            response.sendRedirect("CartController");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}