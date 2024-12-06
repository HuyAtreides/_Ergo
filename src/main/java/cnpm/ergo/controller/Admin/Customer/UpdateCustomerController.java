package cnpm.ergo.controller.Admin.Customer;

import cnpm.ergo.service.implement.CustomerServiceImpl;
import cnpm.ergo.service.interfaces.ICustomerService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import cnpm.ergo.entity.Customer;

@WebServlet(name = "UpdateEmployeeController", value = "/admin/customer/update")
public class UpdateCustomerController extends HttpServlet {
    private ICustomerService customerService;

    public void init() throws ServletException {
        customerService = new CustomerServiceImpl();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("admin") == null) {
            response.sendRedirect(request.getContextPath() + "/admin/login");
            return;
        }
        try {
            // Retrieve form data from the request
            int userId = Integer.parseInt(request.getParameter("userId"));
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");
            String gender = request.getParameter("gender");
            String status = request.getParameter("status");
            String password = request.getParameter("password");

            // Validate input fields (optional, add your validation logic here)

            // Retrieve the existing Customer object
            Customer customer = customerService.getCustomerById(userId);
            if (customer != null) {
                // Update the Customer object with new values
                customer.setName(name);
                customer.setEmail(email);
                customer.setPhone(phone);
                customer.setAddress(address);
                customer.setGender(gender);
                customer.setStatus(status);
                customer.setPassword(password); // Update password

                // Call the service to update the customer
                customerService.update(customer);
                response.sendRedirect(request.getContextPath() + "/admin/customer");
            }
        } catch (Exception e){
            e.printStackTrace();
            request.setAttribute("errorMessage", "Failed to update the customer. Please try again.");
            request.getRequestDispatcher("/errorPage.jsp").forward(request, response);
        }
    }
}