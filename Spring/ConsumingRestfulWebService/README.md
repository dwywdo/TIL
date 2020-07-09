### Consuming a Restful Web Service

#### Objective

---

- Spring의 RestTemplate을 사용해서 임의의 Spring Boot Quotation을 가져와보자
- [Spring Boot Quotation](https://gturnquist-quoters.cfapps.io/api/random)
  - 임의의 인용문을 가져올 수 있는 API 서버!

#### Prerequisite

---

- 15분
- IDE
- JDK 1.8 or later (나의 환경은 Open JDK 1.8.0)
- Gradle 4+ or Maven 3.2+

##### Step 1. Starting with Spring Initializer

---

- Spring Initializer 사용!
- 설정 캡처

  ![Init](./consuming-rest.png)

- Gradle로 빌드 시스템을 구성하여 프로젝트를 생성하였으므로, 프로젝트 내에는 build.gradle이 생겼을 것이다

##### Step 2. Fetching a REST Resource

---

- 프로젝트 셋업을 끝내고 나면, 다른 Restful Service를 Consuming하는 기능을 추가해보자
- https://gturnquist-quoters.cfapps.io/api/random 에서는 Spring Boot와 관련한 랜덤한 인용문을 JSON의 형태로 반환한다
- 브라우저를 통해 접속하거나 Curl로 요청을 보내면 아래의 데이터가 반환된다
  ```json
  {
    "type": "success",
    "value": {
      "id": 10,
      "quote": "Really loving Spring Boot, makes stand alone Spring apps easy."
    }
  }
  ```
- 그냥 데이터를 받아오는 것으로는 그렇게 유용하다고 볼 수 없다. 이 데이터를 좀 더 가지고 놀기 위해 Spring에서 제공하는 Template Class인 RestTemplate을 사용하자!
- RestTemplate은 **One-Line incantation**을 통해 Restful 서비스와 상호작용할 수 있고, 커스텀 도메인 타입과 데이터를 바인딩할 수도 있다
- 먼저, 필요한 데이터를 담을 도메인 클래스를 생성해보자.

  - src/main/java/com/example/consumingrest/Quote.java

    ```java
    package com.example.consumingrest;

    import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public class Quote {

      private String type;
      private Value value;

      public Quote() {
      }

      public String getType() {
        return type;
      }

      public void setType(String type) {
        this.type = type;
      }

      public Value getValue() {
        return value;
      }

      public void setValue(Value value) {
        this.value = value;
      }

      @Override
      public String toString() {
        return "Quote{" +
            "type='" + type + '\'' +
            ", value=" + value +
            '}';
      }
    }
    ```
