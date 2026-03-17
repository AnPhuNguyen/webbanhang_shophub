<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>ShopHub - Login</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <style>
        .login-container {
            max-width: 400px;
            margin: auto;
            padding: 20px;
        }
        .login-icon {
            background-color: #6f42c1;
            border-radius: 50%;
            width: 80px;
            height: 80px;
            display: flex;
            align-items: center;
            justify-content: center;
            margin: auto;
        }
        .login-icon i {
            color: white;
            font-size: 40px;
        }
    </style>
</head>
<body>
    <div class="container mt-5">
        <div class="login-container">
            <div class="text-center mb-4">
                <div class="login-icon">
                    <i class="fas fa-lock"></i>
                </div>
                <h4>Welcome to ShopHub</h4>
                <p>Sign in to continue</p>
            </div>
            <form action="LoginController" method="post">
                <div class="form-group">
                    <label for="username">Username</label>
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text"><i class="fas fa-user"></i></span>
                        </div>
                        <input type="text" class="form-control" id="username" name="username" placeholder="Enter username" required>
                    </div>
                </div>
                <div class="form-group">
                    <label for="password">Password</label>
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text"><i class="fas fa-lock"></i></span>
                        </div>
                        <input type="password" class="form-control" id="password" name="password" placeholder="Enter password" required>
                    </div>
                </div>
                <button type="submit" class="btn btn-primary btn-block">Sign In</button>
            </form>
        </div>
    </div>
    <c:if test="${not empty error}">
        <script>alert('${error}');</script>
    </c:if>
</body>
</html>