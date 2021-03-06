package blog.service;

import blog.model.Rating;
import blog.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    public void saveRating(Rating rating) {
        ratingRepository.save(rating);
    }

    public Rating getRatingById(long ratingId) {
        Optional<Rating> optional = ratingRepository.findById(ratingId);
        Rating rating = null;
        if (optional.isPresent()) {
            rating = optional.get();
        } else {
            throw new RuntimeException("Record not found for id ::" + ratingId);
        }
        return rating;
    }

    public void deleteRatingById(long ratingId) {
        this.ratingRepository.deleteById(ratingId);
    }

    public List<Rating> getAllRatings() {
        return (List<Rating>) ratingRepository.findAll();
    }
}
