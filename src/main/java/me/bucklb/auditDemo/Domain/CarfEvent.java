package me.bucklb.auditDemo.Domain;

import java.util.List;

/*
    End game of the adapter is to create a CARFed event that can be kicked on to an audit service
 */
public class CarfEvent {

    // Will want a header (or many) for every carf event in a very structured/repeatable way
    // and a list of CarfEventDetails

    private String carfEventNumber;
    private List<CarfEventDetail> carfEventDetails;

    @Override
    public String toString() {
        return "CarfEvent{" +
                "carfEventNumber='" + carfEventNumber + '\'' +
                ", carfEventDetails=" + carfEventDetails +
                '}';
    }

    public String getCarfEventNumber() {
        return carfEventNumber;
    }

    public void setCarfEventNumber(String carfEventNumber) {
        this.carfEventNumber = carfEventNumber;
    }

    public List<CarfEventDetail> getCarfEventDetails() {
        return carfEventDetails;
    }

    public void setCarfEventDetails(List<CarfEventDetail> carfEventDetails) {
        this.carfEventDetails = carfEventDetails;
    }


    // Very likely to need a builder


}
