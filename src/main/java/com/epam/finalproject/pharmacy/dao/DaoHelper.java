package com.epam.finalproject.pharmacy.dao;

import com.epam.finalproject.pharmacy.connection.ConnectionPool;
import com.epam.finalproject.pharmacy.connection.ProxyConnection;
import com.epam.finalproject.pharmacy.dao.medicament.MedicamentDao;
import com.epam.finalproject.pharmacy.dao.medicament.MedicamentDaoImpl;
import com.epam.finalproject.pharmacy.dao.order.OrderDao;
import com.epam.finalproject.pharmacy.dao.order.OrderDaoImpl;
import com.epam.finalproject.pharmacy.dao.order.OrderDetailsDao;
import com.epam.finalproject.pharmacy.dao.order.OrderDetailsDaoImpl;
import com.epam.finalproject.pharmacy.dao.recipe.RecipeDao;
import com.epam.finalproject.pharmacy.dao.recipe.RecipeDaoImpl;
import com.epam.finalproject.pharmacy.dao.recipe.RecipeDtoDao;
import com.epam.finalproject.pharmacy.dao.recipe.RecipeDtoDaoImpl;
import com.epam.finalproject.pharmacy.dao.request.RequestDao;
import com.epam.finalproject.pharmacy.dao.request.RequestDaoImpl;
import com.epam.finalproject.pharmacy.dao.user.UserDao;
import com.epam.finalproject.pharmacy.dao.user.UserDaoImpl;
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

    public RecipeDao createRecipeDao() {
        logger.debug("RecipeDaoImpl was created");
        return new RecipeDaoImpl(connection);
    }

    public RecipeDtoDao createRecipeDtoDao() {
        logger.debug("RecipeDaoImpl was created");
        return new RecipeDtoDaoImpl(connection);
    }

    public RequestDao createRequestDao() {
        return new RequestDaoImpl(connection);
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
