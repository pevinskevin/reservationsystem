package com.pevinskevin.reservationsystem.Models;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

public class Reservation {
    private int id;
    private String name;
    private String email;
    private String companyName;
    private int phoneNumber;
    private int numberOfSeats;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate reservationDate;
    private LocalTime time;
    private int durationInHours;
    private String celebration;
    private String birthday;
    private String comments;
    private String url;
    private Set<CafeTable> cafeTables;
    private Set<BeverageReservation> beverageReservations;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDate reservationDate) {
        this.reservationDate = reservationDate;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public int getDurationInHours() {
        return durationInHours;
    }

    public void setDurationInHours(int durationInHours) {
        this.durationInHours = durationInHours;
    }

    public String getCelebration() {
        return celebration;
    }

    public void setCelebration(String celebration) {
        this.celebration = celebration;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Set<CafeTable> getCafeTables() {
        return cafeTables;
    }

    public void setCafeTables(Set<CafeTable> cafeTables) {
        this.cafeTables = cafeTables;
    }

    public Set<BeverageReservation> getBeverageReservations() {
        return beverageReservations;
    }

    public void setBeverageReservations(Set<BeverageReservation> beverageReservations) {
        this.beverageReservations = beverageReservations;
    }
}
