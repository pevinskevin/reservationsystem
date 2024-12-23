package com.pevinskevin.reservationsystem.Controllers;

import com.pevinskevin.reservationsystem.Models.Beverage;
import com.pevinskevin.reservationsystem.Models.BeverageReservation;
import com.pevinskevin.reservationsystem.Models.Reservation;
import com.pevinskevin.reservationsystem.Services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Controller
public class ReservationPageController {

    @Autowired
    ReservationService reservationService;

    @GetMapping("/reservation")
    public String displayBookingPage(Model model) {

        LocalDate tomorrow = LocalDate.now().plusDays(1);
        model.addAttribute("minDate", tomorrow);

        int minNumberOfGuests = 2;
        model.addAttribute("minNumberOfGuests", minNumberOfGuests);

        String minTime = "07:00";
        String maxTime = "22:00";
        model.addAttribute("minTime", minTime);
        model.addAttribute("maxTime", maxTime);

        model.addAttribute("beverage", new Beverage());
        model.addAttribute("beverageReservation", new BeverageReservation());
        model.addAttribute("reservation", new Reservation());
        return "reservationpage";
    }

    @GetMapping("/reservationdenied/{numberOfAvailableSeats}")
    public String reservationDenied(@PathVariable int numberOfAvailableSeats,
                                    Model model) {

        model.addAttribute("numberOfAvailableSeats", numberOfAvailableSeats);
        return "reservationdenied.html";
    }

    @PostMapping("/reservation")
    public String createBooking(@ModelAttribute("reservation") Reservation reservation,
                                @ModelAttribute("beverage") Beverage beverage,
                                @ModelAttribute("beverageReservation") BeverageReservation beverageReservation,
                                BindingResult result,
                                Model model) {

        LocalDate tomorrow = LocalDate.now().plusDays(1);
        model.addAttribute("minDate", tomorrow);

        String minTime = "07:00";
        String maxTime = "22:00";
        model.addAttribute("minTime", minTime);
        model.addAttribute("maxTime", maxTime);

        if (reservation.getName() == null || reservation.getName().isEmpty()) {
            result.rejectValue("name", "error.reservation", "Name is mandatory");
        }
        if (reservation.getEmail() == null || reservation.getEmail().isEmpty()) {
            result.rejectValue("email", "error.reservation", "Email is mandatory");
        }
        if (reservation.getPhoneNumber() == 0) {
            result.rejectValue("phoneNumber", "error.reservation", "Phone number is mandatory");
        }
        if (reservation.getNumberOfSeats() == 0) {
            result.rejectValue("numberOfSeats", "error.reservation", "Number of attending guests");
        }
        if (reservation.getReservationDate() == null) {
            result.rejectValue("reservationDate", "error.reservation", "Date is mandatory");
        }
        if (reservation.getTime() == null) {
            result.rejectValue("time", "error.reservation", "Time is mandatory");
        }
        if (reservation.getDurationInHours() == 0) {
            result.rejectValue("durationInHours", "error.reservation", "Duration is mandatory");
        }
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime reservationDateTime = LocalDateTime.of(reservation.getReservationDate(), reservation.getTime());

        if (reservationDateTime.isBefore(now.plusDays(1))) {
            result.rejectValue("time", "error.reservation", "Reservations must be made at least 24 hours in advance");
        }
        if (result.hasErrors()) {
            return "reservationpage";
        }

        //First it needs to check for total seat capacity, where tables within the same timeframe that are assigned to a reservation are excluded.
        //Then it checks for unassigned reservations and uses that to figure out the total available seat capacity.
            int theoreticalTotalSeatCapacity = reservationService.checkTotalSeatCapacity();
            int reservedSeats = reservationService.checkForAssignedTables(reservation);
            int realTotalSeatCapacity = theoreticalTotalSeatCapacity - reservedSeats;

            int unavailableSeats = reservationService.checkUnavailableSeats(reservation) - reservedSeats;
            int totalAvailableSeats = (realTotalSeatCapacity - unavailableSeats);
            int numberOfGuests = reservation.getNumberOfSeats();
            if (totalAvailableSeats >= numberOfGuests) {
                // Set default values if checkboxes are unchecked
                if (reservation.getCelebration() == null) {
                    reservation.setCelebration("false");
                }
                if (reservation.getBirthday() == null) {
                    reservation.setBirthday("false");
                }
                String reservationUrl = reservationService.createReservation(reservation);
                //Get the ID for the newly created reservation.
                //Use the ID for creating a new beverage reservation.
                return "redirect:/beveragereservation/" + reservationUrl;
            }
            if (totalAvailableSeats < 0) {return "redirect:/reservationdenied/" + 0; }
            return "redirect:/reservationdenied/" + totalAvailableSeats;
        }
}