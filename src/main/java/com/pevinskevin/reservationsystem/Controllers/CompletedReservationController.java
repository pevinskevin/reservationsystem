package com.pevinskevin.reservationsystem.Controllers;

import com.pevinskevin.reservationsystem.Models.BeverageReservation;
import com.pevinskevin.reservationsystem.Models.Reservation;
import com.pevinskevin.reservationsystem.Services.BeverageReservationService;
import com.pevinskevin.reservationsystem.Services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CompletedReservationController {

    @Autowired
    ReservationService reservationService;
    @Autowired
    BeverageReservationService beverageReservationService;

    @GetMapping("/reservation/{reservationUrl}")
    public String showCompletedReservation(@PathVariable String reservationUrl,
                                            Model model) {

        Reservation reservation = reservationService.getReservationByUrl(reservationUrl);
        int reservationId = reservationService.getReservationIdUsingReservationUrl(reservationUrl);
        model.addAttribute("reservation", reservation);
        model.addAttribute("beverageReservation", new BeverageReservation());
        model.addAttribute("listOfBeverageReservations", beverageReservationService.getListOfBeverageReservationsUsingReservationId(reservationId));
        return "completedReservation";
    }
}
