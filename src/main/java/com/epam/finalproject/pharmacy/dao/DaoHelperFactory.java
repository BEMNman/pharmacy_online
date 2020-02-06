package com.epam.finalproject.pharmacy.dao;

import com.epam.finalproject.pharmacy.connection.ConnectionPool;
import com.epam.finalproject.pharmacy.exception.DaoException;

public class DaoHelperFactory {

    public DaoHelper create() throws DaoException {
            return new DaoHelper(ConnectionPool.getInstance());
    }
}
