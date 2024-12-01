package cnpm.ergo.controller.Admin.Marketing;

import cnpm.ergo.entity.Voucher;
import cnpm.ergo.entity.VoucherByPrice;
import cnpm.ergo.entity.VoucherByProduct;
import cnpm.ergo.service.implement.IVoucherByPriceServiceImpl;
import cnpm.ergo.service.implement.IVoucherByProductServiceImpl;
import cnpm.ergo.service.implement.MarketingCampaignServiceImpl;
import cnpm.ergo.service.interfaces.IMarketingCampaignService;
import cnpm.ergo.service.interfaces.IVoucherByPriceService;
import cnpm.ergo.service.interfaces.IVoucherByProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/admin/marketing")
public class MarketingController extends HttpServlet {
    IMarketingCampaignService marketingCampaignService = new MarketingCampaignServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageNo = 1;
        int pageSize = 10;
        if (request.getParameter("page") != null) {
            pageNo = Integer.parseInt(request.getParameter("page"));
        }
        IVoucherByPriceService voucherByPriceService = new IVoucherByPriceServiceImpl();
        IVoucherByProductService voucherByProductService = new IVoucherByProductServiceImpl();

        List<VoucherByPrice> voucherByPriceList = voucherByPriceService.findAll();
        List<VoucherByProduct> voucherByProductList = voucherByProductService.findAll();
        List<Voucher> vouchers = new ArrayList<>();

        for(VoucherByPrice voucherByPrice : voucherByPriceList){
            vouchers.add(voucherByPrice);
        }
        for(VoucherByProduct voucher : voucherByProductList){
            vouchers.add(voucher);
        }

        long totalvoucher = vouchers.stream().count();
        int totalPages = (int) Math.ceil((double) totalvoucher / pageSize);

        request.setAttribute("vouchers",vouchers);
        request.setAttribute("currentPage", pageNo);
        request.setAttribute("totalPages", totalPages);

        request.getRequestDispatcher("views/marketing.jsp").forward(request, response);
    }
}
