package me.dwywdo.labs.java.supertype_token;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class SuperTypeToken {
    static class Sup<T> {
        T value;
    }

    static class Sub extends Sup<String> {

    }

    static class SubNested extends Sup<List<String>> {

    }

    public static void main(String[] args) throws NoSuchFieldException {
        Sup<String> s = new Sup<>();
        // s를 어딘가에 던지는 상황에서 받는 쪽에서 <T>가 뭐로 정의되었는지 알고 싶다.
        // 받는 쪽은 Sup<?> 같은 것으로 받았겠지...

        System.out.println(s.getClass().getDeclaredField("value").getType());
        // Object Type이 나온다? Reflection을 통해서도 정보를 알 수 없다.
        // Runtime 시에 이 정보가 Erasure에 의해 싹 사라져버리기 때문
        // 즉, Sup s = new Sup<>(); 으로 정의했던 경우랑 결과가 같음


        // 오브젝트 선언하는 곳에서 타입을 준 게 아니고, 상속하는 시점에서 타입이 정해진 경우
        // 가져올 수 있다.
        final Sub b = new Sub();
        Type t = b.getClass().getGenericSuperclass();
        // Sup<String>과 같은 것을 ParameterizedType이라 부른다.
        ParameterizedType ptype = (ParameterizedType) t;
        System.out.println("ptype.getActualTypeArguments() = " + ptype.getActualTypeArguments()[0]);

        // s와 b의 차이점은?
        // s는 클래스의 인스턴스를 만들면서 타입을 제공한 것
        // 이렇게 작성한 경우 바이트코드 호환성 문제로 인해 이 정보를 다 삭제한다.
        // b는 새로운 타입을 정의하면서 Generic Superclass을 상속하면서 타입을 지정 (고정, Reification 한 것)
        // 이렇게 작성한 경우에는 Reflection을 통해서 Runtime 시 접근할 수 있도록 바이트코드에 그 정보가 남아있다.
        // 타입을 지정하지 않은 경우에는 없다.

        // List<String>과 같은 것도 정확히 가져올 수 있다.
        final SubNested sb = new SubNested();
        Type sbt = sb.getClass().getGenericSuperclass();
        ParameterizedType sbptype = (ParameterizedType) sbt;
        System.out.println("ptype.getActualTypeArguments() = " + sbptype.getActualTypeArguments()[0]);
    }
}
