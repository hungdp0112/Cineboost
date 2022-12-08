package com.org.app.util;

import java.text.SimpleDateFormat;

public class ValidationUtil {

    public static boolean isEmpty(String input){
        return input.equals("");
    }

    public static boolean isLenghtLess(String input, int lenght){
        return input.length() < lenght;
    }
    
    public static boolean isLengthMore(String input, int lenght){
        return input.length() > lenght;
    }
    
 
    public static boolean isLenghtEqual(String input, int lenght){
        return input.length() == lenght;
    }
    //định dạng email
    public static boolean isValidEmail(String input){
        return input.matches("^([\\w\\-\\.]+){2,64}\\@(\\w){2,255}(\\.[a-z]{2,10}){1,2}$");
    }
    
    public static boolean dienThoai(String input) {
        input = input.replaceAll("[^0-9]+", "");
        return (input.length() >= 9 && input.length() <=12);
    }
    
    public static boolean isValidDate(String date){
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        try {
            sdf.format(date);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}