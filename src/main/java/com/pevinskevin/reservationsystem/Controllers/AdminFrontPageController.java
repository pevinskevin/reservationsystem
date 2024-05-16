package com.pevinskevin.reservationsystem.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AdminFrontPageController {

    @GetMapping("/{adminUrl}")
    public String diplayAdminFrontPage(Model model,
                                       @PathVariable String adminUrl){
        return "adminfrontpage";
    }
}
