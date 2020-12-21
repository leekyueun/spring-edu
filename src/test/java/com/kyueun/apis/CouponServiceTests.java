package com.kyueun.apis;

import com.kyueun.apis.repository.CouponRepository;
import com.kyueun.apis.service.CouponService;
import com.kyueun.apis.datamodels.vo.CouponRegisterVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class CouponServiceTests {
    private final CouponService couponService;
    private final CouponRepository couponRepository;

    @Autowired
    public CouponServiceTests(CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
        this.couponService = new CouponService(couponRepository);
    }

    @Test
    public void testCreateCouponWhenPercentageAndPriceExists() {
        CouponRegisterVO couponRegisterVO = new CouponRegisterVO(new Date(), 7,
                1, "null", 1000, 10);

        Exception thrown = assertThrows()
    }
}
