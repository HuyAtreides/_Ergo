package cnpm.ergo.configs;

import cnpm.ergo.entity.Product;
import cnpm.ergo.entity.Voucher;
import cnpm.ergo.entity.VoucherByPrice;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class MainApp {
    public static void main(String[] args) {
        //insert VoucherByPrice
        EntityManager em = JPAConfig.getEntityManager();
        em.getTransaction().begin();
        //get product by id
        VoucherByPrice voucherByPrice = new VoucherByPrice();
        voucherByPrice.setLowerbound(100000);

        em.persist(voucherByPrice);
        em.getTransaction().commit();
        em.close();


    }
}

