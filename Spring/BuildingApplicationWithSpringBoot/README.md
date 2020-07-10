### Building an Application With Spring Boot

#### Objective

---

- Spring Boot로 무엇을 할 수 있는지 알아보자

#### Prerequisite

---

- 15분
- IDE (STS or IntelliJ)
- JDK 1.8 or later (나의 환경은 Open JDK 1.8.0)
- Gradle 4+

##### Step 1. Learn What You Can Do with Spring Boot

---

- Spring Boot는 어플리케이션을 만들 수 있는 빠른 방법을 제공한다
- 설정한 classpath / beans들을 스캔하고 합리적인 가정을 통해 필요한 요소들을 추가한다
- 인프라에 관심을 줄이고, 비즈니스 로직에 더 집중할 수 있다!

- 스프링 부트가 하는 일의 예시

  - Spring MVC가 classpath에 있다면 여러 특정 빈들을 필요로 할 것이다. 스프링 부트는 이런 빈들을 자동적으로 추가한다.Spring MVC Application은 Servlet 컨테이너도 필요하며, 스프링 부트는 임베디드된 톰캣을 알아서 설정한다
  - Jetty가 classpath에 있다면 톰캣이 필요한 대신, 임베디드된 Jetty가 필요할 것이고. 부트는 이 또한 알아서 처리해준다
  - Thymeleaf가 classpath에 있다면 application context 내에 반드시 추가되어야 하는 빈들도 알아서 추가해준다

- 부트는 알아서 SpringTemplateEngine을 Application Context 내에 추가하지만, 이미 SpringTemplateEngine을 정의한 상태라면 부트가 더 추가하지는 않는다

  > Spring Boot does not generate code and make edits to your files. Instead, when you start your application, Spring Boot dynamically wires up beans and settings and applies them to your application context.

##### Step 2. Create a Simple Web Application

---

- 간단한 웹 컨트롤러를 만들어 추가하자

  - src/main/java/com/example/springboot/HelloController.java

    ```java
    package com.example.springboot;

    import org.springframework.web.bind.annotation.RestController;
    import org.springframework.web.bind.annotation.RequestMapping;

    @RestController
    public class HelloController {

        @RequestMapping("/")
        public String index() {
            return "Greetings from Spring Boot!";
        }

    }
    ```

    - @RestController라고 Flagged된 것은 Spring MVC가 Web Request를 처리하는데 쓰이도록 준비한다는 의미
    - @RequestMapping: **/** -> **index()** 메소드로 연결
    - @RestController == @Controller + @ResponseBody

- Application Class를 생성하자

  - src/main/java/com/example/springboot/Application.java

    ```java
    package com.example.springboot;
    import java.util.Arrays;
    import org.springframework.boot.CommandLineRunner;
    import org.springframework.boot.SpringApplication;
    import org.springframework.boot.autoconfigure.SpringBootApplication;
    import org.springframework.context.ApplicationContext;
    import org.springframework.context.annotation.Bean;
    @SpringBootApplication
    public class Application {

        public static void main(String[] args) {
            SpringApplication.run(Application.class, args);
        }

        @Bean
        public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
            return args -> {

                System.out.println("Let's inspect the beans provided by Spring Boot:");

                String[] beanNames = ctx.getBeanDefinitionNames();
                Arrays.sort(beanNames);
                for (String beanName : beanNames) {
                    System.out.println(beanName);
                }

            };
        }

    }
    ```

    - @SpringBootApplication: 아래 요소들을 다 포함한다
      - @Configuration: 이 클래스를 ApplicationContext를 위한 Bean definition의 Source로 표시
      - @EnableAutoConfiguration: 스프링 부트로 하여금 classpath / other beans / property 세팅을 참고하여 Beans을 추가하도록 함. 예를 들어, spring-webmvc가 classpath에 있다면, 이 어플리케이션을 웹 어플리케이션이라 표시하고 DispatcherServlet과 같은 핵심 Behavior를 수행하도록 함
      - @ComponentScan: 스프링으로 하여금 com/example 패키지 내의 다른 컴포넌트, Configuration, 서비스들을 확인하고 Controller를 찾게 한다
    - main() 메소드는 SpringApplication.run() 메소드를 호출하여 어플리케이션을 시작한다.
    - CommandlineRunner도 @Bean으로 표시하여 Startup때 호출되도록 한다. 이 코드의 역할은 어플리케이션에서 자동적으로든, 아니든 생성된 모든 bean들의 이름을 정렬하여 출력한다.

##### Step 3. Run the Application

---

```bash
./bradlew bootRun
```

- 생성된 모든 빈들의 목록을 콘솔에서 확인할 수 있고, 웹 브라우저를 통해 Web Request도 잘 처리되는 것을 확인할 수 있다

##### Step 4. Add Unit Tests

---

- 추가한 Endpoint가 잘 동작하는지 확인하기 위해, Spring Test는 테스트 기능을 지원한다.

  - build.gradle에 아래의 Dependency를 추가하자

    ```
    testImplementation('org.springframework.boot:spring-boot-starter-test') {
    	exclude group: 'org.junit.vintage', module: 'junit-vintage-engine'
    }

    ```

  - Test 코드를 아래와 같이 작성하자
  - src/test/java/com/example/springboot/HelloControllerTest.java

    ```java
    package com.example.springboot;

    import static org.hamcrest.Matchers.equalTo;
    import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
    import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

    import org.junit.jupiter.api.Test;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
    import org.springframework.boot.test.context.SpringBootTest;
    import org.springframework.http.MediaType;
    import org.springframework.test.web.servlet.MockMvc;
    import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

    @SpringBootTest
    @AutoConfigureMockMvc
    public class HelloControllerTest {

        @Autowired
        private MockMvc mvc;

        @Test
        public void getHello() throws Exception {
            mvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().string(equalTo("Greetings from Spring Boot!")));
        }
    }
    ```

    - MockMvc는 Spring Test에 포함되어 있으며, HTTP Request를 만들어 DispatcherServlet에 보내고 결과를 확인할 수 있다
    - @AutoConfigureMockMvc and @SpringBootTest -> Injection -> MockMvc Instance

- HTTP Request Cycle을 흉내내는 것뿐만 아니라, Spring Boot로 하여금 간단한 풀스택 통합 테스트를 수행하도록 할 수 있다. 예를 들면, 위의 Mocking 예를 제외하고 아래의 테스트를 작성할 수도 있다
- src/test/java/com/example/springboot/HelloControllerIT.java

  ```java
  package com.example.springboot;
  import static org.assertj.core.api.Assertions.*;

  import java.net.URL;

  import org.junit.jupiter.api.BeforeEach;
  import org.junit.jupiter.api.Test;

  import org.springframework.beans.factory.annotation.Autowired;
  import org.springframework.boot.test.context.SpringBootTest;
  import org.springframework.boot.test.web.client.TestRestTemplate;
  import org.springframework.boot.web.server.LocalServerPort;
  import org.springframework.http.ResponseEntity;

  @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
  public class HelloControllerIT {

      @LocalServerPort
      private int port;

      private URL base;

      @Autowired
      private TestRestTemplate template;

      @BeforeEach
      public void setUp() throws Exception {
          this.base = new URL("http://localhost:" + port + "/");
      }

      @Test
      public void getHello() throws Exception {
          ResponseEntity<String> response = template.getForEntity(base.toString(),
                  String.class);
          assertThat(response.getBody()).isEqualTo("Greetings from Spring Boot!");
      }
  }
  ```
