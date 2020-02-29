package com.epam.finalproject.pharmacy.dao;

import com.epam.finalproject.pharmacy.entity.Identifable;
import com.epam.finalproject.pharmacy.exception.DaoException;

import java.util.Optional;

/**
 * The interface extends an {@link Identifable}.
 * The interface is intended to describe the general method for
 * working with the database.
 * The interface should be implemented by all DAO objects.
 *
 * @author Gogolinsky
 * @see Identifable
 */

public interface Dao<T extends Identifable> {

    /**
     * Returns {@link Optional} object with is result of SQL request.
     * If there isn't DAO object with <code>id</code> in database
     * the method returns <code>optional</code> with an empty value.
     *
     * @param id type is <code>Long</code>.
     *           <code>id</code> is an identifier of the object in the database.
     * @return {@link Optional} which contains an object found in the database.
     * If that object wasn't found in the database {@link Optional} is empty.
     * If object was found {@link Optional} contains object type <T>.
     * @throws DaoException if an exception was thrown during the execution of SQL request.
     */
    Optional<T> findById(Long id) throws DaoException;

    /**
     * The method saves <code>item</code> in database.
     *
     * @param item is object to be saved in database.
     * @throws DaoException if an exception was thrown during the execution of SQL request.
     */
    void save(T item) throws DaoException;
}