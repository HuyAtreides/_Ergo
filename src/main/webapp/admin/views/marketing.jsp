<%@ page import="cnpm.ergo.entity.VoucherByPrice" %>
<%@ page import="cnpm.ergo.entity.VoucherByProduct" %>
<%@ page import="cnpm.ergo.entity.Voucher" %>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

    <title>Marketing Campaign Management</title>

    <!-- Nhúng jQuery -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

    <!-- Nhúng Bootstrap nếu cần -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-4">
    <h1 class="text-center">Marketing Campaign Management</h1>

    <!-- Button to open the modal -->
    <div class="mb-3">
        <button class="btn btn-success" data-bs-toggle="modal" data-bs-target="#addCampaignModal">Add New Campaign</button>
    </div>

    <!-- Campaign List Table -->
    <table class="table table-bordered table-hover mt-3">
        <thead class="table-dark">
        <tr>
            <th>Campaign ID</th>
            <th>Content</th>
            <th>Voucher</th>
            <th>Status</th>
            <th>Images</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <!-- Using c:forEach to iterate through the campaign list -->
        <c:forEach var="campaign" items="${campaigns}">
            <tr>
                <td>${campaign.campaignId}</td>
                <td>${campaign.content}</td>
                <td>
                    <c:choose>
                        <c:when test="${campaign.voucher != null}">
                            ${campaign.voucher.voucherId}
                        </c:when>
                        <c:otherwise>
                            No Voucher
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <c:if test="${campaign.isDelete}">
                        Deleted
                    </c:if>
                    <c:if test="${!campaign.isDelete}">
                        Active
                    </c:if>
                </td>
                <td>
                    <c:choose>
                        <c:when test="${campaign.getCampaignImages() != null && campaign.getCampaignImages().size()>0}">
                            ${campaign.getCampaignImages().get(0).getImagePath()}
                        </c:when>
                        <c:otherwise>
                            No Image
                        </c:otherwise>
                    </c:choose>

                </td>
                <td>
                    <div class="d-flex justify-content-center">
                        <!-- Edit Button -->
                        <form action="${pageContext.request.contextPath}/admin/campaign/editCampaign" method="get" style="margin-right: 5px;">
                            <input type="hidden" name="campaignId" value="${campaign.getCampaignId()}">
                            <input type="hidden" name="content" value="${campaign.getContent()}">

                            <c:choose>
                                <c:when test="${campaign.getCampaignImages() != null && campaign.getCampaignImages().size()>0}">
                                    <input type="hidden" name="image" value="${campaign.getCampaignImages().get(0).getImagePath()}">
                                </c:when>
                                <c:otherwise>
                                    <input type="hidden" name="image" value="Rong">
                                </c:otherwise>
                            </c:choose>
