package cnpm.ergo.service.interfaces;

import java.util.List;

import cnpm.ergo.entity.CampaignImageEntity;

public interface ICampaignImageService {
	void addImage(CampaignImageEntity campaignImage);
    List<CampaignImageEntity> findImagesByCampaignId(Long campaignId);
    void deleteByCampaignId(Long campaignId);
}
