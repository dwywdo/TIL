### Accessing Data With MySQL

#### Objective

---

- MySQL 인스턴스를 생성하고, 접근하는 Spring Application을 만들자

#### Prerequisite

---

- 15분
- MySQL version 5.6 or higher
- IDE (STS or IntelliJ)
- JDK 1.8 or later (나의 환경은 Open JDK 1.8.0)
- Gradle 4+

##### Step 1. Set Up

---

![Screenshot](./spring-mysql.png)

##### Step 2. Mysql Installation & Setup

---

```bash
  brew install mysql
  mysql.server start
  mysql_secure_installation # For MySQL Configuration
  mysql -uroot -p # Conenct to MySQL

  mysql> create database db_example;
  mysql> create user 'springuser'@'%' identified by 'PASSWORD';
  mysql> grant all on db_example.* to 'springuser'@'%';
```

##### Step 3. Create the application.properties File

---

- Spring Boot는 기본적으로 H2 데이터베이스에 연결 가능한 모든 설정을 미리 준다
- 다른 데이터베이스에 접근하고자 한다면 Connection Atrributes에 대해 설정해야 한다

  - src/main/resources/application.properties

  ```properties
  spring.jpa.hibernate.ddl-auto=update
  spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/db_example
  spring.datasource.username=springuser
  spring.datasource.password=password
  ```

  - spring.jpa.hibernate.ddl-auto
    - none: MySQL의 기본값. 데이터베이스 구조에는 변화를 발생시키지 않는다
    - update: Hibernate는 주어진 Entity Structure를 기반으로 MySQL 데이터베이스를 변경한다
    - create: 매번 데이터베이스를 생성하고 종료시에는 삭제하지 않는다
    - create-drop: 매번 데이터베이스를 생성하고 SessionFactory가 닫힐 때 Drop시킨다
  - 아직 데이터베이스 테이블을 생성하지 않았기 때문에, create / update로 시작해야 한다
  - 첫 실행 이후에는, 시스템의 요구에 따라 update / none으로 변경할 수 있다
  - 참고: 기본 데이터베이스인 H2의 기본값은 create-drop이다

  > 기본적으로 첫 Production 단계가 끝난 후에는 none으로 설정하고,
  > MySQL유저의 권한을 모두 내린 후에 SELECT, UPDATE, INSERT, DELETE 권한만 주는 것이 좋다

##### Step 4. Create the @Entity Model

---

- Database Entity의 모델이 될 클래스를 만들자.
- src/main/java/com/example/accessingdatawithmysql/User.java

  ```java
  package com.example.accessingdatawithmysql;

  import javax.persistence.Entity;
  import javax.persistence.GeneratedValue;
  import javax.persistence.GenerationType;
  import javax.persistence.Id;

  @Entity // This tells Hibernate to make a table out of this class
  public class User {
      @Id
      @GeneratedValue(strategy= GenerationType.AUTO)
      private Integer id;
      private String name;

      public Integer getId() {
          return id;
      }

      public void setId(Integer id) {
          this.id = id;
      }

      public String getName() {
          return name;
      }

      public void setName(String name) {
          this.name = name;
      }

      public String getEmail() {
          return email;
      }

      public void setEmail(String email) {
          this.email = email;
      }

      private String email;
  }
  ```

  - Hibernate는 자동적으로 이 Entity를 테이블로 변환한다

##### Step 4. Create the @Repository

---

- 유저 레코드를 저장할 Repository를 만들자
- src/main/java/com/example/accessingdatawithmysql/UserRepository.java

  ```java
  package com.example.accessingdatawithmysql;

  import org.springframework.data.repository.CrudRepository;

  public interface UserRepository extends CrudRepository<User, Integer> {

  }
  ```

  - 이 부분은 Spring에 의해 userRepository라는 Bean으로 자동 생성된다

##### Step 5. Create the @Controller

---

- HTTP Request를 처리할 Controller를 만들자
- src/main/java/com/example/accessingdatawithmysql/MainController.java

  ```java
  package com.example.accessingdatawithmysql;

  import org.springframework.beans.factory.annotation.Autowired;
  import org.springframework.stereotype.Controller;
  import org.springframework.web.bind.annotation.*;

  @Controller // 이 클래스가 Controller 임을 명시
  @RequestMapping(path="/demo") // URL의 시작이 /demo임을 의미 (물론, Application Path 이후에)
  public class MainController {
      @Autowired // 스프링에 의해 자동생성된 userRepository를 가져옴
      private UserRepository userRepository;

      @PostMapping(path="/add") // POST Request 매핑
      public @ResponseBody String addNewUser (@RequestParam String name, @RequestParam String email){
          // @ResponseBody는 요청에 대한 응답으로 뷰가 아닌 String 데이터를 반환함을 의미
          // @RequestParam은 요청의 매개변수로 받을 데이터임을 의미
          User n = new User();
          n.setName(name);
          n.setEmail(email);
          userRepository.save(n);
          return "Saved";
      }

      @GetMapping(path="/all") // GET Request 매핑
      public @ResponseBody Iterable<User> getAllUsers(){
          return userRepository.findAll();
      }

      // @GetMapping은 GET / POST 이외에도 모든 HTTP Method를 매핑시킬 수 있다
  }
  ```

##### Step 6. Build & Execute

---

```bash
./gradlew bootRun
```

- MySQL 5.1.X 버전부터 KST 타임존을 인식하지 못하는 이슈가 있다ㅠ
- properties 파일을 아래와 같이 수정한다

  ```
  spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/db_example?serverTimezone=UTC // 서버 타임존 UTC로 추가
  ```

- Spring Application이 정상적으로 동작하면, 간단하게 curl 명령어를 이용해 테스트 해본다
  ```bash
  > curl localhost:8080/demo/add -d name=First email=testemail@mailprovider.com
  Saved
  > curl 'localhost:8080/demo/all'
  [{"id":1,"name":"Frist","email":"testemail@mailprovider.com"}]
  ```
