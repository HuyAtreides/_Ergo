package cnpm.ergo.controller.Admin.Product;


import cnpm.ergo.entity.ProductType;
import cnpm.ergo.service.implement.ProductTypeServiceImpl;
import cnpm.ergo.service.interfaces.IProductTypeService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/admin/producttype")
public class ProductTypeController extends HttpServlet {
        IProductTypeService productTypeService = new ProductTypeServiceImpl();
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
                if (req.getSession().getAttribute("admin") == null) {
                        res.sendRedirect("/admin/login");
                        return;
                }
                try {
                        List<ProductType> listProductType = productTypeService.getAllProductTypes();
                        req.setAttribute("listProductType", listProductType);
                        req.getRequestDispatcher("/admin/views/producttype.jsp").forward(req, res);
                }
                catch (Exception ex) {
                        ex.printStackTrace();
                        // Forward the error details to an error page
                        req.setAttribute("errorMessage", "Failed to get the order types information. Please try again.");
                        req.getRequestDispatcher("/errorPage.jsp").forward(req, res);
                }
        }


}
