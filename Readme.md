# 객체 지향 프로그래밍

- OOP는 프로그램을 명령어의 목록으로 보는 시각에서 벗어나 여러개의 독립된 단위, 즉 '객체'들의 모임으로 파악하고자 한다. 각각의 객체는 메시지를 주고받고, 데이터를 처리한다.
- OOP는 프로그램을 유연하고 변경이 용이하기 만들기 때문에 대규모 개발에 사용

## 다형성의 실세계 비유

- 실세계와 객체 지향을 1:1로 매칭하기 어려움
- 그래도 실세계의 비유로 이해해야함
- 역할과 구현으로 세상을 구분



운전자는 자동차의 역할만 알고있음(면허증만 있으면 어떤 자동차를 탈 수 있음)

## 역할과 구현을 분리

- 역할과 구현으로 구분하면 세상이 단순해지고, 유연해지며 변경도 용이

> 클라이언트는 대상의 역할(인터페이스)만 알면됨
>
> 클라이언트는 구현 대상의 내부 구조를 몰라도 됨
>
> 클라이언트는 내부 구조가 변경되어도 영향받지 않음
>
> 클라이언트는 구현 대상 자체를 변경해도 영향받지 않음



## 다형성의 본질

- 인터페이스를 구현한 객체 인스턴스를 실행 시점에 유연하게 변경 가능
- 다형성의 본질을 이해하려면 협력이라는 객체관계에서 시작
- 클라이언트를 변경하지 않고, 서버의 구현 기능을 유현하게 변경

> > 인터페이스가 변경이되면 서버에 큰 영향,
> >
> > 인터페이스를 안정적으로 잘 설계하는것이 중요



## 스프링과 객체지향

- 다형성이 가장 중요
- 스프링은 다형성을 극대화해서 이용 가능하게 도와줌
- 스프링에서 IoC, DI는 다형성을 활용해서 역할과 구현을 편리하게 다룰 수 있도록 지원
- 스프링을 사용하면 레고 블록 조립하듯이 배우를 선택하듯이 구현을 편리하게 변경 가능



## 좋은 객체 지향의 설계의 5가지 

> 클린코드로 유명한 로버트 마틴이 좋은 객체지향설계의 5가지 원칙을 정리

- SRP: (Single responsibility princile)
- OCP: (Open/closed principle)
- LSP:(Liskov substitution principle)
- ISP: (Interface segregation principle)
- DIP: (Dependency inversion principle)

###  SRP

- 한 클래스는 하나의 책임만 가진다.

- 하나의 책임은 모호함(클수도 있고, 작을 수 있음, 문맥과 상황에 따라 다르다)

  Ex) UI를 변경할때 서버까지 변경이 크면(원칙을 잘 따르지 못한것)

- 중요한 기준은 변경이다, 변경이 있을 때, 파급효과가 적으면 단일 책임 원칙을 잘 따른것



### OCP

- 소프트웨어 요스는 확장에는 열려있으나, 변경에는 닫혀 있어야 한다.
- 다형성을 활용해야함
- MemoryMemberRepository, JDBCMemberRepository를 생각해보면 이해가 됨

- MemberServcie 클라이언트가 구현 클래스를 직접 선택
  - MemberRepository m = new MemoryMemberRepository()//기존
  - MemberRepository m = new JdbcMemberRepository()//새로운 코드
- OCP원칙이 깨짐(다형성을 사용했지만,)
- 설정자가 필요(연관관계를 맺어주는 별도의 조립 ex) Spring container



### LSP 리스코프 치환 원칙

- 프로그램의 객체는 프로그램의 정확성을 깨트리지 않으면 하위 타입의 인스턴스로 바꿀 수 있음
- 다형성에서 하위 클래스는 인터페이스 규약을 다 지켜야한다
- ex) 인터페이스의 exel 규약을 구현체에서 뒤로가기로 개발해서는 안됨



### ISP 인터페이스 분리 원칙

- 인터페이스를 여러개의 범용 인터페이스로 구현
- 분리하면 인터페이스가 명확해지고, 변경에도 용이



