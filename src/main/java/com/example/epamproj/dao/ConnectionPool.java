package com.example.epamproj.dao;


import com.mysql.cj.jdbc.MysqlDataSource;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

public class ConnectionPool extends MysqlDataSource {
    private static Logger logger = LogManager.getLogger(ConnectionPool.class);
    private final int POOL_SIZE = 20;
    private Queue<Connection> connectionPool;


    public ConnectionPool(String url, String username, String password){
        setUrl(url);
        setUser(username);
        setPassword(password);
        connectionPool = new ConcurrentLinkedDeque<>();
        for (int i = 0; i < POOL_SIZE; i++) {
            try {
                Connection physicalConection = super.getConnection();
                Connection connection = new ConnectionProxy(physicalConection, connectionPool);
                connectionPool.add(connection);
            } catch (SQLException e) {
                logger.log(Level.ALL,e);
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        return connectionPool.poll();
    }


}

