package cnpm.ergo.DAO.interfaces;

import java.util.List;

import org.hibernate.query.Page;

import cnpm.ergo.entity.Product;
public interface IProductDao {
	void insert(Product product);
	void update(Product product);
	void delete(int productId);
	Product findById(int productId);
	List<Product> searchByName(String name);
	int count();
	List<Product> findAll(int page, int size);
}

