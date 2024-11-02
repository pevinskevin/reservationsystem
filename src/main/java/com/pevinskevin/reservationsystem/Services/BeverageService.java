package com.pevinskevin.reservationsystem.Services;

import com.pevinskevin.reservationsystem.Models.Beverage;
import com.pevinskevin.reservationsystem.Repositories.BeverageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeverageService {

    @Autowired
    BeverageRepository beverageRepository;

    public List<Beverage> getListOfAllBeverages() {
        return beverageRepository.getListOfAllBeverages();
    }

    public void addBeverage(String name, int price) {
        beverageRepository.addBeverage(name, price);
    }

    public Beverage getBeverageUsingId(int id){
        return beverageRepository.getBeverageUsingId(id);
    }

    public void updateBeverage(String name, int price, int id){
        beverageRepository.updateBeverage(name, price, id);
    }

    public void deleteBeverage(int id){
        beverageRepository.deleteBeverage(id);
    }

    }
