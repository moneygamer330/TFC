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
import model.ProductModel;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AdminController implements Initializable {

    @FXML
    private TableView<OrderModel> orderTable;
    @FXML
    private TableColumn<OrderModel, Integer> orderIdColumn;
    @FXML
    private TableColumn<OrderModel, Integer> tableNumberColumn;
    @FXML
    private TableColumn<OrderModel, String> orderDetailsColumn;
    @FXML
    private Button deleteOrderButton;
    private ObservableList<OrderModel> orders = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        orderIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableNumberColumn.setCellValueFactory(new PropertyValueFactory<>("tableNumber"));
        orderDetailsColumn.setCellValueFactory(new PropertyValueFactory<>("details"));

        loadOrderData();

        orderTable.setItems(orders);

        deleteOrderButton.setDisable(true);

        orderTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            deleteOrderButton.setDisable(newValue == null);
        });
    }

    private void loadOrderData() {
        DatabaseController databaseController = new DatabaseController();
        List<OrderModel> orderData = databaseController.getAllOrders(); // Método para obtener todos los pedidos de la base de datos

        orders.addAll(orderData);
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
