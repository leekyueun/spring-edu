package com.kyueun.apis.service;

import com.kyueun.apis.datamodels.dto.CouponDTO;
import com.kyueun.apis.datamodels.exception.ControllableException;
import com.kyueun.apis.model.Coupon;
import com.kyueun.apis.repository.CouponRepository;
import com.kyueun.apis.datamodels.vo.CouponRegisterVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public class CouponService {
    private final CouponRepository couponRepository;

    @Autowired
    public CouponService(CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }

    public int createCoupon(CouponRegisterVO couponRegisterVO) throws ControllableException {
        if (couponRegisterVO.getDiscountPercentage() != 0 && couponRegisterVO.getDiscountPrice() != 0) {
            throw new ControllableException("할인 금액과 할인 비율이 동시에 존재할수 없습니다!");
        }

        Coupon createdCoupon = Coupon.builder()
                .availableDays(couponRegisterVO.getAvailableDays())
                .category(couponRegisterVO.getCategory())
                .discountPercentage(couponRegisterVO.getDiscountPercentage())
                .discountPrice(couponRegisterVO.getDiscountPrice())
                .expireAt(couponRegisterVO.getExpireAt())
                .productID(couponRegisterVO.getProductID())
                .build();

        this.couponRepository.save(createdCoupon);
        this.couponRepository.flush();

        return createdCoupon.getCouponId();
    }

    public CouponDTO couponById(int couponId) throws ControllableException {
        Optional<Coupon> coupon = this.couponRepository.findById(couponId);

        return new CouponDTO(coupon.orElseThrow(() -> new ControllableException("해당 쿠폰을 확인할수 없습니다")));
    }
}