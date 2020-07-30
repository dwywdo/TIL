# Spring - PSA
#tech/Spring
- - - -
#### PSA? Portable Service Abstraction의 약자
* 주로 Service Abstraction이라고 부른다
* Spring으로 개발을 하면, 서블릿을 쓰고 있음에도 아래처럼 서블릿 관련 코드를 작성하지 않는다
```java
import ...
public class OwnerCreateServlet extends HttpServlet {
	// /owner/create
	@Override
	// GET 메소드가 들어오면 이게 실행됨
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		super.doGet(req, resp);
	}
	// POST 메소드가 들어오면 이게 실행됨
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
		super.doPost(req, resp);
	}
}
```

* @GetMapping이라는 Annotation을 사용해서 요청들을 분리하고, 메소드들에 할당하는 방식으로 Spring에서는 구현된다
* 그렇게 구현이 되긴 하지만, 그 아래 기반에서는 사실 서블릿 기반으로 코드가 동작한다. 
* Servlet Abstraction! (PSA의 대표적 예) Wikipedia 검색을 통해 그 목적을 확인할 수 있다

#### Spring Web MVC 추상화 계층
* MVC의 M (Model): Owner와 같은 객체
* MVC의 V: View 이름을 Return? 그 View는 resources/template 에 들어가있게 된다 (주로 Template Engine, Thymeleaf를 사용해서 만든 뷰 html)
* MVC의 C:? @Controller: 사용 시, 요청을 Mapping하는 Controller 역할을 수행하는 `클래스`가 된다
-> Mapping을 한다는 것은, 특정 URL로 요청이 왔을 때 특정 메소드로 연결을 해준다는 의미
	* @GetMapping을 살펴보면, name, value, parameter, consumes, produces 등 다양한 속성을 가지고 있다
		* 이 모든 속성은 요청과 관련이 있다. 요청의 헤더 정보, Parameter 정보 등..
			* 매핑된 메소드가 그 정보들을 처리하는 역할을 수행하게 됨
* 왜 쓰느냐?
	* 서블릿을 Low-level로 사용하지 않아도 된다 (httpServlet) -> 추상화
	* Portable의 의미는 기본적으로 Spring Boot 기반의 App을 실행하면 Tomcat 기반으로 로드되는 것을 알 수 있다
		* 코드를 거의 둔 상태로, 완전히 다른 기술 스택으로 실행하는 것도 가능하다
		* 다른 기술 스택이라고 하면..?
			* WebFlux: 하나의 요청 당 하나의 쓰레드를 기존 구조가 아닌, CPU 갯수만큼의 요청을 받는 쓰레드를 유지하면서 뒤쪽에서는 Thread Pool을 사용. 최소한의 쓰레드로 가용성을 높일 수 있는 기술 스택
			* WebFlux를 적용하면 Netty를 사용할 수도 있다
				* <dependency>에서 아래의 내용들을 수정
					* spring-boot-starter-web -> spring-boot-starter-webflux
					* web을 빼면, spring web MVC가 빠지므로, ModelandView를 사용할 수가 없으므로 해당 부분 주석처리
				* 그 상태로 실행을 하면, Netty 기반으로 실행이 된다
				* 원래 Netty 기반으로 코드를 작성하려면 코딩 방법이 완전히 다르다. 하지만, 스프링이 제공하는 추상화 인터페이스로 Netty를 사용할 수 있다
			* @Controller | @RequestMapping | ...
			* Servlet | Reactive | ...
			* Tomcat | Jetty | Netty | 언더토우 | ...
		* 이렇게 코드를 변경하지 않고도 여러 프레임워크를 바꾸어가며 사용할 수 있다. 스프링 웹 MVC가 PSA 중 하나다!
	* Spring Transaction 처리..
		* Transaction(All or Nothing): DB에 데이터를 주고 받는다고 가정했을 때, A -> B -> C 까지 다 되어야 하나의 작업으로 완료됨이 인정되는 경우
		* JDBC 단에서 이걸 수행하는 코드가
			* dbConnection.setAutoCommit(false); 후 내가 직접 dbConnection.commit(); 하는 코드
			* 에러가 나면 dbConnection.rollback();
		* 스프링의 추상화 계층 기능 중 하나가 @Transactional Annotation -> 알아서 트랜잭션 처리가 됨. 명시적으로 커밋 처리를 하지 않아도 된다
			* PlatformTransactionManager
			* JpaTransactionManager | DatasourceTransactionManager | HibernateTransactionManager
			* 마찬가지로 사용하는 기술 스택에 따라 바꾸어 가며 사용할 수 있다
		* 다른 예제로는 @Cacheable이라는 것이 있다 (구현체로는 javax.cache | ehcache | ...) 






















