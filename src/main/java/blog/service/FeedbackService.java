package blog.service;

import blog.model.Feedback;
import blog.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    public void saveFeedback(Feedback feedback) {
        feedbackRepository.save(feedback);
    }

    public List<Feedback> getFeedbackList() {
        return (List<Feedback>) feedbackRepository.findAll();
    }


    public Feedback getFeedbackById(long fbId) {
        Optional<Feedback> optional = feedbackRepository.findById(fbId);
        Feedback feedback = null;
        if (optional.isPresent()) {
            feedback = optional.get();
        } else {
            throw new RuntimeException("Feedback not found for id ::" + fbId);
        }
        return feedback;
    }

    public void deleteFeedbackById(long fbId) {
        this.feedbackRepository.deleteById(fbId);
    }
}
