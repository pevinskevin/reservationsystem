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
public class EditBookingController {

    @Autowired
    ReservationService reservationService;

    @GetMapping("/{adminUrl}/editreservation/{reservationUrl}")
    public String editReservationDisplay(@PathVariable String adminUrl,
                                     @PathVariable String reservationUrl,
                                     Model model){

        model.addAttribute("reservation", reservationService.getReservationByUrl(reservationUrl));

        return "editreservation";
    }

    @PostMapping("/{adminUrl}/editreservation/{reservationUrl}")
    public String postEditedReservation(@PathVariable String adminUrl,
                                        @PathVariable String reservationUrl,
                                        @ModelAttribute("reservation") Reservation reservation){

        reservationService.updateReservation(reservation, reservationUrl);
        return "redirect:/" + adminUrl;
    }
}
