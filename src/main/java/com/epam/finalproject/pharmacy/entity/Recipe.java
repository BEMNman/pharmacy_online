package com.epam.finalproject.pharmacy.entity;

import java.time.LocalDate;

public class Recipe implements Identifable {
    public final static String NAME_TABLE_IN_DB = "recipes";
    public final static String COLUMN_ID = "id";
    public final static String COLUMN_CREATION_DATE = "creationDate";
    public final static String COLUMN_EXP_DATE = "expDate";
    public final static String COLUMN_MEDICAMENT_ID = "medicamentId";
    public final static String COLUMN_AMOUNT = "amount";
    public final static String COLUMN_PATIENT_ID = "patientId";
    public final static String COLUMN_DOCTOR_ID = "doctorId";

    private final Long id;
    private final LocalDate createdDate;
    private LocalDate expDate;
    private final Long medicamentId;
    private final Integer quantity;
    private final Long patientId;
    private final Long doctorId;


    public Recipe(Long id, LocalDate createdDate, LocalDate expDate, Long medicamentId, Integer quantity,
                  Long patientId, Long doctorId) {
        this.id = id;
        this.createdDate = createdDate;
        this.expDate = expDate;
        this.medicamentId = medicamentId;
        this.quantity = quantity;
        this.patientId = patientId;
        this.doctorId = doctorId;
    }

    public Recipe(LocalDate expDate, Long medicamentId, Integer quantity, Long patientId, Long doctorId) {
        this.id = null;
        this.createdDate = LocalDate.now();
        this.expDate = expDate;
        this.medicamentId = medicamentId;
        this.quantity = quantity;
        this.patientId = patientId;
        this.doctorId = doctorId;
    }

    public static Recipe newRecipe(LocalDate expDate, Long medicamentId,
                                   Integer quantity, Long patientId, Long doctorId) {
        return new Recipe(expDate, medicamentId, quantity, patientId, doctorId);
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public LocalDate getExpDate() {
        return expDate;
    }

    public void setExpDate(LocalDate expDate) {
        this.expDate = expDate;
    }

    public Long getMedicamentId() {
        return medicamentId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Long getPatientId() {
        return patientId;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    @Override
    public Long getId() {
        return id;
    }
}
