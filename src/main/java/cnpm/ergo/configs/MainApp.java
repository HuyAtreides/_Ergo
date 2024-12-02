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
        System.out.println("khong chay");
        MarketingCampaign marketingCampaign = new MarketingCampaign();
        IMarketingCampaignService service = new MarketingCampaignServiceImpl();
        System.out.println(marketingCampaign.getCampaignImages());
        List<MarketingCampaign> list = service.findAllMarketingCampaign();
        for(MarketingCampaign marketingCampaign1 : list)
        {
            if(marketingCampaign1.getCampaignImages() != null &&  marketingCampaign1.getCampaignImages().size() >0)
            {
                System.out.println(marketingCampaign1.getCampaignImages().get(0).getImagePath());
            }
            else
            {
                System.out.println("khong co");
            }
        }
        marketingCampaign = service.findByID(Long.parseLong("19"));
        if(marketingCampaign.getCampaignImages() != null &&  !marketingCampaign.getCampaignImages().isEmpty())
        {
            System.out.println(marketingCampaign.getCampaignImages().get(0).getImagePath());
        }
        else
        {
            System.out.println("khong co");
        }

        System.out.println("chay");



        entityManager.getTransaction().commit();
        entityManager.close();
    }
}

