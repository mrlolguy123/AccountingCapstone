package com.team7.dfa;
import java.sql.*;
import java.util.*;
import java.util.logging.Logger;

public class SQLConnect {
    private static final Logger log;
    public static Connection connection;

    static {
        System.setProperty("java.util.logging.SimpleFormatter.format", "[%4$-7s] %5$s %n");
        log = Logger.getLogger(SQLConnect.class.getName());
    }

    public static void startConnection(String[] args) throws Exception {
        log.info("Loading application properties");
        Properties properties = new Properties();
        properties.load(SQLConnect.class.getClassLoader().getResourceAsStream("application.properties"));

        log.info("Connecting to the database");
        try {
            connection = DriverManager.getConnection(properties.getProperty("url"), properties);
        } catch (Exception E){
            log.info("Unable to connect");
        }
        log.info("Connection successful");

    }

    public Connection getConnection(){
        return connection;
    }

    public static void endConnection() throws Exception{
        log.info("Ending db connection");
        connection.close();
    }

}
