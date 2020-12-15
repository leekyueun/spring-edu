package com.kyueun.apis.service;

import com.kyueun.apis.model.Product;
import com.kyueun.apis.repository.ProductRepository;
import com.kyueun.apis.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product find(int productId) throws Exception {
        Optional<Product> searchedProduct = this.productRepository.findById(productId);
        return searchedProduct.orElseThrow(() -> new Exception("해당 상품을 찾지 못하였습니다."));
    }

    public void initializeProducts() {
        Product product1 = Product.builder()
                .name("컴퓨터")
                .description("여러분들이 쓰고 계신겁니다")
                .listPrice(1200000)
                .price(1000000)
                .build();

        Product product2 = Product.builder()
                .name("갤럭시 s20")
                .description("핸드폰")
                .listPrice(1240000)
                .price(1110000)
                .build();

        Product product3 = Product.builder()
                .name("에어팟 프로")
                .description("달라진 것은 하나, 전부입니다")
                .listPrice(230000)
                .price(210000)
                .build();

        this.productRepository.save(product1);
        this.productRepository.save(product2);
        this.productRepository.save(product3);
        this.productRepository.flush();
    }
}
