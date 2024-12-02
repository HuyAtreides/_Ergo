package cnpm.ergo.DAO.implement;

import cnpm.ergo.DAO.interfaces.IReviewDao;
import cnpm.ergo.configs.JPAConfig;
import cnpm.ergo.entity.Review;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class ReviewDaoImpl implements IReviewDao {

    @Override
    public void insert(Review review) {
        EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(review);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public void update(Review review) {
        EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.merge(review);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public void delete(int reviewId) {
        EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Review review = em.find(Review.class, reviewId);
            if (review != null) {
                em.remove(review);
            }
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public Review findById(int reviewId) {
        EntityManager em = JPAConfig.getEntityManager();
        return em.find(Review.class, reviewId);
    }

    @Override
    public List<Review> findAll() {
        EntityManager em = JPAConfig.getEntityManager();
        String jpql = "SELECT r FROM Review r";
        TypedQuery<Review> query = em.createQuery(jpql, Review.class);
        return query.getResultList();
    }

    @Override
    public List<Review> findByProductId(int productId) {
        EntityManager em = JPAConfig.getEntityManager();
        String jpql = "SELECT r FROM Review r WHERE r.product.productId = :productId";
        TypedQuery<Review> query = em.createQuery(jpql, Review.class);
        query.setParameter("productId", productId);
        return query.getResultList();
    }
}
