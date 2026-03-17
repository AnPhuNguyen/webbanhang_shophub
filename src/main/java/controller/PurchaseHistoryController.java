// New: src/controller/PurchaseHistoryController.java
package controller;

import bo.OrderBO;
import bo.ProductBO;
import model.OrderBill;
import model.OrderItem;
import model.Product;
import model.User;

import java.io.IOException;
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

@WebServlet("/PurchaseHistoryController")
public class PurchaseHistoryController extends HttpServlet {
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

        OrderBO orderBO = new OrderBO();
        ProductBO productBO = new ProductBO();
        List<Map<String, Object>> orderDetails = new ArrayList<>();
        try {
            List<OrderBill> orders = orderBO.getOrdersForUser(user.getAccountId());
            for (OrderBill order : orders) {
                Map<String, Object> detail = new HashMap<>();
                detail.put("order", order);
                List<Map<String, Object>> items = new ArrayList<>();
                for (OrderItem item : orderBO.getItemsForOrder(order.getOrderId())) {
                    Map<String, Object> itemMap = new HashMap<>();
                    Product p = productBO.read(item.getProductId());
                    itemMap.put("product", p);
                    itemMap.put("quantity", item.getQuantity());
                    items.add(itemMap);
                }
                detail.put("items", items);
                orderDetails.add(detail);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("orderDetails", orderDetails);
        request.getRequestDispatcher("purchase_history.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}