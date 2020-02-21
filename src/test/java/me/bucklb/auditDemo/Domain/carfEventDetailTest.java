package me.bucklb.auditDemo.Domain;

import org.junit.Test;

public class carfEventDetailTest {

    @Test
    public void testBuilder() {

        CarfEventDetail ced = new CarfEventDetail.CarfEventDetailBuilder("test").build();
        System.out.println(ced.toString());

        ced = new CarfEventDetail.CarfEventDetailBuilder("next").setRf("RF value").build();
        System.out.println(ced.toString());

        ced = new CarfEventDetail.CarfEventDetailBuilder("again").setAf("AF value").setB4("In the beginning").build();
        System.out.println(ced.toString());

    }



}
