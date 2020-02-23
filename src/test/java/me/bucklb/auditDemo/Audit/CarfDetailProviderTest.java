package me.bucklb.auditDemo.Audit;

import me.bucklb.auditDemo.Domain.Quote;
import me.bucklb.auditDemo.Exception.NoSuchPathException;
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

        System.out.println("Expect quote type & value");
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

        System.out.println("Expect author name and first quote's type");
        System.out.println(cdp.getCarfDetailValue("$.name"));
        System.out.println(cdp.getCarfDetailValue("$.quotes[0].type"));

        // If we ask for a non-existent field then expect a no
        System.out.println("Request for non-existent path should get an exception");
        try {
            System.out.println(cdp.getCarfDetailValue("$.genre"));
        } catch (NoSuchPathException e) {
            System.out.println("NoSuchPathException, msg = " + e.getLocalizedMessage());
        }

    }

    @Test
    public void testDoubleProviderQuote() {

        // Want a string based provider
        String json = AuditTestTools.jsonQuote("firstType","firstValue");
        String kson = AuditTestTools.jsonQuote("nextType","nextValue");

        // Give it two strings
        CarfDetailProvider cdp = new CarfDetailProviderImpl(json,kson);

        System.out.println("Expect pair of NON null values for type(s)");
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

        System.out.println("Expect NON-null values for authors' first quote");
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

    @Test
    public void testDoubleProviderAuthorMix() {

        List<Quote> b4quotes = new ArrayList<>();
        b4quotes.add(new Quote("1","a"));
        String jsonB4 = AuditTestTools.jsonAuthor("random", b4quotes);

        List<Quote> afquotes = new ArrayList<>();
        afquotes.add(new Quote("2","b"));
        String jsonAF = AuditTestTools.jsonAuthorPlus("known", "romantic fiction", afquotes);

        // Feed the two author$ to provider
        CarfDetailProvider cdp = new CarfDetailProviderImpl(jsonB4, jsonAF);

        String[] vals=null;

        // Only one of the strings will contain a "genre" entry
        vals = cdp.getCarfDetailValues("$.genre");
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

        // Neither string contains this, so ought to be advised of that
        System.out.println("A request for path that exists in NEITHER should get an exception");
        try {
            vals = cdp.getCarfDetailValues("$.nonSuch");
        } catch (NoSuchPathException e) {
            System.out.println("NoSuchPathException, msg = " + e.getLocalizedMessage());
        }



    }

}
