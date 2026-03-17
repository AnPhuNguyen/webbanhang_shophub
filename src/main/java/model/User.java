package model;

public class User {
    private long accountId;
    private String accountName;
    private String fullName;
    private String email;
    private String passwordHash;
    private String passwordPlain;
    private Integer phoneNumber;
    private String userAddress;
    private boolean isAdmin;

    // Constructors
    public User() {}

    public User(long accountId, String accountName, String fullName, String email, String passwordHash, String passwordPlain, Integer phoneNumber, String userAddress, boolean isAdmin) {
        this.accountId = accountId;
        this.accountName = accountName;
        this.fullName = fullName;
        this.email = email;
        this.passwordHash = passwordHash;
        this.passwordPlain = passwordPlain;
        this.phoneNumber = phoneNumber;
        this.userAddress = userAddress;
        this.isAdmin = isAdmin;
    }

    // Getters and Setters
    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getPasswordPlain() {
        return passwordPlain;
    }

    public void setPasswordPlain(String passwordPlain) {
        this.passwordPlain = passwordPlain;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}