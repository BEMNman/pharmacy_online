package com.epam.finalproject.pharmacy.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {

    private static final Logger logger = LogManager.getLogger(ConnectionPool.class.getName());

    private static final int POOL_SIZE = 10;
    private Queue<ProxyConnection> availableConnection;
    private Queue<ProxyConnection> connectionsInUse;

    private static final AtomicReference<ConnectionPool> instance = new AtomicReference<>(null);
    private static final ReentrantLock connectionLock = new ReentrantLock();
    private static final ReentrantLock instanceLock = new ReentrantLock();
    private static final Semaphore SEMAPHORE = new Semaphore(POOL_SIZE);

    private ConnectionPool() {
        availableConnection = new ArrayDeque<>();
        connectionsInUse = new ArrayDeque<>();
    }

    public static ConnectionPool getInstance() {
        ConnectionPool instanceTemp = null;
        if (instance.get() == null) {
            instanceLock.lock();
            try {
                if (instance.get() == null) {
                    instanceTemp = new ConnectionPool();
                    instance.set(instanceTemp);
                }
            } finally {
                instanceLock.unlock();
            }
        }
        return instance.get();
    }

    public ProxyConnection getConnection() {
        ProxyConnection proxyConnection = null;
        try {
            connectionLock.lock();
            if (availableConnection.size() + connectionsInUse.size() == 0) {
                ConnectionFactory connectionFactory = new ConnectionFactory();
                for (int i = 0; i < POOL_SIZE; i++) {
                    availableConnection.add(connectionFactory.createProxyConnection());
                }
            }
            SEMAPHORE.acquire();
            proxyConnection = availableConnection.poll();
            connectionsInUse.add(proxyConnection);
        } catch (InterruptedException e) {
            logger.warn(e);
        } finally {
            connectionLock.unlock();
        }
        logger.debug("Connection to use: " + proxyConnection);

        return proxyConnection;
    }

    public void returnConnection(ProxyConnection proxyConnection) {
        try {
            connectionLock.lock();
            if (connectionsInUse.contains(proxyConnection)) {
                availableConnection.offer(proxyConnection);
                connectionsInUse.remove(proxyConnection);

                logger.debug("Connection was returned in PoolConnection: " + proxyConnection);
            }
        } finally {
            connectionLock.unlock();
            SEMAPHORE.release();
        }
    }

    public void closeAllConnection() {
        try {
            for (ProxyConnection connection : availableConnection) {
                connection.reallyClose();
            }
            for (ProxyConnection connection : connectionsInUse) {
                connection.reallyClose();
            }
        } catch (SQLException e) {
            logger.error(e);
        }
    }
}
