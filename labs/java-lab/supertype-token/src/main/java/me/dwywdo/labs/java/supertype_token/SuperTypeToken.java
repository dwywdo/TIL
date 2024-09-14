package me.dwywdo.labs.java.supertype_token;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class SuperTypeToken {
    // NESTED STATIC CLASS = INNER CLASS???
    static class Sup<T> {
        T value;
    }

    /**
     * 하나의 Map에 여러 종류의 Object (타입) 들을 넣고 싶은 경우
     */
    static class TypesafeMap {
        Map<TypeReference<?>, Object> map = new HashMap<>();

        <T> void put(TypeReference<T> tr, T value) {
            map.put(tr, value);
        }

        <T> T get(TypeReference<T> tr) {
            // return (T).map.get(clazz);는 Typesafe한 방법이 아니다
            // clazz.case();는 타입 캐스팅을 명시적으로 해줄 수 있다.
            if (tr.type instanceof Class<?>) {
                return ((Class<T>)tr.type).cast(map.get(tr)); // TypeReference<String>으로 던질때는 문제가 없다. 하지만 List<String>은 동작하지 않는다.
            } else {
                return ((Class<T>)((ParameterizedType)tr.type).getRawType()).cast(map.get(tr)); // TypeReference<List<String>
            }
        }
    }

    static class TypeReference<T> { // Sup
        Type type;

        public TypeReference() {
            final Type stype = getClass().getGenericSuperclass();
            if (stype instanceof ParameterizedType) {
                this.type = ((ParameterizedType) stype).getActualTypeArguments()[0];
            } else throw new RuntimeException();
        }

        // hashCode를 같이 정의해줘야 더 빠르게 할 수 있다.
        // equals도 정의해주자

        @Override
        public boolean equals(Object o) {
            if (this == o) {return true;}
            if (o == null || getClass().getSuperclass() != o.getClass().getSuperclass()) {return false;}
            final TypeReference<?> that = (TypeReference<?>) o;
            return Objects.equals(type, that.type);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(type);
        }
    }

    public static void main(String[] args) throws NoSuchFieldException {
        Sup<String> s = new Sup<>();
        // s를 어딘가에 던지는 상황에서 받는 쪽에서 <T>가 뭐로 정의되었는지 알고 싶다.
        // 받는 쪽은 Sup<?> 같은 것으로 받았겠지...

        System.out.println(s.getClass().getDeclaredField("value").getType());
        // Object Type이 나온다? Reflection을 통해서도 정보를 알 수 없다.
        // Runtime 시에 이 정보가 Erasure에 의해 싹 사라져버리기 때문
        // 즉, Sup s = new Sup<>(); 으로 정의했던 경우랑 결과가 같음

        // LOCAL CLASS도 바꾸어도 똑같이 적용된다.
        // 하지만 메서드 안에서 한 번만 사용하는 것이기 때문에, 굳이 이것을 이름까지 주지 않아도 되지 않나?
        // 이름을 생각하면 ANONYMOUS CLASS로도 가능하지 않나? 그렇다! 아래에서는 Sub를 익명 클래스로 만들어보았다.

        // 순수한 익명 클래스가 되었다. 이 코드 자체가 내부적으로 임의의 클래스를 만들어 인스턴스를 하나 만들고 b에 어싸인한다.
        Sup b = new Sup<String>() {};

        class SubNested extends Sup<List<String>> {}

        // 오브젝트 선언하는 곳에서 타입을 준 게 아니고, 상속하는 시점에서 타입이 정해진 경우
        // 가져올 수 있다.
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

        // 아래는 RuntimeException. 왜냐면 TypeReference<String>의 String은 사라지니까 ParameterizedType이 아니다.
        // TypeReference tref = new TypeReference<String>();
        // System.out.println("tref.type = " + tref.type);

        // 아래처럼 익명클래스를 이용한 방식을 쓰면 타입을 가져오는 것이 가능하다!!
        TypeReference tref = new TypeReference<String>() {};
        System.out.println("tref.type = " + tref.type);

        // TypesafeMap을 다시 수정해보면...!
        final TypesafeMap m = new TypesafeMap();
        m.put(new TypeReference<Integer>() {} , 1);
        m.put(new TypeReference<String>() {} , "String");
        m.put(new TypeReference<List<Integer>>() {}, Arrays.asList(1, 2, 3));
        m.put(new TypeReference<List<String>>() {}, Arrays.asList("a", "b", "c"));

        System.out.println("m.get(new TypeReference<Integer>() {}) = " + m.get(new TypeReference<Integer>() {}));
        System.out.println("m.get(new TypeReference<String>() {}) = " + m.get(new TypeReference<String>() {}));
        System.out.println("m.get(new TypeReference<List<Integer>>() {}) = " + m.get(new TypeReference<List<Integer>>() {}));
        System.out.println("m.get(new TypeReference<List<String>>() {}) = " + m.get(new TypeReference<List<String>>() {}));

    }
}
