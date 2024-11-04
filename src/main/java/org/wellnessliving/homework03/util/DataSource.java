package org.wellnessliving.homework03.util;

import org.wellnessliving.homework03.exception.DbException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataSource {
    private static final String URL = "jdbc:mysql://localhost:3307/todo";
    private static Connection dboConnection = null;

    public static Connection getConnection() {
        if (dboConnection == null) {
            try {
                Properties dboProperties = new Properties();
                dboProperties.put("user", "root");
                dboProperties.put("password", "example");
                dboConnection = DriverManager.getConnection(URL, dboProperties);
                System.out.println("Connected to DB");
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
        return dboConnection;
    }
}
