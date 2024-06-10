package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.OrderModel;

import java.net.URL;
import java.sql.*;
import java.util.*;


public class AdminController implements Initializable {

    @FXML
    private TableView<OrderModel> orderTable;
    @FXML
    private TableColumn<OrderModel, Integer> orderIdColumn;
    @FXML
    private TableColumn<OrderModel, Integer> tableNumberColumn;
    @FXML
    private TableColumn<OrderModel, String> orderDateColumn;
    @FXML
    private TableColumn<OrderModel, String> orderDetailsColumn;
    @FXML
    private TableColumn<OrderModel, Double> orderTotalColumn;
    @FXML
    private Button deleteOrderButton;
    private final ObservableList<OrderModel> orders = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        orderIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableNumberColumn.setCellValueFactory(new PropertyValueFactory<>("tableId"));
        orderDateColumn.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        orderDetailsColumn.setCellValueFactory(new PropertyValueFactory<>("details"));
        orderTotalColumn.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                loadOrders();
            }
        }, 0, 3_000);

        orderTable.setItems(orders);

        deleteOrderButton.setDisable(true);

        orderTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            deleteOrderButton.setDisable(newValue == null);
        });
    }

    private void loadOrders() {
        try (Connection conn = DatabaseController.getConnection()) {
            Statement stmt = conn.createStatement();
            String query = "SELECT o.id_order, o.total_price, o.table_number, o.order_date, d.product_name, d.quantity " +
                    "FROM order_detail d " +
                    "JOIN order_table o ON o.id_order = d.id_order " +
                    "WHERE d.id_order = o.id_order;";
            ResultSet rs = stmt.executeQuery(query);

            // Almacenamos los datos para no tener que concatenar los datos lo cual complica sql.
            Map<Integer, OrderModel> ordersMap = new HashMap<>();

            while (rs.next()) {
                int orderId = rs.getInt("id_order");
                int tableNumber = rs.getInt("table_number");
                Timestamp orderDate = rs.getTimestamp("order_date");
                String productName = rs.getString("product_name");
                int quantity = rs.getInt("quantity");
                double totalPrice = rs.getDouble("total_price");
                String details = productName + " x" + quantity;

                if (ordersMap.containsKey(orderId)) {
                    OrderModel order = ordersMap.get(orderId);
                    String[] existingOrderDetails = order.getDetails().split("\n");
                    String lastLine = existingOrderDetails[existingOrderDetails.length - 1];
                    String separator = lastLine.length() < 50 && details.length() < 50 ? " | " : "\n";
                    order.setDetails(order.getDetails() + separator + details);
                } else {
                    OrderModel order = new OrderModel(orderId, tableNumber, orderDate, details, totalPrice);
                    ordersMap.put(orderId, order);
                }
            }

            for (OrderModel order : ordersMap.values()) {
                if (orders.stream().noneMatch(o -> o.getId() == order.getId())) {
                    orders.add(order);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void deleteSelectedOrder() {
        OrderModel selectedOrder = orderTable.getSelectionModel().getSelectedItem();
        if (selectedOrder != null) {
            deleteOrder(selectedOrder.getId());
            orderTable.getItems().remove(selectedOrder);
            showAlert("Pedido eliminado", "El pedido ha sido eliminado correctamente.");
        } else {
            showAlert("No se ha seleccionado ningún pedido", "Por favor, seleccione un pedido para eliminar.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void deleteOrder(int orderId) {
        Connection conn = null;
        try {
            conn = DatabaseController.getConnection();
            conn.setAutoCommit(false);

            String orderQuery = "DELETE FROM order_table WHERE id_order = ?;";
            try (PreparedStatement stmtOrder = conn.prepareStatement(orderQuery)) {
                stmtOrder.setInt(1, orderId);
                stmtOrder.executeUpdate();
            }

            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException closeEx) {
                    closeEx.printStackTrace();
                }
            }
        }
    }
}
