package com.epam.finalproject.pharmacy.service;

import com.epam.finalproject.pharmacy.dao.DaoHelper;
import com.epam.finalproject.pharmacy.dao.DaoHelperFactory;
import com.epam.finalproject.pharmacy.dao.OrderDetailsDao;
import com.epam.finalproject.pharmacy.entity.Medicament;
import com.epam.finalproject.pharmacy.entity.OrderDetails;
import com.epam.finalproject.pharmacy.exception.DaoException;
import com.epam.finalproject.pharmacy.exception.ServerException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
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

    public void saveOrderDetails(long orderId, Map<Medicament, Integer> medicinesInOrder) throws ServerException {
        try {
            for (Medicament medicament : medicinesInOrder.keySet()) {
                long medicamentId = medicament.getId();
                int amount = medicinesInOrder.get(medicament);
                BigDecimal totalPrice = medicament.getPrice().multiply(BigDecimal.valueOf(amount));
                OrderDetails orderDetails = new OrderDetails(orderId, medicamentId, amount, totalPrice);
                orderDetailsDao.save(orderDetails);
            }
        } catch (DaoException e) {
            logger.warn("Can't save orderDetails in DB: " + e);
            throw new ServerException(e);
        }
    }
}
