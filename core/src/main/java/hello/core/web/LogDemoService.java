package hello.core.web;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LogDemoService {
    //ScopedProxyMode.TARGET_CLASS 사용
    private final MyLogger myLogger;
    // private final ObjectProvider<MyLogger> myLoggerProvider;

    public void logic (String id) {
        // MyLogger myLogger = myLoggerProvider.getObject();
        myLogger.log("service id = " + id);
    }

}
