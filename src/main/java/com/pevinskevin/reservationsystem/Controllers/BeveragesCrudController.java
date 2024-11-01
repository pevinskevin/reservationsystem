package com.pevinskevin.reservationsystem.Controllers;

import com.pevinskevin.reservationsystem.Models.Beverage;
import com.pevinskevin.reservationsystem.Services.BeverageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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

    @PostMapping("/{adminUrl}/redirectnewbeverage")
    public String redirectToBeverageDisplay(@PathVariable String adminUrl){
        return "redirect:/" + adminUrl + "/postnewbeverage";
    }

    @GetMapping("/{adminUrl}/postnewbeverage")
    public String addBeverageDisplay(Model model,
                                  @ModelAttribute("beverage") Beverage beverage){
        model.addAttribute("beverage", new Beverage());
        return "createnewbeverage";
    }

    @PostMapping("/{adminUrl}/postnewbeverage")
    public String postNewBeverage(Model model,
                                  @ModelAttribute("beverage") Beverage beverage,
                                  @PathVariable String adminUrl){

        beverageService.addBeverage(beverage.getName(), beverage.getPrice());
        return "redirect:/" + adminUrl + "beveragescrud";
    }

    @GetMapping("/{adminUrl}/editbeverage")
    public String editBeverageDisplay(Model model){
        model.addAttribute()
        return "createnewbeverage";
    }

    @PostMapping("/{adminUrl}/editbeverage")
    public String postEditedBeverage(Model model,
                                      @PathVariable String adminUrl){

        return "createnewbeverage";
    }

    @PostMapping("/{adminUrl}/deletebeverage")
    public String deleteBeverage(){
        return "beveragescrud";
    }
}
