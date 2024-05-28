package model;

public class TableModel {
    private int id;
    private int tableNumber;

    public TableModel(int id, int tableNumber) {
        this.id = id;
        this.tableNumber = tableNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    @Override
    public String toString() {
        return "TableOrder{" +
                "id=" + id +
                ", tableNumber=" + tableNumber +
                '}';
    }
}
