package cnpm.ergo.controller.customer;

import java.io.IOException;
import java.util.List;

import cnpm.ergo.entity.Order;
import cnpm.ergo.entity.OrderItem;
import cnpm.ergo.entity.Voucher;
import cnpm.ergo.entity.VoucherDto;
import cnpm.ergo.service.interfaces.IOrderItemService;
import cnpm.ergo.service.interfaces.IOrderService;
import cnpm.ergo.service.interfaces.IVoucherService;
import cnpm.ergo.service.implement.OrderItemServiceImpl;
import cnpm.ergo.service.implement.OrderServiceImpl;
import cnpm.ergo.service.implement.VoucherServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, 
                 maxFileSize = 1024 * 1024 * 5, 
                 maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet(urlPatterns = {"/customer/order", "/customer/order/voucher"})
public class OrderController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private IOrderService orderService;
    private IOrderItemService orderItemService;
    private IVoucherService voucherService;
    private List<VoucherDto> listVoucherCanApply;

    @Override
    public void init() throws ServletException {
        this.orderService = new OrderServiceImpl();
        this.orderItemService = new OrderItemServiceImpl();
        this.voucherService = new VoucherServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	String url = req.getRequestURI();
    	if (url.endsWith("/customer/order")) {
    		try {
    			//Order
                int orderId = 1; 
                Order order = orderService.findById(orderId);
                List<VoucherDto> listVoucherCanApply = voucherService.voucherByPriceForOder(order);
                if (order == null) {
                    resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Order not found");
                    return;
                }
                
                //Voucher
                String selectedVoucher = null;
                if (req.getParameter("selectedVoucher") != null) {
                	int voucherId = Integer.parseInt(req.getParameter("selectedVoucher"));
                    Voucher voucher = voucherService.findById(voucherId);
                    selectedVoucher = voucher.getCode();
                    if (voucher != null) {
                    	order.setDiscount(voucher.getDiscount());
                    	order.setVoucher(voucher);
                    	order.setActualCost(order.getTotalCost() * (1 - order.getDiscount()));
                    	orderService.update(order);
                    }
                }               

                //Order Item
                List<OrderItem> orderItems = orderItemService.findAll(orderId);
                if (orderItems != null && !orderItems.isEmpty()) {
                    req.setAttribute("orderItems", orderItems);
                } else {
                    req.setAttribute("orderItems", "No items found for this order");
                }
                            

                // Đặt thuộc tính order vào request và chuyển tiếp tới JSP
                req.setAttribute("order", order);
                req.setAttribute("selectedVoucher", selectedVoucher);
                req.setAttribute("listVoucher", listVoucherCanApply);
                req.getRequestDispatcher("/customer/views/Order.jsp").forward(req, resp);

            } catch (Exception e) {
                // Log lỗi chi tiết
                e.printStackTrace();  // Hoặc sử dụng một logger để ghi lại lỗi
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while processing your request.");
            }
    	}
    }

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
    	if (url.contains("voucher")) {
    		String selectedVoucherCode = req.getParameter("selectedVoucher");
			if (selectedVoucherCode != null) {
				try {
					Order order = orderService.findById(Integer.parseInt(req.getParameter("orderId")));
					boolean isVoucherSelected = false;
					int voucherCodeInt = Integer.parseInt(selectedVoucherCode);
					List<VoucherDto> listVoucherCanApply = voucherService.voucherByPriceForOder(order);
					
			        // Check if the voucherCodeInt exists in the list
			        isVoucherSelected = listVoucherCanApply.stream()
			                .anyMatch(voucher -> voucher.getVoucherId() == voucherCodeInt);
					
					if (isVoucherSelected == true) {
						resp.sendRedirect(req.getContextPath() + "/customer/order?selectedVoucher=" + voucherCodeInt);
					} else {
						resp.sendRedirect(req.getContextPath() + "/customer/order");
					}
			        			
				}catch (Exception e) {
		            e.printStackTrace();  
		        }
			}
			else {
				resp.sendRedirect(req.getContextPath() + "/customer/order");
			}
		}
	}
    
    
}
