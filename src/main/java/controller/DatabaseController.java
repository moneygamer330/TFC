package controller;

import model.OrderDetailModel;
import model.OrderModel;
import model.ProductModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseController {

  private static final String URL = "jdbc:postgresql://localhost:5432/tfc";
  private static final String USER = "post";
  private static final String PASSWORD = "1234";

  private static Connection connection;

  public static Connection getConnection() {
    if (connection == null) {
      try {
        connection = DriverManager.getConnection(URL, USER, PASSWORD);
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return connection;
  }

  public static void closeConnection() {
    if (connection != null) {
      try {
        connection.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  public Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public List<ProductModel> getAllProducts() throws SQLException {
        String query = "SELECT * FROM product";
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            List<ProductModel> products = new ArrayList<>();
            while (rs.next()) {
                ProductModel product = new ProductModel(
                        rs.getInt("id_product"),
                        rs.getString("product_name"),
                        rs.getDouble("price")
                );
                products.add(product);
            }
            return products;
        }
    }

    public void saveOrder(int tableId, List<OrderDetailModel> orderDetails) throws SQLException {
        String insertOrderSQL = "INSERT INTO order_table (id_table) VALUES (?) RETURNING id_order";
        String insertOrderDetailSQL = "INSERT INTO order_detail (id_order, id_product, quantity) VALUES (?, ?, ?)";

        try (Connection conn = connect();
             PreparedStatement stmtOrder = conn.prepareStatement(insertOrderSQL);
             PreparedStatement stmtOrderDetail = conn.prepareStatement(insertOrderDetailSQL)) {

            conn.setAutoCommit(false);

            stmtOrder.setInt(1, tableId);
            ResultSet rs = stmtOrder.executeQuery();
            rs.next();
            int orderId = rs.getInt(1);

            for (OrderDetailModel detail : orderDetails) {
                stmtOrderDetail.setInt(1, orderId);
                stmtOrderDetail.setInt(2, detail.getProductId());
                stmtOrderDetail.setInt(3, detail.getQuantity());
                stmtOrderDetail.addBatch();
            }

            stmtOrderDetail.executeBatch();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
