package com.epam.finalproject.pharmacy.util;

import com.epam.finalproject.pharmacy.entity.Medicament;

import java.math.BigDecimal;
import java.util.Map;

public class Calculator {
    public static BigDecimal getTotalPrice(Map<Medicament, Integer> medicinesCountInBasket) {
        BigDecimal totalPrice = new BigDecimal(0);
        for(Medicament medicament : medicinesCountInBasket.keySet()) {
            Integer amountItem = medicinesCountInBasket.get(medicament);
            BigDecimal totalPriceItem = medicament.getPrice().multiply(new BigDecimal(amountItem));
            totalPrice = totalPrice.add(totalPriceItem);
        }
        return totalPrice;
    }
}
