package cnpm.ergo.controller.customer;

import java.io.IOException;
import java.util.List;

import cnpm.ergo.entity.Order;
import cnpm.ergo.entity.OrderItem;
import cnpm.ergo.service.interfaces.IOrderItemService;
import cnpm.ergo.service.interfaces.IOrderService;
import cnpm.ergo.service.implement.OrderItemServiceImpl;
import cnpm.ergo.service.implement.OrderServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, 
                 maxFileSize = 1024 * 1024 * 5, 
                 maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet(urlPatterns = {"/customer/order"})
public class OrderController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private IOrderService orderService;
    private IOrderItemService orderItemService;

    @Override
    public void init() throws ServletException {
        this.orderService = new OrderServiceImpl();
        this.orderItemService = new OrderItemServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int orderId = 1; 

            Order order = orderService.findById(orderId);
            if (order == null) {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Order not found");
                return;
            }

            List<OrderItem> orderItems = orderItemService.findAll(orderId);
            if (orderItems != null && !orderItems.isEmpty()) {
                req.setAttribute("orderItems", orderItems);
            } else {
                req.setAttribute("orderItems", "No items found for this order");
            }


            // Đặt thuộc tính order vào request và chuyển tiếp tới JSP
            req.setAttribute("order", order);
            req.getRequestDispatcher("/customer/views/Order.jsp").forward(req, resp);

        } catch (Exception e) {
            // Log lỗi chi tiết
            e.printStackTrace();  // Hoặc sử dụng một logger để ghi lại lỗi
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while processing your request.");
        }
    }
}
