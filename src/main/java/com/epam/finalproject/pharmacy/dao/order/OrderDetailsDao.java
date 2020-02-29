package com.epam.finalproject.pharmacy.dao.order;

import com.epam.finalproject.pharmacy.dao.Dao;
import com.epam.finalproject.pharmacy.entity.OrderDetails;

/**
 * The interface extends an {@link Dao}.
 * Now the interface doesn't contain description any method for
 * working with the database for objects {@link OrderDetails}.
 *
 * @author Gogolinsky
 * @see Dao
 * @see OrderDetails
 */

public interface OrderDetailsDao extends Dao<OrderDetails> {
}
