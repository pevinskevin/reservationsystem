package com.pevinskevin.reservationsystem.Controllers;

import com.pevinskevin.reservationsystem.Services.AdminServices;
import com.pevinskevin.reservationsystem.Services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AdminFrontPageController {

    @Autowired
    AdminServices adminServices;
    @Autowired
    ReservationService reservationService;

    @GetMapping("/{adminUrl}")
    public String diplayAdminFrontPage(Model model,
                                       @PathVariable String adminUrl){

        //Display bookings without tables assigned for ALL guests.
        List<Integer> listofIds = reservationService.getReservationsWithoutAllSeatsCovered();

        //Within each booking display the available tables
        //When a booking has seating for all guests, it's not available in the list.
        //Potentially add a calendar that displays all bookings in a calendar grid format?

        model.addAttribute("reservation", adminServices.getAllReservations(listofIds));
        return "adminview";
    }

    @PostMapping("/{adminUrl}/assigntable")
    public String assignTableToReservation(Model model,
                                           @PathVariable String adminUrl,
                                           @RequestParam("reservationUrl") String reservationUrl){

        return "redirect:/{adminUrl}/" + reservationUrl;
    }

}
