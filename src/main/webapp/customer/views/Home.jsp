<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Customer Home</title>
  <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
  <style>
    .carousel-item {
      height: 100vh;
    }

    .carousel-item img {
      height: 100%;
      width: 100%;
      object-fit: cover;
    }
  </style>
</head>
<body>

<div id="campaignCarousel" class="carousel slide" data-ride="carousel">
  <div class="carousel-inner">
    <c:forEach var="campaign" items="${marketingCampaigns}">
      <c:set var="firstImage" value="${campaign.campaignImages[0].imagePath}"/>
      <div class="carousel-item ${campaign.campaignId == 1 ? 'active' : ''}">
        <img src="${firstImage}" class="d-block w-100" alt="Campaign Image">
        <div class="carousel-caption d-none d-md-block">
          <h5>Campaign ${campaign.campaignId}</h5>
          <p>${campaign.content}</p>
        </div>
      </div>
    </c:forEach>
  </div>
  <a class="carousel-control-prev" href="#campaignCarousel" role="button" data-slide="prev">
    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
    <span class="sr-only">Previous</span>
  </a>
  <a class="carousel-control-next" href="#campaignCarousel" role="button" data-slide="next">
    <span class="carousel-control-next-icon" aria-hidden="true"></span>
    <span class="sr-only">Next</span>
  </a>
</div>

<div class="container mt-5">
  <h2>Sản phẩm</h2>
  <div class="row">
    <c:forEach var="product" items="${products}" end="9">
      <div class="col-md-3 mb-4">
        <div class="card">
          <img src="${product.productImages[0].productImage}" class="card-img-top" alt="${product.name}">
          <div class="card-body">
            <h5 class="card-title">${product.name}</h5>
            <p class="card-text">${product.descript}</p>
            <p class="card-text"><strong>Giá: </strong>${product.productTypes[0].price} VND</p>
          </div>
        </div>
      </div>
    </c:forEach>
  </div>
  <div class="text-center">
    <a href="${pageContext.request.contextPath}/products/search" class="btn btn-primary">Xem tất cả</a>
  </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>

