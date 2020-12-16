package com.kyueun.apis.route;

import com.kyueun.apis.model.Product;
import com.kyueun.apis.service.ProductService;
import com.kyueun.apis.vo.ProductRegisterVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductRoute {
    private final ProductService productService;

    @Autowired
    public ProductRoute(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{product_id}")
    @ResponseBody
    public Product getProduct(@PathVariable(value = "product_id") String productId) throws Exception {
        return this.productService.find(Integer.parseInt(productId));
    }

    @GetMapping("/initialize")
    public void initializeProducts() {
        this.productService.initializeProducts();
    }

    @DeleteMapping("/{product_id}")
    public void deleteUser(@PathVariable(value = "user_id") String productId) {
        this.productService.deleteProduct(Integer.parseInt(productId));
    }

    @PostMapping
    public void createProduct(ProductRegisterVO productRegisterVO) {
        this.productService.createProduct(productRegisterVO);
    }
}
