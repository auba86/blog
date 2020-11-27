package blog.controller;

import blog.model.Feedback;
import blog.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @GetMapping("/feedback")
    public String feedbackForm(Model model) {
        Feedback feedback = new Feedback();
        model.addAttribute("feedback", feedback);
        return "feedbackform";
    }

    @PostMapping("/saveFeedback")
    public String saveFeedback(@ModelAttribute(value = "feedback") Feedback feedback) {
        feedbackService.saveFeedback(feedback);
        return "redirect:/";
    }

    @GetMapping("/getFeedback")
    public String feedbackList(Model model) {
        List<Feedback> feedbackList = feedbackService.getFeedbackList();
        Collections.sort(feedbackList, new CustomComparator());
        model.addAttribute("feedbackList", feedbackList);
        return "feedbackpage";
    }

    public class CustomComparator implements Comparator<Feedback> {
        @Override
        public int compare(Feedback o1, Feedback o2) {
            return o2.getFbId().compareTo(o1.getFbId());
        }
    }

    @GetMapping("/deleteFeedback/{fbId}")
    public String deleteFeedback(@PathVariable(value = "fbId") long fbId) {
        this.feedbackService.deleteFeedbackById(fbId);
        return "adminmanagersystem";
    }
}
