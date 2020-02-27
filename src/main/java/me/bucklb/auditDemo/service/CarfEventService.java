package me.bucklb.auditDemo.service;

import me.bucklb.auditDemo.Domain.CarfEvent;
import me.bucklb.auditDemo.Domain.CarfManifest;

/*
    Where we expect to turn a CarfManifest in to CarfEvent, using an (injected) CarfDetailService
 */
public interface CarfEventService {

    // One item at a time
    default public CarfEvent getCarfEvent(CarfManifest carfManifest){

        // Probably ought to raise a not implemented exception!!
        System.out.println("getCarfEvent is NOT YET IMPLEMENTED");
        return null;

    }
}
