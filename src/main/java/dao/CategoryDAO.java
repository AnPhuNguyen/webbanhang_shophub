package dao;

import model.Category;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {

    public void create(Category category) throws SQLException {
        String sql = "INSERT INTO categories (category_name) VALUES (?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, category.getCategoryName());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    category.setCategoryId(rs.getLong(1));
                }
            }
        }
    }

    public Category read(long id) throws SQLException {
        String sql = "SELECT * FROM categories WHERE category_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Category category = new Category();
                    category.setCategoryId(rs.getLong("category_id"));
                    category.setCategoryName(rs.getString("category_name"));
                    return category;
                }
            }
        }
        return null;
    }

    public void update(Category category) throws SQLException {
        String sql = "UPDATE categories SET category_name = ? WHERE category_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, category.getCategoryName());
            ps.setLong(2, category.getCategoryId());
            ps.executeUpdate();
        }
    }

    public void delete(long id) throws SQLException {
        String sql = "DELETE FROM categories WHERE category_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        }
    }

    public List<Category> getAll() throws SQLException {
        List<Category> categories = new ArrayList<>();
        String sql = "SELECT * FROM categories";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Category category = new Category();
                category.setCategoryId(rs.getLong("category_id"));
                category.setCategoryName(rs.getString("category_name"));
                categories.add(category);
            }
        }
        return categories;
    }
    
 // Add these methods to existing CategoryDAO.java

    public List<Category> getAllWithCount() throws SQLException {
        List<Category> list = new ArrayList<>();
        String sql = 
            "SELECT c.category_id, c.category_name, COUNT(p.product_id) AS product_count " +
            "FROM categories c " +
            "LEFT JOIN products p ON c.category_id = p.category_id " +
            "GROUP BY c.category_id, c.category_name " +
            "UNION ALL " +
            "SELECT NULL AS category_id, 'Uncategorized' AS category_name, COUNT(*) AS product_count " +
            "FROM products " +
            "WHERE category_id IS NULL " +
            "ORDER BY category_name";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Category c = new Category();
                Long id = rs.getObject("category_id") != null ? rs.getLong("category_id") : null;
                c.setCategoryId(id != null ? id : -1); // Use -1 as sentinel for Uncategorized (since null not allowed in long)
                c.setCategoryName(rs.getString("category_name"));
                c.setProductCount(rs.getInt("product_count"));
                list.add(c);
            }
        }
        return list;
    }

    public Category getByName(String name) throws SQLException {
        String sql = "SELECT * FROM categories WHERE category_name = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Category c = new Category();
                    c.setCategoryId(rs.getLong("category_id"));
                    c.setCategoryName(rs.getString("category_name"));
                    return c;
                }
            }
        }
        return null;
    }
    
 // 1. Add method to CategoryDAO.java
    public String getCategoryNameById(Long categoryId) throws SQLException {
        if (categoryId == null) {
            return "Uncategorized";
        }
        String sql = "SELECT category_name FROM categories WHERE category_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, categoryId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("category_name");
                }
            }
        }
        return "Uncategorized"; // fallback if ID not found
    }
}