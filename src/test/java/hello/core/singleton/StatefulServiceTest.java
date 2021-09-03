package hello.core.singleton;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class StatefulServiceTest {

    @Test
    @DisplayName("싱글톤 문제점")
    void statefulServiceSingleton(){
       ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService bean = ac.getBean(StatefulService.class);
        StatefulService bean2 = ac.getBean(StatefulService.class);

        //Thread A사용자가 10000원 주문
        bean.order("userA", 10000);

        //Thread B사용자가 20000원 주문
        bean2.order("userB", 20000);

        //Thread A가 주문금액을 조회
        int price = bean.getPrice();
        System.out.println("price = " + price);
    }

    static class TestConfig{

        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }
    }
}