### DIP

- 프로그래머는 추상화에 의존해야지, 구체화에 의존하면 안된다. 의존성 주입은 이 원칙을 따르는 방법 중 하나
- 구현 클래스에 의존하지 말고 인터페이스에 의존해야함
- 역할에 의존하게 해야함(차를 예로 자동차 인터페이스에 의존해야하고, 구현(k5, 아반떼)에 의존하지 않아야함)

- MemberRepository m = new MemoryMemberRepository()
- 위의 코드는 DIP 위반!!!!!(구현체에도 의존하기 때문)



### 정리

- 객체 지향의 핵심은 다형성
- 다형성 만으로는 쉽게 부품끼우듯이 개발하기 힘듦
- 다형성 만으로는 구현 객체를 변경할 때, 클라이언트 코드 변경이 필요
- 다형성 만으로는 OCP, DIP를 지키기 어려움



# 객체지향 설계와 스프링

- 스프링은 DI, DI컨테이너로 OCP, DIP를 가능하게 함
- 클라이언트 코드의 변경 없이 기능 확장
- 공연을 설계하듯이 배역만 만들어두고 배우는 언제든지 변경할 수 있도록 만드는것



### 비즈니스 요구사항과 설계

- 회원
  - 회원을 가입하고 조회할 수 있다.
  - 회원은 일반과 VIP 두가지가 있다.
  - 회원 데이터는 자체 DB를 구축할 수 있고, 외부 시스템을 연동할 수 있다
- 주문과 할인 정책
  - 회원은 상품을 주문 가능
  - 회원 등급에 따라 할인 정책을 적용할 수 있다.
  - 할인 정책은 모든 VIP는 1000원 할인해주는 고정 금액할인을 적용(변경가능)
  - 최악의 경우 할인을 적용하지 않을 수 있음



동시성의 문제로 ConcurrentHashMap사용 필요(찾아보기)

### 새로운 할인 정책 적용과 문제점

할인 정책을 변경하려면 클라이언트인 orderServiceImpl코드를 수정해야함

```java
public class OrderServiceImpl implements OrderService {
    private final MemberRepository memberRepository= new MemoryMemberRepository();
//    private final DiscountPolicy discountPolicy = new FixDisoucntPolicy();
    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
}
```

'문제점 발견'

- 역할과 구현을 충실하게 분리 -> ok
- 다형성도 활용하고, 인터페이스와 구현 객체를 분리 -> ok
- OCP, DIP 같은 객체지향 설계 원칙을 충실히 준수
  - 그렇게 보이지만 사실은 아니다
- DIP:주문서비스 클라이언트는 DiscountPolicy인터페이스에 의존하면서 DIPf를 지킨 것 같은데?
  - 클래스 의존관계 분석을 하면 인터페이스 뿐만 아니라 구현클래스에도 의존
- OCP: 변경하지 않고 확장할 수 있다고 했는데?
  - 현재는 클라이언트 코드에 영향을 준다 -> OCP위반

'해결방안'

- 이 문제를 해결하려면 누군가가 클라이언트인 OrderServiceImpl에 DiscountPolisy의 구현객체를 대신 생성 및 주입해주어야 한다.

### AppConfig 등장

- 애플리케이션의 전체 동작 방식을 구성하기위해, '구현 객체를 생성'하고 '연결'하는 책임을 가지는 별도의 설정 클래스를 만들자

- AppConfig는 애플리케이션의 실제 동작에 필요한 '구현 객체를 생성'한다.
- AppConfig는 생성한 객체 인스턴스의 참조를 '생성자를 통해서 주입(연결)'해준다.
  - MemberServiceImpl -> MemoryMemeberRepository
  - OrderServiceImpl ->MemoryMemeberRepository, FixDiscountPolicy



MemberServiceImpl '생성자 주입'

- MemberServiceImpl은 MemoryMemberRepository를 의존하지 않음
- 단지 MemberRepository인터페이스만 의존
- MemberServiceImpl은 생성자를 통해서 어떤 구현객체를 주입할지는 오직 외부에서만 결정(AppConfig)

