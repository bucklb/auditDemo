package me.bucklb.auditDemo.Audit;

import me.bucklb.auditDemo.Domain.CarfAction;
import me.bucklb.auditDemo.Domain.ManifestItem;
import me.bucklb.auditDemo.service.CarfDetailService;
import org.junit.Test;

public class CarfDetailsServiceTest {

    @Test
    public void testService() {

        String json = AuditTestTools.jsonQuote("firstType","firstValue");
        String kson = AuditTestTools.jsonQuote("secondType","firstValue");

        CarfDetailService svc1,svc2,svc3;
        ManifestItem mi = new ManifestItem("x","$.type");


        svc1 = new CarfDetailService(json, CarfAction.CREATE);
        System.out.println(svc1.getCarfEventDetail(mi));





        svc2 = new CarfDetailService(json, kson);
        System.out.println(svc2.getCarfEventDetail(mi));


        // Identical jSon, so shouldn't get much back.
        svc3 = new CarfDetailService(kson, kson);
        System.out.println(svc3.getCarfEventDetail(mi));


    }


}
