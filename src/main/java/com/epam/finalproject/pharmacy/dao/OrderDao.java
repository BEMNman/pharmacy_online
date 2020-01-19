package com.epam.finalproject.pharmacy.dao;

import com.epam.finalproject.pharmacy.entity.Order;
import com.epam.finalproject.pharmacy.exception.DaoException;

import java.util.List;

public interface OrderDao extends Dao<Order> {
    long saveAndGetIdLastSavedOrder(Order order) throws DaoException;

    List<Order> getAllOrdersForUser(long userId) throws DaoException;
}
