package com.pevinskevin.reservationsystem.Services;
import com.pevinskevin.reservationsystem.Models.BeverageReservation;
import com.pevinskevin.reservationsystem.Models.Reservation;
import com.pevinskevin.reservationsystem.Models.CafeTable;
import com.pevinskevin.reservationsystem.Repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

@Service
public class ReservationService {

    @Autowired
    ReservationRepository reservationRepository;

    public String createReservation(Reservation reservation){
       /* if (!checkAvailableTables(reservation.getReservationDate(), reservation.getReservationDurationInHours(), reservation.getNumberOfSeats()).isEmpty()) {
            reservationRepository.save(reservation);
        } else {
            return null;
        }*/

        return reservationRepository.addReservation(reservation);
    }

    public int checkUnavailableSeats(Reservation reservation) {
        return reservationRepository.checkUnavailableSeats(reservation);
    }

    public int checkTotalSeatCapacity() {
        return reservationRepository.checkTotalSeatCapacity();
    }

    public Set<CafeTable> checkAvailableTables(LocalDate reservationDate, int reservationDurationInHours, int numberOfPeople) {
        return reservationRepository.checkAvailableTables(reservationDate, reservationDurationInHours, numberOfPeople);
    }

    public void addTablesToReservation(Reservation reservation, Set<CafeTable> cafeTables) {

    }

    public List<CafeTable> getAllAvailableTables(LocalDate reservationDate, LocalTime time, LocalTime toTime) {
        return reservationRepository.getAvailableTables(reservationDate, time, toTime);
    }

    public Reservation getReservationByUrl(String Url) {
        return reservationRepository.getReservationByUrl(Url);
    }

    public void addBeverageReservationsToReservation(Reservation reservation, Set<BeverageReservation> beverageReservations) {
        reservation.setBeverageReservations(beverageReservations);
    }

    public void assignTableToReservation(int reservationId, int tableId) {
        reservationRepository.assignTableToReservation(reservationId, tableId);
    }

}
