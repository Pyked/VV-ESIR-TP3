package fr.istic.vv;

public class StringUtils {

    private StringUtils() {}

    public static boolean isBalanced(String str) {
        int balance_indicator = 0;  // Initialize balance_indicator to 0

        for (int i = 0; i < str.length(); i++) {
            char currentChar = str.charAt(i);
    
            if (currentChar == '{') {
                balance_indicator += 1;
            } else if (currentChar == '[') {
                balance_indicator += 2;
            } else if (currentChar == '(') {
                balance_indicator += 3;
            } else if (currentChar == '}') {
                balance_indicator -= 1;
            } else if (currentChar == ']') {
                balance_indicator -= 2;
            } else if (currentChar == ')') {
                balance_indicator -= 3;
            }
    
            // Check if the balance_indicator goes negative at any point
            if (balance_indicator < 0) {
                return false;
            }
        }
    
        // Check if the balance_indicator is zero after processing the entire string
        return balance_indicator == 0;
    }

}
