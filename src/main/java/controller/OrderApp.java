package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class OrderApp {

    private ObservableList<OrderItem> orderItems = FXCollections.observableArrayList();

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
}
