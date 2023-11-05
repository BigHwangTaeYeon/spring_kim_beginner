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

@ComponentScan 으로 Bean 생성할 위치를 잡아주고, @Component가 붙은 모든 Class를 빈으로 등록한다.
설정과 @Autowired로 생성할 Bean을 잡아준다.

@SpringBootApplication
public class CoreApplication {
	public static void main(String[] args) {
		SpringApplication.run(CoreApplication.class, args);
	}
}
프로젝트 최초 생성 시, CoreApplication에서 @SpringBootApplication이것을 통해 @ComponentScan이 된다.
이미 boot가 전부 scan해서 bean으로 등록하기에 사실 이제는 따로 @ComponentScan을 할 필요가 없다.

컴포넌트 스캔 대상은
@Component
@Controller
@Service
@Repository
@Configuration 이 있다.

@ComponentScan(
    // includeFilters = @Filter(type=FilterType.ANNOTATION, classes = MyIncludeComponent.class) ,
    // excludeFilters = @Filter(type=FilterType.ANNOTATION, classes = MyExcludeComponent.class)
    includeFilters = @Filter(classes = MyIncludeComponent.class) ,
    excludeFilters = @Filter(classes = MyExcludeComponent.class)
    //type=FilterType.ANNOTATION default라 없어도 된다.
)
ComponentScan에 등록할 bean 과 사용하지 않는 것을 설정할 수 있다.

자동과 수동으로 등록한 빈이 중복으로 충돌나면,
수동으로 등록한 빈이 우선권을 가진다.
(수동 빈이 자동 빈을 오버라이딩 한다. console에 다 나옴)

@SpringBootApplication붙은 class에서 실행하면,
오류 메시지 중
setting spring.main.allow-bean-definition-overriding=true 이 나온다.

기본 overriding false로 되어있기에, properties에 위 코드를 작성해주면 된다.