### 관심사의 분리

- 객체를 생성하고 연결하는 역할과 실행하는 역할이 명확히 분리되었다.
- 클라이언트인 MemberServiceImpl 입장에서 보면 의존관계를 마치 외부에서 주입해준것 같다고 해서 'DI(Dependency Injection) 의존관계 주입'이라고 한다.

> 정리
>
> - AppConfig를 통해서 관심사를 분리
> - 배역,배우를 생각해보면 AppConfig는 기획자
> - AppConfig는 구체 클래스를 선택한다.
> - 배역에 맞는 배우를 선택하게 함



### AppConfig

- ''구성영역'인 AppConfig는 사용영역의 역할과 구현을 알고 있다.
- 기존의 클라이언트 코드의 변경 없이 구현체를 바꿀 수 있다(OCP, DIP만족)



# IoC, DI, 컨테이너



### 제어의 역전 IoC(Inversion of Control)

- 기존 프로그램은 클라이언트 구현 객체가 스스로 필요한 서버 구현 객체를 생성하고, 연결하고, 실행한다.
- 반면에 AppConfig가 등장한 이후에 구현 객체는 자신의 로직을 실행하는 역할만 담당 ex) OrderServiceImpl은 인터페이스를 호출하지만 어떤 구현체인지는 모름
- 프로그램 제어 흐름에 대한 권한을 AppConfig가 가짐
- 이렇듯 프로그램의 제어 흐름을 직접 제어하는 것이 아니라 외부에서 관리하는것을 IoC라 한다.



### 프레임워크 vs 라이브러리

- 프레임워크가 내가 작성한 코드를 제어하고 대신 실행하면 프레임워크가 됨(JUnit) 프레임워크가 라이프사이클에 의해 내 소스를 실행가능함
- 반면에 내가 작성한 코드가 직접 제어의 흐름을 담당하면 라이브러리



### 의존관계 주입 DI

- OrderServiceImpl은 DiscountPolicy 인터페이스에만 의존
- 의존관계는 '정적인 클래스 의존관계와 실행 시점에 결정되는 동적인 인스턴스 의존 관계'들을 분리해야 한다.

> '정적인 클래스 의존관계'
>
> - Import 코드만 보고 의존관계를 판단. 
> - 정적인 의존관계는 애플리케이션 실행 전 판단가능하다.
> - 하지만 클래스 의존관계로는 어떤 구현체가 들어올지는 분석이 불가능하다.



> '동적인 객체 인스턴스 의존 관계'
>
> - 런타임시에 외부에서 실제 구현 객체를 생성하고 클라이언트에 전달해서 클라이언트와 서버의 실제 의존관계과 연결 되는 것을 '의존관계 주입(DI)이라 한다.'
> - 객체 인스턴스를 생성하고, 그 참조값을 전달해서 연결된다.
> - 의존관계(DI) 주입하면 클라이언트가 호출하는 대상의 타입 인스턴스를 변경 할 수 있다(RateDiscountPolicy)
> - 의존관계 주입을 사용하면 정적인 클래스 의존관계를 변경하지 않고, 동적인 객체 인스턴스 의존관계를 쉽게 변경 할 수 있다.



### IoC 컨테이너, DI 컨테이너

- AppConfig처럼 객체를 생성하고 의존관계를 연결해주는것
- 의존관계 주입에 추점을 맞추어 최근에는 주로 DI 컨테이너라 한다.



### 스프링 컨테이너

- ApplicationContext를 스프링컨테이너라 한다.
- 기존에는 개발자가 AppConfig를 사용해서 직접 객체를 생성하고 DI를 했지만, 이젠 스프링 컨테이너를 통해서 사용한다.
- 스프링 컨테이너는 @Configuration이 붙은 AppConfig를 구성정보로 사용. 여기서 @Bean이라 적힌 메서드를 모두 호출해서 반환된 객체를 스프링 컨테이너에 등록한다. 이렇게 등록된 객체를 '스프링 빈'이라고 함

