package cnpm.ergo.configs;

import cnpm.ergo.DAO.implement.EmployeeDAOImpl;
import cnpm.ergo.entity.*;
import cnpm.ergo.service.implement.EmployeeServiceImpl;
import cnpm.ergo.service.interfaces.IEmployeeService;
import jakarta.persistence.EntityManager;

import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        //insert voucher
        EntityManager entityManager = JPAConfig.getEntityManager();
        entityManager.getTransaction().begin();
        //Get voucherByProduct by id 4
        VoucherByProduct voucherByProduct = entityManager.find(VoucherByProduct.class, 4);
        //get productType by id 2
        ProductType productType = entityManager.find(ProductType.class, 2);

        //add productType to voucherByProduct
        voucherByProduct.getProductTypes().add(productType);
        productType.getVoucher().add(voucherByProduct);

        //update voucherByProduct



        entityManager.merge(voucherByProduct);
        entityManager.merge(productType);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}

