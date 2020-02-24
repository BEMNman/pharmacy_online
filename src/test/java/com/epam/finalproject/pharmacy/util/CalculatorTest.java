package com.epam.finalproject.pharmacy.util;

import com.epam.finalproject.pharmacy.entity.Medicament;
import com.epam.finalproject.pharmacy.entity.MedicamentForm;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

public class CalculatorTest {

    private final Calculator calculator = new Calculator();

    @Test
    public void testCalculateTotalPriceShouldReturnTenTwentyFourWhenCountMedicinesFourWithPriceTwoFiftySix() {
        double delta  = 0.00001;
        Medicament medicament = Medicament.newMedicament(null,"TestName", MedicamentForm.PILL, "10", false, 20, new BigDecimal(2.56), 100);
        Integer countMedicament = 4;
        Map<Medicament, Integer> medicinesCountInBasket = new LinkedHashMap<>();
        medicinesCountInBasket.put(medicament, countMedicament);
        BigDecimal expectedTotalPrice = new BigDecimal("10.24");

        BigDecimal actualTotalPrice = calculator.calculateTotalPrice(medicinesCountInBasket);

        Assert.assertEquals(actualTotalPrice.doubleValue(), expectedTotalPrice.doubleValue(), delta);
    }

    @DataProvider(name = "testDataForCalculateStartRow")
    private Object[][] getDataForCalculateStartRow() {
        return new Object[][]{
                {2, 30, 124, 30},
                {0, 30, 124, 0},
                {6, 30, 124, 120}
        };
    }

    @Test(dataProvider = "testDataForCalculateStartRow")
    public void testCalculateStartRowShouldReturnExpectedResultWhenInputDataContainsBoundaryConditions(
            int currentPage, int quantityItemOnPage, int quantityRows, int expectedStartRow) {

        int actualStartRow = calculator.calculateStartRow(currentPage, quantityItemOnPage, quantityRows);

        Assert.assertEquals(actualStartRow, expectedStartRow);
    }

    @Test
    public void testCalculateMaxPageShouldReturnEightWhenQuantityItemOnPageThirtyAndQuantityRowsTwoHundredTwentyOne() {
        int quantityItemOnPage = 30;
        int quantityRows = 221;
        int expectedMaxPage = 8;

        int actualMaxPage = calculator.calculateMaxPage(quantityItemOnPage, quantityRows);

        Assert.assertEquals(actualMaxPage, expectedMaxPage);
    }
}