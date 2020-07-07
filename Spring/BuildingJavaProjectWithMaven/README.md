### Building Java Project with Maven

#### Objective

---

- 간단한 Java Application을 작성해보고, Maven으로 Build 해보기

#### Prerequisite

---

- 15분
- IDE
- JDK 6 or later (나의 환경은 Open JDK 1.8.0)

##### Step 1. Set up the Project

---

- 완성된 Github Repo를 클론해와도 되지만, From Scrach 해보자!
- Maven으로 빌드할 Java Project를 설정해보자. 아래와 같이 간단하게 만들어보자
- Create the Directory Structure

  ```bash
  mkdir -p src/main/java/hello
  ```

  - 이 디렉터리 안에, 어떤 Java Class라도 생성할 수 있다. 단순화를 위해, HelloWorld.java와 Greeter.java 두 개의 파일만 생성해보자
  - src/main/java/hello/HelloWorld.java
    ```java
    package hello;
    public class HelloWorld {
        public static void main(String[] args) {
            Greeter greeter = new Greeter();
            System.out.println(greeter.sayHello());
        }
    }
    ```
  - src/main/java/hello/Greeter.java
    ```java
    package hello;
    public class Greeter {
        public String sayHello() {
            return "Hello world!";
        }
    }
    ```

##### Step 2. Install Maven & Configure pom.xml

---

- SDKMAN이나, homebrew를 이용해 Maven을 설치하자

  ```bash
  > brew install maven
  ```

  - maven을 실행시켜 정상적으로 설치가 되었는지 확인

  ```bash
  > mvn -v
  Apache Maven 3.6.3 (cecedd343002696d0abb50b32b541b8a6ba2883f)
  Maven home: /usr/local/Cellar/maven/3.6.3_1/libexec
  Java version: 1.8.0_252, vendor: AdoptOpenJDK, runtime: /Library/Java/JavaVirtualMachines/adoptopenjdk-8.jdk/Contents/Home/jre
  Default locale: ko_KR, platform encoding: UTF-8
  OS name: "mac os x", version: "10.15.5", arch: "x86_64", family: "mac"
  ```

  - 만약 다른 개발자로 하여금 Maven의 정확한 버전을 필요로 하게 한다거나 하는 일을 피하고 싶다?
  - Spring Initializer로부터 다운로드 받은 프로젝트는 Maven Wrapper가 포함되어 있다.
  - 해당 Wrapper는 프로젝트 루트에 mvnw라는 이름의 스크립트 형태로 존재한다

