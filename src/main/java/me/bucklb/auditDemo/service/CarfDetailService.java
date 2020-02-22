package me.bucklb.auditDemo.service;

import me.bucklb.auditDemo.Domain.CarfEventDetail;
import me.bucklb.auditDemo.Domain.ManifestItem;

import java.util.List;

public interface CarfDetailService {

    // One item at a time
    default public CarfEventDetail getCarfEventDetail(ManifestItem manifestItem){

        // Probably ought to raise a not implemented exception!!
        System.out.println("getCarfEventDetail is NOT YET IMPLEMENTED");
        return null;

    }

//    // Or en masse
//    default public List<CarfEventDetail> getCarfEventDetails(List<ManifestItem> manifestItems){
//
//        // Probably ought to raise a not implemented exception!!
//        System.out.println("getCarfEventDetails is NOT YET IMPLEMENTED");
//        return null;
//
//    }

}
