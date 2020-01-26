package com.epam.finalproject.pharmacy.service;

import com.epam.finalproject.pharmacy.dao.DaoHelper;
import com.epam.finalproject.pharmacy.dao.DaoHelperFactory;
import com.epam.finalproject.pharmacy.dao.order.OrderDao;
import com.epam.finalproject.pharmacy.dao.order.OrderDetailsDao;
import com.epam.finalproject.pharmacy.entity.Medicament;
import com.epam.finalproject.pharmacy.entity.Order;
import com.epam.finalproject.pharmacy.entity.OrderDetails;
import com.epam.finalproject.pharmacy.entity.User;
import com.epam.finalproject.pharmacy.exception.DaoException;
import com.epam.finalproject.pharmacy.exception.ServerException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class OrderService {

    private static final Logger logger = LogManager.getLogger(OrderService.class.getName());

    private OrderDao orderDao;
    private OrderDetailsDao orderDetailsDao;

    public OrderService(DaoHelperFactory daoHelperFactory) throws ServerException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            orderDao = daoHelper.createOrderDao();
            orderDetailsDao = daoHelper.createOrderDetailsDao();
        } catch (DaoException e) {
            logger.warn("UserDao wasn't created " + e);

            throw new ServerException(e);
        }
    }

    public Long saveNewOrderForUser(User user, Map<Medicament, Integer> medicinesInOrder) throws ServerException {
        BigDecimal totalPriceOrder = new BigDecimal(0);
        for (Medicament medicament : medicinesInOrder.keySet()) {
            BigDecimal quantity = new BigDecimal(medicinesInOrder.get(medicament));
            BigDecimal tempPrice = medicament.getPrice().multiply(quantity);
            totalPriceOrder = tempPrice.add(totalPriceOrder);
        }
        long userId = user.getId();
        Order order = Order.newOrder(userId, totalPriceOrder);
        try {
            return orderDao.saveAndGetIdLastSavedOrder(order);
        } catch (DaoException e) {
            logger.warn("Can't save order in DB: " + e);
            throw new ServerException(e);
        }
    }

    public List<Order> showAll() throws ServerException {
        try {
            return orderDao.findAll();
        } catch (DaoException e) {
            logger.warn("Can't get all orders from DB: " + e);

            throw new ServerException(e);
        }
    }

    public List<Order> showAllForUser(User user) throws ServerException {
        long userId = user.getId();

        try {
            return orderDao.getAllOrdersForUser(userId);
        } catch (DaoException e) {
            throw new ServerException(e);
        }
    }

    public void saveOrderDetails(long orderId, Map<Medicament, Integer> medicinesInOrder) throws ServerException {
        try {
            for (Medicament medicament : medicinesInOrder.keySet()) {
                Long medicamentId = medicament.getId();
                BigDecimal quantity = new BigDecimal(medicinesInOrder.get(medicament));
                BigDecimal totalPrice = medicament.getPrice().multiply(quantity);
                OrderDetails orderDetails = new OrderDetails(orderId, medicamentId, quantity.intValue(), totalPrice);
                orderDetailsDao.save(orderDetails);
            }
        } catch (DaoException e) {
            logger.warn("Can't save orderDetails in DB: " + e);
            throw new ServerException(e);
        }
    }
}
