package me.dwywdo.labs.java.supertype_token;

import java.util.HashMap;
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
        Map<String, Object> map = new HashMap<>();
        void run() {
            map.put("a", "a");
            map.put("b", 1);
            // Integer i = map.get("b"); 캐스팅이 필요
            // 그렇다고 해서 아래처럼 캐스팅하면 매우 위험한 코드. 타입 안전성이 떨어짐
            // 실행 중 예상하지 못한 에러가 발생하는 경우를 막을 수 없다.
            final Integer i = (Integer) map.get("b");
            final String s = (String) map.get("a");
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

    }
}
