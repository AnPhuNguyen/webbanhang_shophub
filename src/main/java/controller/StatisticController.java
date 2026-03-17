// File: controller/StatisticController.java
package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;

@WebServlet("/StatisticController")
public class StatisticController extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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

        // Placeholder data - you can enhance later with real stats
        request.setAttribute("totalRevenue", 450000000L);
        request.setAttribute("totalOrders", 1250);
        request.setAttribute("totalItemsSold", 3420);

        request.getRequestDispatcher("admin_statistic.jsp").forward(request, response);
    }
}