// File: controller/PurchaseController.java
package controller;

import bo.OrderBillBO;
import model.OrderBill;
import model.User;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/PurchaseController")
public class PurchaseController extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private OrderBillBO orderBillBO = new OrderBillBO();

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

        try {
            List<OrderBill> orders = orderBillBO.getPurchasedOrders();
            request.setAttribute("purchasedOrders", orders);
        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("msg", "Error loading orders: " + e.getMessage());
        }

        request.getRequestDispatcher("admin_purchase.jsp").forward(request, response);
    }
}