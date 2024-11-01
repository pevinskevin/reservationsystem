package com.pevinskevin.reservationsystem.Controllers;


import com.pevinskevin.reservationsystem.Models.Beverage;
import com.pevinskevin.reservationsystem.Models.BeverageReservation;
import com.pevinskevin.reservationsystem.Services.BeverageReservationService;
import com.pevinskevin.reservationsystem.Services.BeverageService;
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

public class BeverageReservationController {

    @Autowired
    ReservationService reservationService;
    @Autowired
    BeverageService beverageService;
    @Autowired
    BeverageReservationService beverageReservationService;

    @GetMapping("/beveragereservation/{reservationUrl}")
    public String displayBeverageReservation(@PathVariable String reservationUrl, Model model){
        reservationService.getReservationIdUsingReservationUrl(reservationUrl);

        model.addAttribute("Beverage", new Beverage());
        model.addAttribute("BeverageReservation", new BeverageReservation());
        model.addAttribute("listOfBeverages", beverageService.getListOfAllBeverages());

        return "beveragereservationpage";
    }

    @PostMapping("/beveragereservation/{reservationUrl}")
    public String postBeverageReservation(@PathVariable String reservationUrl,
                                          @RequestParam List<Integer> beverageIds,
                                          @RequestParam List<Integer> quantities){

        int reservationId = reservationService.getReservationIdUsingReservationUrl(reservationUrl);

        for (int i = 0; i < beverageIds.size(); i++) {
            int beverageId = beverageIds.get(i);
            int quantity = quantities.get(i);

            if (quantity > 0) {
                // Save each beverage with its quantity for this reservation
                beverageReservationService.createBeverageReservation(reservationId, beverageId, quantity);
            }
        }




        return "redirect:/reservation/" + reservationUrl;
    }

    @PostMapping("/nobeveragereservation/{reservationUrl}")
    public String noBeverageReservation(@PathVariable String reservationUrl){
        return "redirect:/reservation/" + reservationUrl;
    }
}
