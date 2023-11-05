package hello.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hello.core.Order.OrderService;
import hello.core.Order.OrderServiceImpl;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;

// Application에 설정 정보라는 뜻이다. Spring @Configuration
@Configuration
public class AppConfig {

    // @Bean memeberService -> new MemoryMemberRepository()
    // @Bean orderService -> new MemoryMemberRepository()

    // private final MemberRepository memberRepository = new MemoryMemberRepository();
    // MemberRepository에 대한 설정을 MemberServiceImpl에서 직접 했지만
    // 이제는 AppConfig에서 설정을 정리해준다.
    // @Bean을 사용하면 Spring Container에 등록이 된다.
    // @Bean(name="mms")   // name으로 getBean에서 불러오는 이름을 바꿀 수 있다. 하지만 default를 따르는 것이 좋다.
    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    // public MemberService MemberService() {
    //     return new MemberServiceImpl(new MemoryMemberRepository());
    // }
    // 위 코드 리팩토링 - 메소드로 만들어 줌으로써, 향후에 계흭이 바뀌면 바꿔줄 수 있다.
    // 아래 OrderService에도 넣어줄 수 있다.
    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    // 생성자 주입
    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
        // return new OrderServiceImpl();
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        // return new FixDiscountPolicy();
        // 할인 정책 변경
        return new RateDiscountPolicy();
    }
}
