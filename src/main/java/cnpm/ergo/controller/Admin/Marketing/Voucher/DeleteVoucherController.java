package cnpm.ergo.controller.Admin.Marketing.Voucher;

import cnpm.ergo.entity.VoucherByPrice;
import cnpm.ergo.entity.VoucherByProduct;
import cnpm.ergo.service.implement.IVoucherByPriceServiceImpl;
import cnpm.ergo.service.implement.IVoucherByProductServiceImpl;
import cnpm.ergo.service.interfaces.IVoucherByPriceService;
import cnpm.ergo.service.interfaces.IVoucherByProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(urlPatterns = "/admin/deleteVoucher")
public class DeleteVoucherController extends HttpServlet {
    IVoucherByProductService voucherByProduct;
    IVoucherByPriceService voucherByPrice;

    @Override
    public void init() throws ServletException {
        // Initialize the service implementation
        voucherByProduct = new IVoucherByProductServiceImpl();
        voucherByPrice = new IVoucherByPriceServiceImpl();
    }
//    @Override
//    protected void doGet(HttpServlet request, HttpServletResponse response) throws ServletException, IOException {
////        if (request.getSession().getAttribute("admin") == null) {
////            response.sendRedirect(request.getContextPath() + "/admin/login");
////            return;
////        }
//        try {
//            int voucherID = Integer.parseInt(request.getParameter("voucherId"));
//            String voucherType = request.getParameter("voucherType");
//            // Kiểm tra loại voucher và xóa tương ứng
//            if ("PRICE".equalsIgnoreCase(voucherType)) {
//                voucherByPrice.delete(voucherId);
//            } else if ("PRODUCT".equalsIgnoreCase(voucherType)) {
//                voucherByProduct.delete(voucherId);
//            } else {
//                return "Invalid voucher type";
//            }
//            return "Voucher deleted successfully!";
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "Error occurred while deleting the voucher";
//        }
//    }


}
