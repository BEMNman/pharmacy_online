package com.epam.finalproject.pharmacy.util;

public class CreditCartValidator {
    private static final String REG_EXP_FORMAT_OWNERS_NAME = "[A-Z]+ [A-Z]+";
    private static final String REG_EXP_FORMAT_NUMBER = "(\\d{4}[ -]?){3}\\d{4}";
    private static final String REG_EXP_FORMAT_EXP_DATE = "\\d{2}/\\d{2}";
    public static final String REG_EXP_FORMAT_CVV_CODE = "\\d{3}";

    public static boolean isValid(String cardName, String cardNumber, String expDate, String cvv) {
        return cardName.matches(REG_EXP_FORMAT_OWNERS_NAME)
                && cardNumber.matches(REG_EXP_FORMAT_NUMBER)
                && expDate.matches(REG_EXP_FORMAT_EXP_DATE)
                && cvv.matches(REG_EXP_FORMAT_CVV_CODE);
    }
}
