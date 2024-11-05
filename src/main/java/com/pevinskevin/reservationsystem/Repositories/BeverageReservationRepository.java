package com.pevinskevin.reservationsystem.Repositories;

import com.pevinskevin.reservationsystem.Models.BeverageHelper;
import com.pevinskevin.reservationsystem.Models.BeverageReservation;
import com.pevinskevin.reservationsystem.Models.EditBeverageHelper;
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

    public List<BeverageHelper> getBeverageNamesAndQuantitiesByReservationId(int reservationId) {
        String query = "SELECT b.name, br.quantity " +
                "FROM beverage_reservation br " +
                "JOIN beverage b ON br.beverage_id = b.id " +
                "WHERE br.reservation_id = ?";

        return jdbcTemplate.query(query, new Object[]{reservationId}, (rs, rowNum) -> {
            String name = rs.getString("name");
            int quantity = rs.getInt("quantity");
            return new BeverageHelper(name, quantity);
        });
    }

    public List<EditBeverageHelper> editBeverageHelpers(int id){
        String query = "SELECT b.name, br.quantity, br.reservation_id FROM beverage_reservation br JOIN beverage b on br.beverage_id = b.id WHERE br.reservation_id = ?";
        return jdbcTemplate.query(query, new Object[]{id}, (rs, rowNum) -> {
            String name = rs.getString("name");
            int quantity = rs.getInt("quantity");
            int reservationId = rs.getInt("reservation_id");
            return new EditBeverageHelper(reservationId, name, quantity);
        });
    }

}
