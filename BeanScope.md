# BeanScope

빈 스코프란 ?
스프링 컨테이너가 생성될 때, 빈들도 생명주기가 시작된다.
왜냐하면, 스프링 빈이 싱글톤 스코프로 생성되기 때문이다.

싱글톤 스코프는 스프링 컨테이너와 같은 생명주기를 갖는다.
    - 싱글톤 스코프 빈을 스프링 컨테이너에 요청하면 항상 같은 인스턴스를 반환한다.
프로토타입 스코프는 빈 생성과 의존관계 주입까지만 관여하고 더이상 관여하지 않기 때문에 destroy에 호출이 되지 않는다.
    - 핵심은 스프링 컨테이너는 프로토타입 빈을 생성하고, 의존관계 주입, 초기화까지만 처리한다.
웹 관련 스코프
    - request : 클라이언트 요청이 들어오고 나갈 때까지 유지되는 스코프
    - session : session이 생성되고 종료될 때까지 유지되는 스코프
    - application : 웹의 서블릿 컨텍스와 같은 범위로 유지되는 스코프


프로토타입은 소멸에 관여하지 않기에 필요시에 직접 호출을 하면 된다.
    PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
    prototypeBean1.destroy();




의존관계 주입이 아닌 직접 필요한 의존관계를 찾는 것을 Dependency Lookup(DL) 의존관계 조회(탐색)이라한다.
그런데 어플리케이션 컨텍스트 전체를 주입받게 되면, 스프링 컨테이너에 종속적인 코드가 되고
단위테스트도 어려워진다.
지금 필요한 기능은 지정한 프로토타입 빈을 컨테이너에서 대신 찾아주는 딱 DL 정도의 기능을 제공하는 무언가가 있으면 된다.
스프링은 모든게 준비 되어있다.

ObjectFactory, ObjectProvide
ObjectFactory는 하나만 제공하고, ObjectProvide가 편의기능을 몇가지 더 제공하여 편리하다.

@Autowired
private ObjectProvider<PrototypeBean> prototypeBeanProvider;

public int logic() {
PrototypeBean prototypeBean = prototypeBeanProvider.getObject();

javax.inject Provider 사용
    provider libarary를 넣어줘야한다.(implementation 'javax.inject:javax.inject:1')
    자바 표준이므로 스프링이 아닌 다른 컨테이너에서도 사용할 수 있다.

@Autowired
private Provider<PrototypeBean> prototypeBeanProvider;

public int logic() {
PrototypeBean prototypeBean = prototypeBeanProvider.get();

사실 singletonBean으로 모든게 해결되고 프로토타입을 직접 사용할 일은 드물다.
개념을 알고 있어야 하되, 사용할 일은 없을 것이다.

만약 spring이 아닌 다른 컨테이너에서 사용해야 한다면 provider를 사용해야 한다.


































