package cnpm.ergo.DAO.implement;

import java.util.ArrayList;
import java.util.List;

import cnpm.ergo.DAO.interfaces.IOrderDao;
import cnpm.ergo.configs.JPAConfig;
import cnpm.ergo.entity.Customer;
import cnpm.ergo.entity.Order;
import cnpm.ergo.entity.OrderItem;
import cnpm.ergo.entity.Product;
import cnpm.ergo.entity.ProductImage;
import cnpm.ergo.entity.ProductType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

public class OrderDaoImpl implements IOrderDao{

	@Override
	public void insert(Order order) {
		EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            trans.begin();
            em.persist(order); 
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            throw e;
        } finally {
            em.close();
        }
		
	}

	@Override
	public void update(Order order) {
		EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            trans.begin();
            em.merge(order); 
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            throw e;
        } finally {
            em.close();
        }		
	}

	@Override
	public void delete(int orderId) {
		EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction trans = em.getTransaction();

        try {
            trans.begin();
            Order order = em.find(Order.class, orderId);
            if (order != null) {
                em.remove(order);
            }
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            throw e;
        } finally {
            em.close();
        }
		
	}
	@Override
    public List<OrderItem> findByOrderId(int orderId) {
		EntityManager em = JPAConfig.getEntityManager();
        String jpql = "SELECT oi FROM OrderItem oi WHERE oi.order.id = :orderId";
        TypedQuery<OrderItem> query = em.createQuery(jpql, OrderItem.class);
        query.setParameter("orderId", orderId);
        return query.getResultList();
    }
	
	@Override
	public Order findById(int orderId) {
		EntityManager em = JPAConfig.getEntityManager();
        return em.find(Order.class, orderId);
	}

	@Override
	public List<Order> findAll() {
		EntityManager em = JPAConfig.getEntityManager();
        String jpql = "SELECT o FROM Order o";
        TypedQuery<Order> query = em.createQuery(jpql, Order.class);
        return query.getResultList();
	}

	@Override
	public int count() {
		EntityManager em = JPAConfig.getEntityManager();
        String jpql = "SELECT COUNT(o) FROM Order o";
        Query query = em.createQuery(jpql);
        return ((Long) query.getSingleResult()).intValue();
	}
	@Override
	public List<Order> getAllOrdersByCustomer(int customerId) {
	    EntityManager em = JPAConfig.getEntityManager();
	    TypedQuery<Order> query = em.createQuery("SELECT o FROM Order o WHERE o.customer.userId = :customerId", Order.class);
	    query.setParameter("customerId", customerId);
	    List<Order> orders = query.getResultList();
	    em.close();
	    return orders;
	}
	
	@Override
	public List<Order> getOrdersByCustomer(int customerId, String status) {
	    EntityManager em = JPAConfig.getEntityManager();
	    String jpql = "SELECT o FROM Order o WHERE o.customer.userId = :customerId";
	    if (status != null && !status.isEmpty()) {
	        jpql += " AND o.status = :status";
	    }
	    TypedQuery<Order> query = em.createQuery(jpql, Order.class);
	    query.setParameter("customerId", customerId);
	    if (status != null && !status.isEmpty()) {
	        query.setParameter("status", status);
	    }
	    List<Order> orders = query.getResultList();
	    em.close();
	    return orders;
	}

	public static void main(String[] args) {
		OrderDaoImpl dao = new OrderDaoImpl();
        int orderId = 1;  // Thay đổi với ID của đơn hàng mà bạn muốn truy vấn

        // Gọi hàm findByOrderId để lấy danh sách OrderItem
        List<OrderItem> orderItems = dao.findByOrderId(orderId);

        // Kiểm tra kết quả
        if (orderItems != null && !orderItems.isEmpty()) {
            System.out.println("Order Items for Order ID: " + orderId);
            for (OrderItem item : orderItems) {
                System.out.println("Product Name: " + item.getProductType().getProduct().getName());
                System.out.println("Product Price: " + item.getProductType().getPrice());
                System.out.println("Quantity: " + item.getQuantity());
            }
        } else {
            System.out.println("No Order Items found for Order ID: " + orderId);
        }
    }
}


