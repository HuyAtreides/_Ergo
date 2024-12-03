package cnpm.ergo.configs;

import cnpm.ergo.entity.*;
import cnpm.ergo.service.implement.*;
import cnpm.ergo.service.interfaces.*;
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

        ICampaignImageService service = new CampaignImageServiceImpl();
        CampaignImage image = service.finByPath("test");
        if(image != null)
        {
            System.out.println("tim duoc hinh:" + image.getImagePath() );
            image.setImagePath("updateduongdanhthanhcaikhac");
            service.update(image)   ;
        }
        else
            System.out.println("khong tim duoc");




        System.out.println("chay");



        entityManager.getTransaction().commit();
        entityManager.close();
    }
}

