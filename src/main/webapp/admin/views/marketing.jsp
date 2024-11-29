<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Product Management</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <h1 class="text-center">Product Management</h1>

    <!-- Button to open the modal -->
    <div class="mb-3">
        <button class="btn btn-success" data-bs-toggle="modal" data-bs-target="#addProductModal">Add New Product</button>
    </div>

    <!-- Product List Table -->
    <table class="table table-bordered table-hover mt-3">
        <thead class="table-dark">
        <tr>
            <th>Product ID</th>
            <th>Product Name</th>
            <th>Description</th>
            <th>Status</th>
            <th>Category</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <!-- Using c:forEach to iterate through the product list -->
        <c:forEach var="product" items="${listProduct}">
            <tr>
                <td>${product.productId}</td>
                <td>${product.name}</td>
                <td>
                    <c:choose>
                        <c:when test="${product.descript != null}">
                            ${product.descript}
                        </c:when>
                        <c:otherwise>
                            No description available
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <c:if test="${product.isDelete}">
                        Deleted
                    </c:if>
                    <c:if test="${!product.isDelete}">
                        Available
                    </c:if>
                </td>
                <td>
                    <c:choose>
                        <c:when test="${product.category != null}">
                            ${product.category.name}
                        </c:when>
                        <c:otherwise>
                            No category available
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <div class="d-flex justify-content-center">
                        <!-- Edit Button -->
                        <form action="editProduct" method="post" style="margin-right: 5px;">
                            <input type="hidden" name="productId" value="${product.productId}">
                            <button type="submit" class="btn btn-warning btn-sm">Edit</button>
                        </form>
                        <!-- Delete Button -->
                        <form action="deleteProduct" method="post">
                            <input type="hidden" name="productId" value="${product.productId}">
                            <button type="submit" class="btn btn-danger btn-sm" onclick="return confirm('Are you sure you want to delete this product?');">Delete</button>
                        </form>
                    </div>
                </td>
            </tr>
        </c:forEach>
        <!-- Display message if the product list is empty -->
        <c:if test="${empty listProduct}">
            <tr>
                <td colspan="6" class="text-center">No products available!</td>
            </tr>
        </c:if>
        </tbody>
    </table>

    <!-- Back to home page -->
    <a href="${pageContext.request.contextPath}/" class="btn btn-primary mt-3">Back to Home</a>
</div>

<!-- Add Product Modal -->
<div class="modal fade" id="addProductModal" tabindex="-1" aria-labelledby="addProductModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="addProductModalLabel">Add New Product</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <!-- Form to add a new product -->
                <form action="addProduct" method="post">
                    <div class="mb-3">
                        <label for="productName" class="form-label">Product Name</label>
                        <input type="text" class="form-control" id="productName" name="productName" required>
                    </div>
                    <div class="mb-3">
                        <label for="description" class="form-label">Description</label>
                        <textarea class="form-control" id="description" name="description"></textarea>
                    </div>
                    <div class="mb-3">
                        <label for="category" class="form-label">Category</label>
                        <select class="form-select" id="category" name="category" required>
                            <c:forEach var="category" items="${categories}">
                                <option value="${category.categoryId}">${category.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="price" class="form-label">Price</label>
                        <input type="number" class="form-control" id="price" name="price" required>
                    </div>
                    <div class="mb-3">
                        <label for="image" class="form-label">Product Image</label>
                        <input type="file" class="form-control" id="image" name="image">
                    </div>
                    <button type="submit" class="btn btn-primary">Add Product</button>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap JS and Popper.js (required for modal functionality) -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
java.sun.com