package cnpm.ergo.DAO.implement;

import java.util.List;

import cnpm.ergo.DAO.interfaces.ICampaignImageDao;
import cnpm.ergo.configs.JPAConfig;
import cnpm.ergo.entity.CampaignImage;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class CampaignImageDaoImpl implements ICampaignImageDao{
    @Override
    public void addImage(CampaignImage campaignImage) {

    }

    @Override
    public List<CampaignImage> findImagesByCampaignId(Long campaignId) {
        return List.of();
    }

    @Override
    public void deleteByCampaignId(Long campaignId) {

    }

//    private EntityManager entityManager = JPAConfig.getEntityManager();
//    @Override
//    public void addImage(CampaignImage campaignImage) {
//        EntityTransaction transaction = entityManager.getTransaction();
//        try {
//            transaction.begin();
//            entityManager.persist(campaignImage);
//            transaction.commit();
//        } catch (Exception e) {
//            if (transaction.isActive()) {
//                transaction.rollback();
//            }
//            throw new RuntimeException("Lỗi khi thêm CampaignImage: " + e.getMessage(), e);
//        }
//    }
//
//    @Override
//    public List<CampaignImage> findImagesByCampaignId(Long campaignId) {
//        try {
//            return entityManager.createQuery("SELECT * FROM campaignimage ci WHERE ci.campaignId = :campaignId", CampaignImageEntity.class)
//                    .setParameter("campaignId", campaignId)
//                    .getResultList();
//        } catch (Exception e) {
//            throw new RuntimeException("Lỗi khi truy vấn CampaignImage: " + e.getMessage(), e);
//        }
//    }
//
//    @Override
//    public void deleteByCampaignId(Long campaignId) {
//        EntityTransaction transaction = entityManager.getTransaction();
//        try {
//            transaction.begin();
//            entityManager.createQuery("DELETE FROM CampaignImageEntity ci WHERE ci.campaignId = :campaignId")
//                    .setParameter("campaignId", campaignId)
//                    .executeUpdate();
//            transaction.commit();
//        } catch (Exception e) {
//            if (transaction.isActive()) {
//                transaction.rollback();
//            }
//            throw new RuntimeException("Lỗi khi xóa hình ảnh theo CampaignId: " + e.getMessage(), e);
//        }
//    }

}