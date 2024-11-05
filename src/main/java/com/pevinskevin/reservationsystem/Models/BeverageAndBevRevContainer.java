package com.pevinskevin.reservationsystem.Models;

import java.util.List;

public class BeverageAndBevRevContainer {
    private List<BeverageReservation> beverageReservations;
    private List<BeverageHelper> helperClasses;

    public BeverageAndBevRevContainer(List<BeverageReservation> beverageReservations, List<BeverageHelper> helperClasses) {
        this.beverageReservations = beverageReservations;
        this.helperClasses = helperClasses;
    }

    public List<BeverageReservation> getBeverageReservations() {
        return beverageReservations;
    }

    public void setBeverageReservations(List<BeverageReservation> beverageReservations) {
        this.beverageReservations = beverageReservations;
    }

    public List<BeverageHelper> getHelperClasses() {
        return helperClasses;
    }

    public void setHelperClasses(List<BeverageHelper> helperClasses) {
        this.helperClasses = helperClasses;
    }
}


