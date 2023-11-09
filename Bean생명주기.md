# Bean생명주기

DB Connection pool이나, 네트워크 소켓처럼 어플리케이션 시작 시점에 필요한 연결을 미리 해두고,
어플리케이션 종료 시점이 연결을 모두 종료하는 작업을 진행하려면 객체의 초기화와 종료 작업이 필요하다.

초기화 작업과 종료 작업

스프링 빈의 이벤트 라이프 사이클
스프링 컨테이너 생성 > 스프링 빈 생성 > 의존관계 주입 > 초기화 콜백 > 사용 > 소멸전 콜백 > 스프링 종료
    초기화 콜백 : 빈이 생성되고, 빈의 의존관계 주입이 완료된 후 호출
    소멸전 콜백 : 빈이 소멸되기 직전에 호출

객체 생성과 초기화를 분리하자.
생성자는 필수 정보(파라미터)를 받고 메모리를 할당하여 객체를 생성하는 책임을 가진다.
반면 초기화는 이렇게 생성된 값을 활용하여 외부 커넥션을 연결하는 등, 무거운 동작을 수행한다.
따라서 생성자 안에서 무거운 초기화 작업을 함께 하는 것 보다는 객체를 생성하는 부분과 초기화 부분을 명확히 나누는 것이 유지보수 관정에서 좋다.

### 빈 생명주기 콜백 3가지
1) 인터페이스(initializingBean, DisposableBean)
    1. implements InitializingBean
        - afterPropertiesSet() 의존관계 주입이 끝나면 호출
    2. implements DisposableBean
        - void destroy() 종료(disconnect()) 시 호출

    // 의존관계 주입이 끝나면 이라는 뜻이다 void afterPropertiesSet() throws Exception;
    @Override
    public void afterPropertiesSet() throws Exception {
        connect();
        call("초기화 연결 메시지");
    }

    // 종료 void destroy() throws Exception
    @Override
    public void destroy() throws Exception {
        disconnect();
    }

    이 인터페이스는 Spring에서 지원하기에 너무 의존적이다는 단점이 있다.
    외부 라이브러리에 적용할 수 없다.

2) 설정 정보에 초기화 메서드, 종료 메서드 지정
    @Bean(initMethod = "init", destroyMethod = "close")

    class NetworkClient
    // 의존관계 주입이 끝나면 이라는 뜻이다 void afterPropertiesSet() throws Exception;
    public void init()  {
        connect();
        call("초기화 연결 메시지");
    }
    // 종료 void destroy() throws Exception
    public void close() throws Exception {
        disconnect();
    }

    class BeanLifecycleTest
    @Configuration
    static class lifecycleConfig {
        @Bean(initMethod = "init", destroyMethod = "close")
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello.com");
            return networkClient;
        }
    }

    ** 코드를 고칠 수 없는 외부 라이브러리에도 초기화, 종료 메서드를 적용할 수 있다. **
    Bean 등록시에만 호출된다.

3) @PostConstruct, @PreDestroy 어노테이션 지원
    이방법을 사용하면 된다.
    
    @PostConstruct
    public void init()  {
        connect();
        call("초기화 연결 메시지");
    }

    @PreDestroy
    public void close() throws Exception {
        disconnect();
    }

    권장사항, java interface의 모음, Spring의 종속적이지 않다.
    ** 외부 라이브러리에 적용하지 못한다. **

