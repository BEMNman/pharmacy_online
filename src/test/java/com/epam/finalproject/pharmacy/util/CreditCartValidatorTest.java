package com.epam.finalproject.pharmacy.util;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CreditCartValidatorTest {

    private final CreditCartValidator creditCartValidator = new CreditCartValidator();

    @DataProvider(name = "correctData")
    private Object[][] getCorrectDataCreditCart() {
        return new Object[][]{
                {"NAME SURNAME", "1234-1234-1234-1234", "11/22", "456"},
                {"NAME SURNAME", "1234123412341234", "12/20", "111"}
        };
    }

    @DataProvider(name = "incorrectName")
    private Object[][] getIncorrectNameCreditCart() {
        return new Object[][]{
                {"NAME  SURNAME", "1234-1234-1234-1234", "11/22", "456"},
                {"NAME SURNAME  ", "1234-1234-1234-1234", "11/22", "456"},
                {"  NAME SURNAME", "1234-1234-1234-1234", "11/22", "456"},
                {"A NAME SURNAME", "1234-1234-1234-1234", "11/22", "456"},
                {"123 SURNAME", "1234-1234-1234-1234", "11/22", "456"},
                {"ИМЯ ФАМИЛИЯ", "1234-1234-1234-1234", "11/22", "456"},
                {"name surname", "1234-1234-1234-1234", "11/22", "456"},
                {"NAME SuRNAME", "1234-1234-1234-1234", "11/22", "456"}
        };
    }

    @DataProvider(name = "incorrectNumber")
    private Object[][] getIncorrectNumberCreditCart() {
        return new Object[][]{
                {"NAME SURNAME", "1234-1234-1234-1234 0", "11/22", "456"},
                {"NAME SURNAME  ", "1ddd-1234-1234-1234", "11/22", "456"},
                {"NAME SURNAME", "1234|1234-1234+1234", "11/22", "456"}
        };
    }

    @DataProvider(name = "incorrectExpDate")
    private Object[][] getIncorrectExpDateCreditCart() {
        return new Object[][]{
                {"NAME SURNAME", "1234-1234-1234-1234", "1o/22", "456"},
                {"NAME SURNAME  ", "1111-1234-1234-1234", "11/2022", "456"},
                {"NAME SURNAME", "1234-1234-1234-1234", "11-22", "456"}
        };
    }

    @DataProvider(name = "incorrectCvv")
    private Object[][] getIncorrectCvvCreditCart() {
        return new Object[][]{
                {"NAME SURNAME", "1234-1234-1234-1234", "11/22", "1456"},
                {"NAME SURNAME  ", "1111-1234-1234-1234", "11/2022", "q56"},
                {"NAME SURNAME", "1234-1234-1234-1234", "11/22", "45"}
        };
    }

    @Test(dataProvider = "correctData")
    public void testIsValidShouldReturnTrueWhenAllInputDataForCreditCartCorrect(
            String cardName, String cardNumber, String expDate, String cvv) {
        boolean actualResult = creditCartValidator.isValid(cardName, cardNumber, expDate, cvv);

        Assert.assertTrue(actualResult);
    }

    @Test(dataProvider = "incorrectName")
    public void testIsValidShouldReturnFalseWhenInputDataForCreditCartContainsIncorrectCartName(
            String cardName, String cardNumber, String expDate, String cvv) {

        boolean actualResult = creditCartValidator.isValid(cardName, cardNumber, expDate, cvv);

        Assert.assertFalse(actualResult);
    }

    @Test(dataProvider = "incorrectNumber")
    public void testIsValidShouldReturnFalseWhenInputDataForCreditCartContainsIncorrectCartNumber(
            String cardName, String cardNumber, String expDate, String cvv) {

        boolean actualResult = creditCartValidator.isValid(cardName, cardNumber, expDate, cvv);

        Assert.assertFalse(actualResult);
    }

    @Test(dataProvider = "incorrectExpDate")
    public void testIsValidShouldReturnFalseWhenInputDataForCreditCartContainsIncorrectCartExpDate(
            String cardName, String cardNumber, String expDate, String cvv) {

        boolean actualResult = creditCartValidator.isValid(cardName, cardNumber, expDate, cvv);

        Assert.assertFalse(actualResult);
    }

    @Test(dataProvider = "incorrectCvv")
    public void testIsValidShouldReturnFalseWhenInputDataForCreditCartContainsIncorrectCartCvvCode(
            String cardName, String cardNumber, String expDate, String cvv) {

        boolean actualResult = creditCartValidator.isValid(cardName, cardNumber, expDate, cvv);

        Assert.assertFalse(actualResult);
    }
}