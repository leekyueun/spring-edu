package com.kyueun.apis.route;

import com.kyueun.apis.datamodels.dto.ProductDTO;
import com.kyueun.apis.model.Product;
import com.kyueun.apis.service.ProductService;
import com.kyueun.apis.datamodels.vo.ProductRegisterVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Product getProduct(@PathVariable(value="product_id") String productId) throws Exception {
        return this.productService.productById(Integer.parseInt(productId));
    }

    @GetMapping
    @ResponseBody
    public List<ProductDTO> getProducts() {
        return this.productService.products();
    }

    @GetMapping("/initialize")
    public void initializeProducts() {
        this.productService.initializeProducts();
    }

    @DeleteMapping("/{product_id}")
    public void deleteProduct(@PathVariable(value="product_id") String productId) throws Exception {
        this.productService.deleteProduct(Integer.parseInt(productId));
    }

    @PostMapping
    public int createProduct(ProductRegisterVO productRegisterVO) {
        return this.productService.createProduct(productRegisterVO);
    }

    @GetMapping("/category/{category_name}")
    @ResponseBody
    public List<ProductDTO> getProductsByCategory(@PathVariable(value="category_name") String category_name) {
        return this.productService.productsByCategory(category_name);
    }
}
