package com.kyueun.apis.datamodels.dto;

import com.kyueun.apis.model.Review;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class ReviewDTO {
    private int reviewId;
    private int userId;
    private int rate;
    private String review;

    public ReviewDTO(Review review) {
        this.reviewId = review.getReviewId();
        this.userId = review.getUserId();
        this.rate = review.getRate();
        this.review = review.getReview();
    }

    @Override
    public String toString() {
        return String.format(
                "ReviewDTO[reviewId=%d, userId=%d, rate=%d, review='%s']",
                this.reviewId, this.userId, this.rate, this.review
        );
    }
}
