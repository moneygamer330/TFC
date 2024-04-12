package tfc.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    @FXML
    private StackPane stackPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("menu.fxml"));
            stackPane.getChildren().removeAll();
            stackPane.getChildren().setAll(fxml);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMenu() {
        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("menu.fxml"));
            stackPane.getChildren().removeAll();
            stackPane.getChildren().setAll(fxml);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadStarters(javafx.event.ActionEvent actionEvent) {
        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("starters.fxml"));
            stackPane.getChildren().removeAll();
            stackPane.getChildren().setAll(fxml);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadPrincipal(javafx.event.ActionEvent actionEvent) {
        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("principal.fxml"));
            stackPane.getChildren().removeAll();
            stackPane.getChildren().setAll(fxml);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadDesserts(javafx.event.ActionEvent actionEvent) {
        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("desserts.fxml"));
            stackPane.getChildren().removeAll();
            stackPane.getChildren().setAll(fxml);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadDrinks(javafx.event.ActionEvent actionEvent) {
        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("drinks.fxml"));
            stackPane.getChildren().removeAll();
            stackPane.getChildren().setAll(fxml);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadOrder(javafx.event.ActionEvent actionEvent) {
        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("order.fxml"));
            stackPane.getChildren().removeAll();
            stackPane.getChildren().setAll(fxml);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadBill(javafx.event.ActionEvent actionEvent) {
        try {
            Parent fxml = FXMLLoader.load(getClass().getResource("bill.fxml"));
            stackPane.getChildren().removeAll();
            stackPane.getChildren().setAll(fxml);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