- 간단한 Maven Build를 정의해보자
- Maven이 시스템에 설치되었으므로, Maven Project Definition을 만들어주어야 한다
- Maven 프로젝트는 pom.xml이라는 XML파일에 의해 정의되며, 이 파일은 프로젝트의 이름, 버전, 외부 라이브러리에 대한 의존성 등을 명시한다
- pom.xml 파일을 src 폴더의 옆 (프로젝트 최상위 위치)에 생성하고 아래와 같이 작성하자.
- pom.xml

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
      xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
      <modelVersion>4.0.0</modelVersion>

      <groupId>org.springframework</groupId>
      <artifactId>gs-maven</artifactId>
      <packaging>jar</packaging>
      <version>0.1.0</version>

      <properties>
          <maven.compiler.source>1.8</maven.compiler.source>
          <maven.compiler.target>1.8</maven.compiler.target>
      </properties>

      <build>
          <plugins>
              <plugin>
                  <groupId>org.apache.maven.plugins</groupId>
                  <artifactId>maven-shade-plugin</artifactId>
                  <version>2.1</version>
                  <executions>
                      <execution>
                          <phase>package</phase>
                          <goals>
                              <goal>shade</goal>
                          </goals>
                          <configuration>
                              <transformers>
                                  <transformer
                                      implementation="org.apache.maven.plugins.shade.resource.ManifestResourceTransformer">
                                      <mainClass>hello.HelloWorld</mainClass>
                                  </transformer>
                              </transformers>
                          </configuration>
                      </execution>
                  </executions>
              </plugin>
          </plugins>
      </build>
  </project>
  ```

  - <packaging> 요소를 제외하고는, 위의 내용은 프로젝트를 빌드하는 데에 필요한 최소의 pom.xml 파일이다.
  - <modelVersion>: POM model Version (always 4.0.0)
  - <groupId>: 해당 프로젝트가 속한 그룹이나 조직의 이름
  - <artifactId>: 프로젝트의 라이브러리 아티팩트에 주어진 이름(예를 들면, JAR or WAR파일의 이름)
  - <version> 빌드될 프로젝트의 버전
  - <packaging>: 프로젝트가 어떻게 패키징될 것인가를 명시. 기본은 jar이며, war를 사용 시 WAR파일 패키징을 수행한다

##### Step 3. Build Java

---

- 위의 내용대로 수행하면 Maven은 이제 자바 프로젝트를 빌드할 준비가 되었다.
- 빌드를 한번 살펴보면?

  - 성공적으로 프로젝트의 코드를 컴파일
  - JAR와 같은 라이브러리 패키지를 생성
  - 로컬 의존성 레포지터리에 있는 라이브러리를 설치

- 아래의 명령어로 Maven을 이용해 컴파일을 수행한다

  ```bash
  mvn compile
  ```

  - target/classes 폴더에 컴파일된 .class 파일들이 생성된다

- .class 파일들을 그대로 배포하거나 전달하는 일은 거의 없으므로, packaging을 수행하자

  ```bash
  mvn package
  ```

  - 이 명령어는 자바 코드를 컴파일하고, 존재하는 테스트를 수행한 뒤, 코드를 target 디렉터리 안에 .JAR 파일로 패키징한다
  - 생성된 .JAR파일의 이름은 <artifactId> + <version>

- .JAR 파일을 실행하려면 아래와 같이 자바 명령어를 사용한다

  ```bash
  java -jar target/gs-maven-0.1.0.jar
  ```

- Maven은 또한 의존성 레포지터리를 로컬 머신 내에 유지한다 (.m2/repository)
- 만약 프로젝트의 JAR 파일을 로컬 레포지터리 안에 설치하고 싶으면, install 명령어를 사용한다

  ```bash
  [INFO] Installing /Users/dwywdo/daybyeday/study_spring/BuildingJavaProjectWithMaven/target/gs-maven-0.1.0.jar to /Users/dwywdo/.m2/repository/org/springframework/gs-maven/0.1.0/gs-maven-0.1.0.jar
  [INFO] Installing /Users/dwywdo/daybyeday/study_spring/BuildingJavaProjectWithMaven/pom.xml to /Users/dwywdo/.m2/repository/org/springframework/gs-maven/0.1.0/gs-maven-0.1.0.pom
  ```

##### Step 4. Declare Dependencies

---

- 이 간단한 예제는 어떤 라이브러리에 대한 의존성도 존재하지 않는다. 하지만, 대부분의 어플리케이션은 외부 라이브러리를 사용하지 않는 경우가 드물다
- 예를 들어, Hello World!를 출력할 때 날짜와 시간도 함께 출력하고 싶을 수 있다. 물론 Native Java 라이브러리를 사용할 수도 있지만, Maven을 더 활용해보기 위해 Joda Time library를 사용해보자!
- HelloWorld.java 수정하기

  ```java
  package hello;
  import org.joda.time.LocalTime;
  public class HelloWorld {
      public static void main(String[] args) {
          LocalTime currentTime = new LocalTime();
          System.out.println("The Current Local Time Is: " + currentTime);
          Greeter greeter = new Greeter();
          System.out.println(greeter.sayHello());
      }
  }
  ```

- 이렇게 수정한 뒤, mvn compile을 수행하면 컴파일에 실패한다. 왜? Joda Time에 대한 의존성 명시가 안되었기 때문
- pom.xml의 <project> 요소 안에 아래의 내용을 추가하자

  ```xml
  <dependencies>
    <dependency>
      <groupId>joda-time</groupId>
      <artifactId>joda-time</artifactId>
      <version>2.9.2</version>
    </dependency>
  </dependencies>
  ```

  - <groupId>: 이 의존성 라이브러리가 속한 그룹 혹은 조직
  - <artifactId>: 요구되는 라이브러리
  - <version>: 요구 라이브러리의 버전 정보

- 기본적으로, 모든 의존성들은 Compile 의존성으로 간주된다. 즉, 컴파일 타임에는 무조건 사용 가능한 상황이어야 한다는 것
- 만약 /WEB-INF/libs 폴더를 포함한 WAR 파일을 빌드한다면, 아마도 <scope>의 값을 아래와 같은 값 중 하나로 설정해야 한다
  - provided: 프로젝트 코드를 컴파일 함에 있어서 의존성을 요구하지만, 컨테이너에 의해 런타임에 제공된다. 대표적인 예로는 Java Servelet API
  - test: 컴파일과 테스트 실행에 사용되는 의존성. 빌드하거나, 프로젝트의 런타임 코드를 실행할 때에는 요구되지 않는다

##### Step 5. Write a Test

---

- 테스트 코드를 작성하기 위해, JUnit을 의존성에 추가해주자
- pom.xml

  ```xml
  <dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <version>4.12</version>
    <scope>test</scope>
  </dependency>
  ```

- 테스트 코드를 아래와 같이 작성해보자
- src/test/java/hello/GreeterTest.java

  ```java
  package hello;
  import static org.hamcrest.CoreMatchers.containsString;
  import static org.junit.Assert.*;
  import org.junit.Test;
  public class GreeterTest {
    private Greeter greeter = new Greeter();
    @Test
    public void greeterSaysHello() {
      assertThat(greeter.sayHello(), containsString("Hello"));
    }
  }
  ```

- Maven은 유닛테스트를 실행하기 위해 "surefire"라는 플러그인을 이용한다.
- surefire의 기본 설정은 src/test/java안에 있는 클래스중 \*Test라는 이름에 매칭되는 것들을 실행한다
- 테스트를 하기 위해서는 아래의 명령어를 이용한다

  ```bash
  mvn test
  ```

- 완성된 pom.xml의 [Maven Shade Plugin](https://maven.apache.org/plugins/maven-shade-plugin/)은 JAR 실행파일을 생성하는데에 있어서 편의를 위해 사용한 것.
