### Building Java Project with Gradle

#### Objective

---

- 간단한 Java Application을 작성해보고, Gradle로 Build 해보기

#### Prerequisite

---

- 15분
- IDE
- JDK 6 or later (나의 환경은 Open JDK 1.8.0)

##### Step 1. Set up the Project

---

- 완성된 Github Repo를 클론해와도 되지만, From Scrach 해보자!
- Gradle로 빌드할 Java Project를 설정해보자. 아래와 같이 간단하게 만들어보자
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

##### Step 2. Install Gradle & Build Java Project using Gradle

---

- SDKMAN이나, homebrew를 이용해 Gradle을 설치하자

  ```bash
  > brew install gradle
  ```

  - 아래의 경고가 떴음. 아마 MacOS의 Java Wrapper와 관련된 내용인듯

  ```
  For the system Java wrappers to find this JDK, symlink it with
  sudo ln -sfn /usr/local/opt/openjdk/libexec/openjdk.jdk /Library/Java/JavaVirtualMachines/openjdk.jdk

  openjdk is keg-only, which means it was not symlinked into /usr/local,
  because it shadows the macOS `java` wrapper.

  If you need to have openjdk first in your PATH run:
  echo 'export PATH="/usr/local/opt/openjdk/bin:$PATH"' >> ~/.zshrc

  For compilers to find openjdk you may need to set:
  export CPPFLAGS="-I/usr/local/opt/openjdk/include"
  ```

  - gradle을 실행시켜 정상적으로 설치가 되었는지 확인

  ```bash
  gradle
  Welcome to Gradle 6.5.1!

    Here are the highlights of this release:
    - Experimental file-system watching
    - Improved version ordering
    - New samples

    For more details see https://docs.gradle.org/6.5.1/release-notes.html

    Starting a Gradle Daemon (subsequent builds will be faster)

    > Task :help

    Welcome to Gradle 6.5.1.

    To run a build, run gradle <task> ...

    To see a list of available tasks, run gradle tasks

    To see a list of command-line options, run gradle --help

    To see more detail about a task, run gradle help --task <task>

    For troubleshooting, visit https://help.gradle.org

    Deprecated Gradle features were used in this build, making it incompatible with Gradle 7.0.
    Use '--warning-mode all' to show the individual deprecation warnings.
    See https://docs.gradle.org/6.5.1/userguide/command_line_interface.html#sec:command_line_warnings

    BUILD SUCCESSFUL in 2s
    1 actionable task: 1 executed
  ```

- Gradle로 뭘 할 수 있는지 확인해보자

  ```
  > gradle tasks
  ```

  - 실행한 폴더에 build.gradle 파일이 없을 것이기에, 가능한 명령어들을 출력하게 됨
  - build.gradle에 플러그인을 추가할 때마다, 할 수 있는 tasks도 많이 출력될 것

- 자바 빌드를 가능하게 하기 위해서, build.gradle 파일을 작성해보자

  - build.gradle
    ```
    apply plugin: 'java'
    ```
    - 이렇게 작성 후 해당 폴더에서 gradle tasks 수행 시 더 많아진 명령어를 확인할 수 있다.

- Gradle로 자바 빌드

  ```bash
  > gradle build
  BUILD SUCCESSFUL in 459ms
  2 actionable tasks: 2 up-to-date
  ```

  - 빌드 후, build 폴더 안을 살펴보자

    - classes: 프로젝트에 의해 컴파일된 .class 파일들
      - classes 폴더에는 자바 코드를 컴파일한 결과물들이 존재한다. 현재 예제의 경우, HelloWorld.class와 Greeter.class가 존재할 것
    - reports: 테스트 리포트와 같은 빌드 과정 중 생성된 레포트들
      - 프로젝트에서 유닛 테스트를 실행한 후의 레포트가 존재
    - libs: Assemble 된 프로젝트 라이브러리 (주로 JAR / WAR 파일들)
      - 프로젝트의 폴더 이름을 따온 JAR 파일이 존재한다.

  - 이 시점에서, 프로젝트는 어떤 라이브러리 의존성도 갖지 않기 때문에 dependency_cache 폴더에는 아무것도 없다.

