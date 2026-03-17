// New: src/bo/OrderBO.java
package bo;

import dao.OrderBillDAO;
import dao.OrderItemDAO;
import model.OrderBill;
import model.OrderItem;
import java.sql.SQLException;
import java.util.List;

public class OrderBO {
    private OrderBillDAO billDAO = new OrderBillDAO();
    private OrderItemDAO itemDAO = new OrderItemDAO();

    public long createOrder(long accountId) throws SQLException {
        return billDAO.create(accountId, true);
    }

    public void addOrderItem(long orderId, long productId, int quantity) throws SQLException {
        itemDAO.create(orderId, productId, quantity);
    }

    public List<OrderBill> getOrdersForUser(long accountId) throws SQLException {
        return billDAO.getByUser(accountId);
    }

    public List<OrderItem> getItemsForOrder(long orderId) throws SQLException {
        return itemDAO.getByOrder(orderId);
    }
}