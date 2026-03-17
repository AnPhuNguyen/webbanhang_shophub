package bo;

import dao.PurchaseDAO;
import model.Purchase;
import java.sql.SQLException;
import java.util.List;

public class PurchaseBO {
    private PurchaseDAO purchaseDAO = new PurchaseDAO();

    public List<Purchase> getAll() throws SQLException {
        return purchaseDAO.getAll();
    }

    public void setProcessed(long purchaseId, boolean isProcessed) throws SQLException {
        purchaseDAO.setProcessed(purchaseId, isProcessed);
    }

    public void processOrder(long orderId) throws SQLException {
        purchaseDAO.createPurchase(orderId);
    }


}