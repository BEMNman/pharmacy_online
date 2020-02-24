package com.epam.finalproject.pharmacy.util;

public class InputDataValidator {

    public boolean notNullOrEmpty(String ... params) {
        for(String string : params) {
            if(string == null || string.trim().isEmpty()){
                return false;
            }
        }
        return true;
    }
}
