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

        Product product = new Product();
        product.setName("IP");

        ProductType productType = new ProductType();
        productType.setProduct(product);
        productType.setColor("pink");

        ProductType productType2 = new ProductType();
        productType2.setProduct(product);
        productType2.setColor("black");
        Product product1 = productType2.getProduct();

        IProductService service = new ProductServiceImpl();
        service.addProduct(product);

        IProductTypeService service1 = new ProductTypeServiceImpl();
        service1.addProductType(productType);
        service1.addProductType(productType2);

        service1.getAllProductTypes();


        entityManager.getTransaction().commit();
        entityManager.close();
    }
}

