package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import model.OrderItem;

public class ProductController {

    private ProductController productController;
    private ObservableList<OrderItem> orderItems = FXCollections.observableArrayList();

    // Add items to the order
    @FXML
    private void addItemToOrder(String itemName) {
        boolean found = false;
        for (OrderItem item : orderItems) {
            if (item.getName().equals(itemName)) {
                item.incrementQuantity();
                found = true;
                break;
            }
        }
        if (!found) {
            orderItems.add(new OrderItem(itemName));
        }
    }

    @FXML
    private void handleEdamameButtonAction(ActionEvent event) {
        addItemToOrder("Edamame");
    }

    @FXML
    private void handleGyozaButtonAction(ActionEvent event) {
        addItemToOrder("Gyoza");
    }

    @FXML
    private void handleMisoSoupButtonAction(ActionEvent event) {
        addItemToOrder("Sopa de miso");
    }

    @FXML
    private void handleVegetableTempuraButtonAction(ActionEvent event) {
        addItemToOrder("Tempura de vegetales");
    }

    @FXML
    private void handleTunaTartareButtonAction(ActionEvent event) {
        addItemToOrder("Tartar de atún");
    }

    @FXML
    private void handleSalmonTatakiButtonAction(ActionEvent event) {
        addItemToOrder("Tataki de salmón");
    }

    @FXML
    private void handleCrabStuffedAvocadoButtonAction(ActionEvent event) {
        addItemToOrder("Aguacate relleno de cangrejo");
    }

    @FXML
    private void handleSeaweedSaladButtonAction(ActionEvent event) {
        addItemToOrder("Ensalada de algas");
    }

    @FXML
    private void handleSushiRollsButtonAction(ActionEvent event) {
        addItemToOrder("Rollitos de sushi");
    }

    @FXML
    private void handleSashimiButtonAction(ActionEvent event) {
        addItemToOrder("Sashimi");
    }

    @FXML
    private void handleNigiriButtonAction(ActionEvent event) {
        addItemToOrder("Nigiri");
    }

    @FXML
    private void handleDonburiButtonAction(ActionEvent event) {
        addItemToOrder("Donburi");
    }

    @FXML
    private void handleBentoButtonAction(ActionEvent event) {
        addItemToOrder("Bentō");
    }

    @FXML
    private void handleYakisobaButtonAction(ActionEvent event) {
        addItemToOrder("Yakisoba");
    }

    @FXML
    private void handleTeriyakiButtonAction(ActionEvent event) {
        addItemToOrder("Teriyaki");
    }

    @FXML
    private void handleChickenTatakiButtonAction(ActionEvent event) {
        addItemToOrder("Tataki de pollo");
    }

    @FXML
    private void handleCocaColaButtonAction(ActionEvent event) {
        addItemToOrder("Coca Cola");
    }

    @FXML
    private void handleWaterButtonAction(ActionEvent event) {
        addItemToOrder("Agua");
    }

    @FXML
    private void handleFantaButtonAction(ActionEvent event) {
        addItemToOrder("Fanta");
    }

    @FXML
    private void handleCoffeButtonAction(ActionEvent event) {
        addItemToOrder("Café");
    }

    @FXML
    private void handleTeaButtonAction(ActionEvent event) {
        addItemToOrder("Té");
    }

    @FXML
    private void handleStrawberryMilkshakeButtonAction(ActionEvent event) {
        addItemToOrder("Batido de fresa");
    }

    @FXML
    private void handleSakeButtonAction(ActionEvent event) {
        addItemToOrder("Sake");
    }

    @FXML
    private void handleBeerButtonAction(ActionEvent event) {
        addItemToOrder("Cerveza");
    }

    @FXML
    private void handleCheesCakeButtonAction(ActionEvent event) {
        addItemToOrder("Tarta de queso con fresa");
    }

    @FXML
    private void handleAppleCakeButtonAction(ActionEvent event) {
        addItemToOrder("Tarra de manzana");
    }

    @FXML
    private void handleVanilaIceCreamButtonAction(ActionEvent event) {
        addItemToOrder("Helado de vainilla");
    }

    @FXML
    private void handleBrownieButtonAction(ActionEvent event) {
        addItemToOrder("Brownie");
    }

    @FXML
    private void handleCarrotCakeButtonAction(ActionEvent event) {
        addItemToOrder("Pastel de zanahoria");
    }

    @FXML
    private void handleTiramisuButtonAction(ActionEvent event) {
        addItemToOrder("Tiramisú");
    }

    @FXML
    private void handleChocolateMousseButtonAction(ActionEvent event) {
        addItemToOrder("Mousse de chocolate");
    }

    @FXML
    private void handleCaramelFlanButtonAction(ActionEvent event) {
        addItemToOrder("Flan de caramelo");
    }
}
