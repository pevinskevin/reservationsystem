package com.pevinskevin.reservationsystem.Repositories;

import com.pevinskevin.reservationsystem.Models.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AdminRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public String checkUserNameAndPasswordMatch(String username, String password){
        String query = "SELECT url FROM admin WHERE user_name = ? AND password = ?";
        return jdbcTemplate.queryForObject(query, String.class, username, password);
    }

    public List<Reservation> getAllReservations(List<Integer> listOfIds) {
        String query = "SELECT * FROM reservation WHERE id = ? ORDER BY reservation_date DESC";
        List<Reservation> reservations = new ArrayList<>();

        for (Integer id : listOfIds) {
            Reservation reservation = jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper<>(Reservation.class), id);
            reservations.add(reservation);
        }

        return reservations;
    }

}
