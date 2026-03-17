package bo;

import dao.UserDAO;
import model.User;
import java.sql.SQLException;

public class UserBO {
    private UserDAO userDAO = new UserDAO();

    public User login(String identifier, String password) throws SQLException {
        return userDAO.login(identifier, password);
    }

    // Other CRUD methods...
}