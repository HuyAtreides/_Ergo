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

@WebServlet(urlPatterns = "/admin/addVoucher")
public class AddVoucherController extends HttpServlet {
    IVoucherByProductService voucherByProduct;
    IVoucherByPriceService voucherByPrice;

    @Override
    public void init() throws ServletException {
        // Initialize the service implementation
        voucherByProduct = new IVoucherByProductServiceImpl();
        voucherByPrice = new IVoucherByPriceServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        if (request.getSession().getAttribute("admin") == null) {
//            response.sendRedirect(request.getContextPath() + "/admin/login");
//            return;
//        }
        try {
            // Lấy loại voucher
            String voucherType = request.getParameter("voucherType");

            // Lấy thông tin chung từ form
            String code = request.getParameter("code");
            double discount = Double.parseDouble(request.getParameter("discount"));
            Date dateStart = parseDate(request.getParameter("dateStart"));
            Date dateEnd = parseDate(request.getParameter("dateEnd"));

            try {
                if ("byPrice".equals(voucherType)) {
                    // Tạo VoucherByPrice
                    double lowerbound = Double.parseDouble(request.getParameter("lowerbound"));
                    VoucherByPrice voucher = new VoucherByPrice();
                    voucher.setCode(code);
                    voucher.setDiscount(discount);
                    voucher.setDateStart(dateStart);
                    voucher.setDateEnd(dateEnd);
                    voucher.setLowerbound(lowerbound);

                    // Thêm vào cơ sở dữ liệu
                    voucherByPrice.insert(voucher);
                } else if ("byProduct".equals(voucherType)) {
                    // Tạo VoucherByProduct
                    VoucherByProduct voucher = new VoucherByProduct();
                    voucher.setCode(code);
                    voucher.setDiscount(discount);
                    voucher.setDateStart(dateStart);
                    voucher.setDateEnd(dateEnd);
//
//                    // Xử lý danh sách productTypes
//                    String[] productTypeIds = request.getParameterValues("productTypes");
//                    if (productTypeIds != null) {
//                        for (String typeId : productTypeIds) {
//                            voucher.addProductType(Integer.parseInt(typeId)); // Phương thức addProductType cần được định nghĩa
//                        }
//                    }

                    // Thêm vào cơ sở dữ liệu
                    voucherByProduct.insert(voucher);
                }


                // Redirect to the employee management page upon success
                response.sendRedirect(request.getContextPath() + "/admin/marketing");

            } catch (Exception e) {
                e.printStackTrace();
                // Forward the error details to an error page
                request.setAttribute("errorMessage", "Failed to add the voucher. Please try again.");
                request.getRequestDispatcher("/errorPage.jsp").forward(request, response);
            }
        }catch (Exception e) {
            e.printStackTrace();
            // Forward the error details to an error page
            request.setAttribute("errorMessage", "Failed to add the voucher. Please try again.");
            request.getRequestDispatcher("/errorPage.jsp").forward(request, response);
        }


    }
    private Date parseDate (String dateStr){
        try {
            // Sử dụng định dạng yyyy-MM-dd
            return new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null; // Trả về null nếu parse thất bại
        }
    }
}
