package model;

import java.util.Date;

public class Purchase {
    private long purchaseId;
    private long orderId;
    private Date purchaseTime;
    private boolean isProcessed;

    // Constructors
    public Purchase() {}

    public Purchase(long purchaseId, long orderId, Date purchaseTime, boolean isProcessed) {
        this.purchaseId = purchaseId;
        this.orderId = orderId;
        this.purchaseTime = purchaseTime;
        this.isProcessed = isProcessed;
    }

    // Getters and Setters
    public long getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(long purchaseId) {
        this.purchaseId = purchaseId;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public Date getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(Date purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    public boolean isProcessed() {
        return isProcessed;
    }

    public void setProcessed(boolean processed) {
        isProcessed = processed;
    }
}