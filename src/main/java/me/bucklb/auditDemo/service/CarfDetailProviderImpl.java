package me.bucklb.auditDemo.service;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;
import me.bucklb.auditDemo.Domain.CarfAction;
import me.bucklb.auditDemo.Exception.NoSuchPathException;

/*
    Start with simple case where we use jsonPath to interrogate json string(s)
 */
public class CarfDetailProviderImpl implements CarfDetailProvider {
    String[] json;

    // One string. Probably a bad thing if we're asked for a pair of values ...
    public CarfDetailProviderImpl(String json){
        super();
        this.json = new String[]{json};
    }

    // One string. Probably a bad thing if we're asked for a pair of values ...
    public CarfDetailProviderImpl(String jsonB4, String jsonAF){
        super();
        this.json = new String[]{jsonB4, jsonAF};
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

        // If the parser throws an exception, caller gets to worry about it
        try {
            val=getCarfDetailValue(json[0], dataPath);
        } catch (PathNotFoundException e) {
            // Wrap it in non jsonPath specific exception
            throw new NoSuchPathException(e);
        }

        return val;
    }


    @Override
    public String[] getCarfDetailValues(String dataPath) {
        String[] val=null;
        String b4 = null;
        String af = null;
        boolean b4Failed = false;

        // Grab some values.  Only raise/allow a not-found exception if not in EITHER setup
        try {
            b4 = getCarfDetailValue(json[0], dataPath);
        } catch (PathNotFoundException e) {
            // record the fact it happened.  Only really a problem if it happens with both (and even then ....)
            b4Failed = true;
        }

        try {
            af = getCarfDetailValue(json[1], dataPath);
        } catch (PathNotFoundException e) {
            if (b4Failed) {
                // Not in either, so probably ought to let caller choose what to do.
                // Wrap in our exception, so caller need not know it cam from jSonPath
                throw new NoSuchPathException(e);
            }
        }

        // For now, return as a pair, even if identical (or identically empty).  Null values are an option
        val=new String[]{b4,af};

        return val;
    }
}
