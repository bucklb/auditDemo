package me.bucklb.auditDemo.Domain;

import com.jayway.jsonpath.JsonPath;

/*
    ManifestItem is meant to be a way to get a CarfEventDetail from the given data (could be one or two items) probably in Json format

    Probably want this to be an interface and implementation at some point.  Control what it reads from AND what it outputs as ??

    Look at getting an "output builder" that can be passed in ??
 */
public class ManifestItem {
    String carfName;    // What the output gets called
    String dataPath;    // Where to find the data
    String dataFormat;  // Allow format (for dates specifically)
    boolean mandatory;

    CarfAction action;

    /*
        Must be crying out to have something passed in for this to query.  Having JsonPath wired in is not sustainable!!!!
        Take a string that contains jSon and a path amd return any value found
     */
    private String jsonValue(String json, String dataPath) {

        // Grab the data
        String val = (String)JsonPath.read(json,dataPath);

        return val;
    }


    // Needn't be a format or mandatory.
    public ManifestItem(String carfName, String dataPath, CarfAction action) {
        this.carfName = carfName;
        this.dataPath = dataPath;
        this.action = action;
    }

    // Main event.  Given a source and how the stuff is to be classified, pass back in eventDetail form
    public CarfEventDetail asCarfEventDetail(String json) {
        String val="";
        CarfEventDetail ced=null;

        // Might want this to be an exception in future
        if(json == null || json.isEmpty()){
            // Put dummy in the requested action field
            val="Dummy";
        } else {
            // Reality time
            val = jsonValue(json,dataPath);
        }

        ced = new CarfEventDetail.CarfEventDetailBuilder(this.carfName).setValue(val,action).build();
        return ced;
    }

    // Main event.  Given two sources, so pass back the Before and After where they differ
    public CarfEventDetail asCarfEventDetail(String jsonB4, String jsonAF) {
        String b4="";
        String af="";
        CarfEventDetail ced=null;

        // Might want this to be an exception in future
        if(jsonB4 == null || jsonB4.isEmpty()){
            b4="DummyB4";
            af="DummyAF";
        } else {
            // Reality time
            b4 = jsonValue(jsonB4, dataPath);
            af = jsonValue(jsonAF, dataPath);
        }

        // Put dummy in the requested action field
        ced = new CarfEventDetail.CarfEventDetailBuilder(this.carfName)
                .setB4(b4).setAF(af).build();

        return ced;
    }






    public String getCarfName() {
        return carfName;
    }

    public void setCarfName(String carfName) {
        this.carfName = carfName;
    }

    public String getDataPath() {
        return dataPath;
    }

    public void setDataPath(String dataPath) {
        this.dataPath = dataPath;
    }

    public String getDataFormat() {
        return dataFormat;
    }

    public void setDataFormat(String dataFormat) {
        this.dataFormat = dataFormat;
    }

    public boolean isMandatory() {
        return mandatory;
    }

    public void setMandatory(boolean mandatory) {
        this.mandatory = mandatory;
    }

    @Override
    public String toString() {
        return "ManifestItem{" +
                "carfName='" + carfName + '\'' +
                ", dataPath='" + dataPath + '\'' +
                ", dataFormat='" + dataFormat + '\'' +
                ", mandatory=" + mandatory +
                '}';
    }
}
