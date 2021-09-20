package hello.core.scope;

import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

public class SingletonWithPrototypeTest1 {

    @Test
    void prototypeFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        Assertions.assertThat(prototypeBean1.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();

        Assertions.assertThat(prototypeBean2.getCount()).isEqualTo(1);
    }

    @Test
    void SingletonClientUsePrototype(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);
        ClientBean beanA = ac.getBean(ClientBean.class);
        int count = beanA.logic();
        Assertions.assertThat(count).isEqualTo(1);

        ClientBean beanB = ac.getBean(ClientBean.class);
        int count2 = beanB.logic();
        Assertions.assertThat(count2).isEqualTo(1);

    }

    @RequiredArgsConstructor
    static class ClientBean{
//        private final PrototypeBean prototypeBean; //생성시점에 주입
        private final Provider<PrototypeBean> prototypeBeanProvider;


        public int logic(){
            PrototypeBean prototypeBean = prototypeBeanProvider.get();
            prototypeBean.addCount();
            return prototypeBean.getCount();

        }
    }

    @Scope("prototype")
    static class PrototypeBean{
        private int count = 0;

        public void addCount(){
            count += 1;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init(){
            System.out.println("PrototypeBean.init"+this.count);
        }

        @PreDestroy
        public void destroy(){
            System.out.println("PrototypeBean.destroy"+this.count);
        }

    }
}