- 스프링 컨테이너를 사용하면 어떤 장점이 있을까? -> 이걸 배우는과정

# 스프링 컨테이너와 스프링 빈

```java
ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class)
```

> 참고: 정확히는 스프링 컨테이너를 부를때 BeanFactory, AppicationContext로 구분해서 이야기한다. BeanFactory를 직접사용하는 경우는 거의 없으므로 일반적으로 ApplicationContext를 스프링컨테이너라 한다.



![스크린샷 2021-08-29 오후 3.35.15](/Users/yonghyun/Documents/스크린샷 2021-08-29 오후 3.35.15.png)

빈이름: memberService 빈 객체: MemberServiceImpl

빈이름: orderService.      빈 객체: OrderServiceImpl 

등등..

> ''빈 이름은 항상 다른 이름을 부여''해야함 다른빈이 무시되거나 기존 빈이 덮어버리는 오류가 생김



- 스프링 빈 의존관계 설정

memberService 는 memberRepository를 의존

orderService는 memberRepository와 discountPolicy를 의존

-> 스프링은 빈을 생성하고, 의존관계를 주입하는 단계가 나누어져 있다.



### 컨테이너에 등록된 모든 빈 조회



### 스프링 빈 조회 - 상속관계

- 부모 타입으로 조회하몀ㄴ, 자식 타입도 함께 조회된다.
- 모든 자바의 객체는 최고 부모인 'object' 타입으로 조회하면, 모든 스프링 빈을 조회한다.



### BeanFactory와 ApplicationContext



<img width="349" alt="beanFactory" src="https://user-images.githubusercontent.com/15208005/131524252-91e7ca54-a4e0-43f6-90e1-c8e0f1d68152.png">

'Beanfactory'

- 스프링 컨테이너의 최상위 인터페이스이다
- 스프링 빈을 관리하고 조회하는 역할을 담당한다.
- 대부분의 기능이 BeanFactory가 제공하는 기능이다.



'ApplicationContext'

- BeanFactory 기능을 모두 상속받아서 제공
- 애플리케이션을 개발할 때는 빈을 관리하고 조회하는 기능뿐만 아니라 수 많은 부가기능이 필요하다.



- 메시지소스를 활용한 국제화기능
  - 한국에서 들어오면 한국어로, 영어권에서 들어오면 영어로 출력
- 환경변수
  - 로컬,개발,운영을 구분해서 처리
- 어플리케이션 이벤트
  - 이벤트를 발생하고 구독하는 모델을 편리하게 지원
- 편리한 리소스 조회
  - 파일,클래스패스,외부 등에서 리소스를 편리하게 조회



### 스프링 빈 설정 메타 정보 - BeanDefinition

- 스프링은 어떻게 이런 다양한 설정 형식을 지원하는것일까?
- 그중심에는 BeanDefinition이라는 추상화가 있다.
- 쉽게이야기해서 '역할과 구현을 개념적으로 나눈것'
  - xml을 읽어서 beanDefinition을 만든다.
  - 자바코드를 읽어서 beanDefinition을 만든다.
  - 스프링컨테이너는 자바인지, xml인지 몰라도 BeanDefinition만 알면 된다.
- BeanDefinition을 빈 설정 메타정보라 한다.
- 스프링 컨테이너는 이 메타정보를 기반으로 스프링 빈을 생성한다.



!!![스크린샷 2021-09-01 오전 12.20.07](/Users/yonghyun/Desktop/스크린샷 2021-09-01 오전 12.20.07.png)

- AnnotationConfigApplicationContext는 AnnotatedBeanDefinitionReader를 이용해서 AppConfig.class를 읽고 BeanDefinition을 생성한다.

- GenericXmlApplicationContext는 XmlBeanDefinitionReaderf를 이용해서 appConfig.xml을 읽고 BeanDefinition을 생성한다.

