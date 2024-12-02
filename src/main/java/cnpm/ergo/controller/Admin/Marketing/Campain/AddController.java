package cnpm.ergo.controller.Admin.Marketing.Campain;

import cnpm.ergo.entity.MarketingCampaign;
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
import java.util.Date;

@WebServlet(urlPatterns = "/admin/campaign/addCampaign")
public class AddController extends HttpServlet {
    private IMarketingCampaignService marketingCampaignService = new MarketingCampaignServiceImpl();
    private IVoucherByPriceService voucherByPriceService = new IVoucherByPriceServiceImpl();
    private IVoucherByProductService voucherByProductService = new IVoucherByProductServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        if (request.getSession().getAttribute("admin") == null) {
//            response.sendRedirect(request.getContextPath() + "/admin/login");
//            return;
//        }
        try {
            // Lấy thông tin chung từ form
            String content = request.getParameter("content");
            int voucherID = Integer.parseInt(request.getParameter("voucherID"));
            //xử lý ảnh
//            List<String> imagePaths = new ArrayList<>();
//            String uploadDir = getServletContext().getRealPath("/uploads/campaigns");
//            for (Part part : request.getParts()) {
//                if (part.getName().equals("images") && part.getSize() > 0) {
//                    String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
//                    File uploadFolder = new File(uploadDir);
//                    if (!uploadFolder.exists()) uploadFolder.mkdirs();
//                    String filePath = uploadDir + File.separator + fileName;
//                    part.write(filePath);
//                    imagePaths.add("/uploads/campaigns/" + fileName);
//                }
//            }

            // Tạo đối tượng Campaign
            MarketingCampaign campaign = new MarketingCampaign();
            campaign.setContent(content);
//            campaign.setImages(imagePaths);



            //tìm loại voucher nào
            Voucher voucher;
            if(voucherByPriceService.findById(voucherID) == null)
            {
                if(voucherByProductService.findById(voucherID) == null)
                {
                    return;
                }
                voucher = new VoucherByProduct();
            }
            voucher = new VoucherByPrice();

            // Gán voucher
            voucher.setVoucherId(voucherID);
            campaign.setVoucher(voucher);

            // Gọi service để lưu campaigns
            marketingCampaignService.addCampaign(campaign);

            // Redirect hoặc thông báo thành công
            response.sendRedirect(request.getContextPath() + "/admin/marketing");




        }catch (Exception e) {
            e.printStackTrace();
            // Forward the error details to an error page
            request.setAttribute("errorMessage", "Failed to add the campaign. Please try again.");
            request.getRequestDispatcher("/errorPage.jsp").forward(request, response);
        }


    }
}
