package com.pevinskevin.reservationsystem.Controllers;

import com.pevinskevin.reservationsystem.Models.CafeTable;
import com.pevinskevin.reservationsystem.Models.Reservation;
import com.pevinskevin.reservationsystem.Services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;

@Controller
public class AssignTableController {


    @Autowired
    ReservationService reservationService;

    @GetMapping("/{adminUrl}/{reservationUrl}")
    public String showAssignTablePage(@PathVariable String adminUrl,
                                      @PathVariable String reservationUrl,
                                      Model model) {

        //Display bookings without tables assigned for ALL guests.
        //Within each booking display the available tables
        //When a booking has seating for all guests, it's not available in the list.
        //Potentially add a calendar that displays all bookings in a calendar grid format?


        Reservation reservation = reservationService.getReservationByUrl(reservationUrl);
        model.addAttribute("reservation", reservation);
        LocalTime getTime = reservation.getTime();
        LocalTime toTime = getTime.plusHours((Long.valueOf(reservation.getDurationInHours())));
        List<CafeTable> availableTables = reservationService.getAllAvailableTables(reservation.getReservationDate(), getTime, toTime);
        model.addAttribute("availableTables", availableTables);

        return "assigntable";
    }

    @PostMapping("/{adminUrl}/{reservationUrl}")
    public String assignTable(@RequestParam("cafeTableId") int cafeTableId,
                              @PathVariable String adminUrl,
                              @PathVariable String reservationUrl) {

        Reservation reservation = reservationService.getReservationByUrl(reservationUrl);
        reservationService.assignTableToReservation(reservation.getId(), cafeTableId);

        return "redirect:/{adminUrl}";
    }
}
