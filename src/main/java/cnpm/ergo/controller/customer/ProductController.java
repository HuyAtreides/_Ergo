package cnpm.ergo.controller.customer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import cnpm.ergo.configs.JPAConfig;
import cnpm.ergo.entity.Product;
import cnpm.ergo.service.interfaces.IProductService;
import cnpm.ergo.service.implement.ProductServiceImpl;

@WebServlet(urlPatterns = {"/products", "/products/detail", "/products/search"})
public class ProductController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final IProductService productService = new ProductServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();

        switch (action) {
            case "/products":
                listAllProducts(req, resp);
                break;
            case "/products/detail":
                getProductDetail(req, resp);
                break;
            case "/products/search":
                searchProducts(req, resp);
                break;
            default:
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void listAllProducts(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int page = Integer.parseInt(req.getParameter("page") != null ? req.getParameter("page") : "1");
            int size = Integer.parseInt(req.getParameter("size") != null ? req.getParameter("size") : "12");  
            List<Product> products = productService.getAllProducts(page, size);
            int totalProducts = productService.getProductCount();
            int totalPages = (int) Math.ceil((double) totalProducts / size);
            int startPage = Math.max(1, page - 2);
            int endPage = Math.min(totalPages, page + 2);

            List<Integer> pageNumbers = new ArrayList<>();
            for (int i = startPage; i <= endPage; i++) {
                pageNumbers.add(i);
            }
            req.setAttribute("products", products);
            req.setAttribute("currentPage", page);
            req.setAttribute("totalPages", totalPages);
            req.setAttribute("pageSize", size);
            req.setAttribute("pageNumbers", pageNumbers);
            req.getRequestDispatcher("/customer/views/product/product_list.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unable to retrieve products");
        }
    }

    private void getProductDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String idParam = req.getParameter("id");
            if (idParam == null || idParam.isEmpty()) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Product ID is required");
                return;
            }

            int productId = Integer.parseInt(idParam);
            Product product = productService.getProductById(productId);
            if (product == null) {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Product not found");
                return;
            }
            req.setAttribute("product", product);
            req.getRequestDispatcher("/customer/views/product/product_detail.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unable to retrieve product details");
        }
    }
    
    private void searchProducts(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String keyword = req.getParameter("q");
            if (keyword == null || keyword.trim().isEmpty()) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Search keyword is required");
                return;
            }
            List<Product> products = productService.searchProductsByName(keyword.trim());
            req.setAttribute("products", products);
            req.setAttribute("keyword", keyword);
            req.getRequestDispatcher("/customer/views/product/product_search.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unable to perform search");
        }
    }

}