- 의존성 명시하기

  - 이 간단한 예제는 어떤 라이브러리에 대한 의존성도 존재하지 않는다. 하지만, 대부분의 어플리케이션은 외부 라이브러리를 사용하지 않는 경우가 드물다
  - 예를 들어, Hello World!를 출력할 때 날짜와 시간도 함께 출력하고 싶을 수 있다. 물론 Native Java 라이브러리를 사용할 수도 있지만, Gradle을 더 활용해보기 위해 Joda Time library를 사용해보자!
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

  - 이후 Gradle로 다시 빌드해보자 (gradle build)
    - 빌드에 실패하게 될 것.. 왜? 컴파일 의존성에 Joda Time을 명시하지 않음
    - build.gradle 파일에 아래의 내용을 추가하자
      ```gradle
      repositories{
          mavenCentral()
      }
      ```
      - repositories 블록은 빌드가 이 안에 명시된 의존성들을 Maven Central Repository로부터 해결하도록 명시한다.
      - Gradle은 메이븐 빌드 도구들로부터의 기능들에 상당히 의존하고 있다!
        - 위와 같이 라이브러리 의존성 해결을 위한 소스(출처)를 Maven Central Repository로 사용하는 옵션 등..
  - 이제 Third Party 라이브러리를 추가할 수 있게 되었으므로, 아래와 같은 내용을 추가로 작성하자

    ```gradle
    sourceCompatibility = 1.8
    targetCompatibility = 1.8
    dependencies {
        compile "joda-time:joda-time:2.2"
        testCompile "junit:junit:4.12"
    }
    ```

    - dependencies 블록을 이용해, Joda Time에 해당하는 단일 의존성을 명시했다.
    - 구체적으로 살펴보면, Joda-Time 라이브러리의 2.2버전을 요청한 것
    - compile?
      - 컴파일 의존성을 준 것은 컴파일 타임에 해당 라이브러리가 이용가능해야 한다는 의미
      - WAR파일과의 관계?
      - providedCompile: 프로젝트 코드를 컴파일 함에 있어서 의존성을 요구하지만, 컨테이너에 의해 런타임에 제공된다. 대표적인 예로는 Java Servelet API
      - testCompile: 컴파일과 테스트 실행에 사용되는 의존성. 빌드하거나, 프로젝트의 런타임 코드를 실행할 때에는 요구되지 않는다

  - 마지막으로, JAR Artifact의 이름을 명시하자
    ```gradle
    jar {
        baseName = 'gs-gradle'
        version = '0.1.0'
    }
    ```
    - jar 블록은 JAR 파일이 어떻게 이름지어질지 결정. 위의 경우, gs-gradle-0.1.0.jar로 생길 것
    - 다시 빌드해보면 성공적으로 빌드가 수행된다

##### Step 3. Build your project with Gradle Wrapper

---

- Gradle Wrapper는 Gradle Build를 위해 가장 선호되는 방법. 쉘 스크립트 (만약 윈도우라면 배치파일)로 구성되어 있으며. 이 스크립트는 시스템에 Gradle 설치 없이도 Gradle Build를 수행할 수 있게 해준다

  ```bash
  gradle wrapper --gradle-version 2.13
  ```

  - 실행하고 나면, 프로젝트 루트 폴더에 생긴 변화를 보자
    ```
    └── <project folder>
        └── gradlew
        └── gradlew.bat
        └── gradle
            └── wrapper
                └── gradle-wrapper.jar
                └── gradle-wrapper.properties
    ```
    - 이제 이 프로젝트를 Gradle Wrapper를 이용해 빌드할 수 있다.
    - 이 말은 이 프로젝트를 다른 사람이 클론해 간 뒤에 Gradle 설치 없이도 빌드할 수 있다는 뜻
      ```bash
      ./gradlew build
      ```
      - 처음 실행 시에는 특정 버전의 Gradle을 다운로드 받느라 시간이 좀 걸린다. 이후에는 해당 버전을 캐시해놓음

- 최종 결과

  ```
  build
    ├── classes
    │   └── main
    │       └── hello
    │           ├── Greeter.class
    │           └── HelloWorld.class
    ├── dependency-cache
    ├── libs
    │   └── gs-gradle-0.1.0.jar
    └── tmp
        └── jar
            └── MANIFEST.MF
  ```

  - 최종적으로 생성된 jar 파일을 확인하면

    ```bash
    jar tvf build/libs/gs-gradle-0.1.0.jar
         0 Mon Jul 06 21:15:52 KST 2020 META-INF/
        25 Mon Jul 06 20:46:24 KST 2020 META-INF/MANIFEST.MF
        0 Mon Jul 06 21:06:10 KST 2020 hello/
        988 Mon Jul 06 21:06:10 KST 2020 hello/HelloWorld.class
        369 Mon Jul 06 21:06:10 KST 2020 hello/Greeter.class
    ```

    - 주목할 점은, Joda-Time 의존성을 명시했지만 라이브러리에는 포함되지 않는다는 것! JAR 파일은 실행도 불가능하다
    - 실행할 수 있도록 만들기 위해, gradle의 application 플러그인을 사용하자
    - 아래의 내용을 build.gradle에 추가하자
      ```gradle
      apply plugin: 'application'
      mainClassName = 'hello.HelloWorld'
      ```
    - 추가 후, Gradle Wrapper로 실행해보면 정상적으로 실행된다
      ```bash
      ./gradlew run
      ```

- 의존성을 따질 때에는 좀 더 여러 생각을 해봐야 한다.
  - 예를 들어, WAR 파일을 빌드한다고 하면 주로 서드 파티 의존성을 포함하므로 Gradle의 WAR plugin을 사용할 수 있다. 만약 Spring Boot를 사용하고 실행가능한 JAR 파일을 원한다면 spring-boot-gradle-plugin이 좀 더 사용하기 편할 것이다.
