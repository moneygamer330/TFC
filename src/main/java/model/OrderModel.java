package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderModel {
    private int id;
    private int tableId;
    private String orderDate;
    private String details;
    private double totalPrice;

    public OrderModel(int id, int tableId, Date orderDate, String details, double totalPrice) {
        this.id = id;
        this.tableId = tableId;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.orderDate = dateFormat.format(orderDate);
        this.details = details;
        this.totalPrice = totalPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.orderDate = dateFormat.format(orderDate);
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "OrderModel{" +
                "id=" + id +
                ", tableId=" + tableId +
                ", orderDate=" + orderDate +
                ", details='" + details + '\'' +
                '}';
    }
}
