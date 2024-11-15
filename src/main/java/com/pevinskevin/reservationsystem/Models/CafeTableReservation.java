package com.pevinskevin.reservationsystem.Models;

public class CafeTableReservation {
    private int id;
    private int reservationId;
    private int cafeTableId;

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

    public int getCafeTableId() {
        return cafeTableId;
    }

    public void setCafeTableId(int cafeTableId) {
        this.cafeTableId = cafeTableId;
    }
}
