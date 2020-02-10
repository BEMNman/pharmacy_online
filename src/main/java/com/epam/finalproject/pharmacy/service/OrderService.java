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

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class OrderService {

    private DaoHelperFactory daoHelperFactory;

    public OrderService(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    public void saveNewOrderWithDetailsForUser(User user, Map<Medicament, Integer> medicinesInOrder) throws ServerException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            OrderDao orderDao = daoHelper.createOrderDao();
            BigDecimal totalPriceOrder = new BigDecimal(0);
            for (Medicament medicament : medicinesInOrder.keySet()) {
                BigDecimal quantity = new BigDecimal(medicinesInOrder.get(medicament));
                BigDecimal tempPrice = medicament.getPrice().multiply(quantity);
                totalPriceOrder = tempPrice.add(totalPriceOrder);
            }
            Long userId = user.getId();
            Order order = Order.newOrder(userId, totalPriceOrder);
            daoHelper.startTransaction();
            long newOrderId = orderDao.saveAndGetIdLastSavedOrder(order);
            OrderDetailsDao orderDetailsDao = daoHelper.createOrderDetailsDao();
            for (Medicament medicament : medicinesInOrder.keySet()) {
                Long medicamentId = medicament.getId();
                BigDecimal quantity = new BigDecimal(medicinesInOrder.get(medicament));
                BigDecimal totalPrice = medicament.getPrice().multiply(quantity);
                OrderDetails orderDetails = new OrderDetails(newOrderId, medicamentId, quantity.intValue(), totalPrice);
                orderDetailsDao.save(orderDetails);
            }
            daoHelper.endTransaction();
        } catch (DaoException e) {
            throw new ServerException(e);
        }
    }

    public List<Order> showAllForUser(User user) throws ServerException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            OrderDao orderDao = daoHelper.createOrderDao();
            long userId = user.getId();
            return orderDao.getAllOrdersForUser(userId);
        } catch (DaoException e) {
            throw new ServerException(e);
        }
    }
}
