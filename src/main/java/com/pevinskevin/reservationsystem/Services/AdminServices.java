package com.pevinskevin.reservationsystem.Services;

import com.pevinskevin.reservationsystem.Models.Reservation;
import com.pevinskevin.reservationsystem.Repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServices {

    @Autowired
    AdminRepository adminRepository;

    public String checkUserNamePasswordMatch(String username, String password) {
        return adminRepository.checkUserNameAndPasswordMatch(username, password);
    }

    public List<Reservation> getAllReservations(List<Integer> listOfIds) {
        return adminRepository.getAllReservations(listOfIds);
    }

    public void addBeverageToDb(){}

    public void deleteBeverageFromDb(){}

    public void editBeverageToDb(){}

    public void addBeverageToReservation(){}

    public void deleteBeverageFromReservation(){}

    public void editBeverageToReservation(){}

    public void assignTableToReservation(){}

    public void removeTableFromReservation(){}

    public void deleteTableFromReservation(){}

    public void updateTable(){};





}
