package me.bucklb.auditDemo.service;

/*
    Will be using jsonPath and strings to get the data initially, but may want to amend that at some point, so aim for flexibility
 */
public interface CarfDetailProvider {

    // Most of the time we have one object to query.
    // Would help to know if the dataPath made any kind of sense, or was toilet
    default public String getCarfDetailValue(String dataPath){
        return "getCarfDetailValue NOT implemented!!";
    }

    // Will also want a before & after pair ...
    default public String[] getCarfDetailValues(String dataPath){
        return new String[]{"getCarfDetailValues NOT implemented!!"};
    }

}
