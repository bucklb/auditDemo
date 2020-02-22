package me.bucklb.auditDemo.service;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;
import me.bucklb.auditDemo.Domain.CarfAction;

/*
    Start with simple case where we use jsonPath to interrogate json string(s)
 */
public class CarfDetailProviderImpl implements CarfDetailProvider {
    String[] json;
//    String jsonB4;
//    String jsonAF;

    // One string. Probably a bad thing if we're asked for a pair of values ...
    public CarfDetailProviderImpl(String json){
        super();
        this.json = new String[]{json};
    }

    // One string. Probably a bad thing if we're asked for a pair of values ...
    public CarfDetailProviderImpl(String jsonB4, String jsonAF){
        super();
        this.json = new String[]{jsonB4,jsonAF};
//        this.jsonB4 = jsonB4;
//        this.jsonAF = jsonAF;
    }





    // Given json in a string and a path, get the value
    private String getCarfDetailValue(String jsonData, String dataPath)  {
        String val = (String) JsonPath.read(jsonData, dataPath);
        return val;
    }

    // Just pass back whatever we might find. Caller can worry about how to deal with it!!
    @Override
    public String getCarfDetailValue(String dataPath) {
        // TODO - if we were given no jSon do we throw an exception?
        String val=null;

        // PathNotFound is something we should be expecting to encounter (and therefore be able to deal with)
        try {
//            val=getCarfDetailValue(json, dataPath);
            val=getCarfDetailValue(json[0], dataPath);
        } catch (PathNotFoundException e) {
            // TODO - turn this in to an exception of ours and kick it upstairs!!
            e.printStackTrace();
        }

        return val;
    }


    @Override
    public String[] getCarfDetailValues(String dataPath) {
        String[] val=null;

        // Grab some values
        String b4 = getCarfDetailValue(json[0], dataPath);
        String af = getCarfDetailValue(json[1], dataPath);

        // For now, return as a pair, even if identical (or identically empty)
        val=new String[]{b4,af};

        return val;
    }
}
