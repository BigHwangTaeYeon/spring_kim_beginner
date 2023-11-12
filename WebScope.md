# WebScope

웹 스코프
웹 환경에서만 동작한다.
프로토타입과는 다르게 종료시점까지 관리한다.

request : HTTP 요청마다 별도의 빈 인스턴스가 생성되고 관리된다.
session : HTTP Session과 동일한 생명주기
applicatioin : ServletContext와 동일한 생명주기
websocket : 웹 소켓과 동일한 생명주기

request scope
    [UUID][requestURL]{message}

myLogger.setRequestURL(requestURL);
requestURL을 MyLogger에 저장하는 부분은, 컨트롤러보다는 공통 처리가 가능한
인터셉터나 서블릿 필터같은 곳에서 활용하는 것이 좋다.

스코프와 Provider

