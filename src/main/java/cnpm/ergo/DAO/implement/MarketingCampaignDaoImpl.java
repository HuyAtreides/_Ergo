package cnpm.ergo.DAO.implement;

import java.util.List;

import cnpm.ergo.DAO.interfaces.IMarketingCampaignDao;
import cnpm.ergo.configs.JPAConfig;
import cnpm.ergo.entity.MarketingCampaign;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class MarketingCampaignDaoImpl implements IMarketingCampaignDao {
    @Override
    public void insert(MarketingCampaign campaignEntity) {

    }

    @Override
    public void update(MarketingCampaign campaignEntity) {

    }

    @Override
    public void delete(Long campaignId) {

    }

    @Override
    public List<MarketingCampaign> findAll() {
        return List.of();
    }

//    private EntityManager entityManager = JPAConfig.getEntityManager();
//
//    @Override
//    public void insert(MarketingCampaign campaignEntity) {
//        EntityTransaction transaction = entityManager.getTransaction();
//        try {
//            transaction.begin();
//            entityManager.persist(campaignEntity);
//            transaction.commit();
//        } catch (Exception e) {
//            if (transaction.isActive()) {
//                transaction.rollback();
//            }
//            throw new RuntimeException("Không thêm được MarketingCampaignEntity này: " + e.getMessage(), e);
//        }
//    }
//
//    @Override
//    public void update(MarketingCampaign campaignEntity) {
//        EntityTransaction transaction = entityManager.getTransaction();
//        try {
//            transaction.begin();
//            entityManager.merge(campaignEntity);
//            transaction.commit();
//        } catch (Exception e) {
//            if (transaction.isActive()) {
//                transaction.rollback();
//            }
//            throw new RuntimeException("Cập nhật MarketingCampaign không thành công: " + e.getMessage(), e);
//        }
//    }
//
//    @Override
//    public void delete(Long campaignId) {
//        EntityTransaction transaction = entityManager.getTransaction();
//        try {
//            transaction.begin();
//            MarketingCampaign campaign = entityManager.find(MarketingCampaign.class, campaignId);
//            if (campaign != null) {
//                entityManager.remove(campaign);
//            } else {
//                throw new RuntimeException("Không tìm thấy MarketingCampaign với ID: " + campaignId);
//            }
//
//            transaction.commit();
//        } catch (Exception e) {
//            if (transaction.isActive()) {
//                transaction.rollback();
//            }
//            throw new RuntimeException("Xóa lỗi: " + e.getMessage(), e);
//        }
//    }
//
//    @Override
//    public List<MarketingCampaign> findAll() {
//        try {
//            return entityManager.createQuery("SELECT * FROM marketingcampaign m", MarketingCampaignEntity.class)
//                    .getResultList();
//        } catch (Exception e) {
//            throw new RuntimeException("Lỗi truy vấn: " + e.getMessage(), e);
//        }
//    }

}