package me.bucklb.auditDemo.service;

import com.jayway.jsonpath.JsonPath;
import me.bucklb.auditDemo.Domain.CarfAction;
import me.bucklb.auditDemo.Domain.CarfEventDetail;
import me.bucklb.auditDemo.Domain.ManifestItem;

/*
    Want something that we can use to get our details to present to ATAS
 */
public class CarfDetailService {
    CarfAction action;
    String json;
    String jsonB4;
    String jsonAF;

    // One thing to query and what action it relates to
    public CarfDetailService(String json, CarfAction action){
        this.action = action;
        this.json = json;
    }

    // Two things to query, so a given that it's an update
    public CarfDetailService(String jsonB4, String jsonAF){
        this.action = CarfAction.UPDATE;
        // TODO - spot (effectively) identical strings?
        this.jsonB4 = jsonB4;
        this.jsonAF = jsonAF;
    }

    // Mechanism to fish a value out ...
    private String jsonValue(String json, String dataPath) {
        // Grab the data
        String val = (String) JsonPath.read(json,dataPath);
        return val;
    }

    // Mechanism to fish a value out and then decide if it's OK
    private String jsonValue(String json, ManifestItem manifestItem) {
        // Grab the data using the details in manifestItem
        String val = (String) JsonPath.read(json,manifestItem.getDataPath());

        // TODO - if missing/null and mandatory, then that's bad

        // TODO - if there's a format, then enforce it

        return val;
    }

    // Given a manifest item, use it to generate a corresponding eventDetailObject
    public CarfEventDetail getCarfEventDetail(ManifestItem manifestItem) {
        CarfEventDetail ced = null;

        // Need to look at 2 strings
        if(action.equals(CarfAction.UPDATE)) {
            String b4 = jsonValue(jsonB4, manifestItem.getDataPath());
            String af = jsonValue(jsonAF, manifestItem.getDataPath());

            if (af.equals(b4)) {
                // TODO - if no change do we NOT return an object here (or let someone further on decide that ?)
            } else {
                ced = new CarfEventDetail.CarfEventDetailBuilder(manifestItem.getCarfName())
                        .setValue(b4,CarfAction.B4).setValue(af,CarfAction.AF).build();
            }
        } else {
            String val = jsonValue(json, manifestItem.getDataPath());
            ced = new CarfEventDetail.CarfEventDetailBuilder(manifestItem.getCarfName())
                    .setValue(val,action).build();
        }

        return ced;
    }



}
