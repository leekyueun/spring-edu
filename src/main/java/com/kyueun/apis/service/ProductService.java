package com.kyueun.apis.service;

import com.kyueun.apis.datamodels.dto.ProductDTO;
import com.kyueun.apis.datamodels.exception.ControllableException;
import com.kyueun.apis.model.Product;
import com.kyueun.apis.repository.ProductRepository;
import com.kyueun.apis.datamodels.vo.ProductRegisterVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductDTO> products() {
        return this.productRepository.findAll().stream()
                .map(ProductDTO::new)
                .collect(Collectors.toList());
    }

    public Product productById(int productId) throws ControllableException {
        Optional<Product> searchedProduct = this.productRepository.findById(productId);
        return searchedProduct.orElseThrow(() -> new ControllableException("해당 상품을 찾지 못하였습니다"));
    }

    public void initializeProducts() {
        Product product1 = Product.builder()
                .name("컴퓨터")
                .description("여러분들이 쓰고 계신겁니다")
                .listPrice(1200000)
                .price(1000000)
                .category("전자기기")
                .imageURL("http://s3.aws-amazon.com/url-computer")
                .build();

        Product product2 = Product.builder()
                .name("갤럭시 s20")
                .description("핸드폰입니다")
                .listPrice(1240000)
                .price(1110000)
                .category("전자기기")
                .imageURL("http://s3.aws-amazon.com/url-galuxy")
                .build();

        Product product3 = Product.builder()
                .name("에어팟 프로")
                .description("달라진 것은 하나, 전부입니다")
                .listPrice(230000)
                .price(210000)
                .category("이어폰")
                .imageURL("http://s3.aws-amazon.com/url-airpod")
                .build();

        this.productRepository.save(product1);
        this.productRepository.save(product2);
        this.productRepository.save(product3);
        this.productRepository.flush();
    }

    public int createProduct(ProductRegisterVO productRegisterVO) {
        Product createdProduct = Product.builder()
                .name(productRegisterVO.getName())
                .description(productRegisterVO.getDescription())
                .listPrice(productRegisterVO.getListPrice())
                .price(productRegisterVO.getPrice())
                .category(productRegisterVO.getCategory())
                .imageURL(productRegisterVO.getImageURL())
                .build();

        this.productRepository.save(createdProduct);
        this.productRepository.flush();

        return createdProduct.getProductId();
    }

    public void deleteProduct(int productId) {
        this.productRepository.deleteById(productId);
    }

    public List<ProductDTO> productsByCategory(String category) {
        return this.productRepository.findByCategory(category).stream()
                .map(ProductDTO::new)
                .collect(Collectors.toList());
    }
}