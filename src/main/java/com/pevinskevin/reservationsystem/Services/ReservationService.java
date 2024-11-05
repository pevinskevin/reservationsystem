package com.pevinskevin.reservationsystem.Services;
import com.pevinskevin.reservationsystem.Models.AssignedTableHelper;
import com.pevinskevin.reservationsystem.Models.BeverageReservation;
import com.pevinskevin.reservationsystem.Models.Reservation;
import com.pevinskevin.reservationsystem.Models.CafeTable;
import com.pevinskevin.reservationsystem.Repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    public List<Integer> getReservationsWithoutAllSeatsCovered() {
        return reservationRepository.getReservationIdsNeedingTables();
    }

    public int checkForAssignedTables(Reservation reservation) {
        return reservationRepository.checkForAssignedTables(reservation);
    }

    public int checkUnavailableSeats(Reservation reservation) {
        return reservationRepository.checkUnavailableSeats(reservation);
    }

    public int checkTotalSeatCapacity() {
        return reservationRepository.checkTotalSeatCapacity();
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

    public List<Integer> getListOfAssignedTablesWithIdList(List<Integer> tableIdList) {
        return reservationRepository.getListOfAssignedTablesWithIdList(tableIdList);
    }

    public int getReservationIdUsingReservationUrl(String reservationUrl){
        return reservationRepository.getReservationIdUsingReservationUrl(reservationUrl);
    }

    public void deleteBookingUsingBookingUrl(String reservationUrl){
        reservationRepository.deleteBookingUsingBookingUrl(reservationUrl);
    }

    public void updateReservation(Reservation reservation, String reservationUrl){
        reservationRepository.updateReservation(reservation, reservationUrl);
    }

    public List<Reservation> getAllUpcomingReservations(){
        return reservationRepository.getAllUpcomingReservations();
    }

    public Map<Integer, List<AssignedTableHelper>> getTablesForReservations(List<Integer> reservationIds) {
        Map<Integer, List<AssignedTableHelper>> tablesByReservation = new HashMap<>();

        for (int reservationId : reservationIds) {
            List<AssignedTableHelper> assignedTables = reservationRepository.getAssignedTablesByReservationId(reservationId);
            tablesByReservation.put(reservationId, assignedTables);
        }
        return tablesByReservation;
    }
}
