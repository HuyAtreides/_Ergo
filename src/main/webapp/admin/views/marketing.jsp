<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Marketing Campaign Management</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
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
                    <c:forEach var="image" items="${campaign.campaignImages}">
                        <img src="${image.url}" alt="Campaign Image" class="img-thumbnail" style="width: 100px; height: auto;">
                    </c:forEach>
                </td>
                <td>
                    <div class="d-flex justify-content-center">
                        <!-- Edit Button -->
                        <form action="editCampaign" method="post" style="margin-right: 5px;">
                            <input type="hidden" name="campaignId" value="${campaign.campaignId}">
                            <button type="submit" class="btn btn-warning btn-sm">Edit</button>
                        </form>
                        <!-- Delete Button -->
                        <form action="deleteCampaign" method="post">
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
    <div class="modal fade" id="addCampaignModal" tabindex="-1" aria-labelledby="addCampaignModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="addCampaignModalLabel">Add New Campaign</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <!-- Form to add a new campaign -->
                    <form action="addCampaign" method="post">
                        <div class="mb-3">
                            <label for="content" class="form-label">Content</label>
                            <textarea class="form-control" id="content" name="content" required></textarea>
                        </div>
                        <div class="mb-3">
                            <label for="voucherId" class="form-label">Voucher</label>
                            <select class="form-select" id="voucherId" name="voucherId">
                                <option value="">Select a voucher</option>
                                <c:forEach var="voucher" items="${vouchers}">
                                    <option value="${voucher.voucherId}">${voucher.code} - Discount: ${voucher.discount}%</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="mb-3">
                            <label for="images" class="form-label">Campaign Images</label>
                            <input type="file" class="form-control" id="images" name="images" multiple>
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
                <th>Type</th>
                <th>Campaign</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="voucher" items="${vouchers}">
                <tr>
                    <td>${voucher.voucherId}</td>
                    <td>${voucher.code}</td>
                    <td>${voucher.discount}</td>
                    <td>${voucher.dateStart}</td>
                    <td>${voucher.dateEnd}</td>
                    <td>${voucher.voucherType}</td>
                    <td>
                        <c:choose>
                            <c:when test="${voucher.marketingCampaign != null}">
                                ${voucher.marketingCampaign.campaignId}
                            </c:when>
                            <c:otherwise>
                                Not Assigned
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <div class="d-flex justify-content-center">
                            <!-- Edit Button -->
                            <button type="button" class="btn btn-warning btn-sm"
                                    onclick="showEditVoucherModal('${voucher.voucherId}','${voucher.voucherType}')">Edit</button>

                            <!-- Delete Button -->
                            <form action="deleteVoucher" method="post">
                                <input type="hidden" name="voucherId" value="${voucher.voucherId}">
                                <button type="submit" class="btn btn-danger btn-sm" onclick="return confirm('Are you sure you want to delete this voucher?');">Delete</button>
                            </form>
                        </div>
                    </td>
                </tr>
            </c:forEach>
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
                                        <option value="${productType.typeId}">${productType.name}</option>
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

    <!-- Edit Voucher Modal -->
    <div class="modal fade" id="editVoucherModal" tabindex="-1" aria-labelledby="editVoucherModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="editVoucherModalLabel">Edit Voucher</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                    <div class="modal-body">
                        <c:choose>
                            <c:when test="${voucher.voucherType == 'PRICE'}">
                                <form action="editVoucherByPrice" method="post">
                                    <input type="hidden" name="voucherId" value="${voucher.voucherId}" />
                                    <div class="mb-3">
                                        <label for="editPriceCode" class="form-label">Code</label>
                                        <input type="text" class="form-control" id="editPriceCode" name="code" value="${voucher.code}" required>
                                    </div>
                                    <div class="mb-3">
                                        <label for="editPriceDiscount" class="form-label">Discount (%)</label>
                                        <input type="number" class="form-control" id="editPriceDiscount" name="discount" value="${voucher.discount}" step="0.01" required>
                                    </div>
                                    <div class="mb-3">
                                        <label for="editLowerbound" class="form-label">Minimum Order Value</label>
                                        <input type="number" class="form-control" id="editLowerbound" name="lowerbound" value="${voucher.lowerbound}" required>
                                    </div>
                                    <button type="submit" class="btn btn-primary">Save Changes</button>
                                </form>
                            </c:when>


                            <c:otherwise>
                                <form action="editVoucherByProduct" method="post">
                                    <input type="hidden" name="voucherId" value="${voucher.voucherId}" />
                                    <div class="mb-3">
                                        <label for="editProductCode" class="form-label">Code</label>
                                        <input type="text" class="form-control" id="editProductCode" name="code" value="${voucher.code}" required>
                                    </div>
                                    <div class="mb-3">
                                        <label for="editProductDiscount" class="form-label">Discount (%)</label>
                                        <input type="number" class="form-control" id="editProductDiscount" name="discount" value="${voucher.discount}" step="0.01" required>
                                    </div>
                                    <div class="mb-3">
                                        <label for="editProductTypes" class="form-label">Applicable Product Types</label>
                                        <select multiple class="form-select" id="editProductTypes" name="productTypes">
                                            <c:forEach var="productType" items="${productTypes}">
                                                <option value="${productType.typeId}" ${voucher.productTypes.contains(productType.typeId) ? 'selected' : ''}>
                                                        ${productType.name}
                                                </option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <button type="submit" class="btn btn-primary">Save Changes</button>
                                </form>
                            </c:otherwise>
                        </c:choose>

                    </div>


            </div>
        </div>
    </div>

</div>
<!-- Bootstrap JS and Popper.js -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
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
    function openEditVoucherModal(voucherId, voucherType) {
        // Gán giá trị vào các trường trong modal
        document.getElementById("editVoucherId").value = voucherId;

        // Hiển thị các trường tùy thuộc vào voucherType
        if (voucherType === "PRICE") {
            document.getElementById("voucherByPriceFields").classList.remove("d-none");
            document.getElementById("voucherByProductFields").classList.add("d-none");
            document.getElementById("editLowerbound").value = lowerbound || "";
        } else if (voucherType === "PRODUCT") {
            document.getElementById("voucherByPriceFields").classList.add("d-none");
            document.getElementById("voucherByProductFields").classList.remove("d-none");

            // Xử lý productTypes nếu cần
            const productTypesField = document.getElementById("editProductTypes");
            productTypesField.value = productTypes || "";
        }

        // Hiển thị modal
        new bootstrap.Modal(document.getElementById("editVoucherModal")).show();
    }
</script>

</body>
</html>


