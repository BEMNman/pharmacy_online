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
    public static final String COLUMN_ARCHIVE = "archive";

    private final Long id;
    private final String name;
    private final MedicamentForm form;
    private final String dosage;
    private final boolean recipe;
    private final Integer amountInPack;
    private final BigDecimal price;
    private Integer quantity;
    private final boolean archive;

    public Medicament(Long id, String name, MedicamentForm form, String dosage, boolean recipe,
                      Integer amountInPack, BigDecimal price, Integer quantity, boolean archive) {
        this.id = id;
        this.name = name;
        this.form = form;
        this.dosage = dosage;
        this.recipe = recipe;
        this.price = price;
        this.amountInPack = amountInPack;
        this.quantity = quantity;
        this.archive = archive;
    }

    private Medicament(Long id, String name, MedicamentForm form, String dosage,
                       boolean recipe, Integer amountInPack, BigDecimal price, Integer quantity) {
        this.id = id;
        this.name = name;
        this.form = form;
        this.dosage = dosage;
        this.recipe = recipe;
        this.price = price;
        this.amountInPack = amountInPack;
        this.quantity = quantity;
        this.archive = false;
    }

    public static Medicament newMedicament(Long id, String name, MedicamentForm form, String dosage,
                                boolean recipe, Integer amountInPack, BigDecimal price, Integer quantity){
        return new Medicament(id, name, form, dosage, recipe, amountInPack, price, quantity);
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public boolean isRecipe() {
        return recipe;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Integer getAmountInPack() {
        return amountInPack;
    }

    public boolean isArchive() {
        return archive;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Medicament that = (Medicament) o;

        return getId() != null ? getId().equals(that.getId()) : that.getId() == null;
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }
}
