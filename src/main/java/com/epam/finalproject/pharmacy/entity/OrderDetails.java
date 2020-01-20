package com.epam.finalproject.pharmacy.entity;

import java.math.BigDecimal;

public class OrderDetails implements Identifable{

    public static final String NAME_TABLE_IN_DB = "orderdetails";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_ORDER_ID = "orderId";
    public static final String COLUMN_MEDICAMENT_ID = "medicamentId";
    public static final String COLUMN_QUANTITY = "quantity";
    public static final String COLUMN_PRICE = "price";

    private long id;
    private final Long orderId;
    private final Long medicamentId;
    private final Integer quantity;
    private final BigDecimal price;

    public OrderDetails(Long orderId, Long medicamentId, Integer quantity, BigDecimal price) {
        this.orderId = orderId;
        this.medicamentId = medicamentId;
        this.quantity = quantity;
        this.price = price;
    }

    public OrderDetails(Long id, Long orderId, Long medicamentId, Integer quantity, BigDecimal price) {
        this.id = id;
        this.orderId = orderId;
        this.medicamentId = medicamentId;
        this.quantity = quantity;
        this.price = price;
    }

    @Override
    public Long getId() {
        return id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Long getMedicamentId() {
        return medicamentId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

}
