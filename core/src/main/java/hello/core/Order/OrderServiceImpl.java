package hello.core.Order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;

public class OrderServiceImpl implements OrderService{

    // private final MemberRepository memberRepository = new MemoryMemberRepository();
    private MemberRepository memberRepository;
    // private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    // 고정 할인 금액에서 비율로 변경
    // private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    /*
     * DIP 위반  DiscountPolicy추상 인터페이스 FixDiscountPolicy, RateDiscountPolicy 구체 클래스
     * OrderServiceImpl(주문)는 DiscountPolicy만 의존해야하지만 현재는 FixDiscountPolicy, RateDiscountPolicy까지 함께 의존하고 있다.
     * FixDiscountPolicy에서 RateDiscountPolicy로 변경하는 순간 OrderServiceImpl도 코드를 수정(discountPolicy 인스턴스 변경)하게 되는 문제
     * 
     * private DiscountPolicy discountPolicy; 로 변경하여 문제점 해결
     */
    private DiscountPolicy discountPolicy;
    // 이제야 DIP를 지킨다.

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
        this.memberRepository = memberRepository;
    }

    @Override
    public Order creatOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
    
}