- BeanDefinition 살펴보기

  > BeanClassName:생성할 빈의 클래스 명
  >
  > factoryBeanName: 팩토리 역할의 빈을 사용할 경우 이름
  >
  > factoryMethodName: 빈을 생성할 팩토리 메서드 지정 (memberService)
  >
  > Scope: 싱글톤
  >
  > lazyInit: 스프링 컨테이너를 생성할 때 빈을 생성하는 것이 아니라 실제 빈을 사용할 때 까지 최대한 생성을 지연처리 하는지 여부

'정리'

- BeanDefinition은 스프링이 다양한 형태의 설정 정보를 BeanDefinition으로 추상화해서 사용하는 것이다.

### BeanDefition이 java Config파일을 읽어드릴때, factoryMethod방법을 이용한다..



# 싱글톤 컨테이너



### 웹 어플리케이션과 싱글톤

- 스프링은 태생이 기업을 온라인 서비스 기술을 지원하기 위해 탄생
- 대부분의 스프링 어플리케이션은 웹 어플리케이션
- 웹은 여러 고객이 동시에 요청한다.

> 스프링없는 순수한 DI컨테이너인 AppConfig는 요청할 때마다 객체를 생성한다.
>
> 고객 트랙픽이 초당 100이 나옴ㄴ 초당 100개의 객체가 생상되고 소멸된다.
>
> 해결방안은 객체가 딱 1개만 생성되고 공유하도록 설계해야한다.



### 싱글톤 패턴

- 클래스의 인스턴스가 딱 1개만 생성되는 것을 보장하는 디자인 패턴
- 그래서 객체 인스턴스를 2개 이상 생성하지 못하도록 막아야 한다.
  - Private 생성자를 생성해서 외부에서 임의로 new 키워드를 막는다.



```java
public class SingletonService {

    //static 선언은 클래스 레벨로 올라가기 때문에 하나만 생성됨
    private static final SingletonService instance = new SingletonService();

    //private 생성자
    private SingletonService(){

    }

    private static SingletonService getInstance(){
        return instance;
    }
    
}
```

- Static 영역에 객체 instance를 하나 미리 생성해서 올려둔다.
- 객체 인스턴스가 필요하면 getInstance()메서드를 통해서만 조회할 수 있다. 이 메서드를 호출하면 항상 같은 인스턴스를 반환
- Private 생성자로 new 키워드로 객체 인스턴스 생성을 막는다.

- 호출할 때 마다 같은 객체 인스턴스를 반환하는 것을 확인가능함.
- 위의 방법은 객체를 미리 생성해두는 가장 단순하고 안전한 방법을 선택한다.

- 싱글톤 패턴을 적용하면 고객의 요청이 올 때 마다 이미 만들어진 객체를 반환

> 싱글톤 패턴 문제점
>
> - 패턴을 구현하는 코드가 많이 들어감
>
> - 테스트가 어렵다.
>
> - 내부 속성을 변경하거나 초기화 하기 어렵다.
>
> - private 생성자로 자식 클래스를 만들기 어렵다.
>
> - 유연성이 떨어진다.
>
> - 안티패턴으로 불리기도 한다.
>
>   

<img width="608" alt="singletonContainer" src="https://user-images.githubusercontent.com/15208005/131948997-4d672959-afdc-4668-af20-7977296f85ea.png">



스프링 컨테이너는  빈 이름으로 빈 객체를 관리한다.

스프링컨테이너의 기능 덕분에 싱글톤 패턴의 단점을 해결하면서 객체를 싱글톤으로 유지할 수 있다. 

- 싱글톤 패턴을 위한 불필요한 코드가 들어가지 않는다.
- DIP,OCP,테스트로부터 자유로울 수 있다.

싱글톤을 사용하지 않는 케이스

- http 세션에 라이프사이클에 맞춘 객체를 생성해야하는 경우
- 그 외의 대부분은 99% 싱글톤 방식의 객체생성을 선택한다.



## 싱글톤 방식의 주의점

- 싱글톤 패턴이든, 싱글톤 컨테이너를 사용하던, 인스턴스를 하나만생성해서 공유하기 때문에, 상태를 유지(stateful)하게 설계하면 안된다.
  - 특정 클라이언트에 의존적인 필드가 있으면 안된다.
  - 특정 클라이언트가 값을 변경할 수 있는 필드가 있으면 안된다.
  - 자바에서 공유되지 않는, 지역변수 파라미터, ThreadLocal등을 사용해야한다.



