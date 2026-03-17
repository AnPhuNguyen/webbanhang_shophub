<%-- File: admin_statistic.jsp (updated with clear labels and descriptions) --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>ShopHub - Statistics</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <style>
        .chart-container { 
            max-width: 800px; 
            margin: 40px auto; 
            background: #fff; 
            padding: 20px; 
            border-radius: 10px; 
            box-shadow: 0 4px 12px rgba(0,0,0,0.1); 
        }
        .chart-title { text-align: center; font-weight: bold; margin-bottom: 15px; }
        .chart-desc { text-align: center; color: #666; margin-bottom: 20px; font-size: 0.95rem; }
    </style>
</head>
<body>
    <jsp:include page="navbar_admin.jsp" />

    <div class="container mt-5">
        <h2 class="text-center mb-5">ShopHub Statistics Dashboard</h2>

        <!-- Revenue Chart -->
        <div class="chart-container">
            <div class="chart-title">Monthly Revenue (2025)</div>
            <div class="chart-desc">
                Shows total revenue generated each month from all processed orders in Vietnamese Đồng (₫).
            </div>
            <canvas id="revenueChart"></canvas>
        </div>

        <!-- Orders Chart -->
        <div class="chart-container">
            <div class="chart-title">Monthly Orders Count (2025)</div>
            <div class="chart-desc">
                Number of completed (purchased) orders placed each month.
            </div>
            <canvas id="ordersChart"></canvas>
        </div>

        <!-- Top Products Pie Chart -->
        <div class="chart-container">
            <div class="chart-title">Top Selling Products by Quantity</div>
            <div class="chart-desc">
                Distribution of total items sold across the top 4 products plus all others. 
                Based on quantity from processed orders.
            </div>
            <canvas id="topProductsChart"></canvas>
        </div>

        <!-- Summary Cards -->
        <div class="row mt-5 justify-content-center">
            <div class="col-md-4 text-center">
                <div class="card p-4 shadow-sm">
                    <h4>Total Revenue</h4>
                    <h3 class="text-primary"><fmt:formatNumber value="${totalRevenue}" type="currency" currencySymbol="₫"/></h3>
                    <small>All processed orders to date</small>
                </div>
            </div>
            <div class="col-md-4 text-center">
                <div class="card p-4 shadow-sm">
                    <h4>Total Orders</h4>
                    <h3 class="text-success">${totalOrders}</h3>
                    <small>Completed purchases</small>
                </div>
            </div>
            <div class="col-md-4 text-center">
                <div class="card p-4 shadow-sm">
                    <h4>Total Items Sold</h4>
                    <h3 class="text-info">${totalItemsSold}</h3>
                    <small>Across all orders</small>
                </div>
            </div>
        </div>
    </div>

    <script>
        // Monthly Revenue - Line Chart
        const revenueCtx = document.getElementById('revenueChart').getContext('2d');
        new Chart(revenueCtx, {
            type: 'line',
            data: {
                labels: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
                datasets: [{
                    label: 'Revenue (₫)',
                    data: [12000000, 19000000, 3000000, 5000000, 2000000, 3000000, 45000000, 35000000, 25000000, 40000000, 55000000, 60000000],
                    borderColor: '#28a745',
                    backgroundColor: 'rgba(40, 167, 69, 0.2)',
                    tension: 0.3,
                    fill: true
                }]
            },
            options: {
                responsive: true,
                plugins: {
                    legend: { position: 'top' },
                    title: { display: false }
                },
                scales: {
                    y: { beginAtZero: true }
                }
            }
        });

        // Monthly Orders - Bar Chart
        const ordersCtx = document.getElementById('ordersChart').getContext('2d');
        new Chart(ordersCtx, {
            type: 'bar',
            data: {
                labels: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
                datasets: [{
                    label: 'Number of Orders',
                    data: [65, 59, 80, 81, 56, 55, 120, 95, 110, 130, 145, 160],
                    backgroundColor: '#007bff',
                    borderColor: '#0056b3',
                    borderWidth: 1
                }]
            },
            options: {
                responsive: true,
                plugins: {
                    legend: { position: 'top' }
                },
                scales: {
                    y: { beginAtZero: true }
                }
            }
        });

        // Top Products - Pie Chart
        const topProductsCtx = document.getElementById('topProductsChart').getContext('2d');
        new Chart(topProductsCtx, {
            type: 'pie',
            data: {
                labels: ['Wireless Headphones', 'Smart Watch', 'Professional Laptop', 'Leather Backpack', 'Others'],
                datasets: [{
                    data: [30, 25, 20, 15, 10],
                    backgroundColor: [
                        '#FF6384', '#36A2EB', '#FFCE56', '#4BC0C0', '#9966FF'
                    ],
                    hoverOffset: 15
                }]
            },
            options: {
                responsive: true,
                plugins: {
                    legend: { position: 'right' },
                    tooltip: {
                        callbacks: {
                            label: function(context) {
                                let label = context.label || '';
                                if (label) label += ': ';
                                label += context.raw + '%';
                                return label;
                            }
                        }
                    }
                }
            }
        });
    </script>
</body>
</html>