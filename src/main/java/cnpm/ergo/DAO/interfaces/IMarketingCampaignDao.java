package cnpm.ergo.DAO.interfaces;

import java.util.List;

import cnpm.ergo.entity.MarketingCampaignEntity;

public interface IMarketingCampaignDao {
	void insert(MarketingCampaignEntity campaignEntity);
	void update(MarketingCampaignEntity campaignEntity);
	void delete(Long campaignId);
	List<MarketingCampaignEntity> findAll();
}
