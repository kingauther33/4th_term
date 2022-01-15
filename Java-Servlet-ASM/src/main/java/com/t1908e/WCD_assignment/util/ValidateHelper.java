package com.t1908e.WCD_assignment.util;

import java.util.regex.Pattern;

public class ValidateHelper {
    public static boolean checkValidEmail(String email) {
        String emailPattern = "^[a-z][a-z0-9_\\.]{5,32}@[a-z0-9]{2,}(\\.[a-z0-9]{2,4}){1,2}$";
        return Pattern.matches(emailPattern, email);
    }
}
