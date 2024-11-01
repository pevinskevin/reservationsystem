package com.pevinskevin.reservationsystem.Services;

import com.pevinskevin.reservationsystem.Repositories.BeverageReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BeverageReservationService {

    @Autowired
    BeverageReservationRepository beverageReservationRepository;

    public void createBeverageReservation(int reservationId, int beverageId, int beverageQuantity){
        beverageReservationRepository.createBeverageReservation(reservationId, beverageId, beverageQuantity);
    }
}
