package com.codecool.onlineshop.main.dao;

import java.sql.*;
public class Connector {
    
    Connection c = null;
    
    public void connectToSql() {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("");
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }
}