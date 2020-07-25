# Spring - Bean
#a-part/todo
- - - -
#### Bean?
	* 일반적인 객체인데, Spring IoC 컨테이너가 관리하는 객체
	* 어떤 객체를 만들었을 때, 가령..
		* new keyword를 통해서 만들어주면 Bean이라고 하지 않는다
		* ApplicationContext (BeanFactory) 가 알고 있고, 그 안에 담고 있는 객체들을 Bean이라고 한다
	* 어떻게 특정한 인스턴스를 Bean으로 만들도록 할 수 있을까? 크게 2가지
		* Component Scanning을 활용
			* @Component Annotation
			* @Controller도 @Component 메타 Annotation을 사용하는 것이다 
				* (@Repository, @Service, @Controller, @Configuration -> 직접 정의하는 것도 가능하다)
				* @Repository는 Spring Data JPA에 의해 좀 특이한 방법으로 Bean을 제공한다
					* 특정 인터페이스를 상속하게 되면 그 클래스에 해당하는 구현체를 만들어서 Bean으로 등록을 해줌
			* Annotation Processor 중에, Spring IoC 컨테이너가 사용하는 여러가지 인터페이스들이 있다.
				* 이런 것들을 LifeCycle Callback이라고 부른다
					* 이런 Callback 중에는
						* @Component Annotation이 붙어있는 모든 클래스를 찾아서 인스턴스를 만들어서 Bean으로 등록하는 복잡한 일을 하는 Annotation Processor가 있다
				* @SpringBootApplication을 들어가보면, @ComponentScan이라는 Annotation이 붙어있다. 이 Annotation이 어디서부터 컴포넌트를 찾아볼 지 알려준다.
		* [토막 지식] Spring Boot 기반의 테스트를 작성하는 방법이 별도로 있다! @SpringBootTest활용
		* 직접 Bean으로 등록하는 방법?
			* Bean 설정파일이 XML이냐, Java이냐에 따라 다르긴 한데 최근 추세는 Java 설정파일 사용
				* SampleConfig.java
```java
@Configuration // 이것도 @Component 메타 Annotation
public class SampleConfig(){
	@Bean
	public SampleController sampleController() {
		return new SampleController();
	}
}		
```
			* 어떤 클래스 위에 @Configuration이라는 Annotation 사용
			* 그 안에 특정 메소드를 정의하고 리턴하는 객체가 Bean으로 등록됨
* Bean을 꺼내서 쓰는 방법?
	* 직접 ApplicationContext.getBean()을 사용 
	* 클래스의 생성자를 통해서 직접 주입 받음
	* @Autowired Annotation 사용