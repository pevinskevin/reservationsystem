package com.pevinskevin.reservationsystem.Controllers;

import com.pevinskevin.reservationsystem.Models.Reservation;
import com.pevinskevin.reservationsystem.Services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;


@Controller
public class ReservationPageController {

    @Autowired
    ReservationService reservationService;

    @GetMapping("/reservation")
    public String displayBookingPage(Model model) {

        LocalDate tomorrow = LocalDate.now().plusDays(1);
        model.addAttribute("minDate", tomorrow);

        String minTime = "07:00";
        String maxTime = "22:00";
        model.addAttribute("minTime", minTime);
        model.addAttribute("maxTime", maxTime);

        model.addAttribute("reservation", new Reservation());
        return "reservationpage";
    }

    @GetMapping("/reservationdenied/{numberOfAvailableSeats}")
    public String reservationDenied(@PathVariable int numberOfAvailableSeats,
                                    Model model) {

        model.addAttribute("numberOfAvailableSeats", numberOfAvailableSeats);
        return "reservationdenied.html";
    }

    @PostMapping("/postreservation")
    public String createBooking(@ModelAttribute("reservation") Reservation reservation) {
        int totalSeatCapacity = reservationService.checkTotalSeatCapacity();
        int unavailableSeats = reservationService.checkUnavailableSeats(reservation);
        int totalAvailableSeats = totalSeatCapacity - unavailableSeats;
        int numberOfGuests = reservation.getNumberOfSeats();
        if (totalAvailableSeats >= numberOfGuests) {
            String reservationUrl = reservationService.createReservation(reservation);
            return "redirect:/reservation/" + reservationUrl;
        }
        return "redirect:/reservationdenied/" + totalAvailableSeats;
    }
}