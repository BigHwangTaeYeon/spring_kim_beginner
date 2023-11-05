package hello.core.Order;

import org.springframework.stereotype.Component;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor // lombok annotation
public class OrderServiceImpl implements OrderService{

    // private final MemberRepository memberRepository = new MemoryMemberRepository();
    // @Autowired
    private final MemberRepository memberRepository;
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
    // @Autowired
    private final DiscountPolicy discountPolicy;
    // 이제야 DIP를 지킨다.

    // @Autowired
    // public void setDiscountPolicy(DiscountPolicy discountPolicy){
    //     this.discountPolicy = discountPolicy;
    // }

    // @Autowired
    // public void setMemberRepository(MemberRepository memberRepository){
    //     this.memberRepository = memberRepository;
    // }

    // @Autowired
    // public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
    //     this.discountPolicy = discountPolicy;
    //     this.memberRepository = memberRepository;
    // }

    // @Autowired
    // public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy){
    //     this.memberRepository = memberRepository;
    //     this.discountPolicy = discountPolicy;
    // }
    
    // @Autowired 생성자 하나이기에 생략 가능
    // @RequiredArgsConstructor이 아래와 같은 생성자를 만들어준다.
    // 그래서 final을 사용해도 문제없다. private final MemberRepository memberRepository
    // public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy){
    //     this.memberRepository = memberRepository;
    //     this.discountPolicy = discountPolicy;
    // }

    @Override
    public Order creatOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
    
    // 테스트용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
