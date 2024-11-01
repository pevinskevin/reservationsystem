package com.pevinskevin.reservationsystem.Repositories;

import com.pevinskevin.reservationsystem.Models.Beverage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BeverageRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Beverage> getListOfAllBeverages() {
        String query = "SELECT * FROM beverage";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(Beverage.class));
    }

    public void addBeverage(String name, int price){
        String query = "INSERT INTO beverage (name, price) VALUES (?, ?)";
        jdbcTemplate.update(query, name, price);
    }
}
