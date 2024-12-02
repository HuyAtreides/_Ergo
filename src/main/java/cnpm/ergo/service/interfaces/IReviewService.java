package cnpm.ergo.service.interfaces;

import cnpm.ergo.entity.Review;

import java.util.List;

public interface IReviewService {
    void insert(Review review);
    void update(Review review);
    void delete(int reviewId);
    Review findById(int reviewId);
    List<Review> findAll();
    List<Review> findByProductId(int productId);
}
