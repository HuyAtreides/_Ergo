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


        System.out.println("test get all");
        MarketingCampaignServiceImpl marketingCampaignService = new MarketingCampaignServiceImpl();
        List<MarketingCampaign> Campaigns = marketingCampaignService.findAllMarketingCampaign();
        System.out.println(Campaigns.size());
        System.out.println("test get all");
        for(MarketingCampaign campain : Campaigns)
        {
            System.out.println(campain.getCampaignId());
        }
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}

