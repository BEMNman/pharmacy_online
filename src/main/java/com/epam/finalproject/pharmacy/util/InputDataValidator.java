package com.epam.finalproject.pharmacy.util;

public class InputDataValidator {

    public static boolean notNullOrEmpty(String ... params) {
        for(String string : params) {
            if(string == null || string.isEmpty()){
                return false;
            }
        }
        return true;
    }
}
