// File: src/bo/CategoryBO.java
package bo;

import dao.CategoryDAO;
import model.Category;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryBO {
    private CategoryDAO categoryDAO = new CategoryDAO();

    public List<Category> getAllWithCount() throws SQLException {
        return categoryDAO.getAllWithCount(); 
    }

    public void create(String name) throws SQLException {
        if (name == null || name.trim().isEmpty()) throw new SQLException("Category name required");
        if (exists(name)) throw new SQLException("Category name already exists");
        Category c = new Category();
        c.setCategoryName(name.trim());
        categoryDAO.create(c);
    }

    public void update(long id, String name) throws SQLException {
        if (id == 0) throw new SQLException("Cannot edit Uncategorized");
        if (name == null || name.trim().isEmpty()) throw new SQLException("Category name required");
        if (exists(name, id)) throw new SQLException("Category name already exists");
        Category c = new Category();
        c.setCategoryId(id);
        c.setCategoryName(name.trim());
        categoryDAO.update(c);
    }

    public void delete(long id) throws SQLException {
        if (id == 0) throw new SQLException("Cannot delete Uncategorized");
        categoryDAO.delete(id);
    }

    private boolean exists(String name) throws SQLException {
        return categoryDAO.getByName(name) != null;
    }

    private boolean exists(String name, long excludeId) throws SQLException {
        Category c = categoryDAO.getByName(name);
        return c != null && c.getCategoryId() != excludeId;
    }
    
 // 2. Add method to CategoryBO.java
    public String getCategoryNameById(Long categoryId) throws SQLException {
        return categoryDAO.getCategoryNameById(categoryId);
    }

	public List<Category> getAllCategoriesForDropdown() throws SQLException {
    List<Category> all = categoryDAO.getAllWithCount();
    List<Category> result = new ArrayList<>();
    for (Category c : all) {
        if (c.getCategoryId() >= 0) { // exclude sentinel -1
            result.add(c);
        }
    }
    return result;
}
}