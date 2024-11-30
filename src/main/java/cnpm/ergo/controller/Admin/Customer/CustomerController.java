package cnpm.ergo.controller.Admin.Customer;

import cnpm.ergo.service.implement.CustomerServiceImpl;
import cnpm.ergo.service.interfaces.ICustomerService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "CustomerController", value = "/admin/customer")
public class CustomerController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageNo = 1;
        int pageSize = 10;
        if (request.getParameter("page") != null) {
            pageNo = Integer.parseInt(request.getParameter("page"));
        }
        ICustomerService customerService = new CustomerServiceImpl();
        HttpSession session = request.getSession();
        session.setAttribute("customerList", customerService.findAll(pageNo, pageSize));
        long totalCustomers = customerService.count();
        int totalPages = (int) Math.ceil((double) totalCustomers / pageSize);
        request.setAttribute("currentPage", pageNo);
        request.setAttribute("totalPages", totalPages);
        request.getRequestDispatcher("/admin/views/customer.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}