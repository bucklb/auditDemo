package me.bucklb.auditDemo.Audit;

import me.bucklb.auditDemo.Domain.Quote;
import me.bucklb.auditDemo.service.CarfDetailProvider;
import me.bucklb.auditDemo.service.CarfDetailProviderImpl;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CarfDetailProviderTest {

    // If we have just one "object", how does that look?
    @Test
    public void testSingleProviderQuote() {

        // Want a string based provider
        String json = AuditTestTools.jsonQuote("firstType","firstValue");
        CarfDetailProvider cdp = new CarfDetailProviderImpl(json);

        System.out.println(cdp.getCarfDetailValue("$.type"));
        System.out.println(cdp.getCarfDetailValue("$.value"));

    }

    // If we have just one "object", how does that look?
    @Test
    public void testSingleProviderAuthor() {

        List<Quote> quotes = new ArrayList<>();
        quotes.add(new Quote("1","a"));

        // Want a string based provider
        String json = AuditTestTools.jsonAuthor("random", quotes);
        CarfDetailProvider cdp = new CarfDetailProviderImpl(json);

        System.out.println(cdp.getCarfDetailValue("$.name"));
        System.out.println(cdp.getCarfDetailValue("$.quotes[0].type"));
        System.out.println(cdp.getCarfDetailValue("$.quotes[1].type"));

    }

    @Test
    public void testDoubleProviderQuote() {

        // Want a string based provider
        String json = AuditTestTools.jsonQuote("firstType","firstValue");
        String kson = AuditTestTools.jsonQuote("nextType","nextValue");

        // Give it two strings
        CarfDetailProvider cdp = new CarfDetailProviderImpl(json,kson);
        String[] vals=cdp.getCarfDetailValues("$.type");
        // Allow us to see if implemented yet
        if(vals!=null && vals.length>0) {
            if(vals.length>1){
                System.out.println(vals[0] + " " + vals[1]);
            } else {
                System.out.println(vals[0]);
            }
        } else {
            System.out.println("nada!");
        }

    }

    @Test
    public void testDoubleProviderAuthors() {

        List<Quote> b4quotes = new ArrayList<>();
        b4quotes.add(new Quote("1","a"));
        String jsonB4 = AuditTestTools.jsonAuthor("random", b4quotes);

        List<Quote> afquotes = new ArrayList<>();
        afquotes.add(new Quote("2","b"));
        String jsonAF = AuditTestTools.jsonAuthor("known", afquotes);

        // Feed the two author$ to provider
        CarfDetailProvider cdp = new CarfDetailProviderImpl(jsonB4, jsonAF);

        String[] vals=cdp.getCarfDetailValues("$.quotes[0].value");
        // Allow us to see if implemented yet
        if(vals!=null && vals.length>0) {
            if(vals.length>1){
                System.out.println(vals[0] + " " + vals[1]);
            } else {
                System.out.println(vals[0]);
            }
        } else {
            System.out.println("nada!");
        }

    }

}
