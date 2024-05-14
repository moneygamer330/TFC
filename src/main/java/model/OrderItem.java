package model;

public class OrderItem {
    private final String name;
    private int quantity;

    public OrderItem(String name) {
        this.name = name;
        this.quantity = 1;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void incrementQuantity() {
        quantity++;
    }
}