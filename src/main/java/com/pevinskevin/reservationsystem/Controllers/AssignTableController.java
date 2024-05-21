package com.pevinskevin.reservationsystem.Controllers;

import com.pevinskevin.reservationsystem.Models.Reservation;
import com.pevinskevin.reservationsystem.Services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AssignTableController {


    @Autowired
    ReservationService reservationService;

    @GetMapping("/{adminUrl}/{reservationUrl}")
    public String showAssignTablePage(@PathVariable String adminUrl,
                                      @PathVariable String reservationUrl,
                                      Model model) {

        Reservation reservation = reservationService.getReservationByUrl(reservationUrl);
        model.addAttribute("reservation", reservation);

        //Logic for getting all available tables

        return "assigntable";
    }
}
