package com.example.springbootvalidation.util;

public class StringUtil {

    private static String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
    public static boolean validateEmail(String email) {
        return email.matches(regex);
    }
}
