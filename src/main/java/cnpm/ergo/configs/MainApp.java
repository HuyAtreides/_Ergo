package cnpm.ergo.configs;

import cnpm.ergo.entity.*;
import cnpm.ergo.service.implement.IVoucherByPriceServiceImpl;
import cnpm.ergo.service.implement.IVoucherByProductServiceImpl;
import cnpm.ergo.service.implement.ProductServiceImpl;
import cnpm.ergo.service.implement.ProductTypeServiceImpl;
import cnpm.ergo.service.interfaces.IProductService;
import cnpm.ergo.service.interfaces.IProductTypeService;
import cnpm.ergo.service.interfaces.IVoucherByPriceService;
import cnpm.ergo.service.interfaces.IVoucherByProductService;
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

        List<ProductType> list = new ArrayList<>();
        list.add(productType);
        list.add(productType2);

        IProductService productService = new ProductServiceImpl();
        productService.addProduct(product);

        IProductTypeService productTypeService = new ProductTypeServiceImpl();
        productTypeService.addProductType(productType);
        productTypeService.addProductType(productType2);





       VoucherByPrice voucher1 = new VoucherByPrice();
       voucher1.setCode("123");
       voucher1.setDiscount(1234);
        voucher1.setLowerbound(12);
        voucher1.setDateStart(Date.valueOf("2003-11-20"));

        VoucherByProduct voucher2 = new VoucherByProduct();
        voucher2.setCode("456");
        voucher2.setDiscount(678);
        voucher2.setProductTypes(list);

        IVoucherByPriceService service1 = new IVoucherByPriceServiceImpl();
        IVoucherByProductService service2 = new IVoucherByProductServiceImpl();


        service1.insert(voucher1);
        service2.insert(voucher2);

        List<VoucherByPrice> voucherByPriceList = service1.findAll();
        List<VoucherByProduct> results1 = service2.findAll();
        List<Voucher> vouchers = new ArrayList<>();

        for(VoucherByPrice voucherByPrice : voucherByPriceList){
            vouchers.add(voucherByPrice);
        }

        //get customer
        for(Voucher voucher : vouchers){
            System.out.println(voucher.getVoucherId());
            System.out.println(voucher.getCode());
            if (voucher instanceof VoucherByProduct){
                System.out.println("VoucherByProduct");
            }
            else System.out.println("VoucherByPrice");
        }


        entityManager.getTransaction().commit();
        entityManager.close();
    }
}

