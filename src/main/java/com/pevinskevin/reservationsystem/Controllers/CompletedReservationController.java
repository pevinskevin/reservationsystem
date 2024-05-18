package com.pevinskevin.reservationsystem.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CompletedReservationController {


    @GetMapping("/reservation/{reservationUrl}")
    public String showCompletedReservation(@PathVariable String reservationUrl) {

        return "completedReservation";
    }
}
