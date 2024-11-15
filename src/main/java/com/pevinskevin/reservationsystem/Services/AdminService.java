package com.pevinskevin.reservationsystem.Services;

import com.pevinskevin.reservationsystem.Models.Reservation;
import com.pevinskevin.reservationsystem.Repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    AdminRepository adminRepository;

    public String checkUserNamePasswordMatch(String username, String password) {
        return adminRepository.checkUserNameAndPasswordMatch(username, password);
    }

    public List<Reservation> getAllReservations(List<Integer> listOfIds) {
        return adminRepository.getAllReservations(listOfIds);
    }
}
