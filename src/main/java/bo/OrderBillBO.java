// File: bo/OrderBillBO.java
package bo;

import dao.OrderBillDAO;
import model.OrderBill;
import java.sql.SQLException;
import java.util.List;

public class OrderBillBO {
    private OrderBillDAO orderBillDAO = new OrderBillDAO();

    public List<OrderBill> getPurchasedOrders() throws SQLException {
        return orderBillDAO.getPurchasedOrders();
    }
}