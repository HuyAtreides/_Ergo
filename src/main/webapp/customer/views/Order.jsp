<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600;700&family=Roboto:wght@400;500;700&display=swap" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container mt-5">
    <!-- Shipping Info -->
    <div class="card mb-4 shadow-sm" style="border-radius: 12px; overflow: hidden;">
        <div class="card-header" 
             style="background-color: #000000; 
                    font-family: 'Poppins', sans-serif; /* Font Poppins */
                    font-weight: bold; 
                    font-size: 18px; 
                    color: #ffffff; /* Màu chữ trắng */
                    padding: 15px 20px; 
                    text-transform: uppercase;">
            Thông Tin Giao Hàng
        </div>

        <div class="card-body d-flex justify-content-between align-items-center" 
             style="padding: 20px; background-color: #ffffff;">
            <!-- Order Details -->
            <div style="max-width: 80%; font-family: 'Roboto', sans-serif;">
                <h5 class="text-highlight mb-2" 
                    style="font-weight: bold; color: #007bff; font-size: 18px;">
                    Mã đơn hàng: ${order.orderId}
                </h5>
                <p class="mb-1" style="font-size: 16px; color: #333;">
                    Khách hàng: 
                    <span class="fw-bold">${order.customer.name}</span>
                </p>
                <p class="small text-muted" style="margin-bottom: 0;">
                    ${order.streetNumber}, ${order.ward}, ${order.district}, ${order.cityOfProvince}
                </p>
            </div>

            <!-- Edit Button -->
            <a href="/shippingInfo" 
               class="btn-edit d-flex align-items-center justify-content-center"
               style="background-color: #007bff; color: #ffffff; width: 40px; height: 40px; 
                      border-radius: 50%; text-decoration: none; transition: transform 0.3s;">
                <img src="https://cdn-icons-png.flaticon.com/512/1250/1250615.png" 
                     width="18" alt="Edit Icon" style="filter: invert(1);">
            </a>
        </div>
    </div>

    <!-- Order Items -->
    <div class="card mb-4"> <!-- Thêm lớp Bootstrap để tạo khoảng cách -->
        <div class="card-header" 
             style="background-color: #000000; 
                    font-family: 'Poppins', sans-serif; 
                    font-weight: bold; 
                    font-size: 18px; 
                    color: #ffffff; 
                    padding: 15px 20px;">
            Danh Sách Sản Phẩm
        </div>
        <div class="card-body">
            <ul class="list-group">
                <c:forEach var="item" items="${orderItems}">
                    <li class="list-group-item d-flex align-items-center" 
                        style="border: 1px solid #ddd; border-radius: 10px; margin-bottom: 15px; padding: 15px; 
                               transition: background-color 0.3s ease;">
                        <!-- Product Image -->
                        <img src="${item.productType.product.productImages[0].productImage}" 
                             alt="Product Image" class="product-image me-3" 
                             style="width: 100px; height: 100px; object-fit: cover; border-radius: 8px;">
                        
                        <!-- Product Details -->
                        <div class="product-details" style="flex-grow: 1;">
                            <h6 class="mb-1" style="font-weight: 600; font-size: 16px; color: #333;">${item.productType.product.name}</h6>
                            <p class="small text-muted mb-1">Kích thước: ${item.productType.length} x ${item.productType.width} x ${item.productType.height}</p>
                            <p class="small text-muted mb-0">Số lượng: ${item.quantity} | Đơn giá: ${item.price}</p>
                        </div>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
    
    <!-- Voucher -->
    <div class="promo-checkbox" style="margin-top: 20px; display: flex; align-items: center; gap: 15px;"> 
        <!-- Nút Xem Voucher -->
        <div class="promo-code">
            <a href="javascript:void(0);" class="btn-list-voucher" 
               data-bs-toggle="modal" data-bs-target="#voucherModal"
			   style="background-color: #000000; 
			          color: white; 
			          border: none; 
			          padding: 17px 24px; 
			          border-radius: 8px; 
			          cursor: pointer; 
			          font-family: 'Poppins', sans-serif; 
			          font-weight: 600; 
			          font-size: 16px; 
			          text-decoration: none; /* Bỏ gạch chân */
			          transition: background-color 0.3s ease, transform 0.2s ease;" 
			   onmouseover="this.style.backgroundColor='#333333'; this.style.transform='scale(1.05)';" 
			   onmouseout="this.style.backgroundColor='#000000'; this.style.transform='scale(1)';">			    
			    Xem voucher
			</a>
			
			<!-- Modal list voucher-->
			<div class="modal fade" id="voucherModal" tabindex="-1" aria-labelledby="voucherModalLabel" aria-hidden="true">
			  <div class="modal-dialog">
			    <div class="modal-content" style="background-color: #fff; border-radius: 12px; color: #333; padding: 20px;">
			      <div class="modal-header" style="border-bottom: 2px solid #e0e0e0; background-color: #f9f9f9;">
			        <h5 class="modal-title" id="voucherModalLabel" style="font-weight: 600; font-size: 1.25rem; color: #333;">Voucher Details</h5>
			        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close" style="border: none; background: transparent; color: #333;"></button>
			      </div>
			      <form method="POST" action="${pageContext.request.contextPath}/customer/order/voucher">
			      	<input type="hidden" name="orderId" value="${order.orderId}" />
			        <div class="modal-body" style="font-size: 1rem; color: #333;">
			          <c:if test="${not empty listVoucher}">
			            <ul style="list-style-type: none; padding: 0;">
			              <c:forEach var="voucher" items="${listVoucher}">
			                <li style="margin-bottom: 20px; border-bottom: 1px solid #ddd; padding-bottom: 15px;">
			                  <input type="radio" name="selectedVoucher" value="${voucher.voucherId}" id="voucher${voucher.code}" style="margin-right: 15px; accent-color: #007bff;" />
			                  <label for="voucher${voucher.code}" style="font-size: 1.1rem; color: #333; font-weight: 500;">
			                    ${voucher.code} - <fmt:formatNumber value="${voucher.discount}" pattern="#%" />
			                  </label>
			                  <p style="font-size: 0.9rem; color: #777;">Hạn sử dụng: ${voucher.dateEnd}</p>
			                </li>
			              </c:forEach>
			            </ul>
			          </c:if>
			          <c:if test="${empty listVoucher}">
			            <p style="font-size: 1rem; color: #888;">Không có voucher nào để hiển thị.</p>
			          </c:if>
			        </div>
			        <div class="modal-footer" style="border-top: 2px solid #e0e0e0; background-color: #f9f9f9;">
			          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal" style="background-color: #6c757d; color: white; border: none; padding: 8px 16px; border-radius: 4px;">Close</button>
			          <button type="submit" class="btn btn-primary" style="background-color: #007bff; color: white; border: none; padding: 8px 16px; border-radius: 4px;">Select Voucher</button>
			        </div>
			      </form>
			    </div>
			  </div>
			</div>
        </div>
        <!-- Voucher đã chọn hoặc thông báo -->
        <div class="selected-voucher" 
             style="padding: 10px; background-color: #f8f9fa; border-radius: 8px; 
                    border: 1px solid #ddd; font-family: 'Poppins', sans-serif; font-size: 14px; color: #333;">
            <c:choose>
                <c:when test="${selectedVoucher == null || selectedVoucher eq ''}">
                    <span>Bạn chưa chọn voucher nào.</span>
                </c:when>
                <c:otherwise>
                    <span>Voucher đã chọn: 
                        <span style="font-weight: bold; color: #007bff;">${selectedVoucher}</span>
                    </span>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>

