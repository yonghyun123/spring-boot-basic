package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

@Service
public class LogDemoService {
    public LogDemoService(ObjectProvider<MyLogger> myLoggerProvider) {
        this.myLoggerProvider = myLoggerProvider;
    }

    private final ObjectProvider<MyLogger> myLoggerProvider;

    public void logic(String id) {
        myLoggerProvider.getObject().log("service id=" + id);
    }
}
