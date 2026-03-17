// File: src/bo/ProductBO.java
package bo;

import dao.CategoryDAO;
import dao.ProductDAO;
import model.Category;
import model.Product;
import java.sql.SQLException;
import java.util.List;

public class ProductBO {
    private ProductDAO productDAO = new ProductDAO();
    private CategoryDAO categoryDAO = new CategoryDAO();

    public List<Product> getAll() throws SQLException {
        return productDAO.getAllWithCategoryName(); // We'll add this to DAO
    }

    public List<Category> getAllCategories() throws SQLException {
        return categoryDAO.getAllWithCount();
    }

    public void create(Product product) throws SQLException {
        validate(product);
        productDAO.create(product);
    }

    public void update(Product product) throws SQLException {
        validate(product);
        productDAO.update(product);
    }

    public void delete(long id) throws SQLException {
        productDAO.delete(id);
    }

    public void updateStock(long productId, int amount, boolean isPlus) throws SQLException {
        if (amount <= 0) throw new SQLException("Quantity must be positive");
        productDAO.updateStock(productId, amount, isPlus);
    }

    private void validate(Product p) throws SQLException {
        if (p.getProductName() == null || p.getProductName().trim().isEmpty())
            throw new SQLException("Product name is required");
        if (p.getPrice() <= 0) throw new SQLException("Price must be positive");
        if (p.getStockQuantity() < 0) throw new SQLException("Stock cannot be negative");
    }

 // Updated: src/bo/ProductBO.java (added methods)
    public List<Product> searchByName(String name) throws SQLException {
        return productDAO.searchByName(name);
    }

    public List<Product> getByCategory(Long categoryId) throws SQLException {
        return productDAO.getByCategory(categoryId);
    }

    public Product read(long id) throws SQLException {
        return productDAO.read(id);
    }
}