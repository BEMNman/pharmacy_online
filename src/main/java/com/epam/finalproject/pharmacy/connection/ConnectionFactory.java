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
import java.util.ArrayDeque;
import java.util.Properties;
import java.util.Queue;

public class ConnectionFactory {

    private static final Logger logger = LogManager.getLogger(ProxyConnection.class.getName());

    private static final URL PATH_PROPERTY_FILE = ConnectionFactory.class
            .getClassLoader()
            .getResource("database.properties");
    private static final String DRIVER = "db.driver";
    private static final String URL = "db.url";
    private static final String USER = "db.user";
    private static final String PASSWORD = "db.password";
    private static final String POOL_SIZE = "db.poolsize";

    public static Queue<ProxyConnection> createPoolConnections() {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream(PATH_PROPERTY_FILE.getFile())) {
            properties.load(fis);
            String driver = properties.getProperty(DRIVER);
            Class.forName(driver);

            String url = properties.getProperty(URL);
            String user = properties.getProperty(USER);
            String pass = properties.getProperty(PASSWORD);

            Queue<ProxyConnection> proxyConnections = new ArrayDeque<>();
            String poolSizeString = properties.getProperty(POOL_SIZE);
            int poolSize = Integer.parseInt(poolSizeString);
            Connection connection = DriverManager.getConnection(url, user, pass);
            ProxyConnection proxyConnection = new ProxyConnection(connection, ConnectionPool.getInstance());
            for (int i = 0; i < poolSize; i++) {
                proxyConnections.add(proxyConnection);
            }
            return proxyConnections;
        } catch (ClassNotFoundException | IOException | SQLException e) {
            logger.warn(e);
            throw new CreateConnectionException(e);
        }
    }
}
