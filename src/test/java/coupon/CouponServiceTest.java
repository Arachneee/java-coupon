package coupon;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import coupon.application.CouponService;
import coupon.domain.Coupon;
import java.time.LocalDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CouponServiceTest {

    @Autowired
    private CouponService couponService;

    @Test
    void 복제지연테스트() throws Exception {
        Coupon coupon = new Coupon("CouponName", 1_000, 10_000, "가구", LocalDate.now(), LocalDate.now());
        couponService.create(coupon);
        Coupon savedCoupon = couponService.getCoupon(coupon.getId());
        assertThat(savedCoupon).isNotNull();
    }

    @DisplayName("Writer DB에도 없으면 지정한 예외를 반환한다.")
    @Test
    void 복제지연테스트_예외() throws Exception {
        assertThatThrownBy(() -> couponService.getCoupon(0L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("쿠폰이 존재하지 않습니다.");
    }
}
