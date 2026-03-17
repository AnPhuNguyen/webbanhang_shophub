package controller;

import bo.UserBO;
import model.User;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            if (user.isAdmin()) {
                response.sendRedirect("AdminController");
            } else {
                response.sendRedirect("UserController");
            }
        } else {
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        try {
            UserBO bo = new UserBO();
            User user = bo.login(username, password);
            if (user == null) {
                request.setAttribute("error", "Incorrect username or password");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                if (user.isAdmin()) {
                    response.sendRedirect("AdminController");
                } else {
                    response.sendRedirect("UserController");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();  // This will show the real error in Tomcat console/logs
            request.setAttribute("error", "Login failed: " + e.getMessage());
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}