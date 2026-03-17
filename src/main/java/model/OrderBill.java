// File: model/OrderBill.java (add processed field + helpers)
package model;

import java.util.Date;

public class OrderBill {
    private long orderId;
    private long accountId;
    private String userName;           // joined from users
    private boolean isPurchased;
    private boolean processed;         // new: from purchases table or false if not exists
    private Date createdAt;
    private int totalAmount;           // calculated from order items

    // Constructors
    public OrderBill() {}

    // Getters and Setters
    public long getOrderId() { return orderId; }
    public void setOrderId(long orderId) { this.orderId = orderId; }

    public long getAccountId() { return accountId; }
    public void setAccountId(long accountId) { this.accountId = accountId; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public boolean isPurchased() { return isPurchased; }
    public void setPurchased(boolean purchased) { isPurchased = purchased; }

    public boolean isProcessed() { return processed; }
    public void setProcessed(boolean processed) { this.processed = processed; }

    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }

    public int getTotalAmount() { return totalAmount; }
    public void setTotalAmount(int totalAmount) { this.totalAmount = totalAmount; }
}