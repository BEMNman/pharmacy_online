package com.epam.finalproject.pharmacy.entity;

import java.math.BigDecimal;

public class Medicament implements Identifable {
    public static final String NAME_TABLE_IN_DB = "medicines";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_FORM = "form";
    public static final String COLUMN_DOSAGE = "dosage";
    public static final String COLUMN_RECIPE = "recipe";
    public static final String COLUMN_AMOUNT_IN_PACK = "amountInPack";
    public static final String COLUMN_PRICE = "price";
    public static final String COLUMN_QUANTITY_IN_STOCK = "quantity";

    private final long id;
    private final String name;
    private final MedicamentForm form;
    private final String dosage;
    private final boolean recipe;
    private int amountInPack;
    private final BigDecimal price;
    private int quantity;

    public Medicament(long id, String name, MedicamentForm form, String dosage, boolean recipe, int amountInPack, BigDecimal price, int quantity) {
        this.id = id;
        this.name = name;
        this.form = form;
        this.dosage = dosage;
        this.recipe = recipe;
        this.price = price;
        this.amountInPack = amountInPack;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public MedicamentForm getForm() {
        return form;
    }

    public String getDosage() {
        return dosage;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean isRecipe() {
        return recipe;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getAmountInPack() {
        return amountInPack;
    }

    public void setAmountInPack(int amountInPack) {
        this.amountInPack = amountInPack;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Medicament that = (Medicament) o;

        return getName() != null ? getName().equals(that.getName()) : that.getName() == null;
    }

    @Override
    public int hashCode() {
        return getName() != null ? getName().hashCode() : 0;
    }
}
