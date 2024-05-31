package model;

import java.util.Date;

public class OrderModel {
    private int id;
    private int tableId;
    private Date orderDate;
    private String details;

    public OrderModel(int id, int tableId, Date orderDate, String details) {
        this.id = id;
        this.tableId = tableId;
        this.orderDate = orderDate;
        this.details = details;
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

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
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
