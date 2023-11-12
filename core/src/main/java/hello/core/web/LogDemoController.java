package hello.core.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService logDemoService;
    // @Autowired @RequiredArgsConstructor으로 Autowired를 사용안해도된다.
    // MyLogger는 HTTP request, client 요청이 들어와야 scope가 생성되기 때문에
    // 서버 올리는 것 만으로 생성을 할 수 없기에 오류이다.
    private final MyLogger myLogger;
    // ScopedProxyMode.TARGET_CLASS를 사용함으로써, 위에 코드를 사용
    // private final ObjectProvider<MyLogger> myLoggerProvider;

    @RequestMapping("log-demo")
    @ResponseBody // web view 없이 문자 반환
    public String logDemo(HttpServletRequest request){
        String requestURL = request.getRequestURI().toString();
        // MyLogger myLogger = myLoggerProvider.getObject();

        System.out.println("myLogger :  " + myLogger.getClass());
        // myLogger :  class hello.core.common.MyLogger$$EnhancerBySpringCGLIB$$d28e3596
        // 진짜 myLogger가 아니다. 가짜 프록시 객체이다. (CGLIB 바이트코드를 조작하는 라이브러리)
        // 가짜 프록시 객체는 request 스코프의 진짜 myLogger.logic()을 호출한다.
        myLogger.setRequestURL(requestURL);

        myLogger.log("controller Test");
        logDemoService.logic("testId");
        return "OK";
    }

}
