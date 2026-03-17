// File: src/dao/ProductDAO.java
package dao;

import model.Product;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

	public void create(Product product) throws SQLException {
		String sql = "INSERT INTO products (category_id, product_name, product_description, price, stock_quantity, image_url) "
				+ "VALUES (?, ?, ?, ?, ?, ?)";
		try (Connection conn = DBConnection.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			ps.setObject(1, product.getCategoryId(), Types.BIGINT);
			ps.setString(2, product.getProductName());
			ps.setString(3, product.getProductDescription());
			ps.setInt(4, product.getPrice());
			ps.setInt(5, product.getStockQuantity());
			ps.setString(6, product.getImageUrl());
			ps.executeUpdate();

			try (ResultSet rs = ps.getGeneratedKeys()) {
				if (rs.next()) {
					product.setProductId(rs.getLong(1));
				}
			}
		}
	}

	public Product read(long id) throws SQLException {
		String sql = "SELECT * FROM products WHERE product_id = ?";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setLong(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					Product product = new Product();
					product.setProductId(rs.getLong("product_id"));
					product.setCategoryId(rs.getLong("category_id"));
					product.setProductName(rs.getString("product_name"));
					product.setProductDescription(rs.getString("product_description"));
					product.setPrice(rs.getInt("price"));
					product.setStockQuantity(rs.getInt("stock_quantity"));
					product.setImageUrl(rs.getString("image_url"));
					return product;
				}
			}
		}
		return null;
	}

	public void update(Product product) throws SQLException {
		String sql = "UPDATE products SET category_id = ?, product_name = ?, product_description = ?, "
				+ "price = ?, stock_quantity = ?, image_url = ? WHERE product_id = ?";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setObject(1, product.getCategoryId(), Types.BIGINT);
			ps.setString(2, product.getProductName());
			ps.setString(3, product.getProductDescription());
			ps.setInt(4, product.getPrice());
			ps.setInt(5, product.getStockQuantity());
			ps.setString(6, product.getImageUrl());
			ps.setLong(7, product.getProductId());
			ps.executeUpdate();
		}
	}

	public void delete(long id) throws SQLException {
		String sql = "DELETE FROM products WHERE product_id = ?";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setLong(1, id);
			ps.executeUpdate();
		}
	}

	public List<Product> getAll() throws SQLException {
		List<Product> products = new ArrayList<>();
		String sql = "SELECT * FROM products";
		try (Connection conn = DBConnection.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {
			while (rs.next()) {
				Product product = new Product();
				product.setProductId(rs.getLong("product_id"));
				product.setCategoryId(rs.getLong("category_id"));
				product.setProductName(rs.getString("product_name"));
				product.setProductDescription(rs.getString("product_description"));
				product.setPrice(rs.getInt("price"));
				product.setStockQuantity(rs.getInt("stock_quantity"));
				product.setImageUrl(rs.getString("image_url"));
//				System.out.println(product);
				products.add(product);
			}
		}
		return products;
	}

	public void updateStock(long productId, int changeAmount, boolean isPlus) throws SQLException {
		String sql = "UPDATE products SET stock_quantity = stock_quantity + ? WHERE product_id = ?";
		int amount = isPlus ? changeAmount : -changeAmount;
		try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, amount);
			ps.setLong(2, productId);
			ps.executeUpdate();
		}
	}

	public List<Product> getAllWithCategoryName() throws SQLException {
		List<Product> list = new ArrayList<>();
		String sql = "SELECT * FROM products ORDER BY product_id";

		try (Connection conn = DBConnection.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {
			while (rs.next()) {
				Product p = new Product();
				p.setProductId(rs.getLong("product_id"));
				p.setProductName(rs.getString("product_name"));
				p.setProductDescription(rs.getString("product_description"));
				p.setPrice(rs.getInt("price"));
				p.setStockQuantity(rs.getInt("stock_quantity"));
				p.setImageUrl(rs.getString("image_url"));
				long categoryId = rs.getLong("category_id");
				if (rs.wasNull()) {
				    categoryId = -1;  // Or use a special value to indicate null
				}
				p.setCategoryId(categoryId);
//				System.out.println(p);
				list.add(p);
			}
		}
		return list;
	}

	// Updated: src/dao/ProductDAO.java (added methods)
    public List<Product> searchByName(String name) throws SQLException {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE product_name LIKE ? ORDER BY product_id";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + name + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Product product = new Product();
                    product.setProductId(rs.getLong("product_id"));
                    Long categoryId = rs.getObject("category_id", Long.class);
                    if (categoryId != null) {
                        product.setCategoryId(categoryId);
                    }
                    product.setProductName(rs.getString("product_name"));
                    product.setProductDescription(rs.getString("product_description"));
                    product.setPrice(rs.getInt("price"));
                    product.setStockQuantity(rs.getInt("stock_quantity"));
                    product.setImageUrl(rs.getString("image_url"));
                    products.add(product);
                }
            }
        }
        return products;
    }

    public List<Product> getByCategory(Long categoryId) throws SQLException {
        List<Product> products = new ArrayList<>();
        String sql;
        PreparedStatement ps;
        try (Connection conn = DBConnection.getConnection()) {
            if (categoryId == null) {
                sql = "SELECT * FROM products WHERE category_id IS NULL ORDER BY product_id";
                ps = conn.prepareStatement(sql);
            } else {
                sql = "SELECT * FROM products WHERE category_id = ? ORDER BY product_id";
                ps = conn.prepareStatement(sql);
                ps.setLong(1, categoryId);
            }
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Product product = new Product();
                    product.setProductId(rs.getLong("product_id"));
                    product.setCategoryId(rs.getObject("category_id", Long.class));
                    product.setProductName(rs.getString("product_name"));
                    product.setProductDescription(rs.getString("product_description"));
                    product.setPrice(rs.getInt("price"));
                    product.setStockQuantity(rs.getInt("stock_quantity"));
                    product.setImageUrl(rs.getString("image_url"));
                    products.add(product);
                }
            }
        }
        return products;
    }
}