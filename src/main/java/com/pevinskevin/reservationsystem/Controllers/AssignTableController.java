package com.pevinskevin.reservationsystem.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AssignTableController {


    @GetMapping("/{adminUrl}/{reservationUrl}")
    public String showAssignTablePage(@PathVariable String adminUrl,
                                      @PathVariable String reservationUrl,
                                      Model model) {

        //Logic for getting all available tables

        return "assigntable";
    }
}
