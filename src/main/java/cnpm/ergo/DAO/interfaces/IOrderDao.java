package cnpm.ergo.DAO.interfaces;

import java.util.List;

import cnpm.ergo.entity.Order;

public interface IOrderDao {
	void insert(Order order);
	void update(Order order);
	void delete(int orderId);
	Order findById(int orderId);
	List<Order> findAll();
	List<Order> findByPage(int offset, int limit);
	int count();

}
