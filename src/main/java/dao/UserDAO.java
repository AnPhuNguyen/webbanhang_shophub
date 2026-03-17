// File: src/dao/UserDAO.java
package dao;

import model.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    public User login(String identifier, String password) throws SQLException {
        // Check both password_plain (for dev/test users) and password_hash
        String sql = "SELECT * FROM users " +
                     "WHERE (account_name = ? OR email = ?) " +
                     "AND (password_plain = ? OR password_hash = ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, identifier);
            ps.setString(2, identifier);
            ps.setString(3, password);        // plain text input
            ps.setString(4, password);        // in case hash was stored directly (rare but safe)

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    User user = new User();
                    user.setAccountId(rs.getLong("account_id"));
                    user.setAccountName(rs.getString("account_name"));
                    user.setFullName(rs.getString("full_name"));
                    user.setEmail(rs.getString("email"));
                    user.setPasswordHash(rs.getString("password_hash"));
                    user.setPasswordPlain(rs.getString("password_plain"));

                    Object phoneObj = rs.getObject("phone_number");
                    user.setPhoneNumber(phoneObj != null ? rs.getInt("phone_number") : null);

                    user.setUserAddress(rs.getString("user_address"));
                    user.setAdmin(rs.getBoolean("is_admin"));
                    return user;
                }
            }
        }
        return null;
    }

    // Optional CRUD methods if needed later
    public User read(long id) throws SQLException {
        String sql = "SELECT * FROM users WHERE account_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    User user = new User();
                    user.setAccountId(rs.getLong("account_id"));
                    user.setAccountName(rs.getString("account_name"));
                    user.setFullName(rs.getString("full_name"));
                    user.setEmail(rs.getString("email"));
                    user.setPasswordHash(rs.getString("password_hash"));
                    user.setPasswordPlain(rs.getString("password_plain"));
                    Object phoneObj = rs.getObject("phone_number");
                    user.setPhoneNumber(phoneObj != null ? rs.getInt("phone_number") : null);
                    user.setUserAddress(rs.getString("user_address"));
                    user.setAdmin(rs.getBoolean("is_admin"));
                    return user;
                }
            }
        }
        return null;
    }

    public List<User> getAll() throws SQLException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                User user = new User();
                user.setAccountId(rs.getLong("account_id"));
                user.setAccountName(rs.getString("account_name"));
                user.setFullName(rs.getString("full_name"));
                user.setEmail(rs.getString("email"));
                user.setPasswordHash(rs.getString("password_hash"));
                user.setPasswordPlain(rs.getString("password_plain"));
                Object phoneObj = rs.getObject("phone_number");
                user.setPhoneNumber(phoneObj != null ? rs.getInt("phone_number") : null);
                user.setUserAddress(rs.getString("user_address"));
                user.setAdmin(rs.getBoolean("is_admin"));
                users.add(user);
            }
        }
        return users;
    }
}