<%--                            <input type="hidden" name="campaignId" value="${campaign.campaignId}">--%>

                            <button type="submit" class="btn btn-warning btn-sm">Edit</button>
                        </form>
                        <!-- Delete Button -->
                        <form action="${pageContext.request.contextPath}/admin/campaign/deleteCampaign" method="post">
                            <input type="hidden" name="campaignId" value="${campaign.campaignId}">
                            <button type="submit" class="btn btn-danger btn-sm" onclick="return confirm('Are you sure you want to delete this campaign?');">Delete</button>
                        </form>
                    </div>
                </td>
            </tr>
        </c:forEach>
        <!-- Display message if the campaign list is empty -->
        <c:if test="${empty campaigns}">
            <tr>
                <td colspan="6" class="text-center">No campaigns available!</td>
            </tr>
        </c:if>
        </tbody>
    </table>


    <!-- Add Campaign Modal -->
    <div class="modal fade" id="addCampaignModal" tabindex="-1" aria-labelledby="addCampaignModal" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="addCampaignModalLabel">Add New Campaign</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <!-- Form to add a new campaign -->
                    <form action="${pageContext.request.contextPath}/admin/campaign/addCampaign" method="post">
                        <div class="mb-3">
                            <label for="content" class="form-label">Content</label>
                            <textarea class="form-control" id="content" name="content" required></textarea>
                        </div>
                        <div class="mb-3">
                            <label for="voucherId" class="form-label">Voucher</label>
                            <select class="form-select" id="voucherId" name="voucherId">
                                <option value="">Select a voucher</option>
                                <c:forEach var="voucher" items="${vouchers}">
                                    <option value="${voucher.voucherId}"> ${voucher.code} - Discount: ${voucher.discount}%</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="mb-3">
                            <label for="image" class="form-label">Campaign Images</label>
                            <input type="text" class="form-control" id="image" name="image" required>
                        </div>
                        <button type="submit" class="btn btn-primary">Add Campaign</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <!-- Voucher Management Section -->
    <div class="mt-5">
        <h2 class="text-center">Voucher Management</h2>
        <!-- Button to open modal for adding voucher -->
        <div class="mb-3">
            <button class="btn btn-success" data-bs-toggle="modal" data-bs-target="#addVoucherModal">Add New Voucher</button>
        </div>

        <!-- Voucher List Table -->
        <table class="table table-bordered table-hover mt-3">
            <thead class="table-dark">
            <tr>
                <th>Voucher ID</th>
                <th>Code</th>
                <th>Discount (%)</th>
                <th>Start Date</th>
                <th>End Date</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <%
                List<Voucher> voucherList = (List<Voucher>) request.getAttribute("vouchers");
                for (Voucher voucher : voucherList) {
                    boolean isVoucherByPrice = voucher instanceof VoucherByPrice;
            %>
            <tr>
                <td><%= voucher.getVoucherId() %></td>
                <td><%= voucher.getCode() %></td>
                <td><%= voucher.getDiscount() %></td>
                <td><%= voucher.getDateStart() %></td>
                <td><%= voucher.getDateEnd() %></td>
                <td>
                    <div class="d-flex justify-content-center">
                        <!-- Edit Button -->
                        <c:choose>
                            <c:when test="<%= isVoucherByPrice %>">
                                <form action="voucher/editPrice" method="get">
                                    <input type="hidden" name="voucherId" value="<%= voucher.getVoucherId() %>">
                                    <input type="hidden" name="voucherCode" value="<%= voucher.getCode() %>">
                                    <input type="hidden" name="voucherDiscount" value="<%= voucher.getDiscount() %>">
                                    <input type="hidden" name="voucherDateStart" value="<%= voucher.getDateStart() %>">
                                    <input type="hidden" name="voucherDateEnd" value="<%= voucher.getDateEnd() %>">
                                    <button type="submit" class="btn btn-warning btn-sm">Edit
                                    </button>
                                </form>
                            </c:when>
                            <c:otherwise>
                                <form action="voucher/editProduct" method="get">
                                    <input type="hidden" name="voucherId" value="<%= voucher.getVoucherId() %>">
                                    <input type="hidden" name="voucherCode" value="<%= voucher.getCode() %>">
                                    <input type="hidden" name="voucherDiscount" value="<%= voucher.getDiscount() %>">
                                    <input type="hidden" name="voucherDateStart" value="<%= voucher.getDateStart() %>">
                                    <input type="hidden" name="voucherDateEnd" value="<%= voucher.getDateEnd() %>">
                                    <button type="submit" class="btn btn-warning btn-sm">Edit
                                    </button>
                                </form>
                            </c:otherwise>
                        </c:choose>

                        <!-- Delete Button based on type -->
                        <c:choose>
                            <c:when test="<%= isVoucherByPrice %>">
                                <form action="voucher/deletePrice" method="get">
                                    <input type="hidden" name="voucherId" value="<%= voucher.getVoucherId() %>">
                                    <button type="submit" class="btn btn-danger btn-sm"
                                            onclick="return confirm('Are you sure you want to delete this voucher?');">Delete</button>
                                </form>
                            </c:when>
                            <c:otherwise>
                                <form action="voucher/deleteProduct" method="get">
                                    <input type="hidden" name="voucherId" value="<%= voucher.getVoucherId() %>">
                                    <button type="submit" class="btn btn-danger btn-sm"
                                            onclick="return confirm('Are you sure you want to delete this voucher?');">Delete</button>
                                </form>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </td>
            </tr>
            <%
                }
            %>
            <!-- Display message if voucher list is empty -->
            <c:if test="${empty vouchers}">
                <tr>
                    <td colspan="7" class="text-center">No vouchers available!</td>
                </tr>
            </c:if>
            </tbody>
        </table>
    </div>

    <!-- Add Voucher Modal -->
    <div class="modal fade" id="addVoucherModal" tabindex="-1" aria-labelledby="addVoucherModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="addVoucherModalLabel">Add New Voucher</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <!-- Form to add a new voucher -->
                    <form action="addVoucher" method="post">
                        <!-- Common Fields -->
                        <div class="mb-3">
                            <label for="voucherType" class="form-label">Voucher Type</label>
                            <select class="form-select" id="voucherType" name="voucherType" required>
                                <option value="byPrice" selected>Voucher by Price</option>
                                <option value="byProduct">Voucher by Product</option>
                            </select>
                        </div>
                        <div class="mb-3">
                            <label for="code" class="form-label">Voucher Code</label>
                            <input type="text" class="form-control" id="code" name="code" required>
                        </div>
                        <div class="mb-3">
                            <label for="discount" class="form-label">Discount (%)</label>
                            <input type="number" class="form-control" id="discount" name="discount" step="0.01" required>
                        </div>
                        <div class="mb-3">
                            <label for="dateStart" class="form-label">Start Date</label>
                            <input type="date" class="form-control" id="dateStart" name="dateStart" required>
                        </div>
                        <div class="mb-3">
                            <label for="dateEnd" class="form-label">End Date</label>
                            <input type="date" class="form-control" id="dateEnd" name="dateEnd" required>
                        </div>

                        <!-- Voucher By Price Fields -->
                        <div class="voucherByPriceFields" class="d-none">
                            <div class="mb-3">
                                <label for="lowerbound" class="form-label">Minimum Order Value</label>
                                <input type="number" class="form-control" id="lowerbound" name="lowerbound" step="0.01">
                            </div>
                        </div>

                        <!-- Voucher By Product Fields -->
                        <div class="voucherByProductFields" class="d-none">
                            <div class="mb-3">
                                <label for="productTypes" class="form-label">Applicable Product Types</label>
                                <select multiple class="form-select" id="productTypes" name="productTypes">
                                    <c:forEach var="productType" items="${productTypes}">
                                        <option value="${productType.getTypeId()}">${productType.getProduct().getName()} | ${productType.getColor()}</option>
                                    </c:forEach>
                                </select>
                                <small class="text-muted">Hold CTRL (Windows) or CMD (Mac) to select multiple types.</small>
                            </div>

                        </div>

                        <button type="submit" class="btn btn-primary">Add Voucher</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

