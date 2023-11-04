# Bean조회

getBean

AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

MemberService memberService = ac.getBean("memberService", MemberService.class);
assertThat(memberService).isInstanceOf(MemberServiceImpl.class);

MemberService memberService = ac.getBean(MemberService.class);
assertThat(memberService).isInstanceOf(MemberServiceImpl.class);

MemberService memberService = ac.getBean("memberService", MemberServiceImpl.class);
assertThat(memberService).isInstanceOf(MemberServiceImpl.class);

Assertions.assertThrows(NoSuchBeanDefinitionException.class, 
    () -> ac.getBean("XXXX", MemberService.class));

여러가지 유형으로 get할 수 있다.

### 동일한 타입이 둘 이상일 때
빈 이름을 지정.
AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
ac.getBeansOfType()을 사용하면 해당 타입의 모든 빈을 조회할 수 있다.

### 상속관계
스프링 빈 조회 시, 부모 타입으로 조회를 시작. 모든 자식(2층 3층 ...) 타입은 다 끌려온다. 대원칙

### BeanFactory
ApplicationContext가 제공하는 부가기능
- 메시지소스를 활용한 국제화 기능
    한국에서는 한국어로, 영어권에서는 영어로 출력된다.
- 환경변수
    prod, dev, local 환경 분리
- 애플리케이션 이벤트
    이벤트를 발생하고 구독하는 모델을 편리하게 지원
- 편리한 리로스
    파일, 클래스패스, 외부 등에서 리소스를 편리하게 조회

Boot 기반으로 XML을 사용하지 않는 추세,
컴파일 없이 빈 설정이 가능한 장점.

### 스프링 빈 설정 메타 정보 - BeanDefinition
Spring Contatiner는 Java인지 Xml인지 모른다
오직 BeanDefinition만 알고 진행한다

