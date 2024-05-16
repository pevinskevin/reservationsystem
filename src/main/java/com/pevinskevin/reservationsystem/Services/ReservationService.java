package com.pevinskevin.reservationsystem.Services;
import com.pevinskevin.reservationsystem.Models.BeverageReservation;
import com.pevinskevin.reservationsystem.Models.Reservation;
import com.pevinskevin.reservationsystem.Models.CafeTable;
import com.pevinskevin.reservationsystem.Repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Set;

@Service
public class ReservationService {

    @Autowired
    ReservationRepository reservationRepository;

    public Reservation createReservation(Reservation reservation){
        if (!checkAvailableTables(reservation.getReservationDate(), reservation.getReservationDurationInHours(), reservation.getNumberOfSeats()).isEmpty()) {
            reservationRepository.save(reservation);
        } else {
            return null;
        }
        return reservation;
    }

    public Set<CafeTable> checkAvailableTables(Date reservationDate, int reservationDurationInHours, int numberOfPeople) {
        return reservationRepository.checkAvailableTables(reservationDate, reservationDurationInHours, numberOfPeople);
    }

    public void addTablesToReservation(Reservation reservation, Set<CafeTable> cafeTables) {

    }

    public void addBeverageReservationsToReservation(Reservation reservation, Set<BeverageReservation> beverageReservations) {
        reservation.setBeverageReservations(beverageReservations);
    }

}
