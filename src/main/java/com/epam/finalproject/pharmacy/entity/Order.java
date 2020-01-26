package com.epam.finalproject.pharmacy.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Order implements Identifable {

    public static final String NAME_TABLE_IN_DB = "orders";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_CREATION_DATE = "creationDate";
    public static final String COLUMN_USER_ID = "userId";
    public static final String COLUMN_PRICE = "price";
    public static final String COLUMN_STATUS = "status";

    private Long id;
    private final LocalDateTime creationDate;
    private final Long userId;
    private final BigDecimal price;

    private OrderStatus status;

    private Order(LocalDateTime creationDate, Long userId, BigDecimal price, OrderStatus status) {
        this.creationDate = creationDate;
        this.userId = userId;
        this.price = price;
        this.status = status;
    }

    public Order(Long id, LocalDateTime creationDate, Long userId, BigDecimal price, OrderStatus status) {
        this.id = id;
        this.creationDate = creationDate;
        this.userId = userId;
        this.price = price;
        this.status = status;
    }

    public static Order newOrder(Long userId, BigDecimal totalPrice) {
        return new Order(LocalDateTime.now(), userId, totalPrice, OrderStatus.PAID);
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public Long getUserId() {
        return userId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
