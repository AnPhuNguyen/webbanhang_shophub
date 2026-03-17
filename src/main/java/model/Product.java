package model;

public class Product {
    private long productId;
    private long categoryId;
    private String productName;
    private String productDescription;
    private int price;
    private int stockQuantity;
    private String imageUrl;

    // Constructors
    public Product() {}

    public Product(long productId, long categoryId, String productName, String productDescription, int price, int stockQuantity, String imageUrl) {
        this.productId = productId;
        this.categoryId = categoryId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.imageUrl = imageUrl;
    }

    // Getters and Setters
    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    
    
}