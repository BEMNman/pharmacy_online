package com.epam.finalproject.pharmacy.dao.order;

import com.epam.finalproject.pharmacy.dao.Dao;
import com.epam.finalproject.pharmacy.entity.Order;
import com.epam.finalproject.pharmacy.exception.DaoException;

import java.util.List;

/**
 * The interface extends an {@link Dao}.
 * The interface contains description the general methods for
 * working with the database for objects {@link Order}.
 *
 * @author Gogolinsky
 * @see Dao
 * @see Order
 */

public interface OrderDao extends Dao<Order> {

    /**
     * The method saves <code>order</code> {@link Order} in the database
     * and returns id of saved row.
     *
     * @param order type is {@link Order}.
     *              <code>order</code> which is saved in the database
     * @return identifier <code>id</code> with type {@link Long} of new order
     * which was saved in database.
     * @throws DaoException if an exception was thrown during the execution of SQL request.
     */
    Long saveAndGetIdLastSavedOrder(Order order) throws DaoException;

    /**
     * The method finds all orders {@link com.epam.finalproject.pharmacy.entity.Order}
     * in the database for <code>user</code>.
     *
     * @param userId type is {@link Long}.
     *               <code>userId</code> is id for user for which we are looking for orders.
     * @return {@link List} with objects {@link Order} which belong to the user
     * with <code>userId</code>.
     * If objects {@link Order} weren't found, the list will be empty.
     * @throws DaoException if an exception was thrown during the execution of SQL request.
     */
    List<Order> getAllOrdersForUser(Long userId) throws DaoException;
}
