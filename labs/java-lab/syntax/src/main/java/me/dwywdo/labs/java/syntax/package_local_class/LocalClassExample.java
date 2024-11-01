package me.dwywdo.labs.java.syntax.package_local_class;

public class LocalClassExample {
    static String regularExpression = "[^0-9]";

    public static void validatePhoneNumber(
            String phoneNumber1,
            String phoneNumber2
    ) {
        int numberLength = 10;

        class PhoneNumber {
            String formattedPhoneNumber = null;

            PhoneNumber(String phoneNumber) {
                // numberLength = 7; <- Effectively Final 하지 않게 된다.
                final String currentNumber = phoneNumber.replaceAll(regularExpression, ""); // Directly access
                if (currentNumber.length() == numberLength) {
                    formattedPhoneNumber = currentNumber;
                } else {
                    formattedPhoneNumber = null;
                }
            }

            public String getNumber() {
                return formattedPhoneNumber;
            }

            public void printOriginalNumbers() {
                System.out.println("phoneNumber1 = " + phoneNumber1);
                System.out.println("phoneNumber2 = " + phoneNumber2);
            }
        }

        final PhoneNumber myNumber1 = new PhoneNumber(phoneNumber1);
        final PhoneNumber myNumber2 = new PhoneNumber(phoneNumber2);

        myNumber1.printOriginalNumbers();
        myNumber2.printOriginalNumbers();

        if (myNumber1.getNumber() == null) {
            System.out.println("First number is invalid");
        } else {
            System.out.println("First number is " + myNumber1.getNumber());
        }

        if (myNumber2.getNumber() == null) {
            System.out.println("Second number is invalid");
        } else {
            System.out.println("Second number is " + myNumber2.getNumber());
        }
    }

    public static void main(String[] args) {
        validatePhoneNumber("123-456-7890", "456-7890");
    }
}
