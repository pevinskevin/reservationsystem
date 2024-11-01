package com.pevinskevin.reservationsystem.Controllers;

import com.pevinskevin.reservationsystem.Models.Admin;
import com.pevinskevin.reservationsystem.Services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class IndexController {

    @Autowired
    AdminService adminService;

    @GetMapping("/")
    public String displayIndex(Model model){

        model.addAttribute("admin", new Admin());
        return "index";
    }

    @PostMapping("/adminpage")
    public String redirectAdminPage(@ModelAttribute("admin") Admin admin,
                                    Model model){

        return "redirect:/" + adminService.checkUserNamePasswordMatch(admin.getUserName(), admin.getPassword());
    }

    @PostMapping("/booking")
    public String redirectBookingPage(){

        return "redirect:/reservation";
    }
}
