package controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import model.ProductModel;

public class ProductController {

  @FXML private TableView<ProductModel> orderTable;


  public ProductController() {}

  // Add items to the order
  public void addItemToOrder(String itemName, double price) {
    boolean found = false;
    for (ProductModel item : orderTable.getItems()) {
      if (item.getName().equals(itemName)) {
        item.incrementQuantity();
        found = true;
        break;
      }
    }
    if (!found) {
      orderTable.getItems().add(new ProductModel(itemName, price));
    }
  }

  @FXML
  private void handleEdamameButtonAction(ActionEvent event) {
    addItemToOrder("Edamame", 3.50);
  }

  @FXML
  private void handleGyozaButtonAction(ActionEvent event) {
    addItemToOrder("Gyoza", 3.50);
  }

  @FXML
  private void handleMisoSoupButtonAction(ActionEvent event) {
    addItemToOrder("Sopa de miso", 3.50);
    System.out.println("Miso Soup");
  }

  @FXML
  private void handleVegetableTempuraButtonAction(ActionEvent event) {
    addItemToOrder("Tempura de vegetales", 3.50);
  }

  @FXML
  private void handleTunaTartareButtonAction(ActionEvent event) {
    addItemToOrder("Tartar de atún", 3.50);
  }

  @FXML
  private void handleSalmonTatakiButtonAction(ActionEvent event) {
    addItemToOrder("Tataki de salmón", 3.50);
  }

  @FXML
  private void handleCrabStuffedAvocadoButtonAction(ActionEvent event) {
    addItemToOrder("Aguacate relleno de cangrejo", 3.50);
  }

  @FXML
  private void handleSeaweedSaladButtonAction(ActionEvent event) {
    addItemToOrder("Ensalada de algas", 3.50);
  }

  @FXML
  private void handleSushiRollsButtonAction(ActionEvent event) {
    addItemToOrder("Rollitos de sushi", 3.50);
  }

  @FXML
  private void handleSashimiButtonAction(ActionEvent event) {
    addItemToOrder("Sashimi", 3.50);
  }

  @FXML
  private void handleNigiriButtonAction(ActionEvent event) {
    addItemToOrder("Nigiri", 3.50);
  }

  @FXML
  private void handleDonburiButtonAction(ActionEvent event) {
    addItemToOrder("Donburi", 3.50);
  }

  @FXML
  private void handleBentoButtonAction(ActionEvent event) {
    addItemToOrder("Bentō", 3.50);
  }

  @FXML
  private void handleYakisobaButtonAction(ActionEvent event) {
    addItemToOrder("Yakisoba", 3.50);
  }

  @FXML
  private void handleTeriyakiButtonAction(ActionEvent event) {
    addItemToOrder("Teriyaki", 3.50);
  }

  @FXML
  private void handleChickenTatakiButtonAction(ActionEvent event) {
    addItemToOrder("Tataki de pollo", 3.50);
  }

  @FXML
  private void handleCocaColaButtonAction(ActionEvent event) {
    addItemToOrder("Coca Cola", 3.50);
  }

  @FXML
  private void handleWaterButtonAction(ActionEvent event) {
    addItemToOrder("Agua", 3.50);
  }

  @FXML
  private void handleFantaButtonAction(ActionEvent event) {
    addItemToOrder("Fanta", 3.50);
  }

  @FXML
  private void handleCoffeButtonAction(ActionEvent event) {
    addItemToOrder("Café", 3.50);
  }

  @FXML
  private void handleTeaButtonAction(ActionEvent event) {
    addItemToOrder("Té", 3.50);
  }

  @FXML
  private void handleStrawberryMilkshakeButtonAction(ActionEvent event) {
    addItemToOrder("Batido de fresa", 3.50);
  }

  @FXML
  private void handleSakeButtonAction(ActionEvent event) {
    addItemToOrder("Sake", 3.50);
  }

  @FXML
  private void handleBeerButtonAction(ActionEvent event) {
    addItemToOrder("Cerveza", 3.50);
  }

  @FXML
  private void handleCheesCakeButtonAction(ActionEvent event) {
    addItemToOrder("Tarta de queso con fresa", 3.50);
  }

  @FXML
  private void handleAppleCakeButtonAction(ActionEvent event) {
    addItemToOrder("Tarra de manzana", 3.50);
  }

  @FXML
  private void handleVanilaIceCreamButtonAction(ActionEvent event) {
    addItemToOrder("Helado de vainilla", 3.50);
  }

  @FXML
  private void handleBrownieButtonAction(ActionEvent event) {
    addItemToOrder("Brownie", 3.50);
  }

  @FXML
  private void handleCarrotCakeButtonAction(ActionEvent event) {
    addItemToOrder("Pastel de zanahoria", 3.50);
  }

  @FXML
  private void handleTiramisuButtonAction(ActionEvent event) {
    addItemToOrder("Tiramisú", 3.50);
  }

  @FXML
  private void handleChocolateMousseButtonAction(ActionEvent event) {
    addItemToOrder("Mousse de chocolate", 3.50);
  }

  @FXML
  private void handleCaramelFlanButtonAction(ActionEvent event) {
    addItemToOrder("Flan de caramelo", 3.50);
  }
}
