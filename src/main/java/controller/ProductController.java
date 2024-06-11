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
        productPrices.put("Edamame", new Product("Edamame", 4.50));
        productPrices.put("Gyoza", new Product("Gyoza", 7.00));
        productPrices.put("Sopa", new Product("Sopa miso", 5.00));
        productPrices.put("Témpura", new Product("Témpura", 6.00));
        productPrices.put("Tartar", new Product("Tartar", 10.00));
        productPrices.put("Tataki", new Product("Tataki", 9.00));
        productPrices.put("Aguacate", new Product("Aguacate", 8.50));
        productPrices.put("Ensalada", new Product("Ensalada", 5.50));
        productPrices.put("Sushi", new Product("Sushi", 8.50));
        productPrices.put("Sashimi", new Product("Sashimi", 12.00));
        productPrices.put("Nigiri", new Product("Nigiri", 7.00));
        productPrices.put("Donburi", new Product("Donburi", 9.00));
        productPrices.put("Bento", new Product("Bentō", 11.00));
        productPrices.put("Yakisoba", new Product("Yakisoba", 6.50));
        productPrices.put("Teriyaki", new Product("Teriyaki", 10.00));
        productPrices.put("TatakiPollo", new Product("Tataki Pollo", 9.50));
        productPrices.put("CocaCola", new Product("Coca Cola", 2.50));
        productPrices.put("Agua", new Product("Agua", 1.00));
        productPrices.put("Fanta", new Product("Fanta", 2.50));
        productPrices.put("Cafe", new Product("Café", 1.50));
        productPrices.put("Te", new Product("Té", 1.50));
        productPrices.put("BatidoFresa", new Product("Batido de Fresa", 3.00));
        productPrices.put("Sake", new Product("Sake", 4.00));
        productPrices.put("Cerveza", new Product("Cerveza", 2.00));
        productPrices.put("Tarta1", new Product("Tarta 1", 4.00));
        productPrices.put("Tarta2", new Product("Tarta 2", 4.00));
        productPrices.put("Helado", new Product("Helado", 2.00));
        productPrices.put("Brownie", new Product("Brownie", 2.75));
        productPrices.put("Pastel", new Product("Pastel", 3.75));
        productPrices.put("Tiramisú", new Product("Tiramisú", 4.50));
        productPrices.put("Mousse", new Product("Mousse", 3.75));
        productPrices.put("Flan", new Product("Flan", 3.25));
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