```java
public class StatefulService {

    private int price; //상태를 유지하는 필드

    public void order(String name, int price){
        System.out.println("name = " + name + " price = " + price);
        this.price = price;
    }

    public int getPrice(){
        return price;
    }
}
```



```java
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
```



- 실제로 쓰레드를 사용하진 않았다.
- ThreadA가 사용자 A코드를 호출하고, ThreadB가 사용자 코드 B를 호출한다 가정
- StatefulService의 price필든느 공유되는 필드인데, 특정 클라이언트가 값을 변경
- 사용자A의 주문금액은 10000원인데, 20000원의 결과가 나왔다.



### @Configuration과 싱글톤

```java

@Configuration
public class AppConfig {

    // @Bean인데 memberService -> new MemoryMemeberRepository 호출하게 됨
    // @Bean orderService -> new MemoryMemeberRepository 호출하게 됨

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService(){
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy discountPolicy(){
        return new RateDiscountPolicy();
    }
}

```



- 위의 소스를 보면 MemoryMemberRepository가 두번 생성되면서 싱글톤이 깨진것 처럼 보이게 된다.



순수한 클래스라면 

 class hello.core.AppConfig라고 출력이 되어야한다.

AppConfig를 상속한 AppConfig@CGLIB라는 객체가 등록된다.

스프링 컨터이너가 바이트코드 조작 라이브러리를 사용해서 이 클래스를 스프링빈으로 등록한 것.

### AppConfig@CGLIB 예상코드

```java
@Bean
public MemberRepository memberRepository(){
  if(memoryMemberRepository가 이미 스프링 컨테이너에 등록되어 있으면?){
    return 스프링 컨테이너에서 찾아서 반환
  } else {
    기존 로직을 호출해서 MemoryMemeberRepository를 생성하고 스프링 컨테이너에 등록 
     return 반환
  }
}
```

- @Bean이 붙은 메서드마다 이미 스프링 빈이 존재하면 존재하는 빈을 반환하고, 없으면 생성해서 스프링 빈으로 등록하고 반환하는 코드가 동적으로 만들어진다.
- 이런 구조를 통해 싱글톤이 보장된다.



@Configuration을 적용하지않고, @Bean만 적용하면 어떻게 될까?

- 싱글톤 구조가 이뤄지지 않는다.
- AppConfigCGLIB@이 만들어지지 않음



# 컴포넌트 스캔



### 컴포넌트 스캔과 의존관계 자동 주입

- 지금까지 스프링 빈을 등록할 때는 자바 코드의 @Bean이나 XML의 <bean> 등을 통해서 설정 정보에 직접 등록할 스프링 빈을 나열
- 예제에는 몇개 안했지만, 이렇게 등록해야할 빈들이 많아지면 설정정보가 커지고, 누락하는 문제 발생
- 스프링은 설정 정보가 없어도 자동으로 스프링 빈을 등록하는 컴포넌트 스캔이라는 기능 제공
- 또 의존관계 주입하는 @Autowired 기능 제공



```java
@Configuration
@ComponentScan(
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
        

)
public class AutoAppConfig {

}
```

- 컴포넌트 스캔을 사용하려면 먼저 @ComponentScan을 설정에 추가
- 기존의 AppConfig와는 다르게 @Bean으로 등록한 클래스가 없음

> 컴포넌트 스캔은 이름 그대로 @Component 어노테이션이 붙은 클래스를 스캔해서 스프링 빈으로 등록한다.
>
> 참고: @Component를 등록하게 되면 스프링 빈을 등록하게 되지만 의존관계를 연결할 수 있는 방법이 없음. 그렇기 때문에 생성자에 @Autowired 가 필요하게 됨



- 이전에 AppConfig는 @Bean으로 직접 설정정보를 작성했고, 의존관계도 명시했었다.

