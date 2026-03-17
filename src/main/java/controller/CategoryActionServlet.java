// File: src/controller/CategoryActionServlet.java
package controller;

import bo.CategoryBO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/CategoryActionServlet")
public class CategoryActionServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CategoryBO categoryBO = new CategoryBO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        String msg = null;
        try {
            if ("add".equals(action)) {
                categoryBO.create(request.getParameter("name"));
                msg = "Category added successfully";
            } else if ("edit".equals(action)) {
                long id = Long.parseLong(request.getParameter("id"));
                categoryBO.update(id, request.getParameter("name"));
                msg = "Category updated successfully";
            } else if ("delete".equals(action)) {
                long id = Long.parseLong(request.getParameter("id"));
                categoryBO.delete(id);
                msg = "Category deleted successfully";
            }
        } catch (Exception e) {
            msg = "Error: " + e.getMessage();
        }

        request.getSession().setAttribute("msg", msg);
        response.sendRedirect("admin_category.jsp");
    }
}