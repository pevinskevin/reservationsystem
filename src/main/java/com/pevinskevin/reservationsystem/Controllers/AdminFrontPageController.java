package com.pevinskevin.reservationsystem.Controllers;

import com.pevinskevin.reservationsystem.Models.BeverageReservation;
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


        //Creates list of reservation IDs
        List<Integer> reservationIds = new ArrayList<>();
        for ( int i = 0; i < reservations.size(); i++) {
            Reservation reservation = reservations.get(i);
            reservationIds.add(reservation.getId());
        }

        //Gets list of bev reservations using list of res. IDs
        List<BeverageReservation> listOfBevReservationsUsingIds = new ArrayList<>();
        for (int i = 0; i < reservationIds.size(); i++){
            int reservationId = reservationIds.get(i);
            listOfBevReservationsUsingIds.addAll(beverageReservationService.getListOfBeverageReservationsUsingReservationId(reservationId));
        }

        List<String> listOfBeverageNames = new ArrayList<>();
        for (int i = 0; i < listOfBevReservationsUsingIds.size(); i++){
            listOfBevReservationsUsingIds.get(i);
        }

        // Map to store reservation IDs and their associated list of beverage reservations
        Map<Integer, List<BeverageReservation>> reservationToBeverageMap = new HashMap<>();
        // Iterate over the list of BeverageReservation objects
        for (BeverageReservation beverageReservation : listOfBevReservationsUsingIds) {
            int reservationId = beverageReservation.getReservationId();
            // Check if the reservation ID already exists in the map
            if (!reservationToBeverageMap.containsKey(reservationId)) {
                // If not, create a new list for this reservation ID
                reservationToBeverageMap.put(reservationId, new ArrayList<>());
            }
            // Add the beverage reservation to the list for this reservation ID
            reservationToBeverageMap.get(reservationId).add(beverageReservation);
        }

        for (Map.Entry<Integer, List<BeverageReservation>> entry : reservationToBeverageMap.entrySet()) {
            System.out.println("Reservation ID: " + entry.getKey());
            List<BeverageReservation> beverageReservations = entry.getValue();
            for (BeverageReservation beverageReservation : beverageReservations) {
                System.out.println("Beverage ID: " + beverageReservation.getBeverageId() + ", Quantity: " + beverageReservation.getQuantity());
            }
        }





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

}
