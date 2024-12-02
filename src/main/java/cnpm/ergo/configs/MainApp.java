package cnpm.ergo.configs;

import cnpm.ergo.entity.*;
import cnpm.ergo.service.implement.*;
import cnpm.ergo.service.interfaces.IProductService;
import cnpm.ergo.service.interfaces.IProductTypeService;
import cnpm.ergo.service.interfaces.IVoucherByPriceService;
import cnpm.ergo.service.interfaces.IVoucherByProductService;
import com.mysql.cj.conf.PropertyDefinitions;
import jakarta.persistence.EntityManager;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        //insert voucher
        EntityManager entityManager = JPAConfig.getEntityManager();
        entityManager.getTransaction().begin();

        IVoucherByProductService service = new IVoucherByProductServiceImpl();
        VoucherByProduct product = service.findById(3);
        product.setCode("ProductDaUpdate");
        product.setDelete(true);
        System.out.println("xoa");
        service.update(product);
//        service.delete(product);

        System.out.println("xoa duoc");

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}

