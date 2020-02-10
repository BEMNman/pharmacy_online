package com.epam.finalproject.pharmacy.connection;

import com.epam.finalproject.pharmacy.exception.CreateConnectionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    private static final Logger logger = LogManager.getLogger(ProxyConnection.class.getName());

    private static final URL PATH_PROPERTY_FILE = ConnectionFactory.class
                                                                   .getClassLoader()
                                                                   .getResource("database.properties");
    private static final String DRIVER = "db.driver";
    private static final String URL = "db.url";
    private static final String USER = "db.user";
    private static final String PASSWORD = "db.password";

    private final String url;
    private final String user;
    private final String pass;

    public ConnectionFactory() {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream(PATH_PROPERTY_FILE.getFile())) {
            properties.load(fis);
            String driver = properties.getProperty(DRIVER);
            Class.forName(driver);
            url = properties.getProperty(URL);
            user = properties.getProperty(USER);
            pass = properties.getProperty(PASSWORD);
        } catch (ClassNotFoundException | IOException e) {
            logger.error(e);
            throw new CreateConnectionException(e);
        }
    }

    public ProxyConnection createProxyConnection() {
        try {
            Connection connection = DriverManager.getConnection(url, user, pass);
            return new ProxyConnection(connection, ConnectionPool.getInstance());
        } catch (SQLException e) {
            logger.error(e);
            throw new CreateConnectionException(e);
        }
    }
}
