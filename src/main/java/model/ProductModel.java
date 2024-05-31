package model;

public class ProductModel {
  private int id;
  private String name;
  private double price;
  private int quantity;

  public ProductModel(int id, String name, double price) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.quantity = 1; // Iniciar con una cantidad de 1
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public void incrementQuantity() {
    this.quantity++;
  }

  public double getTotal() {
    return price * quantity;
  }

  @Override
  public String toString() {
    return name + " - " + price + " - " + quantity;
  }
}