<img width="635" alt="스크린샷 2021-09-06 오후 10 20 59" src="https://user-images.githubusercontent.com/15208005/132223741-ef4d0dbd-a7b9-4824-8e76-b9c73355173f.png">



- 스프링 빈의 기본 이름은 클래스명을 사용하되 맨 앞글자만 소문자로 사용한다.
  - MemberServiceImpl -> memberServiceImpl



### 탐색 위치와 기본 스캔 대상

- basePackages: 탐색할 컴포넌트스캔 대상의 시작위치 이 패키지를 포함해서 하위 패키지를 모두 탐색한다.
- basePackageClasses:  지정한 클래스의 패키지를 탐색 위치로 지정
- 지정하지 않으면: @ComponentScan이 붙은 설정 정보 클래스의 패키지가 시작위치가 된다.



예를들어

- com.hello
- com.hello.service
- com.hello.repository



com.hello -> 프로젝트의 시작 루트, 여기에 AppConfig같은 메인정보를 두고 @ComponentScan애노테이션을 붙이고 basePackages는 생략



### 컴포넌트 스캔 기본 대상

- @Component
- @Controller
- @Service
- @Repository
- @Configuration

> 참고: 사실 애노테이션에는 상속관계가 없다. 이렇게 애노테이션이 특정 애노테이션을 들고 있는것을 인식할 수 있는것은 자바가 지원하는게 아니라, 스프링이 지원하는 기능이다.

 

```java
 @ComponentScan(
            includeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyIncludeComponent.class),
            excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class)
    )
```

- includeFilters에 MyIncludeComponent를 추가해서 BeanA가 스프링 빈에 등록된다.
- excludeFilters에 MyExcludeComponent를 추가해서 BeanB는 스프링 빈에 등록되지 않는다.



### 중복 등록과 충돌

- 자동 빈 등록 vs 자동 빈 등록
- 수동 빈 등록 vs 자동 빈 등록

>  수동 빈 등록, 자동 빈 등록 오류시 스프링 부트 에러



### 다양한 의존관계 주입 방법

- 생성자 주입
- Setter 주입
- 필드 주입
- 일반 메서드 주입



### 생성자 주입

- 이름 그대로 생성자를 통해서 의존 관계를 주입 받는 방법이다.
- 특징
  - 생성자 호출시점에 1번 호출되는 것이 보장된다.
  - '불변', '필수' 의존관계에 사용
- 생성자가 한개만 있으면 @Autowired 어노테이션을 없애도 된다. 자동으로 의존관계 주입이 일어나게 됨



### 수정자 주입(Setter 주입)

- '선택','변경' 가능성이 있는 의존관계에 사용

- 사용할 일이 많이 없음

- > @Autowired의 기본 동작은 동작할 대상이 없으면 오류 발생 주입할 대상이 없어도 동작하게 하려면 @Autowired(required = false)로 지정

> 자바빈 프로퍼티란 getter, setter naming convention



### 필드주입

특징

- 코드가 간결해서 사용하고 싶지만 외부에서 변경이 불가능해서 테스트하기 힘들다는 치명적인 단점이 있다.
- DI 프레임워크가 없으면 아무것도 할 수 없다.
- 만약 사용한다면 테스트코드에서 쉽게 사용 가능



### 일반메서드 주입

- 수정자(Setter)주입과 동일하게 동작
- 일반적으로 사용하지 않는다.

> 의존관계 자동 주입은 스프링 컨테이너가 관리하는 스프링 빈이어야 동작한다. 스프링 빈이 아닌 Member 같은 클래스에서 @Autowired를 적용해도 동작하지 않는다.



## 옵션 처리

@Autowired 만 사용하면 required 기본값이 true이기에 영향이 없는 자바빈은 에러가 난다



