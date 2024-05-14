package controller;

import javafx.animation.AnimationTimer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import model.OrderItem;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    @FXML
    StackPane stackPane;
    @FXML
    private Label dateTimeLabel;
    private ObservableList<OrderItem> orderItems = FXCollections.observableArrayList();


    public void initialize(URL url, ResourceBundle resourceBundle) {
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                Date currentTime = new Date();
                SimpleDateFormat dateTimeFormat = new SimpleDateFormat("HH:mm:ss ------------ dd/MM/yyyy");
                String formattedDateTime = dateTimeFormat.format(currentTime);
                dateTimeLabel.setText(formattedDateTime);
            }
        };
        timer.start();
    }

    public void loadMenu() {
        /*try {
            Parent fxml = FXMLLoader.load(getClass().getResource("menu.fxml"));
            stackPane.getChildren().removeAll();
            stackPane.getChildren().setAll(fxml);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    //load the starters template
    @FXML
    public void loadStarters(javafx.event.ActionEvent actionEvent) {
        try {
            Parent fxml = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/gui/starters.fxml")));
            stackPane.getChildren().removeAll();
            stackPane.getChildren().setAll(fxml);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //load the main courses template
    @FXML
    public void loadPrincipal(javafx.event.ActionEvent actionEvent) {
        try {
            Parent fxml = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/gui/principal.fxml")));
            stackPane.getChildren().removeAll();
            stackPane.getChildren().setAll(fxml);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //load the desserts template
    @FXML
    public void loadDesserts(javafx.event.ActionEvent actionEvent) {
        try {
            Parent fxml = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/gui/desserts.fxml")));
            stackPane.getChildren().removeAll();
            stackPane.getChildren().setAll(fxml);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //load the drinks template
    @FXML
    public void loadDrinks(javafx.event.ActionEvent actionEvent) {
        try {
            Parent fxml = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/gui/drinks.fxml")));
            stackPane.getChildren().removeAll();
            stackPane.getChildren().setAll(fxml);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Remove items from the order
    @FXML
    private void clearOrder() {
        orderItems.clear();
    }
}
