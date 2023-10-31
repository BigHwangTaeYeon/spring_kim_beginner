package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;

public class RateDiscountPolicy implements DiscountPolicy{

    private int discountPercent = 10;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            // 불안한 로직. 할인 관련된 개발은 이렇게 쉽지 않음
            return price * discountPercent / 100;
        } else {
            return 0;
        }
    }
    
}
