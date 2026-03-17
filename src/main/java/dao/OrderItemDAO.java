// New: src/dao/OrderItemDAO.java
package dao;

import model.OrderItem;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderItemDAO {
    public void create(long orderId, long productId, int quantity) throws SQLException {
        String sql = "INSERT INTO order_items (order_id, product_id, quantity) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, orderId);
            ps.setLong(2, productId);
            ps.setInt(3, quantity);
            ps.executeUpdate();
        }
    }

    public List<OrderItem> getByOrder(long orderId) throws SQLException {
        List<OrderItem> list = new ArrayList<>();
        String sql = "SELECT * FROM order_items WHERE order_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, orderId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    OrderItem i = new OrderItem();
                    i.setOrderDetailId(rs.getLong("order_detail_id"));
                    i.setOrderId(rs.getLong("order_id"));
                    i.setProductId(rs.getLong("product_id"));
                    i.setQuantity(rs.getInt("quantity"));
                    list.add(i);
                }
            }
        }
        return list;
    }
}