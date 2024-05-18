package com.pevinskevin.reservationsystem.Repositories;

import com.pevinskevin.reservationsystem.Models.Reservation;
import com.pevinskevin.reservationsystem.Models.CafeTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Repository
public class ReservationRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    /*public Reservation save(Reservation reservation) {
        return reservation;
    }*/

    public Set<CafeTable> checkAvailableTables(LocalDate reservationDate, int reservationDurationInHours, int numberOfPeople) {
        // Kald JDBC template med SQL

        String query = "SELECT * FROM Table WHERE foreign key = null";
        return new HashSet<CafeTable>() {{}};
    }

    public int checkTotalSeatCapacity() {
        String query = "SELECT SUM(seat_capacity) AS total_seat_capacity FROM cafe_table";
        return jdbcTemplate.queryForObject(query, Integer.class);
    }

    public int checkUnavailableSeats(Reservation reservation) {
        // Convert duration in hours to time format string
        String durationString = reservation.getReservationDurationInHours() + ":00:00";

        String query = "SELECT SUM(number_of_seats) AS total_reserved_seats FROM reservation WHERE reservation_date = ? AND time = ? AND ADDTIME(time, ?) > ?";

        Integer totalSeatsReserved = jdbcTemplate.queryForObject(query, new Object[] {reservation.getReservationDate(), reservation.getTime(), durationString, reservation.getTime()}, Integer.class);

        if (totalSeatsReserved == null) {
            // Logic when no rows found
            return 0;
        }

        return totalSeatsReserved;
    }

    public String addReservation(Reservation reservation) {
        String url = UUID.randomUUID().toString();
        String query = "INSERT INTO Reservation (name, email, company_name, phone_number, number_of_seats, reservation_date, time, duration, comments, url) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(query, reservation.getName(), reservation.getEmail(), reservation.getCompanyName(), reservation.getPhoneNumber(), reservation.getNumberOfSeats(), reservation.getReservationDate(), reservation.getTime(), reservation.getReservationDurationInHours(), reservation.getComments(), url);
        return url;
    }
}