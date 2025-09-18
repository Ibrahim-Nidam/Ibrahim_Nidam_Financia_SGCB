package utils;

import java.util.regex.Pattern;

public class ValidationUtils {
    private static final Pattern code_Pattern = Pattern.compile("^CPT-\\d{5}$");
    
    public static boolean isValidAccountCode(String code) {
        return code != null && code_Pattern.matcher(code).matches();
    }
    
    public static boolean isValidAmount(double amount) {
        return amount > 0;
    }
}