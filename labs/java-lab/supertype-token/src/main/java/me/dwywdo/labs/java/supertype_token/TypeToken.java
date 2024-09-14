package me.dwywdo.labs.java.supertype_token;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TypeToken {
    static Object create() {
        return new Object();
    }

    static <T> T createWithGenerics(Class<T> clazz) throws InstantiationException, IllegalAccessException {
        // Class<T>의 타입 정보를 정확하게 넘기니까, 별도의 캐스팅을 하지 않고 clazz.newInstance()가 가능하다
        // return (T)clazz.newInstance();
        return clazz.newInstance();
    }

    static class Generic<T> {
        private T value;

        void set(T value) {
            this.value = value;
        }

        T get() {
            return value;
        }
    }

    /**
     * 하나의 Map에 여러 종류의 Object (타입) 들을 넣고 싶은 경우
     */
    static class TypesafeMap {
        Map<Class<?>, Object> map = new HashMap<>();

        <T> void put(Class<T> clazz, T value) {
            map.put(clazz, value);
        }

        <T> T get(Class<T> clazz) {
            // return (T).map.get(clazz);는 Typesafe한 방법이 아니다
            // clazz.case();는 타입 캐스팅을 명시적으로 해줄 수 있다.
            return clazz.cast(map.get(clazz));
        }
    }
    
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        final Object o = create();
        System.out.println("o.getClass() = " + o.getClass());

        final Object o2 = createWithGenerics(Object.class);
        final String s = createWithGenerics(String.class);
        // final Integer i = createWithGenerics(Integer.class); 실행이 되지 않는다. Integer 클래스에는 Integer() Default 생성자가 없기 때문.
        System.out.println("s.getClass() = " + s.getClass());
        System.out.println("o2.getClass() = " + o2.getClass());


        // final Generic<String> sGeneric = new Generic<String>(); -> Diamond 타입으로 생략할 수 있다.
        final Generic<String> sGeneric = new Generic<>();
        sGeneric.set("sGeneric String");
        System.out.println("sGeneric.value = " + sGeneric.value);

        final Generic<Integer> iGeneric = new Generic<>();
        iGeneric.set(10);
        System.out.println("iGeneric.value = " + iGeneric.value);

        System.out.println("iGeneric.value.getClass() = " + iGeneric.value.getClass());

        // 아래 코드는 캐스팅을 잘못 사용해서 컴파일은 되는 것처럼 보이지만, 런타임에서 문제가 발생
        // ClassCastException이 발생한다.
        /*
        final Object stringObject = "String";
        final Integer i = (Integer) stringObject;
        System.out.println("i = " + i);
        */

        final TypesafeMap m = new TypesafeMap();
        m.put(String.class, "Value");
        m.put(Integer.class, 1); // 이제 해당 타입에 맞는 값을 잘 넣을 수 있다 Typesafe하게!
        m.put(List.class, Arrays.asList(1, 2, 3)); // List<Integer>
        m.put(List.class, Arrays.asList("a", "b", "c")); // 덮어씌워져 버린다. 나는 List<String>을 별도로 넣고 싶은 건데...
        /**
         * 그렇다고 해서 아래처럼 작성하면 에러가 난다
         * m.put(List<Integer>.class, Arrays.asList(1,2,3));
         * Compile Error Message: Cannot select from parameterized type
         * Class literal로 클래스에 대한 클래스 오브젝트를 가져올 때, 타입 파라미터를 적용해서 구분되어서 가져올 수는 없다 ㅠㅠ
         * Class라는 타입에는 Generic에 대한 타입 변수 정보가 없기 때문이다...
         */

        final String s1 = m.get(String.class);
        System.out.println("s1 = " + s1);

        System.out.println("m.get(List.class) = " + m.get(List.class));
    }
}
