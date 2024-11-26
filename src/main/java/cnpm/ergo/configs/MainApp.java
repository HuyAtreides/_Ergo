package cnpm.ergo.configs;

import cnpm.ergo.entity.*;
import jakarta.persistence.EntityManager;

import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        //insert voucher
        VoucherByPrice voucher = new VoucherByPrice();
        voucher.setLowerbound(1000);

        //insert campaign
        MarketingCampaign campaign = new MarketingCampaign();
        campaign.setContent("Summer Sale");
        //insert campaign image
        CampaignImage campaignImage = new CampaignImage();
        campaignImage.setImagePath("ok");
        campaignImage.setMarketingCampaign(campaign);
        EntityManager em = JPAConfig.getEntityManager();
        em.getTransaction().begin();
        em.persist(voucher);
        em.persist(campaign);
        em.persist(campaignImage);
        em.getTransaction().commit();
        em.close();


    }
}

