package me.dwywdo.labs.java.supertype_token;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import me.dwywdo.labs.java.supertype_token.SpringTypeReferenceApplication.User;

public class SpringTypeReference {
    public static void main(String[] args) {
        // Spring 3.2 이상에서 사용 가능
        final ParameterizedTypeReference<?> typeRef = new ParameterizedTypeReference<List<Map<Set<Integer>, String>>>() {};
        System.out.println("typeRef.getType() = " + typeRef.getType());

        // 언제 사용하냐?
        RestTemplate rt = new RestTemplate();
        // 아래 요청은 에러가 난다.

        // final List<User> users = rt.getForObject("http://localhost:8080", List.class);

        // List.class는 뭔가? -> 제일 처음에 설명했던 Type Token
        // Generic Type이 무시가 되어서 List users -> 즉 LinkedHashMap으로 바뀐다
        // 왜? Type Token을 List로만 줬는데, 안에 User가 들어있다는 걸 알 수가 없다.
        // 근데 요청에 대한 응답이 JSON Object (Key, value) 쌍으로 왔다. 그래서 가장 근접하게 줄 수 있는 것이 Map
        // 따라서 Map Type으로 리턴되어버렸다. 근데 List<User>로 받으려면 안된다.
        // Type Token은 Generic 정보가 없는 경우 매우 유용하다. 즉 컬렉션이 아니라 단순 User를 받거나 하는 경우에는 쓸 수 있다.
        // System.out.println("user0 = " + users.get(0).getName());

        // 억지로 받게하려면 아래처럼 할 수 있지만 Typesafe하지 않다.
        List<Map> users = rt.getForObject("http://localhost:8080", List.class);
        System.out.println("users.get(0).get(\"name\") = " + users.get(0).get("name"));

        // 즉 컬렉션처럼 Generic 정보가 필요한 경우? ParameterizedTypeReference를 쓸 수 있다.
        List<User> users2 = rt.exchange(
                "http://localhost:8080",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<User>>() {}
        ).getBody();

        // 전부 다 출력해보자.
        users2.forEach(System.out::println);
        System.out.println("users.get(0).getName() = " + users2.get(0).getName());
    }
}