<div class="checkout-container" style="max-width: 600px; margin: auto; padding: 20px; background-color: #fff; box-shadow: 0 8px 16px rgba(0, 0, 0, 0.1); border-radius: 12px; transition: box-shadow 0.3s ease; margin-top: 20px;">
  <header class="checkout-header" style="text-align: center; font-size: 28px; font-family: 'Arial', sans-serif; font-weight: bold; margin-bottom: 20px; color: #333;">
    Shopping Bill
  </header>
  
  <!-- Shopping Bill Section -->
  <div class="shopping-bill" style="margin-top: 20px; margin-bottom: 20px;">
    <table class="bill-table" style="width: 100%; margin-top: 10px; border-collapse: collapse; font-size: 14px; font-family: 'Arial', sans-serif;">
      <tbody>
        <tr>
          <td style="padding: 12px; text-align: left; color: #777;">Shipping fee</td>
          <td style="padding: 12px; text-align: right;">$5.43</td>
        </tr>
        <tr>
		  <td style="padding: 12px; text-align: left; color: #777;"> Discount: ${voucher.discount * 100}% </td>
          <td style="padding: 12px; text-align: right;">-$1.89</td>
        </tr>
        <tr>
          <td style="padding: 12px; text-align: left; color: #333;">Price Total</td>
          <td style="padding: 12px; text-align: right;">${order.totalCost} đ</td>
        </tr>
      </tbody>
      <tfoot>
        <tr>
          <td style="padding: 15px 12px; font-weight: bold; text-align: left; font-size: 16px; color: #333;">Total</td>
          <td style="padding: 15px 12px; font-weight: bold; text-align: right; font-size: 28px; color: #388E3C; font-family: 'Arial', sans-serif;">
            <span style="cursor: pointer; transition: transform 0.2s ease, color 0.2s ease;" 
              onmouseover="this.style.color='#FF9800'; this.style.transform='scale(1.1)';" 
              onmouseout="this.style.color='#388E3C'; this.style.transform='scale(1)';">
              ${order.actualCost + 5} 
            </span>
          </td>
        </tr>
      </tfoot>
    </table>
  </div>

  <!-- Submit Button -->
  <div class="submit-section" style="margin-top: 20px; display: flex; justify-content: flex-end;">
    <button class="submit-button" type="submit" style="padding: 12px 24px; font-size: 16px; background-color: #4CAF50; color: white; border: none; cursor: pointer; border-radius: 6px; display: flex; justify-content: center; align-items: center; box-shadow: 0 6px 12px rgba(0, 0, 0, 0.15); transition: background-color 0.3s ease, transform 0.3s ease;" 
      onmouseover="this.style.backgroundColor='#388E3C'; this.style.transform='scale(1.05)';" 
      onmouseout="this.style.backgroundColor='#4CAF50'; this.style.transform='scale(1)';" 
      onmousedown="this.style.backgroundColor='#2C6B31';">
      <svg class="icon" style="margin-right: 8px; width: 20px; height: 20px;"><use xlink:href="#icon-shopping-bag" /></svg>Buy Now
    </button>
  </div>
</div>


		
		
		<svg xmlns="http://www.w3.org/2000/svg" style="display: none">
		  <symbol id="icon-shopping-bag" viewBox="0 0 24 24">
		    <path d="M20 7h-4v-3c0-2.209-1.791-4-4-4s-4 1.791-4 4v3h-4l-2 17h20l-2-17zm-11-3c0-1.654 1.346-3 3-3s3 1.346 3 3v3h-6v-3zm-4.751 18l1.529-13h2.222v1.5c0 .276.224.5.5.5s.5-.224.5-.5v-1.5h6v1.5c0 .276.224.5.5.5s.5-.224.5-.5v-1.5h2.222l1.529 13h-15.502z" />
		  </symbol>
		</svg>

        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
