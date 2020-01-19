package com.epam.finalproject.pharmacy.dao;

import com.epam.finalproject.pharmacy.connection.ConnectionPool;
import com.epam.finalproject.pharmacy.connection.ProxyConnection;
import com.epam.finalproject.pharmacy.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

public class DaoHelper implements AutoCloseable {

    private static final Logger logger = LogManager.getLogger(DaoHelper.class.getName());

    private ProxyConnection connection;

    public DaoHelper(ConnectionPool pool) throws DaoException {
        try {
            this.connection = pool.getConnection();
        } catch (SQLException e) {
            logger.warn(e);
            throw new DaoException(e);
        }
    }

    public UserDao createUserDao() {
        logger.debug("UserDaoImpl was created");

        return new UserDaoImpl(connection);
    }

    public MedicamentDao createMedicamentDao() {
        logger.debug("MedicamentDaoImpl was created");

        return new MedicamentDaoImpl(connection);
    }

    public OrderDao createOrderDao() {
        logger.debug("OrderDaoImpl was created");

        return new OrderDaoImpl(connection);
    }

    public OrderDetailsDao createOrderDetailsDao() {
        logger.debug("OrderDetailsDaoImpl was created");

        return new OrderDetailsDaoImpl(connection);
    }

    @Override
    public void close(){
        connection.close();
    }

    public void startTransaction() throws DaoException {
        try{
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
