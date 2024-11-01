package me.dwywdo.labs.java.syntax.package_anonymous_class;

public class HelloWorldAnonymousClasses {
    interface HelloWorld {
        public void greet();

        public void greetSomeone(String someone);
    }

    public static void sayHello() {
        class EnglishGreeting implements HelloWorld {
            String name = "world";

            @Override
            public void greet() {
                greetSomeone("world");

            }

            @Override
            public void greetSomeone(String someone) {
                name = someone;
                System.out.println("Hello " + name);
            }
        }

        final HelloWorld englishGreeting = new EnglishGreeting();

        final HelloWorld frenchGreeting = new HelloWorld() {
            String name = "tout le monde";

            @Override
            public void greet() {
                greetSomeone("tout le monde");
            }

            @Override
            public void greetSomeone(String someone) {
                name = someone;
                System.out.println("Salut " + name);
            }
        };

        final HelloWorld spanishGreeting = new HelloWorld() {
            String name = "mundo";

            @Override
            public void greet() {
                greetSomeone("mundo");
            }

            @Override
            public void greetSomeone(String someone) {
                name = someone;
                System.out.println("Hola, " + name);
            }
        };

        englishGreeting.greet();
        frenchGreeting.greetSomeone("Fred");
        spanishGreeting.greet();
    }

    public static void main(String[] args) {
        sayHello();
    }
}
