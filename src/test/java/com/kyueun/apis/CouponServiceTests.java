package com.kyueun.apis;

import com.kyueun.apis.datamodels.exception.ControllableException;
import com.kyueun.apis.repository.CouponRepository;
import com.kyueun.apis.service.CouponService;
import com.kyueun.apis.datamodels.vo.CouponRegisterVO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CouponServiceTests {
    private final CouponService couponService;
    private final CouponRepository couponRepository;

    public CouponServiceTests() {
        this.couponRepository = mock(CouponRepository.class);
        this.couponService = new CouponService(couponRepository);
    }

    @Test
    public void testCreateCouponWhenPercentageAndPriceExists() {
        // given
        CouponRegisterVO couponRegisterVO = new CouponRegisterVO(new Date(), 7,
                1, "null", 1000, 10);

        // when
        ControllableException thrown = assertThrows(ControllableException.class, () -> this.couponService.createCoupon(couponRegisterVO));

        // then
        assertEquals("할인 금액과 할인 비율이 동시에 존재할수 없습니다!", thrown.getMessage());
    }

    @Test
    public void testCreateCouponWhenNormal() throws ControllableException {
        // given
        CouponRegisterVO couponRegisterVO = new CouponRegisterVO(new Date(), 7,
                1, "null", 1000, 0);

        // when
        this.couponService.createCoupon(couponRegisterVO);

        // then
        verify(this.couponRepository).flush();
    }
}