package me.bucklb.auditDemo.Domain;

import org.junit.Test;

public class carfEventDetailTest {

    @Test
    public void testBuilder() {

        CarfEventDetail ced = new CarfEventDetail.CarfEventDetailBuilder("test").build();
        System.out.println(ced.toString());

        ced = new CarfEventDetail.CarfEventDetailBuilder("next").setRF("RF value").build();
        System.out.println(ced.toString());

        ced = new CarfEventDetail.CarfEventDetailBuilder("again").setAF("AF value").setB4("In the beginning").build();
        System.out.println(ced.toString());

        ced = new CarfEventDetail.CarfEventDetailBuilder("by val CREATE").setValue("AF value",CarfAction.CREATE).build();
        System.out.println(ced.toString());

        ced = new CarfEventDetail.CarfEventDetailBuilder("by val AF").setValue("AF value",CarfAction.AF).build();
        System.out.println(ced.toString());

    }



}
