package com.epam.finalproject.pharmacy.service;

import com.epam.finalproject.pharmacy.dao.DaoHelper;
import com.epam.finalproject.pharmacy.dao.DaoHelperFactory;
import com.epam.finalproject.pharmacy.dao.order.OrderDetailsDao;
import com.epam.finalproject.pharmacy.entity.Medicament;
import com.epam.finalproject.pharmacy.entity.OrderDetails;
import com.epam.finalproject.pharmacy.exception.DaoException;
import com.epam.finalproject.pharmacy.exception.ServerException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class OrderDetailsService {

    private static final Logger logger = LogManager.getLogger(OrderDetailsService.class.getName());

    private OrderDetailsDao orderDetailsDao;

    public OrderDetailsService(DaoHelperFactory daoHelperFactory) throws ServerException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            this.orderDetailsDao = daoHelper.createOrderDetailsDao();
        } catch (DaoException e) {
            logger.warn("OrderDetailsDao wasn't created " + e);

            throw new ServerException(e);
        }
    }

    public void saveOrderDetails(long orderId, Map<Medicament, Integer> medicinesInOrder,
                                 String[] correctedMedicinesQuantity) throws ServerException {
        if(medicinesInOrder.size() != correctedMedicinesQuantity.length) {
            throw new ServerException("Invalid data received");
        }

        try {
            int startIndexQuantity = 0;
            for (Medicament medicament : medicinesInOrder.keySet()) {
                Long medicamentId = medicament.getId();
                Integer quantity = new Integer(correctedMedicinesQuantity[startIndexQuantity++]);
                BigDecimal totalPrice = medicament.getPrice().multiply(BigDecimal.valueOf(quantity));
                OrderDetails orderDetails = new OrderDetails(orderId, medicamentId, quantity, totalPrice);
                orderDetailsDao.save(orderDetails);
            }
        } catch (DaoException e) {
            logger.warn("Can't save orderDetails in DB: " + e);
            throw new ServerException(e);
        }
    }
}
