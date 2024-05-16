package com.pevinskevin.reservationsystem.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookingPageController {

    @GetMapping("/booking")
    public String displayBookingPage() {
        return "bookingpage";
    }
}
