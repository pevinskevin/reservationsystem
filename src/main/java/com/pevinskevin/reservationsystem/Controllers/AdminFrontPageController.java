package com.pevinskevin.reservationsystem.Controllers;

import com.pevinskevin.reservationsystem.Models.AssignedTableHelper;
import com.pevinskevin.reservationsystem.Models.BeverageHelper;
import com.pevinskevin.reservationsystem.Models.Reservation;
import com.pevinskevin.reservationsystem.Services.AdminService;
import com.pevinskevin.reservationsystem.Services.BeverageReservationService;
import com.pevinskevin.reservationsystem.Services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AdminFrontPageController {

    @Autowired
    AdminService adminService;
    @Autowired
    ReservationService reservationService;
    @Autowired
    BeverageReservationService beverageReservationService;

    @GetMapping("/{adminUrl}")
    public String diplayAdminFrontPage(Model model,
                                       @PathVariable String adminUrl){

        //Display bookings without tables assigned for ALL guests.
        //Within each booking display the available tables
        //When a booking has seating for all guests, it's not available in the list.
        //Potentially add a calendar that displays all bookings in a calendar grid format?
        List<Integer> listofIds = reservationService.getReservationsWithoutAllSeatsCovered();
        model.addAttribute("reservation", adminService.getAllReservations(listofIds));

        //Display unassigned seats
        //1. Query for reservation ID in cafe_table_reservation.
        //2. Return the seat capacity of the tables that are assigned
        //3. Subtract the tables capacity from the number of guests.
        List<Integer> sumOfAssignedSeats = reservationService.getListOfAssignedTablesWithIdList(listofIds);
        model.addAttribute("sumOfAssignedSeats", sumOfAssignedSeats);

        //For separate showing of all upcoming reservations.
        model.addAttribute("listOfUpcomingReservations", reservationService.getAllUpcomingReservations());

        //1. Get list of all upcoming IDs.
        //2. Use the IDs to fetch a list of beverage reservations.
        //3. Map the beverage reservations to their respective reservation IDs
        //4. Fetch each beverage name and then display each beverage reservation to relevant reservation.
        List<Reservation> reservations = reservationService.getAllUpcomingReservations();
        Map<Integer, List<BeverageHelper>> drinksMap = new HashMap<>();
        // Populates the map with reservation IDs and their respective beverages
        for (Reservation reservation : reservations) {
            int reservationId = reservation.getId();
            List<BeverageHelper> preOrderedDrinks = beverageReservationService.getBeverageNamesAndQuantitiesByReservationId(reservationId);
            drinksMap.put(reservationId, preOrderedDrinks);
        }
        model.addAttribute("drinksMap", drinksMap);

        List<Integer> reservationIds = new ArrayList<>();
        for (int i = 0; i < reservations.size(); i++){
            int id = reservations.get(i).getId();
            reservationIds.add(id);
        }
        Map<Integer, List<AssignedTableHelper>> tablesByReservation = reservationService.getTablesForReservations(reservationIds);
        model.addAttribute("tablesByReservation", tablesByReservation);

        return "adminview";
    }

    @PostMapping("/{adminUrl}/assigntable")
    public String assignTableToReservation(Model model,
                                           @PathVariable String adminUrl,
                                           @RequestParam("reservationUrl") String reservationUrl){

        return "redirect:/{adminUrl}/" + reservationUrl;
    }

    @PostMapping("/{adminUrl}/deletebooking")
    public String deleteBooking(@PathVariable String adminUrl,
                                           @RequestParam("reservationUrl") String reservationUrl){

        reservationService.deleteBookingUsingBookingUrl(reservationUrl);
        return "redirect:/{adminUrl}";
    }

    @PostMapping("/{adminUrl}/editbooking")
    public String editBooking(@PathVariable String adminUrl,
                              @RequestParam("reservationUrl") String reservationUrl){
        return "redirect:/{adminUrl}/editreservation/" + reservationUrl;
    }

    @PostMapping("/{adminUrl}/redirecttobeveragecrud")
    public String redirectToBeverageCrud(@PathVariable String adminUrl){
        return "redirect:/{adminUrl}/beveragescrud";
    }

    @PostMapping("/{adminUrl}/editbeveragepreorder")
    public String redirectToBevEditController(@PathVariable String adminUrl,
                                              @RequestParam("reservationUrl") String reservationUrl){
        return "redirect:/" + adminUrl + "/editbeveragepreorder/" + reservationUrl;
    }

}
