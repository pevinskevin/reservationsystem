package com.pevinskevin.reservationsystem.Models;

import java.util.Map;

public class BeverageReservation {
    private int id;
    private int reservationId;
    private int beverageId;
    private int quantity;
    private Beverage beverages;
    private Map<Integer, Integer> beverageQuantities;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public int getBeverageId() {
        return beverageId;
    }

    public void setBeverageId(int beverageId) {
        this.beverageId = beverageId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Beverage getBeverages() {
        return beverages;
    }

    public void setBeverages(Beverage beverages) {
        this.beverages = beverages;
    }

    public Map<Integer, Integer> getBeverageQuantities() {
        return beverageQuantities;
    }

    public void setBeverageQuantities(Map<Integer, Integer> beverageQuantities) {
        this.beverageQuantities = beverageQuantities;
    }
}
