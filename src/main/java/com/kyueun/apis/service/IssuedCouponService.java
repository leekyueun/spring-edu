package com.kyueun.apis.service;

import com.kyueun.apis.datamodels.dto.IssuedCouponDTO;
import com.kyueun.apis.datamodels.exception.ControllableException;
import com.kyueun.apis.model.Coupon;
import com.kyueun.apis.model.IssuedCoupon;
import com.kyueun.apis.repository.CouponRepository;
import com.kyueun.apis.repository.IssuedCouponRepository;
import com.kyueun.apis.utill.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


import java.util.Date;
import java.util.Optional;

@Controller
public class IssuedCouponService {
    private final IssuedCouponRepository issuedCouponRepository;
    private final CouponRepository couponRepository;

    @Autowired
    public IssuedCouponService(IssuedCouponRepository issuedCouponRepository, CouponRepository couponRepository) {
        this.issuedCouponRepository = issuedCouponRepository;
        this.couponRepository = couponRepository;
    }

    public IssuedCouponDTO issueCouponById(int issueCouponId) throws ControllableException {
        return new IssuedCouponDTO(this.issuedCouponRepository.findById(issueCouponId)
                .orElseThrow(() -> new ControllableException("해당 발급된 쿠폰 ID가 없습니다")));
    }

    public int issueCoupon(int couponId, int userId) throws ControllableException {
        Optional<Coupon> SearchedCoupon = this.couponRepository.findById(couponId);
        Coupon coupon = SearchedCoupon.orElseThrow(() -> new ControllableException("해당 쿠폰을 찾지 못하였습니다."));

        Date expireDate = null;
        Date addedDate = DateUtil.addDays(new Date(), coupon.getAvailableDays());

        int compareDate = addedDate.compareTo(coupon.getExpireAt());
        if (compareDate == 1) {
            expireDate = coupon.getExpireAt();
        }
        else if (compareDate == -1){
            expireDate = addedDate;
        }
        else {
            expireDate = addedDate;
        }

        IssuedCoupon issuedCoupon = IssuedCoupon.builder()
                .couponId(couponId)
                .expiredAt(expireDate)
                .userId(userId)
                .build();

        this.issuedCouponRepository.save(issuedCoupon);
        this.issuedCouponRepository.flush();

        return issuedCoupon.getIssuedCouponId();
    }

}