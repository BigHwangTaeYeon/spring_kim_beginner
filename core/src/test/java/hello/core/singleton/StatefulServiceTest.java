package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class StatefulServiceTest {
    
    @Test
    void StatefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // ThreadA : A 사용자 10000원 주문
        int a1 = statefulService1.order("userA", 10000);
        // ThreadB : B 사용자 20000원 주문
        int a2 = statefulService2.order("userB", 20000);
        // 10000원을 넣은 statefulService1의 price를 꺼넀는데 20000원이 나온다.
        // Singleton이기에 조심해야할 부분
        // int price = statefulService1.getPrice();
        System.out.println("price1 : " + a1);
        System.out.println("price2 : " + a2);

        Assertions.assertThat(a1).isEqualTo(10000);
        Assertions.assertThat(a2).isEqualTo(20000);
    }

    static class TestConfig {
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}
