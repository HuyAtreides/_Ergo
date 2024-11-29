<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Order Page</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <div class="order-page">
        <div class="tabs">
            <a href="#" class="tab active">Tất cả</a>
            <a href="#" class="tab">Chờ thanh toán</a>
            <a href="#" class="tab">Vận chuyển</a>
            <a href="#" class="tab">Chờ giao hàng</a>
            <a href="#" class="tab">Hoàn thành</a>
            <a href="#" class="tab">Đã hủy</a>
            <a href="#" class="tab">Trả hàng/Hoàn tiền</a>
        </div>

        <div class="search-bar">
            <input type="text" placeholder="Bạn có thể tìm kiếm theo tên Shop, ID đơn hàng hoặc Tên Sản phẩm">
        </div>

        <div class="order-card">
            <div class="shop-header">
                <span class="mall-label">Mall</span>
                <span class="shop-name">Oral Oasis</span>
                <button class="chat-btn">Chat</button>
                <button class="view-shop-btn">Xem Shop</button>
            </div>

            <div class="product-info">
                <img src="product-image.jpg" alt="Product Image" class="product-image">
                <div class="product-details">
                    <h3 class="product-title">
                        4 Đầu Bàn Chải Đánh Răng Điện Đầu Bàn Chải Thay Thế Cho Oral B EB17 / 18 / 20 / 25 / 50
                    </h3>
                    <p class="product-type">Phân loại hàng: EB50A-4</p>
                    <p class="product-quantity">x1</p>
                </div>
                <div class="product-price-info">
                    <p class="original-price">474.700₫</p>
                    <p class="sale-price">38.800₫</p>
                </div>
            </div>

            <div class="order-footer">
                <p class="delivery-status">
                    <span class="delivery-icon">🚚</span> Giao hàng thành công
                </p>
                <span class="order-status">HOÀN THÀNH</span>
                <div class="total-price">
                    <span>Thành tiền:</span>
                    <span class="price">42.300₫</span>
                </div>
                <div class="action-buttons">
                    <button class="review-btn">Đánh Giá</button>
                    <button class="contact-seller-btn">Liên Hệ Người Bán</button>
                    <button class="buy-again-btn">Mua Lại</button>
                </div>
            </div>
        </div>
    </div>
    <script>
        document.addEventListener('DOMContentLoaded', function() {
            const tabs = document.querySelectorAll('.tab');
            
            tabs.forEach(tab => {
                tab.addEventListener('click', function(e) {
                    e.preventDefault();
                    
                    // Xóa class active từ tất cả các tab
                    tabs.forEach(t => t.classList.remove('active'));
                    
                    // Thêm class active vào tab được click
                    this.classList.add('active');
                });
            });
        });
    </script>
</body>
</html>
