package controller;

import javafx.animation.AnimationTimer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import model.ProductModel;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public class MenuController implements Initializable {

    @FXML
    StackPane stackPane;
    @FXML
    private Label dateTimeLabel;
    @FXML
    private ObservableList<ProductModel> orderItems = FXCollections.observableArrayList();
    @FXML
    private TableView<ProductModel> productTable;
    @FXML
    private TableColumn<ProductModel, String> productNameColumn;
    @FXML
    private TableColumn<ProductModel, Double> priceColumn;
    @FXML
    private TableColumn<ProductModel, Integer> quantityColumn;
    @FXML
    private Label orderSummaryLabel;
    @FXML
    private Button confirmOrderButton;
    @FXML
    private Button clearOrderButton;
    private DatabaseController dbController = new DatabaseController();

    private Double totalPrice;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dbController = new DatabaseController();

        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        productTable.setItems(orderItems);

        confirmOrderButton.setOnAction(event -> confirmOrder());
        clearOrderButton.setOnAction(event -> clearOrder());

        updateTotalLabel();
        initializeDateTime();
    }

    private void updateTotalLabel() {
        double total = 0.00;
        for (ProductModel product : orderItems) {
            total += product.getPrice() * product.getQuantity();
        }

        totalPrice = total;
        orderSummaryLabel.setText(String.format("Total: %.2f€", total));
    }

    @FXML
    private void confirmOrder() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar Pedido");
        alert.setHeaderText("¿Estás seguro de confirmar el pedido?");
        alert.setContentText("Una vez confirmado, no se podrán realizar cambios en el pedido.");

        ButtonType buttonTypeYes = new ButtonType("Sí");
        ButtonType buttonTypeNo = new ButtonType("No");
        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == buttonTypeYes) {
            saveOrder(orderItems);
            orderItems.clear();
            orderSummaryLabel.setText("Total: 0.00€ (0 artículos)");
            showAlert("Pedido confirmado", "El pedido ha sido confirmado correctamente.");
        }
    }

    private void saveOrder(List<ProductModel> orderItems) {
        Connection conn = null;
        try {
            conn = DatabaseController.getConnection();
            conn.setAutoCommit(false);

            String orderQuery = "INSERT INTO order_table (table_number, order_date, total_price) VALUES (?, ?, ?) RETURNING id_order";
            try (PreparedStatement stmtOrder = conn.prepareStatement(orderQuery)) {
                stmtOrder.setInt(1, 1);
                stmtOrder.setTimestamp(2, new Timestamp(new Date().getTime()));
                stmtOrder.setDouble(3, totalPrice);
                ResultSet rs = stmtOrder.executeQuery();

                if (rs.next()) {
                    int orderId = rs.getInt(1);

                    String detailQuery = "INSERT INTO order_detail (id_order, product_name, quantity) VALUES (?, ?, ?)";
                    try (PreparedStatement stmtDetail = conn.prepareStatement(detailQuery)) {
                        for (ProductModel product : orderItems) {
                            stmtDetail.setInt(1, orderId);
                            stmtDetail.setString(2, product.getName());
                            stmtDetail.setInt(3, product.getQuantity());

                            stmtDetail.addBatch();
                        }
                        stmtDetail.executeBatch();
                    }
                }
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

    @FXML
    private void clearOrder() {
        orderItems.clear();
        updateTotalLabel();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void addProductToOrder(ProductModel product) {
        int count = -1;
        for (ProductModel orderItem : orderItems) {
            count++;
            if (orderItem.getId() == product.getId()) {
                orderItem.incrementQuantity();
                orderItems.set(count, orderItem);
                productTable.setItems(orderItems);
                updateTotalLabel();
                return;
            }
        }
        orderItems.add(product);
        productTable.setItems(orderItems);
        updateTotalLabel();
    }

    // load the starters template
    @FXML
    public void loadStarters(javafx.event.ActionEvent actionEvent) {
        try {
            FXMLLoader loader =
                    new FXMLLoader(Objects.requireNonNull(getClass().getResource("/gui/starters.fxml")));

            Parent parent = loader.load();
            ProductController productController = loader.getController();
            productController.setMenuController(this);
            stackPane.getChildren().removeAll();
            stackPane.getChildren().setAll(parent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // load the main courses template
    @FXML
    public void loadPrincipal(javafx.event.ActionEvent actionEvent) {
        try {
            FXMLLoader loader =
                    new FXMLLoader(Objects.requireNonNull(getClass().getResource("/gui/principal.fxml")));

            Parent parent = loader.load();
            ProductController productController = loader.getController();
            productController.setMenuController(this);
            stackPane.getChildren().removeAll();
            stackPane.getChildren().setAll(parent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // load the desserts template
    @FXML
    public void loadDesserts(javafx.event.ActionEvent actionEvent) {
        try {
            FXMLLoader loader =
                    new FXMLLoader(Objects.requireNonNull(getClass().getResource("/gui/desserts.fxml")));

            Parent parent = loader.load();
            ProductController productController = loader.getController();
            productController.setMenuController(this);

            stackPane.getChildren().removeAll();
            stackPane.getChildren().setAll(parent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // load the drinks template
    @FXML
    public void loadDrinks(javafx.event.ActionEvent actionEvent) {
        try {
            FXMLLoader loader =
                    new FXMLLoader(Objects.requireNonNull(getClass().getResource("/gui/drinks.fxml")));

            Parent parent = loader.load();
            ProductController productController = loader.getController();
            productController.setMenuController(this);
            stackPane.getChildren().removeAll();
            stackPane.getChildren().setAll(parent);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
}
