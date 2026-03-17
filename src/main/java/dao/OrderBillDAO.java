// New: src/dao/OrderBillDAO.java
package dao;

import model.OrderBill;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderBillDAO {
    public long create(long accountId, boolean isPurchased) throws SQLException {
        String sql = "INSERT INTO order_bills (account_id, is_purchased) VALUES (?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setLong(1, accountId);
            ps.setBoolean(2, isPurchased);
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getLong(1);
                }
            }
        }
        return -1;
    }

    public List<OrderBill> getByUser(long accountId) throws SQLException {
        List<OrderBill> list = new ArrayList<>();
        String sql = "SELECT * FROM order_bills WHERE account_id = ? ORDER BY created_at DESC";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, accountId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    OrderBill o = new OrderBill();
                    o.setOrderId(rs.getLong("order_id"));
                    o.setAccountId(rs.getLong("account_id"));
                    o.setPurchased(rs.getBoolean("is_purchased"));
                    o.setCreatedAt(rs.getTimestamp("created_at"));
                    list.add(o);
                }
            }
        }
        return list;
    }

    public List<OrderBill> getPurchasedOrders() throws SQLException {
        List<OrderBill> orders = new ArrayList<>();
        String sql = """
            SELECT 
                ob.order_id AS order_id, 
                ob.account_id, 
                u.account_name AS user_name,
                ob.created_at,
                COALESCE(SUM(oi.quantity * p.price), 0) AS total_amount,
                CASE WHEN EXISTS (SELECT 1 FROM purchases pur WHERE pur.order_id = ob.order_id) 
                     THEN 1 ELSE 0 END AS processed
            FROM order_bills ob
            JOIN users u ON ob.account_id = u.account_id
            LEFT JOIN order_items oi ON ob.order_id = oi.order_id
            LEFT JOIN products p ON oi.product_id = p.product_id
            WHERE ob.is_purchased = 1
            GROUP BY ob.order_id, ob.account_id, u.account_name, ob.created_at
            ORDER BY ob.created_at DESC
            """;

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                OrderBill order = new OrderBill();
                order.setOrderId(rs.getLong("order_id"));
                order.setAccountId(rs.getLong("account_id"));
                order.setUserName(rs.getString("user_name"));
                order.setCreatedAt(rs.getTimestamp("created_at"));
                order.setTotalAmount(rs.getInt("total_amount"));
                order.setProcessed(rs.getBoolean("processed"));
                orders.add(order);
            }
        }
        return orders;
    }


}