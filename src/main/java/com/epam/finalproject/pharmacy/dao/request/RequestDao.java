package com.epam.finalproject.pharmacy.dao.request;

import com.epam.finalproject.pharmacy.dao.Dao;
import com.epam.finalproject.pharmacy.entity.Request;

/**
 * The interface extends an {@link Dao}.
 * Now the interface doesn't contain description any method for
 * working with the database for objects {@link Request}.
 *
 * @author Gogolinsky
 * @see Dao
 * @see Request
 */

public interface RequestDao extends Dao<Request> {
}
