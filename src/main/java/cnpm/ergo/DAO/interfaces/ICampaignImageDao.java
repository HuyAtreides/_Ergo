package cnpm.ergo.DAO.interfaces;

import java.util.List;

import cnpm.ergo.entity.CampaignImageEntity;

public interface ICampaignImageDao {
    void addImage(CampaignImageEntity campaignImage);
    List<CampaignImageEntity> findImagesByCampaignId(Long campaignId);
    void deleteByCampaignId(Long campaignId);
}
