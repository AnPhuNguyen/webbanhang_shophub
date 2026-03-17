// File: src/dao/PurchaseDAO.java
package dao;

import model.Purchase;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class PurchaseDAO {

    public List<Purchase> getAll() throws SQLException {
        List<Purchase> purchases = new ArrayList<>();
        String sql = "SELECT * FROM purchases";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Purchase purchase = new Purchase();
                purchase.setPurchaseId(rs.getLong("purchase_id"));
                purchase.setOrderId(rs.getLong("order_id"));
                purchase.setPurchaseTime(rs.getTimestamp("purchase_time"));
                purchase.setProcessed(rs.getBoolean("is_processed"));
                purchases.add(purchase);
            }
        }
        return purchases;
    }

    public void setProcessed(long purchaseId, boolean isProcessed) throws SQLException {
        String sql = "UPDATE purchases SET is_processed = ? WHERE purchase_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setBoolean(1, isProcessed);
            ps.setLong(2, purchaseId);
            ps.executeUpdate();
        }
    }

    public void createPurchase(long orderId) throws SQLException {
        String sql = "INSERT INTO purchases (order_id, purchase_time, is_processed) VALUES (?, ?, 1)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, orderId);
            ps.setTimestamp(2, new Timestamp(new Date().getTime()));
            ps.executeUpdate();
        }
    }
}