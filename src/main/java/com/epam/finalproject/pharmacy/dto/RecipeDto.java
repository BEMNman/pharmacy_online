package com.epam.finalproject.pharmacy.dto;

import com.epam.finalproject.pharmacy.entity.Identifable;

import java.io.Serializable;
import java.time.LocalDate;

public class RecipeDto implements Identifable, Serializable {

    public final static String COLUMN_PATIENT_NAME = "patientName";
    public final static String COLUMN_DOCTOR_NAME = "doctorName";
    public final static String COLUMN_REQUESTED = "requested";

    private final Long id;
    private final LocalDate createdDate;
    private final LocalDate expDate;
    private final String medicamentName;
    private final String medicamentDosage;
    private final Integer amount;
    private final String patientName;
    private final String doctorName;
    private final boolean requested;

    public RecipeDto(Long id, LocalDate createdDate, LocalDate expDate, String medicamentName, String medicamentDosage,
                     Integer amount, String patientName, String doctorName, boolean requested) {
        this.id = id;
        this.createdDate = createdDate;
        this.expDate = expDate;
        this.medicamentName = medicamentName;
        this.medicamentDosage = medicamentDosage;
        this.amount = amount;
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.requested = requested;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public LocalDate getExpDate() {
        return expDate;
    }

    public String getMedicamentName() {
        return medicamentName;
    }

    public Integer getAmount() {
        return amount;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public String getMedicamentDosage() {
        return medicamentDosage;
    }

    public boolean isRequested() {
        return requested;
    }
}
