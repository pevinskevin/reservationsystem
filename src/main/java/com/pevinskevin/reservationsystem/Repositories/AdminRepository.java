package com.pevinskevin.reservationsystem.Repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

@Repository
public class AdminRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public String checkUserNameAndPasswordMatch(String username, String password){
        String query = "SELECT url FROM admin WHERE user_name = ? AND password = ?";
        return jdbcTemplate.queryForObject(query, String.class, username, password);
    }
}
