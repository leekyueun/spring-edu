package com.kyueun.apis.route;

import com.kyueun.apis.datamodels.dto.CouponDTO;
import com.kyueun.apis.datamodels.dto.IssuedCouponDTO;
import com.kyueun.apis.datamodels.exception.ControllableException;
import com.kyueun.apis.service.CouponService;
import com.kyueun.apis.service.IssuedCouponService;
import com.kyueun.apis.datamodels.vo.CouponRegisterVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/coupon")
public class CouponRoute {
    private final CouponService couponService;
    private final IssuedCouponService issuedCouponService;

    @Autowired
    public CouponRoute(CouponService couponService, IssuedCouponService issuedCouponService) {
        this.couponService = couponService;
        this.issuedCouponService = issuedCouponService;
    }


    @GetMapping("/{coupon_id}")
    @ResponseBody
    public CouponDTO getCoupon(@PathVariable(value="coupon_id") String couponId) throws ControllableException {
        return this.couponService.couponById(Integer.parseInt(couponId));
    }

    @PostMapping
    public int createCoupon(CouponRegisterVO couponRegisterVO) throws ControllableException{
        return this.couponService.createCoupon(couponRegisterVO);
    }

    @PostMapping("/{coupon_id}/issue")
    public int issueCoupon(@PathVariable(value="coupon_id") String couponId,
                           @RequestParam(value="user_id") String userId) throws ControllableException {
        return this.issuedCouponService.issueCoupon(Integer.parseInt(couponId), Integer.parseInt(userId));
    }

    @GetMapping("/issued-coupon/{issued_coupon_id}")
    @ResponseBody
    public IssuedCouponDTO getIssuedCoupon(@PathVariable(value="issued_coupon_id") String issuedCouponId)
            throws ControllableException{
        return this.issuedCouponService.issueCouponById(Integer.parseInt(issuedCouponId));
    }
}