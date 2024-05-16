package com.pevinskevin.reservationsystem.Repositories;

import com.pevinskevin.reservationsystem.Models.Reservation;
import com.pevinskevin.reservationsystem.Models.CafeTable;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Repository
public class ReservationRepository {

    public Reservation save(Reservation reservation) {
        return reservation;
    }

    public Set<CafeTable> checkAvailableTables(Date reservationDate, int reservationDurationInHours, int numberOfPeople) {
        // Kald JDBC template med SQL

        String query = "SELECT * FROM Table WHERE foreign key = null";
        return new HashSet<CafeTable>() {{}};
    }
}
