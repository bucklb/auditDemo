package me.bucklb.auditDemo.service;

import me.bucklb.auditDemo.Domain.*;

import java.util.ArrayList;
import java.util.List;

public class CarfEventServiceImpl implements CarfEventService {

    // Need a means to get our CarfEbventDetails
    CarfDetailService carfDetailService;
    CarfAction carfAction;

    // First constructor if action is implicit
    public CarfEventServiceImpl(CarfDetailProvider carfDetailProvider) {
        carfDetailService = new CarfDetailServiceImpl(carfDetailProvider);
    }
    public CarfEventServiceImpl(CarfDetailProvider carfDetailProvider, CarfAction carfAction) {
        carfDetailService = new CarfDetailServiceImpl(carfDetailProvider, carfAction);
        this.carfAction = carfAction;
    }

    @Override
    public CarfEvent getCarfEvent(CarfManifest carfManifest) {

        // Create the event and set some basic info
        CarfEvent carfEvent = new CarfEvent();
        List<CarfEventDetail> ceds = new ArrayList<>();
        // Barebones
        carfEvent.setCarfEventNumber(carfManifest.getEventNumber());
        carfEvent.setCarfEventDetails(ceds);

        // Spin through and get details one by one (hardly optimal, but will do for now)
        for(ManifestItem mi: carfManifest.getManifestItems()) {
            ceds.add(carfDetailService.getCarfEventDetail(mi));
        }

        return carfEvent;
    }
}
