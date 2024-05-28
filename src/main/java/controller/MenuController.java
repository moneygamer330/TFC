package controller;

import javafx.animation.AnimationTimer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import model.OrderDetailModel;
import model.ProductModel;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;

public class MenuController implements Initializable {

  @FXML StackPane stackPane;
  @FXML private Label dateTimeLabel;
  @FXML private Button calculateTotalButton;
  @FXML private ObservableList<ProductModel> orderItems = FXCollections.observableArrayList();
  @FXML private TableView<ProductModel> orderTable;
  @FXML private Label orderSummaryLabel;
  @FXML private Button confirmOrderButton;
  private final DatabaseController dbController = new DatabaseController();

  public void initialize(URL url, ResourceBundle resourceBundle) {
    initializeOrderTable();
    initializeDateTime();
    loadProductsFromDatabase();
  }

  private void loadProductsFromDatabase() {
    List<ProductModel> products = DatabaseController.getAllProducts();

    orderItems.addAll(products);
    orderTable.setItems(orderItems);
  }

  private void initializeDateTime() {
    AnimationTimer timer = new AnimationTimer() {
      @Override
      public void handle(long now) {
        Date currentTime = new Date();
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("HH:mm:ss   dd/MM/yyyy");
        String formattedDateTime = dateTimeFormat.format(currentTime);
        dateTimeLabel.setText(formattedDateTime);
      }
    };
    timer.start();
  }

  private void initializeOrderTable() {
    orderTable.setItems(orderItems);
  }

  @FXML
  private void confirmOrder() {
    try {
      int orderId = dbController.saveOrder(orderItems);
      if (orderId != -1) {
        showAlert("Pedido confirmado", "El pedido se ha registrado correctamente. ID: " + orderId);
        orderItems.clear();
        updateOrderSummary();
      } else {
        showAlert("Error", "No se pudo registrar el pedido. Por favor, inténtalo de nuevo.");
      }
    } catch (SQLException e) {
      e.printStackTrace();
      showAlert("Error de base de datos", "Hubo un error al acceder a la base de datos.");
    }
  }

  private void showAlert(String title, String message) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
  }

  public void updateOrderSummary() {
    int totalItems = 0;
    double totalPrice = 0.0;

    for (OrderDetailModel item : orderTable.getItems()) {
      totalItems += item.getQuantity();
      totalPrice += item.getTotal();
    }

    orderSummaryLabel.setText(String.format("Total: %.2f€ (%d artículos)", totalPrice, totalItems));
  }

  // Remove items from the order
  public void clearOrder() {
    orderItems.clear();
    // Refresh list
    orderTable.getItems().clear();
  }


  // load the starters template
  @FXML
  public void loadStarters(javafx.event.ActionEvent actionEvent) {
    try {
      Parent fxml =
          FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/gui/starters.fxml")));
      stackPane.getChildren().removeAll();
      stackPane.getChildren().setAll(fxml);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  // load the main courses template
  @FXML
  public void loadPrincipal(javafx.event.ActionEvent actionEvent) {
    try {
      Parent fxml =
              FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/gui/principal.fxml")));
      stackPane.getChildren().removeAll();
      stackPane.getChildren().setAll(fxml);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  // load the desserts template
  @FXML
  public void loadDesserts(javafx.event.ActionEvent actionEvent) {
    try {
      Parent fxml =
          FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/gui/desserts.fxml")));
      stackPane.getChildren().removeAll();
      stackPane.getChildren().setAll(fxml);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  // load the drinks template
  @FXML
  public void loadDrinks(javafx.event.ActionEvent actionEvent) {
    try {
      Parent fxml =
          FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/gui/drinks.fxml")));
      stackPane.getChildren().removeAll();
      stackPane.getChildren().setAll(fxml);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
