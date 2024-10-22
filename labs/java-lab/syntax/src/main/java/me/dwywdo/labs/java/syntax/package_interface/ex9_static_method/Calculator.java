package me.dwywdo.labs.java.syntax.package_interface.ex9_static_method;

interface Calculator {
    public int plus(int a, int b);
    public int multiply(int a, int b);

    default int sub(int i, int j) {
        return i - j;
    }

    public static void explain() {
        System.out.println("This is a static method in 'Calculator' interface.\n"
                           + "This interface has plus / multiply / sub functionality as APIs");
    }
}
