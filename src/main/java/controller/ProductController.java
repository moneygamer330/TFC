package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import model.ProductModel;

import java.net.URL;
import java.util.ResourceBundle;

public class ProductController implements Initializable {

  @FXML
  private VBox productContainer;
  private DatabaseController dbController;
  private MenuController menuController;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {

  }

  public void setMenuController(MenuController menuController) {
    this.menuController = menuController;
  }

  private void addProductToOrder(ProductModel product) {
    if (menuController != null) {
      menuController.addProductToOrder(product);
    }
  }
}
