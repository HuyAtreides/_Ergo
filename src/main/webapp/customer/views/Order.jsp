<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600;700&display=swap" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        /* General Styles */
        body {
            font-family: 'Poppins', sans-serif;
            background-color: #f0f2f5;
            color: #333;
        }

        .container {
            max-width: 900px;
        }

        .card {
            border-radius: 12px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
            border: none;
            background-color: #fff;
        }

        .card-header {
            background: linear-gradient(45deg, #4facfe, #00f2fe);
            color: #fff;
            padding: 1rem;
            border-bottom: none;
            font-weight: bold;
        }

        .card-body {
            padding: 1.5rem;
        }

        .text-highlight {
            color: #007bff;
            font-weight: 600;
        }

        /* Product List */
        .list-group-item {
            padding: 1rem 1.5rem;
            border: none;
            background-color: #f9f9f9;
            border-bottom: 1px solid #e9ecef;
            transition: background-color 0.3s ease;
        }

        .list-group-item:hover {
            background-color: #f1f3f5;
        }

        .product-image {
		    display: block;
		    width: 20px !important;
		    height: 20px !important;
		    object-fit: cover !important;
		    border-radius: 8px !important;
		}




        .product-details h6 {
            font-size: 1.1rem; /* Tăng kích thước chữ */
            margin-bottom: 0.5rem;
        }

        .product-details p {
            font-size: 0.9rem;
        }

        /* Responsive Design */
        @media (max-width: 768px) {
            .card-body {
                padding: 1rem;
            }

            .list-group-item {
                flex-wrap: wrap;
                text-align: center;
            }

            .list-group-item img {
                margin-bottom: 10px;
            }
        }

        .btn-edit {
            background-color: #4facfe;
            border: none;
            border-radius: 8px;
            color: white;
            padding: 0.5rem 1rem;
            transition: all 0.3s ease;
        }

        .btn-edit:hover {
            background-color: #007bff;
            box-shadow: 0 4px 8px rgba(0, 123, 255, 0.3);
        }
    </style>
</head>
<body>

<div class="container mt-5">
    <!-- Shipping Info -->
    <div class="card mb-4">
        <div class="card-header">
            Thông Tin Giao Hàng
        </div>
        <div class="card-body d-flex justify-content-between align-items-center">
            <div>
                <h5 class="text-highlight mb-2">Mã đơn hàng: ${order.orderId}</h5>
                <p class="mb-1">Khách hàng: <span class="fw-bold">${order.customer.name}</span></p>
                <p class="small text-muted">${order.streetNumber}, ${order.ward}, ${order.district}, ${order.cityOfProvince}</p>
            </div>
            <a href="/shippingInfo" class="btn-edit"><img src="https://cdn-icons-png.flaticon.com/512/1250/1250615.png" width="18" alt="Edit Icon">
            </a>
        </div>
    </div>

    <!-- Order Items -->
    <div class="card">
        <div class="card-header">
            Danh Sách Sản Phẩm
        </div>
        <div class="card-body">
            <ul class="list-group">
                <c:forEach var="item" items="${orderItems}">
                    <li class="list-group-item d-flex align-items-center">
                        <img src="${item.productType.product.productImages[0].productImage}" 
					     alt="Product Image" class="product-image me-3" 
					     style="width: 100px; height: 100px; object-fit: cover; border-radius: 8px;">

                        <div class="product-details">
                            <h6 class="mb-1">${item.productType.product.name}</h6>
                            <p class="small text-muted mb-1">Kích thước: ${item.productType.length} x ${item.productType.width} x ${item.productType.height}</p>
                            <p class="small text-muted mb-0">Số lượng: ${item.quantity} | Đơn giá: ${item.price}</p>
                        </div>
                    </li>
                </c:forEach>
            </ul>
<!-- Total Price -->
            <div class="text-end mt-3">
                <h6 class="text-dark fw-bold">Tổng cộng: <span class="text-highlight"> ${order.actualCost}</span></h6>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>


