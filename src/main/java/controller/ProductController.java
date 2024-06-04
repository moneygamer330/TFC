package controller;

import entities.Product;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import model.ProductModel;

import java.net.URL;
import java.util.*;

public class ProductController implements Initializable {

    @FXML
    private MenuController menuController;
    private Map<String, Product> productPrices;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        productPrices = new HashMap<>();
        productPrices.put("edamame", new Product("Edamame", 3.50));
        productPrices.put("gyoza", new Product("Gyoza", 4.00));
        productPrices.put("misoSoup", new Product("Sopa miso", 2.50));
        productPrices.put("tempura", new Product("Témpura", 5.00));
        productPrices.put("tartar", new Product("Tartar", 8.00));
        productPrices.put("tataki", new Product("Tataki", 7.50));
        productPrices.put("aguacate", new Product("Aguacate", 8.00));
        productPrices.put("ensalada", new Product("Ensalada", 5.50));
        productPrices.put("sushi", new Product("Sushi", 8.50));
        productPrices.put("sashimi", new Product("Sashimi", 12.00));
        productPrices.put("nigiri", new Product("Nigiri", 7.00));
        productPrices.put("donburi", new Product("Donburi", 8.50));
        productPrices.put("bento", new Product("Bentō", 11.00));
        productPrices.put("yakisoba", new Product("Yakisoba", 7.00));
        productPrices.put("teriyaki", new Product("Teriyaki", 8.00));
        productPrices.put("tatakiPollo", new Product("Tataki Pollo", 6.50));
        productPrices.put("cocaCola", new Product("Coca Cola", 1.50));
        productPrices.put("agua", new Product("Agua", 1.00));
        productPrices.put("fanta", new Product("Fanta", 1.50));
        productPrices.put("cafe", new Product("Café", 1.20));
        productPrices.put("te", new Product("Té", 1.00));
        productPrices.put("batidoFresa", new Product("Batido de fresa", 2.50));
        productPrices.put("sake", new Product("Sake", 3.00));
        productPrices.put("cerveza", new Product("Cerveza", 2.00));
        productPrices.put("tarta1", new Product("Tarta 1", 4.00));
        productPrices.put("tarta2", new Product("Tarta 2", 4.00));
        productPrices.put("helado", new Product("Helado", 2.00));
        productPrices.put("brownie", new Product("Brownie", 3.00));
        productPrices.put("pastel", new Product("Pastel", 3.50));
        productPrices.put("tiramisu", new Product("Tiramisú", 4.00));
        productPrices.put("mousse", new Product("Mousse", 3.00));
        productPrices.put("flan", new Product("Flan", 2.50));
    }

    public void setMenuController(MenuController menuController) {
        this.menuController = menuController;
    }

    @FXML
    private void handleProductButtonAction(ActionEvent event) {
        Button button = (Button) event.getSource();
        String productName = button.getId();
        Product product = productPrices.get(productName);
        if (product != null) {
            // sumar todos los valores de cada caracter
            int id = 0;
            byte[] chars = productName.getBytes();
            for (byte b : chars) {
                id += b;
            }
            addProductToOrder(new ProductModel(id, productName, product.getPrice()));
        }
    }

    private void addProductToOrder(ProductModel product) {
        if (menuController != null) {
            menuController.addProductToOrder(product);
        }
    }
}
