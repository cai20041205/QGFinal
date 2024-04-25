package com.gdut.util;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

public class JDBCUtils {
    private static String URL;
    private static String USERNAME;
    private static String PASSWORD;
    private static String DRIVER;

    static {
        try {
//            FileInputStream fis=new FileInputStream("src\\main\\resources\\jdbc.properties");
//            Properties p = new Properties();
//            p.load(fis);
//            fis.close();
//            DRIVER = p.getProperty("driver");
//            USERNAME = p.getProperty("username");
//            URL = p.getProperty("url");
//            PASSWORD = p.getProperty("password");
            DRIVER="com.mysql.cj.jdbc.Driver";
            USERNAME="root";
            URL="jdbc:mysql://localhost:3306/dp02";
            PASSWORD ="1234";
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private JDBCUtils() {
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void closeAll(Connection connection, ResultSet resultSet, PreparedStatement preparedStatement) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (preparedStatement != null) {

            try {
                preparedStatement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
