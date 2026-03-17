<%-- File: admin/navbar_admin.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<nav class="navbar navbar-expand-lg navbar-light bg-light mb-4">
    <a class="navbar-brand" href="AdminController">ShopHub</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
            aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav ml-auto">
            <li class="nav-item">
                <a class="nav-link" href="ProductController">Manage Products</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="CategoryController">Manage Categories</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="StatisticController">Statistics</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="PurchaseController">Manage Purchases</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="LogoutController"
                   onclick="return confirm('Are you sure you want to logout?')">Logout</a>
            </li>
        </ul>
    </div>
</nav>