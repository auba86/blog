package blog.controller;

import blog.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;

public class RatingController {

    @Autowired
    private RatingService ratingService;
}
