package com.epam.finalproject.pharmacy.entity;

import java.math.BigDecimal;

public class Medicament implements Identifable{
    private final String name;
    private final boolean needRecipe;
    private final BigDecimal price;
    private int amount;


    public Medicament(String name, boolean needRecipe, BigDecimal price, int amount) {
        this.name = name;
        this.needRecipe = needRecipe;
        this.price = price;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public boolean isNeedRecipe() {
        return needRecipe;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public long getId() {
        return 0;
    }
}
