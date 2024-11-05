// AssignedTableHelper.java
package com.pevinskevin.reservationsystem.Models;

public class AssignedTableHelper {
    private int reservationId;
    private int tableNumber;
    private int seatCapacity;

    public AssignedTableHelper(int reservationId, int tableNumber, int seatCapacity) {
        this.reservationId = reservationId;
        this.tableNumber = tableNumber;
        this.seatCapacity = seatCapacity;
    }

    public int getReservationId() {
        return reservationId;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public int getSeatCapacity() {
        return seatCapacity;
    }
}
