package com.epam.finalproject.pharmacy.util;

import org.testng.Assert;
import org.testng.annotations.Test;

public class InputDataValidatorTest {

    private final InputDataValidator inputDataValidator = new InputDataValidator();

    @Test
    public void testNotNullOrEmptyShouldReturnTrueWhenInputStringParamsNotContainsNullValueAndEmpty() {
        String[] params = {"testString", "12", "PILL", "string test"};

        boolean actualResult = inputDataValidator.notNullOrEmpty(params);

        Assert.assertTrue(actualResult);
    }

    @Test
    public void testNotNullOrEmptyShouldReturnFalseWhenInputStringParamsContainsNullValue() {
        String[] params = {"testString", "12", "PILL", null, "string test"};

        boolean actualResult = inputDataValidator.notNullOrEmpty(params);

        Assert.assertFalse(actualResult);
    }

    @Test
    public void testNotNullOrEmptyShouldReturnFalseWhenInputStringParamsContainsEmptyValue() {
        String[] params = {"testString", "12", "PILL", "   ", "string test"};

        boolean actualResult = inputDataValidator.notNullOrEmpty(params);

        Assert.assertFalse(actualResult);
    }
}