# 김영환 기초

# SOLID
로버트 마틴이 좋은 객체 지향 설계의 5가지 원칙

SRP(Single Responsibility Principle) : 단일 책임 원칙
OCP(Open/Closed Principle) : 개방-폐쇄 원칙
LSP(Liskov Substitution Principle) : 리스코프 치환 원칙
ISP(Interface Segregation Principle) : 인터페이스 분리 원칙
DIP(Dependency Inversion Principle) : 의존관계 역전 원칙

### SRP 단일 책임 원칙
- 한 클래스는 하나의 책임만 가져야 한다.

### OCP 개방-폐쇄 원칙
- 소프트웨어 요소는 확장에는 열려 있으나 변경에는 닫혀 있어야 한다.(다형성)
- Spring PSA가 도와준다.

### LSP 리스코프 치환 원칙
- 프로그램의 객체는 프로그램의 정확성을 깨뜨리지 않으면서 하위 타입의 인스턴스로 바꿀 수 있어야 한다.
- 다형성에서 하위 클래스는 인터페이스 규약을 지켜야한다.

### ISP 인터페이스 분리 원칙
특정 클라이언트를 위한 인터페이스 여러개가 범용 인터페이스 하나보다 낫다.

### DIP 의존관계 역전 원칙
- 추상화에 의존해야지 구체화에 의존하면 안된다. 의존성 주입은 이 원칙을 따르는 방법 중 하나다.



# 객체 지향 설계와 스프링
스프링은 다형성, OCP, DIP를 가능하게 지원
    OCP(Open/Closed Principle) : 개방-폐쇄 원칙
    DIP(Dependency Inversion Principle) : 의존관계 역전 원칙
- DI
- DI Container 제공
코드의 변경 없이 기능 확장
OCP, DIP 원칙을 지키면서 개발하려다 보니, 할게 많아서 프레임워크로 만들어 버린것 이다.

Interface를 도입하면 추상화라는 비용이 발생한다.
확장 가능성이 없다면 구체 클래스를 바로 사용하고 필요할 때 리팩터링을 통해 인터페이스를 도입하는 것도 방법이다.


### DIP 위반
private final MemberRepository memberRepository = new MemoryMemberRepository();
추상화에도 의존하고 구체화에도 의존한다.
* DIP 위반  DiscountPolicy추상 인터페이스 FixDiscountPolicy, RateDiscountPolicy 구체 클래스
* OrderServiceImpl(주문)는 DiscountPolicy만 의존해야하지만 현재는 FixDiscountPolicy, RateDiscountPolicy까지 함께 의존하고 있다.
* FixDiscountPolicy에서 RateDiscountPolicy로 변경하는 순간 OrderServiceImpl도 코드를 수정(discountPolicy 인스턴스 변경)하게 되는 문제
private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
위 코드에서 아래코드로 변경하면서 DIP 문제 해결
private DiscountPolicy discountPolicy;

AppConfig.java에서 정책에 따른 인스턴스를 생성자를 통해 주입해준다.(생성자 주입)

