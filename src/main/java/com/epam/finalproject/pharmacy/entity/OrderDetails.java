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
    private final long orderId;
    private final long medicamentId;
    private final int quantity;
    private final BigDecimal price;

    public OrderDetails(long orderId, long medicamentId, int quantity, BigDecimal price) {
        this.orderId = orderId;
        this.medicamentId = medicamentId;
        this.quantity = quantity;
        this.price = price;
    }

    public OrderDetails(long id, long orderId, long medicamentId, int quantity, BigDecimal price) {
        this.id = id;
        this.orderId = orderId;
        this.medicamentId = medicamentId;
        this.quantity = quantity;
        this.price = price;
    }

    @Override
    public long getId() {
        return id;
    }

    public long getOrderId() {
        return orderId;
    }

    public long getMedicamentId() {
        return medicamentId;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

}
