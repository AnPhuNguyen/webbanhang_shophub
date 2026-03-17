// File: controller/PurchaseActionServlet.java
package controller;

import bo.PurchaseBO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/PurchaseActionServlet")
public class PurchaseActionServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PurchaseBO purchaseBO = new PurchaseBO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        String msg = null;

        try {
            if ("process".equals(action)) {
                long orderId = Long.parseLong(request.getParameter("orderId"));
                purchaseBO.processOrder(orderId);
                msg = "Order " + orderId + " processed and marked as completed successfully";
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg = "Error processing order: " + e.getMessage();
        }

        request.getSession().setAttribute("msg", msg);
        response.sendRedirect("PurchaseController");
    }
}