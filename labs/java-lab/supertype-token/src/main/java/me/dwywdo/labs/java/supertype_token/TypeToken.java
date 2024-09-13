package me.dwywdo.labs.java.supertype_token;

public class TypeToken {
    static Object create() {
        return new Object();
    }

    static <T> T createWithGenerics(Class<T> clazz) throws InstantiationException, IllegalAccessException {
        return clazz.newInstance();
    }
    
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        final Object o = create();
        System.out.println("o.getClass() = " + o.getClass());

        final Object o2 = createWithGenerics(Object.class);
        final String s = createWithGenerics(String.class);
        // final Integer i = createWithGenerics(Integer.class); 실행이 되지 않는다. Integer 클래스에는 Integer() Default 생성자가 없기 때문.
        System.out.println("s.getClass() = " + s.getClass());
        System.out.println("o2.getClass() = " + o2.getClass());
    }
}
