package hello.core.beandefinition;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import hello.core.AppConfig;

public class BeanDefinitionTest {
    // AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
    GenericXmlApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");
    // AnnotationConfigApplicationContext와 GenericXmlApplicationContext는 다르다.
    // GenericXmlApplicationContext에서는 fatory method같은 것이 다 없음
    // 빈은, 직접 스프링 빈 등록(xml) 방법과 Factory Mehtod(AppConfig.java)를 사용하는 방법으로 나뉜다.
    @Test
    @DisplayName("빈 설정 메타 정보 확인")
    void findApplicatioinBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for(String beanDefinitionName : beanDefinitionNames){
            BeanDefinition beandefinition = ac.getBeanDefinition(beanDefinitionName);

            if(beandefinition.getRole() == BeanDefinition.ROLE_APPLICATION){
                System.out.println("beanDefinitionName : " + beanDefinitionName + 
                    " beanDefinition : " + beandefinition);
            }
        }
    }
}
