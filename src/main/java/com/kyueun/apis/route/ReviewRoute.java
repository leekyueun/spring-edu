package com.kyueun.apis.route;

import com.kyueun.apis.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/review")
public class ReviewRoute {
    private final ReviewService reviewService;

    @Autowired
    public ReviewRoute(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("initialize")
    public void initializeReviews() {
        this.reviewService.initializeReviews();
    }
}