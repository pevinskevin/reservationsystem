package com.pevinskevin.reservationsystem.Controllers;

import com.pevinskevin.reservationsystem.Services.AdminServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminFrontPageController {

    @Autowired
    AdminServices adminServices;

    @GetMapping("/{adminUrl}")
    public String diplayAdminFrontPage(Model model,
                                       @PathVariable String adminUrl){

        model.addAttribute("adminUrl", adminUrl);
        model.addAttribute("reservation", adminServices.getAllReservations());
        return "adminview";
    }

    @PostMapping("/{adminUrl}/assigntable")
    public String assignTableToReservation(Model model,
                                           @PathVariable String adminUrl,
                                           @RequestParam("reservationUrl") String reservationUrl){

        String url = adminUrl;
        return "redirect:/" + url + "/" + reservationUrl;
    }

}
