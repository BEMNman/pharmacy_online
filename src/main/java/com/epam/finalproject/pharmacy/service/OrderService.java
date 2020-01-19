package com.epam.finalproject.pharmacy.service;

import com.epam.finalproject.pharmacy.dao.DaoHelper;
import com.epam.finalproject.pharmacy.dao.DaoHelperFactory;
import com.epam.finalproject.pharmacy.dao.OrderDao;
import com.epam.finalproject.pharmacy.entity.Medicament;
import com.epam.finalproject.pharmacy.entity.Order;
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

    public OrderService(DaoHelperFactory daoHelperFactory) throws ServerException {
        try(DaoHelper daoHelper = daoHelperFactory.create()) {
            this.orderDao = daoHelper.createOrderDao();
        } catch (DaoException e) {
            logger.warn("UserDao wasn't created "+ e);

            throw new ServerException(e);
        }
    }

    public long saveNewOrderForUser(User user, Map<Medicament, Integer> medicinesInOrder) throws ServerException  {
        BigDecimal totalPriceOrder = new BigDecimal(0);
        for (Medicament medicament: medicinesInOrder.keySet()) {
            BigDecimal amount = new BigDecimal(medicinesInOrder.get(medicament));
            BigDecimal tempPrice = medicament.getPrice().multiply(amount);
            totalPriceOrder = tempPrice.add(totalPriceOrder);
        }
        long userId = user.getId();
        Order order = Order.newOrder(userId, totalPriceOrder);
        try {
            return orderDao.saveAndGetIdLastSavedOrder(order);
        } catch (DaoException e) {
            logger.warn("Can't save order in DB: "+ e);
            throw new ServerException(e);
        }
    }

    public List<Order> showAll() throws ServerException {
        try {
            return orderDao.getAll();
        } catch (DaoException e) {
            logger.warn("Can't get all orders from DB: "+ e);

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
}
