package com.pevinskevin.reservationsystem.Controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BeverageReservationController {

    @GetMapping("/beveragereservation/{reservationUrl}")
    public String displayBeverageReservation(@PathVariable String reservationUrl){

        return "beveragereservationpage";
    }

    @PostMapping("/beveragereservation")
    public String postBeverageReservation(@PathVariable String reservationUrl){
        return "redirect:/reservation/" + reservationUrl;
    }

    @PostMapping("/nobeverageereservation")
    public String noBeverageReservation(@PathVariable String reservationUrl){
        return "redirect:/reservation/" + reservationUrl;
    }
}
