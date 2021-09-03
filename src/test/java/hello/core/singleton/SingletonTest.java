package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingletonTest {

    @Test
    @DisplayName("스프링없는 DI컨테이너")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
        MemberService memberService2 = appConfig.memberService();
        System.out.println("memberService = " + memberService);
        System.out.println("memberService2 = " + memberService2);

        //memberService != memberService2
        Assertions.assertThat(memberService).isNotEqualTo(memberService2);

    }

    @Test
    @DisplayName("싱글톤 패턴을 사용한 객체 사용")
    void singletonServiceTest() {
        SingletonService singletonService = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        System.out.println("singletonService = " + singletonService);
        System.out.println("singletonService2 = " + singletonService2);

        Assertions.assertThat(singletonService).isSameAs(singletonService2);
        //same == 인스턴스 참조값 비교
        //equal 값비교
    }

    @Test
    @DisplayName("스프링컨테이너와 싱글톤")
    void springContainer(){

        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        Assertions.assertThat(memberService).isSameAs(memberService1);



        //same == 인스턴스 참조값 비교
        //equal 값비교
    }
}
