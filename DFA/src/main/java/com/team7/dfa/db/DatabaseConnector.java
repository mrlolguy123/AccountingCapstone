package com.team7.dfa.db;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Logger;

public class DatabaseConnector {
    Connection conn = null;
    static Logger log = null;

    public boolean connected = false;

    static {
        System.setProperty("java.util.logging.SimpleFormatter.format", "[%4$-7s] %5$s %n");
        log = Logger.getLogger(DatabaseConnector.class.getName());
    }

    public Connection connect() {
        try {
            log.info("Loading application properties");
            Properties properties = new Properties();
            properties.load(DatabaseConnector.class.getClassLoader().getResourceAsStream("application.properties"));
            log.info("Connecting to the database");
            conn = DriverManager.getConnection(properties.getProperty("url"), properties);
            log.info("Database connection test: " + conn.getCatalog());
            connected = true;
            return conn;
        } catch (SQLException | IOException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public void disconnect() {
        if (connected)
            try {
                conn.close();
                log.info("Database is now disconnected.");
            } catch (SQLException e) {
                log.info("Database was not connected.");
            }
        else
            log.info("Database was not connected.");

        connected = false;
    }
}

