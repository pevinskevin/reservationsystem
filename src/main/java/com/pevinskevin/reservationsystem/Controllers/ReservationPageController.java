package com.pevinskevin.reservationsystem.Controllers;

import com.pevinskevin.reservationsystem.Models.Reservation;
import com.pevinskevin.reservationsystem.Services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class ReservationPageController {

    @Autowired
    ReservationService reservationService;

    @GetMapping("/reservation")
    public String displayBookingPage(Model model) {

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
        if ((totalAvailableSeats - numberOfGuests ) >= 0){
            String reservationUrl = reservationService.createReservation(reservation);
            return "redirect:/reservation/" + reservationUrl;
        }
        return "redirect:/reservationdenied/" + totalAvailableSeats;}
}
