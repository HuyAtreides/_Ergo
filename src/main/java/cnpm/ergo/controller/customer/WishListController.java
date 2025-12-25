package cnpm.ergo.controller.customer;

import java.io.IOException;
import java.util.List;

import cnpm.ergo.entity.Product;
import cnpm.ergo.entity.Wishlist;
import cnpm.ergo.service.implement.CustomerServiceImpl;
import cnpm.ergo.service.implement.UserServiceImpl;
import cnpm.ergo.service.implement.WishlistServiceImpl;
import cnpm.ergo.service.interfaces.ICustomerService;
import cnpm.ergo.service.interfaces.IUserService;
import cnpm.ergo.service.interfaces.IWishlistService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class WishListController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public IWishlistService wishlistService = new WishlistServiceImpl();
	public ICustomerService userService = new CustomerServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		
		if(url.contains("/customer/wishlist"))
		{
			List<Product> list = wishlistService.getAllWishlistByUserId(0);

			req.setAttribute("wishlist", list);

			req.getRequestDispatcher("/customer/views/wishlist.jsp").forward(req, resp);
		}
		else if(url.contains("/customer/wishlist/delete"))
		{
			int id = Integer.parseInt(req.getParameter("id"));

			wishlistService.deleteFromWishlist(0, 0);

			req.getRequestDispatcher("/customer/views/wishlist.jsp").forward(req, resp);
		}
	}
}
