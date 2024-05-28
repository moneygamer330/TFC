package model;

import java.util.Date;

public class OrderModel {
    private int id;
    private int tableId;
    private Date orderDate;

    public OrderModel(int id, int tableId, Date orderDate) {
        this.id = id;
        this.tableId = tableId;
        this.orderDate = orderDate;
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

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", tableId=" + tableId +
                ", orderDate=" + orderDate +
                '}';
    }
}
