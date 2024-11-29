package cnpm.ergo.service.implement;

import java.util.List;

import cnpm.ergo.DAO.implement.MarketingCampaignDaoImpl;
import cnpm.ergo.DAO.interfaces.IMarketingCampaignDao;
import cnpm.ergo.entity.MarketingCampaign;
import cnpm.ergo.service.interfaces.IMarketingCampaignService;

public class MarketingCampaignServiceImpl implements IMarketingCampaignService{

	public IMarketingCampaignDao campaignDao = new MarketingCampaignDaoImpl();
	@Override
	public void addCampaign(MarketingCampaign campaignEntity) {
		campaignDao.insert(campaignEntity);
	}

	@Override
	public void updateCampaign(MarketingCampaign campaignEntity) {
		campaignDao.update(campaignEntity);
	}

	@Override
	public void deleteCampaign(Long Id) {
		campaignDao.delete(Id);
	}

	@Override
	public List<MarketingCampaign> findAllMarketingCampaign() {
		List<MarketingCampaign> campaignEntities = campaignDao.findAll();
		return campaignEntities;
	}
}
