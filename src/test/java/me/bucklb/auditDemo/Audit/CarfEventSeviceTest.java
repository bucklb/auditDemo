package me.bucklb.auditDemo.Audit;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.bucklb.auditDemo.Domain.*;
import me.bucklb.auditDemo.service.*;
import org.json.JSONObject;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/*
    Test that details provider and manifest give us a CarfEvent
 */
public class CarfEventSeviceTest {

    // Return canned provider from pair of strings (or just one)
    private CarfDetailProvider getCDP(boolean pair) {

        CarfDetailProvider cdp = null;

        List<Quote> b4quotes = new ArrayList<>();
        b4quotes.add(new Quote("1","a"));
        String jsonB4 = AuditTestTools.jsonAuthor("random", b4quotes);

        List<Quote> afquotes = new ArrayList<>();
        afquotes.add(new Quote("2","b"));
        String jsonAF = AuditTestTools.jsonAuthorPlus("known", "sci-fi",afquotes);

        // Feed the two author$ to provider
        if (pair) {
            // Before & After
            cdp = new CarfDetailProviderImpl(jsonB4, jsonAF);
        } else {
            // Just one
            cdp = new CarfDetailProviderImpl(jsonAF);
        }

        return cdp;
    }




    // Compare jsons
    @Test
    public void testEventServicePair() throws Exception{

        CarfManifestProvider cmp=new CarfManifestProviderImpl();
        CarfManifest cm = cmp.getManifest("authorPlus");

        // Go for broke with a pair
        CarfEventService ces = new CarfEventServiceImpl(getCDP(true));

        CarfEvent ce = ces.getCarfEvent(cm);

        jSonPrettify(ce);

    }

    // Just pull bits out
    @Test
    public void testEventServiceSingle() throws Exception{

        CarfManifestProvider cmp=new CarfManifestProviderImpl();
        CarfManifest cm = cmp.getManifest("authorPlus");

        // Single entity with action
        CarfEventService ces = new CarfEventServiceImpl(getCDP(false), CarfAction.READ);

        CarfEvent ce = ces.getCarfEvent(cm);

        jSonPrettify(ce);

    }



    private static void jSonPrettify(Object o) {
        String s="";

        // Start with Jackson, but might move on to gSon?
        ObjectMapper objectMapper = new ObjectMapper();

        // Just want something for now. Turn "proper" object to string
        try {
            // object -> json
            s = objectMapper.writeValueAsString(o);

            // json - jsonObject and then pretty output
            JSONObject json = new JSONObject(s); // Convert text to object
            System.out.println(json.toString(4)); // Print it with specified indentation



        } catch (Exception e) {
            // Enough for now
            e.printStackTrace();
        }

    }
}
