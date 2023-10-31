package hello.core;

import hello.core.Order.OrderService;
import hello.core.Order.OrderServiceImpl;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;



public class AppConfig {

    // private final MemberRepository memberRepository = new MemoryMemberRepository();
    // MemberRepository에 대한 설정을 MemberServiceImpl에서 직접 했지만
    // 이제는 AppConfig에서 설정을 정리해준다.
    public MemberService MemberService() {
        return new MemberServiceImpl(new MemoryMemberRepository());
    }
    // 생성자 주입
    public OrderService orderService() {
        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
    }

}
