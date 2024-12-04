package cnpm.ergo.DAO.interfaces;

import java.util.List;

import cnpm.ergo.entity.Review;

public interface IReviewDao {
//    void insertReview(Review review);
	List<Review> findReviewsAll(int productId);


}