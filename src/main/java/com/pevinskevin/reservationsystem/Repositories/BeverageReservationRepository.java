package com.pevinskevin.reservationsystem.Repositories;

import com.pevinskevin.reservationsystem.Models.BeverageReservation;
import com.pevinskevin.reservationsystem.Models.CafeTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BeverageReservationRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void createBeverageReservation(int reservationId, int beverageId, int beverageQuantity){
        String query = "INSERT INTO beverage_reservation (reservation_id, beverage_id, quantity) values (?, ?, ?)";
        jdbcTemplate.update(query, reservationId, beverageId, beverageQuantity);
    }

    public List<BeverageReservation> getListOfBeverageReservationsUsingReservationId(int reservationId){
        String query = "SELECT * from beverage_reservation where reservation_id = ?";
        return jdbcTemplate.query(query, new Object[]{reservationId}, new BeanPropertyRowMapper<>(BeverageReservation.class));
    }
}
