
package com.example.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCUtils {
    // This class will be implemented to handle JDBC utilities
    private static String url;
    private static String user;
    private static String password;
    private static String driver;

    // 通過靜態待買塊, 來預先執行讀取配置文件的配置項, 做預處理
    static {
        try {
            // JDBCUtils.class.getClassLoader();
            InputStream inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream("db.properties");

            Properties properties = new Properties();
            properties.load(inputStream);

            url = properties.getProperty("url");
            user = properties.getProperty("user");
            password = properties.getProperty("password");
            driver = properties.getProperty("driver");

            System.out.println("url: " + url + ", user: " + user + ", password: " + password + ", driver: " + driver);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void test() {
        System.out.println("JDBCUtils init() called");
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public static void close(Connection connection, Statement statement)
            throws SQLException {
        if (connection != null) {
            connection.close();
        }

        if (statement != null) {
            statement.close();
        }
    }

    public static void close(Connection connection, Statement statement, ResultSet resultSet) throws SQLException {
        if (connection != null) {
            connection.close();
        }

        if (statement != null) {
            statement.close();
        }

        if (resultSet != null) {
            resultSet.close();
        }
    }

    public static void close(Connection connection, Statement statement, PreparedStatement preparedStatement)
            throws SQLException {
        if (connection != null) {
            connection.close();
        }

        if (statement != null) {
            statement.close();
        }

        if (preparedStatement != null) {
            preparedStatement.close();
        }
    }
}
