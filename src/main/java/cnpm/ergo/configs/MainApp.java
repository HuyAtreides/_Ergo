package cnpm.ergo.configs;

import cnpm.ergo.entity.*;
import jakarta.persistence.EntityManager;

import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        //insert voucher
        EntityManager entityManager = JPAConfig.getEntityManager();
        entityManager.getTransaction().begin();

//        //Get voucherByProduct by id 2
//        VoucherByProduct voucherByProduct = entityManager.find(VoucherByProduct.class, 6);
//        //get productType by id 1
//        ProductType productType = entityManager.find(ProductType.class, 3);
//
//        //add productType to voucherByProduct
//        voucherByProduct.getProductTypes().add(productType);
//        productType.getVoucher().add(voucherByProduct);
//
//        //update voucherByProduct
//
//        entityManager.merge(voucherByProduct);
//        entityManager.merge(productType);
        entityManager.getTransaction().commit();
        entityManager.close();

    }
}

