package com.Retailer.Utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbValidation {
    private static final String DB_URL = "jdbc:mysql://stagingpma.mobisy.com:3306/devbizomqa_bizomdev_in_bizom";
    private static final String DB_USER = "anjana.mohan";
    private static final String DB_PASSWORD = "!e9/qqU47GFuBwV";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }
}
