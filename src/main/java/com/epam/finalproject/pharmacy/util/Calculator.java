package com.epam.finalproject.pharmacy.util;

import com.epam.finalproject.pharmacy.entity.Medicament;

import java.math.BigDecimal;
import java.util.Map;

public class Calculator {

    public BigDecimal calculateTotalPrice(Map<Medicament, Integer> medicinesCountInBasket) {
        BigDecimal totalPrice = new BigDecimal(0);
        for (Medicament medicament : medicinesCountInBasket.keySet()) {
            Integer amountItem = medicinesCountInBasket.get(medicament);
            BigDecimal totalPriceItem = medicament.getPrice().multiply(new BigDecimal(amountItem));
            totalPrice = totalPrice.add(totalPriceItem);
        }
        return totalPrice;
    }

    public int calculateStartRow(int currentPage, int quantityItemOnPage, int quantityRows) {
        int maxNumberPage = calculateMaxPage(quantityItemOnPage, quantityRows);
        if(currentPage <= 0) {
            return  0;
        } else {
            if (currentPage > maxNumberPage) {
                return (quantityRows / quantityItemOnPage) * quantityItemOnPage;
            }
        }
        return (currentPage - 1) * quantityItemOnPage;
    }

    public int calculateMaxPage(int quantityItemOnPage, int quantityRows) {
        return quantityRows / quantityItemOnPage + (quantityRows % quantityItemOnPage > 0 ? 1 : 0);
    }
}