<script>
    // Toggle fields based on voucher type selection
    document.getElementById("voucherType").addEventListener("change", function () {
        const voucherType = this.value;
        var priceFields = document.querySelector('.voucherByPriceFields');
        var productFields = document.querySelector('.voucherByProductFields');

        if (voucherType === "byPrice") {
            priceFields.classList.remove("d-none");
            productFields.classList.add("d-none");
        } else if (voucherType === "byProduct") {
            productFields.classList.remove("d-none");
            priceFields.classList.add("d-none");
        } else {
            priceFields.classList.add("d-none");
            productFields.classList.add("d-none");
        }
    });
</script>
<script>
    document.addEventListener("DOMContentLoaded", function () {
        const voucherType = document.getElementById("voucherType");
        const byPriceFields = document.querySelector(".voucherByPriceFields");
        const byProductFields = document.querySelector(".voucherByProductFields");

        // Hàm hiển thị các trường theo lựa chọn
        function updateFields() {
            if (voucherType.value === "byPrice") {
                byPriceFields.classList.remove("d-none");
                byProductFields.classList.add("d-none");
            } else {
                byPriceFields.classList.add("d-none");
                byProductFields.classList.remove("d-none");
            }
        }

        // Gọi hàm khi thay đổi lựa chọn
        voucherType.addEventListener("change", updateFields);

        // Đặt trạng thái ban đầu khi load
        updateFields();
    });
</script>
</div>
</body>
</html>

