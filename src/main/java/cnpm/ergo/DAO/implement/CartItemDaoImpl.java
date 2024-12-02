package cnpm.ergo.DAO.implement;

import cnpm.ergo.DAO.interfaces.*;
import cnpm.ergo.configs.JPAConfig;
import cnpm.ergo.entity.Cart;
import cnpm.ergo.entity.CartItem;
import cnpm.ergo.entity.Customer;
import cnpm.ergo.entity.ProductType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
public class CartItemDaoImpl implements ICartItemDao {

	@Override
	public void addCartItemWithCart(CartItem cartItem, int customerId) {
	    EntityManager em = JPAConfig.getEntityManager();
	    EntityTransaction trans = em.getTransaction();

	    try {
	        trans.begin();
	        String jpql = "SELECT c FROM Cart c WHERE c.customer.customerId = :customerId";
	        TypedQuery<Cart> query = em.createQuery(jpql, Cart.class);
	        query.setParameter("customerId", customerId);

	        Cart cart;

	        try {
	            cart = query.getSingleResult();
	        } catch (Exception e) {
	            cart = new Cart();
	            Customer customer = em.find(Customer.class, customerId);
	            if (customer == null) {
	                throw new IllegalArgumentException("Customer với ID " + customerId + " không tồn tại.");
	            }
	            cart.setCustomer(customer);
	            em.persist(cart); 
	        }
	        String itemJpql = "SELECT ci FROM CartItem ci WHERE ci.cart.cartId = :cartId AND ci.productType.typeId = :typeId";
	        TypedQuery<CartItem> itemQuery = em.createQuery(itemJpql, CartItem.class);
	        itemQuery.setParameter("cartId", cart.getCartId());
	        itemQuery.setParameter("typeId", cartItem.getProductType().getTypeId());

	        CartItem existingCartItem;
	        try {
	            existingCartItem = itemQuery.getSingleResult();
	            existingCartItem.setQuantity(existingCartItem.getQuantity() + cartItem.getQuantity());
	            em.merge(existingCartItem);
	        } catch (Exception e) {
	            cartItem.setCart(cart);
	            em.persist(cartItem);
	        }

	        trans.commit();
	    } catch (Exception e) {
	        trans.rollback();
	        throw e;
	    } finally {
	        em.close();
	    }
	}
}
