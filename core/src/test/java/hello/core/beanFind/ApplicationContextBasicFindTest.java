package hello.core.beanFind;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;

class ApplicationContextBasicFindTest {
    
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName() {
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        // System.out.println("memberService : " + memberService);
        // System.out.println("memberService.getClass() : " + memberService.getClass());
        // 검증은  Assertions.assertThat 로 !
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("이름없이 타입으로 조회")
    void findBeanByType() {
        MemberService memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("구체 타입으로 조회")
    void findBeanByName2() {
        MemberService memberService = ac.getBean("memberService", MemberServiceImpl.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
        // 이 코드는 좋지 않다 구체에 의존하는 것이기 때문에, (유연성이 떨어짐)
        // MemberService interface를 구체화한 MemberServiceImpl의 타입을 가져와서 테스트 한 것이기 때문이다.
    }

    @Test
    @DisplayName("빈 이름으로 조회 X")
    void findBeanByNameX() {
        // MemberService XXXX = ac.getBean("XXXX", MemberService.class);
        // 위 코드를 오류 코드 작성
        // org.springframework.beans.factory.NoSuchBeanDefinitionException 발생

        // TestCode 검증
        Assertions.assertThrows(NoSuchBeanDefinitionException.class, 
            () -> ac.getBean("XXXX", MemberService.class));
        // 위 코드는 람다식으로, ac.getBean()이 NoSuchBeanDefinitionException.class오류가
        // 발생하면 정상적인 테스트 성공이다. (오류를 예측하여 작성했기 때문에.)
    }

}
