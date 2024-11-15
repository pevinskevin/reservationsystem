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


@Controller
public class EditBeverageReservationController {

    @Autowired
    BeverageService beverageService;
    @Autowired
    ReservationService reservationService;
    @Autowired
    BeverageReservationService beverageReservationService;

    @GetMapping("/{adminUrl}/editbeveragepreorder/{reservationUrl}")
    public String displayEditBeveragePreorder(Model model,
                                              @PathVariable String reservationUrl){
        int id = reservationService.getReservationIdUsingReservationUrl(reservationUrl);
        model.addAttribute("Beverage", new Beverage());
        model.addAttribute("BeverageReservation", new BeverageReservation());
        model.addAttribute("listOfBeverages", beverageService.getListOfAllBeverages());
        model.addAttribute("editHelpers", beverageReservationService.editBeverageHelpers(id));
        return "editbeveragereservation";
    }

    @PostMapping("/{adminUrl}/submiteditbeveragepreorder")
    public String postEditedBevPreorder(@PathVariable String adminUrl){

        return "redirect:/" + adminUrl;
    }
}
