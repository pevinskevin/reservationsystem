package com.pevinskevin.reservationsystem.Repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BeverageReservationRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void createBeverageReservation(int reservationId, int beverageId, int beverageQuantity){
        String query = "INSERT INTO beverage_reservation (reservation_id, beverage_id, beverage_quantity) values (?, ?, ?)";
        jdbcTemplate.update(query, reservationId, beverageId, beverageQuantity);
    }


}
