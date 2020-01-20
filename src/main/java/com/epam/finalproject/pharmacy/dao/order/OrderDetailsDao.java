package com.epam.finalproject.pharmacy.dao.order;

import com.epam.finalproject.pharmacy.dao.Dao;
import com.epam.finalproject.pharmacy.entity.Order;
import com.epam.finalproject.pharmacy.entity.OrderDetails;
import com.epam.finalproject.pharmacy.exception.DaoException;

public interface OrderDetailsDao extends Dao<OrderDetails> {

    void saveDetailsForOrder(Order order, OrderDetails orderDetails) throws DaoException;
}
