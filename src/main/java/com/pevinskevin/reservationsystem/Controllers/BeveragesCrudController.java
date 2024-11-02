package com.pevinskevin.reservationsystem.Controllers;

import com.pevinskevin.reservationsystem.Models.Beverage;
import com.pevinskevin.reservationsystem.Repositories.BeverageRepository;
import com.pevinskevin.reservationsystem.Services.BeverageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BeveragesCrudController {

    @Autowired
    BeverageService beverageService;
    @Autowired
    private BeverageRepository beverageRepository;

    //Beverage front page
    @GetMapping("/{adminUrl}/beveragescrud")
    public String beveragesCrudControllerDisplay(@PathVariable String adminUrl,
                                                 Model model){
        model.addAttribute("listOfBeverages", beverageService.getListOfAllBeverages());
        return "beveragescrud";
    }

    //Button redirecting to adding new beverage functionality
    @PostMapping("/{adminUrl}/redirectnewbeverage")
    public String redirectToBeverageDisplay(@PathVariable String adminUrl){
        return "redirect:/" + adminUrl + "/postnewbeverage";
    }

    //Adding new beverage landing page
    @GetMapping("/{adminUrl}/postnewbeverage")
    public String addBeverageDisplay(Model model,
                                  @ModelAttribute("beverage") Beverage beverage){
        model.addAttribute("beverage", new Beverage());
        return "createnewbeverage";
    }

    //Post for submitting new beverage
    @PostMapping("/{adminUrl}/postnewbeverage")
    public String postNewBeverage(Model model,
                                  @ModelAttribute("beverage") Beverage beverage,
                                  @PathVariable String adminUrl){
        beverageService.addBeverage(beverage.getName(), beverage.getPrice());
        return "redirect:/" + adminUrl + "/beveragescrud";
    }

    //Post method for redirecting to edit beverage display
    @PostMapping("/{adminUrl}/editbeverage")
    public String redirectToEditBeverageDisplay(@PathVariable String adminUrl,
                                                @RequestParam("beverageid") int beverageId) {
        return "redirect:/" + adminUrl + "/editbeverage/" + beverageId;
    }

    //Landing page for editing existing beverage
    @GetMapping("/{adminUrl}/editbeverage/{beverageId}")
    public String editBeverageDisplay(Model model,
                                      @PathVariable String adminUrl,
                                      @PathVariable int beverageId){
        model.addAttribute("beverage", beverageService.getBeverageUsingId(beverageId));
        return "editbeverage";
    }

    //Post method for submitting edited beverage
    @PostMapping("/{adminUrl}/editbeverage/{beverageId}")
    public String postEditedBeverage(@PathVariable String adminUrl,
                                     @PathVariable int beverageId,
                                     @RequestParam("name") String name,
                                     @RequestParam("price") int price) {
        beverageService.updateBeverage(name, price, beverageId);
        return "redirect:/" + adminUrl + "/beveragescrud";
    }

    //Post-method for deleting a specific beverage.
    @PostMapping("/{adminUrl}/deletebeverage")
    public String deleteBeverage(@PathVariable String adminUrl,
                                 @RequestParam("beverageid") int beverageId){
        beverageRepository.deleteBeverage(beverageId);
        return "redirect:/" + adminUrl + "/beveragescrud";
    }
}
