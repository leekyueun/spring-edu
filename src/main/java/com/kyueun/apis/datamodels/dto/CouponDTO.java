package com.kyueun.apis.datamodels.dto;

import com.kyueun.apis.model.Coupon;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class CouponDTO {
    int couponId;
    Date expireAt;
    int availableDays;
    int productID;
    String category;
    int discountPrice = 0;
    int discountPercentage = 0;

    public CouponDTO(Coupon coupon) {
        this.couponId = coupon.getCouponId();
        this.expireAt = coupon.getExpireAt();
        this.availableDays = coupon.getAvailableDays();
        this.productID = coupon.getProductID();
        this.category = coupon.getCategory();
        this.discountPrice = coupon.getDiscountPrice();
        this.discountPercentage = coupon.getDiscountPercentage();
    }

    @Override
    public String toString() {
        return String.format(
                "CouponDTO[couponId=%d, expireAt='%s', availableDays=%d, productID=%d, category='%s', " +
                        "discountPrice=%d, discountPercentage=%d]",
                this.couponId, this.expireAt, this.availableDays, this.productID, this.category, this.discountPrice,
                this.discountPercentage
        );
    }
}
