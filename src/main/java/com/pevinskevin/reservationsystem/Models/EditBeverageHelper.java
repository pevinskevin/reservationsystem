package com.pevinskevin.reservationsystem.Models;


public class EditBeverageHelper {
    private int beverageId;
    private String beverageName;
    private int quantity;

    public EditBeverageHelper(int beverageId, String beverageName, int quantity) {
        this.beverageId = beverageId;
        this.beverageName = beverageName;
        this.quantity = quantity;
    }

    public int getBeverageId() {
        return beverageId;
    }

    public void setBeverageId(int beverageId) {
        this.beverageId = beverageId;
    }

    public String getBeverageName() {
        return beverageName;
    }

    public void setBeverageName(String beverageName) {
        this.beverageName = beverageName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
