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
