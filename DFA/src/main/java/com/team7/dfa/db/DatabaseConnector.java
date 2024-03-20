package com.team7.dfa.db;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Logger;

public class DatabaseConnector {
    Connection conn = null;
    static Logger log = null;

    static {
        System.setProperty("java.util.logging.SimpleFormatter.format", "[%4$-7s] %5$s %n");
        log =Logger.getLogger(DatabaseConnector.class.getName());
    }

    public Connection connect() {
        try {
            log.info("Loading application properties");
            Properties properties = new Properties();
            properties.load(DatabaseConnector.class.getClassLoader().getResourceAsStream("application.properties"));
            log.info("Connecting to the database");
            conn = DriverManager.getConnection(properties.getProperty("url"), properties);
            log.info("Database connection test: " + conn.getCatalog());
            return conn;
        } catch (SQLException | IOException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public void disconnect() {
        try{
            conn.close();
            log.info("Database is now disconnected.");
        }
        catch (SQLException e){
            log.info("Database was not connected.");
        }
    }

    public static void main(String[] args) { // test connection
        DatabaseConnector db = new DatabaseConnector();
        db.connect();
        db.disconnect();
    }
}

