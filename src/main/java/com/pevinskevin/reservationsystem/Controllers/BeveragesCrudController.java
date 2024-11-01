package com.pevinskevin.reservationsystem.Controllers;

import com.pevinskevin.reservationsystem.Services.BeverageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BeveragesCrudController {

    @Autowired
    BeverageService beverageService;

    @GetMapping("/{adminUrl}/beveragescrud")
    public String beveragesCrudControllerDisplay(@PathVariable String adminUrl,
                                                 Model model){
        model.addAttribute("listOfBeverages", beverageService.getListOfAllBeverages());
        return "beveragescrud";
    }


    @PostMapping("/{adminUrl}/postnewbeverage")
    public String postNewBeverage(){
        return "beveragescrud";
    }

    @PostMapping("/{adminUrl}/editbeverage")
    public String postEditedBeverages(){
        return "beveragescrud";
    }

    @PostMapping("/{adminUrl}/deletebeverage")
    public String deleteBeverage(){
        return "beveragescrud";
    }
}
