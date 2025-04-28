import java.util.Scanner;

final class Helper {
    // Shorthand input
    public static String input(String message) {
        Scanner s = new Scanner(System.in);
        System.out.println(message);
        return s.nextLine();
    }
    
    // checks if a character is a digit
    public static boolean isDigit(char c) {
        return (c >= '0' && c <= '9');
    }

    // Validates that a string is a positive integer
    public static boolean stringIsInt(String s) {
        boolean isInt = (s.length() > 0);
        for (int i = 0; i < s.length(); i++) {
            if (!isDigit(s.charAt(i))) {
                isInt = false;
            }
        }
        return isInt;
    }


    //Shorthand interger input with validation
    public static int inputInt(String message) {
        String tempInput = input(message);
        while (!stringIsInt(tempInput)) {
            tempInput = input("Input must be an integer!");
        }
        return Integer.parseInt(tempInput);
    }
}