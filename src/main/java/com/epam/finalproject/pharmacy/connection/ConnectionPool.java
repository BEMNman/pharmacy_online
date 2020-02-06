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

    private Queue<ProxyConnection> availableConnection;
    private Queue<ProxyConnection> connectionsInUse;

    private static AtomicReference<ConnectionPool> instance = new AtomicReference<>(null);
    private static ReentrantLock connectionLock = new ReentrantLock();
    private static ReentrantLock instanceLock = new ReentrantLock();

    private static final int POOL_SIZE = 10;
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

    public ProxyConnection getConnection() throws SQLException {
        if (availableConnection.size() == 0) {
            availableConnection = ConnectionFactory.createPoolConnections();
        }
        ProxyConnection proxyConnection = null;
        try {
            SEMAPHORE.acquire();
            connectionLock.lock();
            proxyConnection = availableConnection.poll();
            connectionsInUse.add(proxyConnection);
        } catch (InterruptedException e) {
            logger.warn(e);
        } finally {
            connectionLock.unlock();
        }
        logger.debug("Connection in using: " + proxyConnection);

        return proxyConnection;
    }

    public void returnConnection(ProxyConnection proxyConnection) {
        connectionLock.lock();
        try {
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
}
