package cnpm.ergo.configs;

import cnpm.ergo.DAO.implement.EmployeeDAOImpl;
import cnpm.ergo.entity.*;
import cnpm.ergo.service.implement.EmployeeServiceImpl;
import cnpm.ergo.service.interfaces.IEmployeeService;
import jakarta.persistence.EntityManager;

import java.util.ArrayList;
import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        //insert voucher
        EntityManager entityManager = JPAConfig.getEntityManager();
        entityManager.getTransaction().begin();
        //Get all voucherByProduct List
        List<VoucherByProduct> voucherByProductList = entityManager.createNamedQuery("VoucherByProduct.findAll", VoucherByProduct.class).getResultList();
        //Get all voucherByPrice List
        List<VoucherByPrice> voucherByPriceList = entityManager.createNamedQuery("VoucherByPrice.findAll", VoucherByPrice.class).getResultList();

        List<Voucher>vouchers = new ArrayList<>();
        for(VoucherByProduct voucherByProduct : voucherByProductList){
            vouchers.add(voucherByProduct);
        }
        for(VoucherByPrice voucherByPrice : voucherByPriceList){
            vouchers.add(voucherByPrice);
        }

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

