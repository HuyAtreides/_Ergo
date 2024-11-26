package cnpm.ergo.configs;

import cnpm.ergo.entity.*;
import jakarta.persistence.EntityManager;

import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        //insert voucher
        EntityManager entityManager = JPAConfig.getEntityManager();
        entityManager.getTransaction().begin();

        //get customer
        Customer customer = entityManager.find(Customer.class, 1);

        //get product
        Product product = entityManager.find(Product.class, 2);
        //create wishlist
        Wishlist wishlist = new Wishlist();
        wishlist.setCustomer(customer);
        wishlist.setDelete(false);
        wishlist.setProducts(List.of(product));
        entityManager.persist(wishlist);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}

