# Spring - AOP
#tech/Spring
- - - -
#### AOP?
* 스프링은 IoC, AOP, PSA 3가지의 개념을 가지고 Spring Triangle이라고 부른다
* AOP (Aspect Oriented Programming)
	* 관점 지향 프로그래밍
	* 관심사를 기준으로? 예제 코드를 보자

```java
class A {
	method a() {
		AAAA
		오늘은 7월 4일 미국 독립기념일이래요
		BBBB
	}
	method b() {
		AAAA
		저는 아침에 운동을 다녀와서 밥먹고 빨래를 했습니다
		BBBB
	}
}
class B {
	method c() {
		AAAA
		점심은 제육볶음이 먹고싶네요
		BBBB
	}
}
```

* 만약 AAAA -> AAA로 바꾸어야 할 일이 생기면 해당 부분을 모두 찾아서 AAAA -> AAA로 바꾸어주어야 한다
* 공통된 AAAA / BBBB 부분은 모아서 별도의 클래스에 별도의 메소드로 아래와 같이 구현할 수 있다

```java
class AAAABBBB {
	method aaaabbbb(JoinPoint point){
		AAAA
		point.execute();
		BBBB
	}
}
```

* Pet Clinic의 코드를 보면 AOP가 적용되어 있다
	* @Transactional: Spring AOP가 사용하는 Annotation
* 예) OwnerController에서 _owner_new  로 오는 요청을 처리할 때 성능을 측정하고 싶다?
	* Stopwatch stopwatch = new Stopwatch() / stopWatch.start() / stopWatch.stop() / stopWatch.prettyPrint() 를 이용해보자
	* 시간을 재고, 콘솔 기반으로 출력 혹은 로그로 출력!
	* 이 부분이 모든 API 요청에 대해 공통적으로 들어갈 것이고, 그냥 넣으면 AOP가 아니다!
	* 코드가 없는데도, 코드가 있는 것처럼 프로그래밍을 할 수 있도록!
* AOP를 구현하는 다양한 방법?
	* 컴파일
		* StopWatch를 넣지 않았지만, A.java -> (AOP) -> A.class 를 통해서 코드가 들어가도록.. (AspectJ)
	* 바이트코드 조작
		* A.java -> A.class -> 클래스로더가 A.class를 로드해와서 메모리에 올릴 때! 조작하는 방식. 클래스로더에 옵션을 붙인다 (AspectJ)
	* 프록시 패턴
		* Spring AOP가 사용하는 방법
		* 디자인 패턴 중 하나를 사용해서 AOP와 같은 효과를 내는 방법
* 프록시 패턴?
	* [참조 링크](https://refactoring.guru/design-patterns/proxy): Real World Analogy 부분의 그림이 핵심!
	* [Spring - Proxy Pattern](bear://x-callback-url/open-note?id=BE1FDA6B-3D59-46E9-AF9B-EFD6E8688663-799-000000E4277C01D2)