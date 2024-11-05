package com.pevinskevin.reservationsystem.Services;

import com.pevinskevin.reservationsystem.Models.BeverageHelper;
import com.pevinskevin.reservationsystem.Models.BeverageReservation;
import com.pevinskevin.reservationsystem.Repositories.BeverageReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeverageReservationService {

    @Autowired
    BeverageReservationRepository beverageReservationRepository;

    public void createBeverageReservation(int reservationId, int beverageId, int beverageQuantity){
        beverageReservationRepository.createBeverageReservation(reservationId, beverageId, beverageQuantity);
    }

    public List<BeverageReservation> getListOfBeverageReservationsUsingReservationId(int reservationId){
        return beverageReservationRepository.getListOfBeverageReservationsUsingReservationId(reservationId);
    }

    public List<BeverageHelper> getBeverageNamesAndQuantitiesByReservationId(int id){
        return beverageReservationRepository.getBeverageNamesAndQuantitiesByReservationId(id);
    }

}
