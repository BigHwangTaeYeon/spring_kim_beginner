package hello.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.core.Order.Order;
import hello.core.Order.OrderService;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;

public class OderApp {
    public static void main(String[] args) {
        /*
        // MemberService memberService = new MemberServiceImpl();
        // OrderService orderService = new OrderServiceImpl();
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
        OrderService orderService = appConfig.orderService();
        
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);
        
        Order order = orderService.creatOrder(memberId, "itemA", 20000);
        
        System.out.println("order = " + order);
        System.out.println("order.caculatePrice = " + order.calculatePrice());
        
        System.out.println("discount : " + order.getDiscountPrice());
         */

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);
        
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.creatOrder(memberId, "itemA", 20000);

        System.out.println("order = " + order);
        System.out.println("order.caculatePrice = " + order.calculatePrice());
        
        System.out.println("discount : " + order.getDiscountPrice());
    }
}
