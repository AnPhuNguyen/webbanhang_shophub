package model;

public class Category {
    private long categoryId;
    private String categoryName;
    private int productCount;

    // Constructors
    public Category() {}

    public Category(long categoryId, String categoryName, int productCount) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.productCount = productCount;
    }

    // Getters and Setters
    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

	public void setProductCount(int int1) {
		this.productCount = int1;
	}
	
	public int getProductCount() {
        return productCount;
    }
}