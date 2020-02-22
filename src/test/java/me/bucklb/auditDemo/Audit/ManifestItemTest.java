package me.bucklb.auditDemo.Audit;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.bucklb.auditDemo.Domain.CarfAction;
import me.bucklb.auditDemo.Domain.ManifestItem;
import me.bucklb.auditDemo.Domain.Quote;
import org.junit.Test;

public class ManifestItemTest {

//    // A basic json string
//    private String jsonQuote(String type, String value) {
//
//        String s="";
//
//        // Start with Jackson, but might move on to gSon?
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        Quote quote = new Quote(type,value);
//
//        // Just want something for now. Turn "proper" object to string
//        try {
//            s = objectMapper.writeValueAsString(quote);
//        } catch (Exception e) {
//            // Enough for now
//            e.printStackTrace();
//        }
//
//        return s;
//    }



    @Test
    public void manifestTest() {

        ManifestItem mi = null;

        // Want to see that it returns the one field we asked for populated
        mi=new ManifestItem("test","", CarfAction.CREATE);
        System.out.println(mi.asCarfEventDetail(null).toString());

        mi=new ManifestItem("test","", CarfAction.READ);
        System.out.println(mi.asCarfEventDetail(null).toString());

        mi=new ManifestItem("test","", CarfAction.DELETE);
        System.out.println(mi.asCarfEventDetail(null).toString());

//        // If we pass two strings, want them treated as B4 and AF
//        mi=new ManifestItem("test","", CarfAction.UPDATE);
//        System.out.println(mi.asCarfEventDetail(null, "").toString());

    }

    // Grab the data?
    @Test
    public void manifestTestToo() {
        String json = AuditTestTools.jsonQuote("firstType","firstValue");
        String kson = AuditTestTools.jsonQuote("secondType","firstValue");

        ManifestItem mi = null;
        mi = new ManifestItem("test","$.type",CarfAction.CREATE);
        System.out.println(mi.asCarfEventDetail(json).toString());

//        ManifestItem mj = null;
//        mi = new ManifestItem("test","$.type",CarfAction.UPDATE);
//        System.out.println(mi.asCarfEventDetail(json,kson).toString());



    }




}
