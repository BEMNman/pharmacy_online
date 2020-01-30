package com.epam.finalproject.pharmacy.entity;

import java.time.LocalDate;

public class Request implements Identifable {
    public static final String NAME_TABLE_IN_DB = "request";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_CREATION_DATE = "creationDate";
    public static final String COLUMN_RECIPE_ID = "recipeId";
    public static final String COLUMN_REQUESTED_PERIOD = "requestedPeriod";
    public static final String COLUMN_STATUS = "status";

    private final Long id;
    private final LocalDate creationDate;
    private final Long recipeId;
    private final Integer requestedPeriod;
    private RequestStatus status;

    private Request(LocalDate creationDate, Long recipeId, Integer requestedPeriod, RequestStatus status) {
        this.id = null;
        this.creationDate = creationDate;
        this.recipeId = recipeId;
        this.requestedPeriod = requestedPeriod;
        this.status = status;
    }

    public Request(Long id, LocalDate creationDate, Long recipeId, Integer requestedPeriod, RequestStatus status) {
        this.id = id;
        this.creationDate = creationDate;
        this.recipeId = recipeId;
        this.requestedPeriod = requestedPeriod;
        this.status = status;
    }

    public static Request newRequest(Long recipeId, Integer requestedPeriod) {
        return new Request(LocalDate.now(), recipeId, requestedPeriod, RequestStatus.NEW);
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public Long getRecipeId() {
        return recipeId;
    }

    public Integer getRequestedPeriod() {
        return requestedPeriod;
    }

    public RequestStatus getStatus() {
        return status;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }
}
