package com.epam.finalproject.pharmacy.dto;

import com.epam.finalproject.pharmacy.entity.Identifable;
import com.epam.finalproject.pharmacy.entity.RequestStatus;

import java.io.Serializable;
import java.time.LocalDate;

public class RequestDto implements Identifable, Serializable {

    public static final String COLUMN_MEDICAMENT_NAME = "medicamentName";
    public static final String COLUMN_PATIENT_NAME = "patientName";

    private final Long id;
    private final LocalDate creationDate;
    private final RequestStatus status;
    private final String medicamentName;
    private final String medicamentDosage;
    private final Integer quantity;
    private final String patientName;
    private final LocalDate expDate;
    private final Integer requestedPeriod;

    public RequestDto(Long id, LocalDate creationDate,
                      RequestStatus status, String medicamentName, String medicamentDosage,
                      Integer quantity, String patientName, LocalDate expDate, Integer requestedPeriod) {
        this.id = id;
        this.creationDate = creationDate;
        this.status = status;
        this.medicamentName = medicamentName;
        this.medicamentDosage = medicamentDosage;
        this.quantity = quantity;
        this.patientName = patientName;
        this.expDate = expDate;
        this.requestedPeriod = requestedPeriod;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public String getMedicamentName() {
        return medicamentName;
    }

    public String getMedicamentDosage() {
        return medicamentDosage;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getPatientName() {
        return patientName;
    }

    public LocalDate getExpDate() {
        return expDate;
    }

    public Integer getRequestedPeriod() {
        return requestedPeriod;
    }

    @Override
    public Long getId() {
        return id;
    }
}
