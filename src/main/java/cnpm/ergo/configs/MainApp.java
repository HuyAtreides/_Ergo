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


        entityManager.getTransaction().commit();
        entityManager.close();
    }
}

