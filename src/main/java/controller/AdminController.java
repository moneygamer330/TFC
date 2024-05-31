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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

public class AdminController implements Initializable {

    @FXML
    private TableView<OrderModel> orderTable;
    @FXML
    private TableColumn<OrderModel, Integer> orderIdColumn;
    @FXML
    private TableColumn<OrderModel, Integer> tableNumberColumn;
    @FXML
    private TableColumn<OrderModel, Timestamp> orderDateColumn;
    @FXML
    private TableColumn<OrderModel, String> orderDetailsColumn;
    @FXML
    private Button deleteOrderButton;
    private ObservableList<OrderModel> orders = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        orderIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableNumberColumn.setCellValueFactory(new PropertyValueFactory<>("tableNumber"));
        orderDateColumn.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        orderDetailsColumn.setCellValueFactory(new PropertyValueFactory<>("details"));

        loadOrders();

        orderTable.setItems(orders);

        deleteOrderButton.setDisable(true);

        orderTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            deleteOrderButton.setDisable(newValue == null);
        });
    }

    private void loadOrders() {
        DatabaseController dbController = new DatabaseController();
        try (Connection conn = dbController.getConnection()) {
            Statement stmt = conn.createStatement();
            String query = "SELECT o.id_order, t.table_number, o.order_date, " +
                    "(SELECT STRING_AGG(CONCAT(p.product_name, ': ', d.quantity), ', ') " +
                    "FROM order_detail d " +
                    "JOIN product p ON d.id_product = p.id_product " +
                    "WHERE d.id_order = o.id_order) AS details " +
                    "FROM order_table o " +
                    "JOIN table_order t ON o.id_table = t.id_table";
            ResultSet rs = stmt.executeQuery(query);

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            while (rs.next()) {
                int orderId = rs.getInt("id_order");
                int tableNumber = rs.getInt("table_number");
                Timestamp orderDate = rs.getTimestamp("order_date");
                String details = rs.getString("details");
                OrderModel order = new OrderModel(orderId, tableNumber, orderDate, details);
                orders.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void deleteSelectedOrder() {
        OrderModel selectedOrder = orderTable.getSelectionModel().getSelectedItem();
        if (selectedOrder != null) {
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
}
