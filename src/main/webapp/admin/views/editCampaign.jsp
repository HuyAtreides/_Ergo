<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Edit Marketing Campaign</title>
</head>
<body>
<div class="show" id="addCampaignModal" tabindex="-1" aria-labelledby="addCampaignModal" aria-hidden="true">
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
                        <input class="form-control" id="content" name="content" value="${content}" required></input>
                    </div>
                    <div class="mb-3">
                        <label for="voucherId" class="form-label">Voucher</label>
                        <select class="form-select" id="voucherId" name="voucherId">
                            <option value="">Select a voucher</option>
                            <c:forEach var="voucher" items="${vouchers}">
                                <option id="voucherId" value="${voucher.voucherId}">${voucher.code} - Discount: ${voucher.discount}%</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="image" class="form-label">Campaign Images</label>
                        <input type="text" class="form-control" id="image" name="image" value="${image}" required>
                    </div>
                    <button type="submit" class="btn btn-primary">Add Campaign</button>
                </form>
            </div>
        </div>
    </div>
</div>

</body>
</html>
