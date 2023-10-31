package hello.core.discount;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import hello.core.member.Grade;
import hello.core.member.Member;

public class RateDiscountPolicyTest {
    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    // 성공 테스트
    @Test
    @DisplayName("VIP는 10% 할인 적용")
    void vip_o() {
        //giben
        Member member = new Member(1L, "memberVIP", Grade.VIP);
        //when
        int discount = discountPolicy.discount(member, 10000);
        //then
        //import org.assertj.core.api.Assertions;
        // Assertions.assertThat(discount).isEqualTo(1000);

        // static import로 올려서 사용하면 편하다.
        // import static org.assertj.core.api.Assertions.*;
        assertThat(discount).isEqualTo(1000);
    }

    // 실패 테스트
    @Test
    @DisplayName("VIP가 아니면 할인 적용 없음")
    void vip_x() {
        //given
        Member member = new Member(2L, "memberBASIC", Grade.BASIC);
        //when
        int discount = discountPolicy.discount(member, 10000);
        //then
        assertThat(discount).isEqualTo(1000);
    }
}
