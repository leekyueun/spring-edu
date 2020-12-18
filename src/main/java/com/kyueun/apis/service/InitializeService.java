package com.kyueun.apis.service;

import com.kyueun.apis.repository.ProductRepository;
import com.kyueun.apis.repository.ReviewRepository;
import com.kyueun.apis.repository.SaleRepository;
import com.kyueun.apis.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class InitializeService {
    private final ProductRepository productRepository;
    private final ReviewRepository reviewRepository;
    private final SaleRepository saleRepository;
    private final UserRepository userRepository;

    @Autowired
    public InitializeService(ProductRepository productRepository, ReviewRepository reviewRepository, SaleRepository saleRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.reviewRepository = reviewRepository;
        this.saleRepository = saleRepository;
        this.userRepository = userRepository;
    }

    public void initialize() {

    }
}
