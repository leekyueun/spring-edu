package com.kyueun.apis.repository;

import com.kyueun.apis.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
public interface ReviewRepository extends JpaRepository<Review, Integer> {
}
