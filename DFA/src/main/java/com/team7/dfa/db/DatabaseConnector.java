package com.team7.dfa.db;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * This class is responsible for connecting to the database.
 * It uses the application.properties file to get the connection details.
 * It also disconnects from the database when the application is closed.
 */
public class DatabaseConnector {
    /**
     * This variable is used to store the connection to the database.
     */
    Connection conn = null;
    /**
     * This variable is used to log messages to the console.
     */
    static Logger log = null;

    /**
     * This variable is used to check if the database is connected.
     */
    public boolean connected = false;

    static {
        System.setProperty("java.util.logging.SimpleFormatter.format", "[%4$-7s] %5$s %n");
        log = Logger.getLogger(DatabaseConnector.class.getName());
    }

    /**
     * This method connects to the database using the connection details in the application.properties file.
     * @return Connection object
     */
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

    /**
     * This method disconnects from the database.
     */
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

