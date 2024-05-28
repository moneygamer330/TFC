package controller;

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


  public List<OrderModel> getAllOrders() {
    List<OrderModel> orders = new ArrayList<>();
    String query = "SELECT * FROM orders";

    try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
         PreparedStatement statement = connection.prepareStatement(query);
         ResultSet resultSet = statement.executeQuery()) {

      while (resultSet.next()) {
        int orderId = resultSet.getInt("order_id");
        int tableNumber = resultSet.getInt("table_number");
        List<ProductModel> products = getProductsForOrder(orderId);
        OrderModel order = new OrderModel(orderId, tableNumber, products);
        orders.add(order);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return orders;
  }


  private List<ProductModel> getProductsForOrder(int orderId) {
    List<ProductModel> products = new ArrayList<>();
    String query = "SELECT p.name, p.price, od.quantity " +
            "FROM Product p " +
            "JOIN OrderDetail od ON p.id_product = od.id_product " +
            "WHERE od.id_order = ?";

    try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
         PreparedStatement statement = connection.prepareStatement(query)) {

      statement.setInt(1, orderId);
      try (ResultSet resultSet = statement.executeQuery()) {
        while (resultSet.next()) {
          int id = resultSet.getInt("id_product");
          String name = resultSet.getString("name");
          double price = resultSet.getDouble("price");
          products.add(new ProductModel(id, name, price));
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return products;
  }

  public int saveOrder() throws SQLException {
    Connection connection = null;
    PreparedStatement stmtOrder = null;
    PreparedStatement stmtOrderDetail = null;
    ResultSet rs = null;

    try {
      connection = DatabaseController.getConnection();

      stmtOrder = connection.prepareStatement("INSERT INTO `Order` (table_number, status) VALUES (?, 'pending')", PreparedStatement.RETURN_GENERATED_KEYS);
      stmtOrder.setInt(1, );
      stmtOrder.executeUpdate();

      rs = stmtOrder.getGeneratedKeys();
      int orderId = -1;
      if (rs.next()) {
        orderId = rs.getInt(1);
      } else {
        throw new SQLException("No se pudo obtener el ID del pedido generado.");
      }

      stmtOrderDetail = connection.prepareStatement("INSERT INTO OrderDetail (id_order, id_product, quantity) VALUES (?, ?, ?)");
      for (ProductModel item : orderItems) {
        stmtOrderDetail.setInt(1, orderId);
        stmtOrderDetail.setInt(2, ;
        stmtOrderDetail.setInt(3, ;
        stmtOrderDetail.executeUpdate();
      }

      return orderId;
    } finally {
      if (rs != null) {
        rs.close();
      }
      if (stmtOrder != null) {
        stmtOrder.close();
      }
      if (stmtOrderDetail != null) {
        stmtOrderDetail.close();
      }
      if (connection != null) {
        connection.close();
      }
    }
  }

  public static List<ProductModel> getAllProducts() {
    List<ProductModel> products = new ArrayList<>();
    String sql = "SELECT * FROM Product";

    try (Connection conn = getConnection();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {
      while (rs.next()) {
        int id = rs.getInt("id_product");
        String name = rs.getString("name");
        double price = rs.getDouble("price");
        ProductModel product = new ProductModel(id,name,price);
        products.add(product);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return products;
  }
}