```java
package hello.core.autowired;

public class AutowiredTest {

    @Test
    void autowiredOption(){

        ApplicationContext ac = new AnnotationConfigApplicationContext(testBean.class);


    }

    static class testBean{

        @Autowired(required = true)
        public void setNoBean1(Member noBean1) {
            System.out.println("noBean1 = " + noBean1);
        }

        @Autowired
        public void setNoBean2(@Nullable Member noBean1) {
            System.out.println("noBean1 = " + noBean1);
        }

        @Autowired
        public void setNoBean3(Optional<Member> noBean1) {
            System.out.println("noBean1 = " + noBean1);
        }
    }
}

```

> 첫번째 예제는 에러가 나는 상황



### 생성자 주입을 선택해라

"불변"

- 대부분의 의존관계 주입은 한번 일어나면 애플리케이션 종료시점까지 의존관계를 변경 할 일이 없다.
- setter주입을 사용하면  set메서드를 public으로 열어두어야한다.
- 변경하면 안되는 메서드를 열어두는 것을 좋은 설계 방법이 아니다.
- 생성자 주입은 객체를 생성할 때 딱 1번만 호출되므로 이후에 호출되는 일이 없다.

"누락"

- 프레임워크 없이 순수 자바코드를 단위 테스트 하는 경우에 직관적으로 어떤 의존관계가 생성되는지 파악
- 런타임단계가 아니라 컴파일단계에서 에러확인 가능



"정리"

- 생성자 주입 방식을 선택하는 이유는 여러가지가 있지만, 프레임워크에 의존하지 않고, 순수한 자바 언어의 특징을 잘살리는 방법이기도 하다.
- 기본으로 생성자 주입을 사용하고, 필수값이 아닌 경우엔 수정자 주입을 사용할 수 있다.



### lombok 설치

- preference에서 plugin에서 lombok 설치
- preference에서 annotation processors 활성(compiler하위)



@RequiredArgsConstructor를 이용해 생성자 소스를 대체한다.



# 중복등록된 케이스 해결



### @Autowired 필드 명, @Quilifier, @Primary



조회 대상 빈이 2개 이상일때 해결방법

- @Autowired 필드명 매칭
- @Qualifier 끼리 매칭
- @Primary 사용



```java
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }
```



```java
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy rateDiscountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = rateDiscountPolicy;
    }
```



필드명이 rateDiscountPolicy이므로 정상주입된다.

`필드명 매칭은 먼저 타입 매칭 시도 후 그 결과에 여러 빈이 있을 때, 추가로 동작하는 기능이다'

> 타입매칭 -> 결과가 2개 이상이면, 필드명, 파라미터명으로 빈 이름 매칭

```java
@Component
@Qualifier("fixDiscountPolicy")
public class FixDisoucntPolicy implements DiscountPolicy{
  
}

```



```java
@Component
@Qualifier("mainDiscountPolicy")
public class RateDiscountPolicy implements DiscountPolicy{

}
```



```java
    public OrderServiceImpl(MemberRepository memberRepository, @Qualifier("mainDiscountPolicy") DiscountPolicy rateDiscountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = rateDiscountPolicy;
    }
```

### @Qualifier 정리

- @Qualifer 끼리 매칭
- 빈 이름 매칭



### @Primary

- 사용할 구현체에 @Primary 를 붙임



> 코드에서 자주 사용하는 메인 데이터베이스의 커넥션을 획득하는 스프링 빈이 있고, 코드에서 특별한 기능으로 가끔 사용하는 서브 데이터베이스의 커넥션을 획득하는 스프링 빈이 있다고 생각.
>
> 메인 데이터베이스의 커넥션을 획득하는 스프링 빈은 @Primary를 적용해서 조회하는 곳에서  @Qualifier 지정 없이 편리하게 조회하고, 서브 데이터베이스 커넥션 빈을 획득 할 때는 @Qualifier를 지정해서 명시적으로 획득하는 방법으로 사용하면 코드를 깔끔하게 유지할 수 있다.



### 애노테이션 직접 만들기

- 애노테이션에는 상속이라는 개념이 없다. 여러 애노테이션을 모아서 사용하는 기능은 스프링이 지원해주는 기능이다. 다른 애노테이션들도 함께 조합해서 사용할 수 있다. 단적으로 @Autowired도 재정의 할 수 있다.





