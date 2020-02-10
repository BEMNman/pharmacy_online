package com.epam.finalproject.pharmacy.dao;

import com.epam.finalproject.pharmacy.connection.ConnectionPool;

public class DaoHelperFactory {

    public DaoHelper create(){
            return new DaoHelper(ConnectionPool.getInstance());
    }
}
