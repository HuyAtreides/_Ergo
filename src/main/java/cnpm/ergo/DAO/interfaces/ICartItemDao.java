package cnpm.ergo.DAO.interfaces;

import cnpm.ergo.entity.CartItem;

public interface ICartItemDao {

	void addCartItemWithCart(CartItem cartItem, int customerId);
}
