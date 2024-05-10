package controller;

import model.ProductModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseController {

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/restaurant_db";
    private static final String DB_USER = "your_username";
    private static final String DB_PASSWORD = "your_password";

    public List<ProductModel> getMainCourses() {
        List<ProductModel> mainCourses = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "SELECT name, price FROM Product";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        String name = resultSet.getString("name");
                        double price = resultSet.getDouble("price");
                        ProductModel mainCourse = new ProductModel(name, price);
                        mainCourses.add(mainCourse);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mainCourses;
    }
}
