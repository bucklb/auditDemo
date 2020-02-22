package me.bucklb.auditDemo.service;

import com.jayway.jsonpath.JsonPath;
import me.bucklb.auditDemo.Domain.CarfAction;
import me.bucklb.auditDemo.Domain.CarfEventDetail;
import me.bucklb.auditDemo.Domain.ManifestItem;

/*
    Want something that we can use to get our details to present to ATAS
 */
public class CarfDetailServiceImpl implements CarfDetailService {

    // We always need a provider.  Might also want to know what its nature is
    CarfDetailProvider carfDetailProvider;
    CarfAction action;


    // Right.  The service really needn't know about how the data is provided, beyond getting a "provider"

    // If we are going to do b4 & af then theaction is implicit and need not be stated explicitly?
    public CarfDetailServiceImpl(CarfDetailProvider carfDetailProvider){
        this.carfDetailProvider = carfDetailProvider;
    }
    // If we are doing just bf, af or rf then need to know which
    public CarfDetailServiceImpl(CarfDetailProvider carfDetailProvider, CarfAction action){
        this.carfDetailProvider = carfDetailProvider;
        this.action = action;
    }

    // If we have a format, and it's appropriate, might want to try and apply it ...
    private String applyFormat(ManifestItem mi, String val){
        return val;
    }




    // Given a manifest item, use it to generate a corresponding eventDetailObject
    public CarfEventDetail getCarfEventDetail(ManifestItem manifestItem) {
        CarfEventDetail ced = null;

        if(action!=null){
            String deet = carfDetailProvider.getCarfDetailValue(manifestItem.getDataPath());

            // Will need to look at format type steps

            ced = new CarfEventDetail.CarfEventDetailBuilder(manifestItem.getCarfName())
                    .setValue(deet,action).build();
        } else {
            // must be doing b4 AND af
            String[] deets = carfDetailProvider.getCarfDetailValues(manifestItem.getDataPath());

            // Will need to look at format type steps

            ced = new CarfEventDetail.CarfEventDetailBuilder(manifestItem.getCarfName())
                    .setValue(deets[0],CarfAction.B4).setValue(deets[1],CarfAction.AF).build();
        }

        return ced;
    }



}
