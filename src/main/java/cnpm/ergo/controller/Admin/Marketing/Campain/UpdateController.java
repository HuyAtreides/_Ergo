package cnpm.ergo.controller.Admin.Marketing.Campain;

import cnpm.ergo.DAO.implement.BlogDaoImpl;
import cnpm.ergo.entity.*;
import cnpm.ergo.service.implement.CampaignImageServiceImpl;
import cnpm.ergo.service.implement.IVoucherByPriceServiceImpl;
import cnpm.ergo.service.implement.IVoucherByProductServiceImpl;
import cnpm.ergo.service.implement.MarketingCampaignServiceImpl;
import cnpm.ergo.service.interfaces.ICampaignImageService;
import cnpm.ergo.service.interfaces.IMarketingCampaignService;
import cnpm.ergo.service.interfaces.IVoucherByPriceService;
import cnpm.ergo.service.interfaces.IVoucherByProductService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(urlPatterns = "/admin/campaign/editCampaign")
public class UpdateController extends HttpServlet {
    private IMarketingCampaignService marketingCampaignService = new MarketingCampaignServiceImpl();



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy giá trị từ tham số của form
        Long campaingID = Long.parseLong(request.getParameter("campaignId"));
        System.out.println("testIDshow" + campaingID);
        String content = request.getParameter("content");
        System.out.println("testContentshow" + content);
        String image = request.getParameter("image");
        System.out.println("testimageshow" + image);

        List<Voucher> vouchers = new ArrayList<>();
        vouchers = createVouchers(vouchers);
        request.setAttribute("vouchers",vouchers);
        // Gửi giá trị vào trang editVoucherPrice.jsp
        request.setAttribute("content", content);
        if(image != "Rong")
            request.setAttribute("image",image);

        // Chuyển hướng đến trang editVoucherPrice
//        RequestDispatcher dispatcher = request.getRequestDispatcher("views/editVoucherPrice.jsp");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/views/editCampaign.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        if (request.getSession().getAttribute("admin") == null) {
//            response.sendRedirect(request.getContextPath() + "/admin/login");
//            return;
//        }

        try {
            // Lấy thông tin
            Long campaingID = Long.parseLong(request.getParameter("campaignId"));
            System.out.println("testID" + campaingID);
            String content = request.getParameter("content");
            System.out.println("testContent" + content);
            String image = request.getParameter("image");
            System.out.println(image);
            // Tạo đối tượng Campaign
            MarketingCampaign campaign = marketingCampaignService.findByID(campaingID);
            campaign.setContent(content);
            if(request.getParameter("image") != null ) {
                if (campaign.getCampaignImages() != null && campaign.getCampaignImages().size() > 0) {
                    System.out.println(campaign.getCampaignImages().get(0).getImagePath());
                    campaign.getCampaignImages().get(0).setImagePath(image);
                } else {
                    CampaignImage image1 = new CampaignImage();
                    image1.setImagePath(image);
                    image1.setMarketingCampaign(campaign);
                    ICampaignImageService campaignImageService = new CampaignImageServiceImpl();
                    campaignImageService.addImage(image1);
                    System.out.println("khong co");
                }
            }
            marketingCampaignService.updateCampaign(campaign);
            // Redirect hoặc thông báo thành công
            response.sendRedirect(request.getContextPath() + "/admin/marketing");

        }catch (Exception e) {
            e.printStackTrace();
            // Forward the error details to an error page
            request.setAttribute("errorMessage", "Failed to delete the campaign. Please try again.");
            request.getRequestDispatcher("/errorPage.jsp").forward(request, response);
        }


    }

    private List<Voucher> createVouchers(List<Voucher> vouchers)
    {
        IVoucherByProductService voucherByProductService = new IVoucherByProductServiceImpl();
        IVoucherByPriceService voucherByPriceService = new IVoucherByPriceServiceImpl();
        List<VoucherByPrice> voucherByPriceList = voucherByPriceService.findAll();
        List<VoucherByProduct> voucherByProductList = voucherByProductService.findAll();

        for(VoucherByPrice voucherByPrice : voucherByPriceList){
            vouchers.add(voucherByPrice);
        }
        for(VoucherByProduct voucher : voucherByProductList){
            vouchers.add(voucher);
        }
        return vouchers;
    }

}
