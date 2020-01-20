package com.epam.finalproject.pharmacy.dto;

import com.epam.finalproject.pharmacy.entity.Identifable;
import com.epam.finalproject.pharmacy.entity.Medicament;

import java.io.Serializable;
import java.sql.Timestamp;

public class RecipeDto implements Identifable, Serializable {

//    public final static String COLUMN_MEDICAMENT_NAME = Medica;
//    public final static String COLUMN_AMOUNT = "amount";
    public final static String COLUMN_PATIENT_NAME = "patientName";
    public final static String COLUMN_DOCTOR_NAME = "doctorName";

    private final Long id;
    private final Timestamp createdDate;
    private final Timestamp expDate;
    private final String medicamentName;
    private final Integer amount;
    private final String patientName;
    private final String doctorName;

    public RecipeDto(Long id, Timestamp createdDate, Timestamp expDate,
                     String medicamentName, Integer amount, String patientName, String doctorName) {
        this.id = id;
        this.createdDate = createdDate;
        this.expDate = expDate;
        this.medicamentName = medicamentName;
        this.amount = amount;
        this.patientName = patientName;
        this.doctorName = doctorName;
    }

    public Long getId() {
        return id;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public Timestamp getExpDate() {
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
}
