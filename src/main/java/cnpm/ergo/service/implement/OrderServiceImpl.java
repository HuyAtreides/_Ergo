package cnpm.ergo.service.implement;

import java.util.List;

import cnpm.ergo.DAO.implement.OrderDaoImpl;
import cnpm.ergo.DAO.interfaces.IOrderDao;
import cnpm.ergo.entity.Order;
import cnpm.ergo.service.interfaces.IOrderService;

public class OrderServiceImpl implements IOrderService{
	
	private final IOrderDao orderDao = new OrderDaoImpl();
	
	@Override
	public void insert(Order order) {
		orderDao.insert(order);
		
	}

	@Override
	public void update(Order order) {
		orderDao.update(order);
		
	}

	@Override
	public void delete(int orderId) {
		try {
            orderDao.delete(orderId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error deleting product with ID: " + orderId);
        }
		
	}

	@Override
	public Order findById(int orderId) {
		return orderDao.findById(orderId);
	}

	@Override
	public List<Order> findAll() {
		return orderDao.findAll();
	}

	@Override
	public List<Order> findByPage(int offset, int limit) {
		return orderDao.findByPage(offset, limit);
	}

	@Override
	public int count() {
		return orderDao.count();
	}

	
	
}
