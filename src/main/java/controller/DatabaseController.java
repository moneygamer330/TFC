package controller;



import java.sql.*;


public class DatabaseController {

    private static final String URL = "jdbc:postgresql://localhost:5432/tfc?currentSchema=tfc";
    private static final String USER = "postgres";
    private static final String PASSWORD = "1234";

    private static Connection connection;

    public static Connection getConnection() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}



