package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
    // basePackages 하위 패키지 부터 찾는다. 이 설정을 안하면 모든 java library 전부 확인하기 때문에 느리다.
    // basePackages = "hello.core.member",
    // basePackages = {"hello.core.member", "hello.core.order"} , 여러개도 가능
    // 지정하지 않으면 default로 ComponentScan이 붙은 AutoAppConfig class의 package hello.core의 하위 패키지 부터 확인한다.
    excludeFilters=@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {
    // @Bean(name="memoryMemberRepository")
    // MemberRepository memberRepository() {
    //     return new MemoryMemberRepository();
    // }
}
