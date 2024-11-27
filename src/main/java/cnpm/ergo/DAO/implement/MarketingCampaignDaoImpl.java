package cnpm.ergo.DAO.implement;

import java.util.List;

import cnpm.ergo.DAO.interfaces.IMarketingCampaignDao;
import cnpm.ergo.configs.JPAConfig;
import cnpm.ergo.entity.CampaignImage;
import cnpm.ergo.entity.MarketingCampaign;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class MarketingCampaignDaoImpl implements IMarketingCampaignDao {

	@Override
	public MarketingCampaign findById(Long id) {
		return entityManager.find(MarketingCampaign.class, id);
	}
	private EntityManager entityManager = JPAConfig.getEntityManager();
	@Override
	public void insert(MarketingCampaign campaignEntity) {
		EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(campaignEntity); 
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Không thêm được MarketingCampaignEntity này: " + e.getMessage(), e);
        }
	}
	@Override
	public void update(MarketingCampaign campaignEntity) {
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			entityManager.merge(campaignEntity);
			transaction.commit();
		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			throw new RuntimeException("Cập nhật MarketingCampaign không thành công: " + e.getMessage(), e);
		}
	}

	@Override
	public void delete(Long campaignId) {
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			MarketingCampaign campaign = entityManager.find(MarketingCampaign.class, campaignId);
			if (campaign != null) {
				entityManager.remove(campaign);
			} else {
				throw new RuntimeException("Không tìm thấy MarketingCampaign với ID: " + campaignId);
			}

			transaction.commit();
		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			throw new RuntimeException("Xóa lỗi: " + e.getMessage(), e);
		}
	}

	@Override
	public List<MarketingCampaign> findAll() {
		try {
			return entityManager.createQuery("SELECT m FROM MarketingCampaign m", MarketingCampaign.class)
					.getResultList();
		} catch (Exception e) {
			throw new RuntimeException("Lỗi truy vấn: " + e.getMessage(), e);
		}
	}

}