package cnpm.ergo.service.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import cnpm.ergo.entity.Order;
import cnpm.ergo.entity.OrderItem;


public interface IOrderItemService{
	void insert(Order order, OrderItem orderItem);
	void update(Order order, OrderItem orderItem);
	void delete(Order order, int orderItemId);
	List<OrderItem> findAll(int orderId);
	int count(int orderId);
}
