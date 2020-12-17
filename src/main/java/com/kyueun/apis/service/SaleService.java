package com.kyueun.apis.service;

import com.kyueun.apis.datamodels.SaleGroupByUserId;
import com.kyueun.apis.datamodels.SaleStatusEnum;
import com.kyueun.apis.datamodels.UserTotalPaidPrice;
import com.kyueun.apis.model.Product;
import com.kyueun.apis.model.Sale;
import com.kyueun.apis.model.User;
import com.kyueun.apis.repository.ProductRepository;
import com.kyueun.apis.repository.SaleRepository;
import com.kyueun.apis.repository.UserRepository;
import com.kyueun.apis.vo.SalePurcheseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class SaleService {
    private final SaleRepository saleRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public Sale find(int saleId) throws Exception {
        Optional<Sale> searchedSale = this.saleRepository.findById(saleId);
        return searchedSale.orElseThrow(() -> new Exception("해당 상품을 찾지 못하였습니다."));
    }

    @Autowired
    public SaleService(SaleRepository saleRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.saleRepository = saleRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public int createSale(SalePurcheseVO salePurcheseVO) throws Exception {
        Optional<Product> product = this.productRepository.findById(salePurcheseVO.getUserId());
        Optional<User> user = this.userRepository.findById(salePurcheseVO.getUserId());

        Product findedProduct = product.orElseThrow(() -> new Exception("해당 상품 ID가 존재하지 않습니다."));
        user.orElseThrow(() -> new Exception("해당 유저 ID가 존재하지 않습니다."));

        if (salePurcheseVO.getListPrice() != findedProduct.getListPrice() * salePurcheseVO.getAmount()) {
            throw new Exception("정가가 상품정보에 등록된 가격과 다릅니다");
        }
        if (salePurcheseVO.getPaidPrice() != findedProduct.getPrice() * salePurcheseVO.getAmount()) {
            throw new Exception("실제 구매 금액이 상품정보에 등록된 가격과 다릅니다");
        }

        Sale createSale = Sale.builder()
                .userId(salePurcheseVO.getUserId())
                .productId(salePurcheseVO.getProductId())
                .paidPrice(salePurcheseVO.getPaidPrice())
                .listPrice(salePurcheseVO.getListPrice())
                .amount(salePurcheseVO.getAmount())
                .build();

        this.saleRepository.save(createSale);
        this.saleRepository.flush();

        return createSale.getSaleId();
    }

    public void purchase(int saleId) throws Exception {
        Optional<Sale> purchaseSale = this.saleRepository.findById(saleId);
        Sale sale = purchaseSale.orElseThrow(() -> new Exception("결제 완료로 변경하는 도중에 문제가 생겼습니다."));

        sale.setStatus(SaleStatusEnum.PAID);
        this.saleRepository.save(sale);
        this.saleRepository.flush();
    }

    public void refund(int saleId) throws Exception {
        Optional<Sale> purchaseSale = this.saleRepository.findById(saleId);
        Sale sale = purchaseSale.orElseThrow(() -> new Exception("결제 취소로 변경하는 도중에 문제가 생겼습니다."));

        sale.setStatus(SaleStatusEnum.REFUNDED);
        this.saleRepository.save(sale);
        this.saleRepository.flush();
    }

    public void initializeSales() {
        Sale sale1 = Sale.builder()
                .userId(1)
                .productId(1)
                .listPrice(1200000)
                .paidPrice(1000000)
                .amount(1)
                .build();
        Sale sale2 = Sale.builder()
                .userId(1)
                .productId(1)
                .listPrice(1230000 * 2)
                .paidPrice(1110000 * 2)
                .amount(2)
                .build();
        Sale sale3 = Sale.builder()
                .userId(3)
                .productId(3)
                .listPrice(230000 * 3)
                .paidPrice(210000 * 3)
                .amount(3)
                .build();
        this.saleRepository.save(sale1);
        this.saleRepository.save(sale2);
        this.saleRepository.save(sale3);
        this.saleRepository.flush();
    }

    public List<Sale> getSalesByUserId(int userId) {
        return this.saleRepository.findByUserId(userId);
    }

    public UserTotalPaidPrice getTotalPaidPriceByUserId(int userId) {
        SaleGroupByUserId groupData = this.saleRepository.PurchaseAmountGroupByUserId(userId);
        return new UserTotalPaidPrice(groupData);
    }
}
