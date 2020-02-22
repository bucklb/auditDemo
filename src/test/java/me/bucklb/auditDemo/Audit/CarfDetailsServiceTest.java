package me.bucklb.auditDemo.Audit;

import me.bucklb.auditDemo.Domain.CarfAction;
import me.bucklb.auditDemo.Domain.CarfEventDetail;
import me.bucklb.auditDemo.Domain.ManifestItem;
import me.bucklb.auditDemo.Domain.Quote;
import me.bucklb.auditDemo.service.CarfDetailProvider;
import me.bucklb.auditDemo.service.CarfDetailProviderImpl;
import me.bucklb.auditDemo.service.CarfDetailService;
import me.bucklb.auditDemo.service.CarfDetailServiceImpl;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CarfDetailsServiceTest {

    // For the time being this is more of an integration test than unit tests

    // Give the service a provider equipped with b4 AND af
    @Test
    public void testServiceQuotes() {

        String json = AuditTestTools.jsonQuote("firstType","firstValue");
        String kson = AuditTestTools.jsonQuote("secondType","secondValue");

        // Create provider
        CarfDetailProvider cdp = new CarfDetailProviderImpl(json,kson);
        CarfDetailService cds = new CarfDetailServiceImpl(cdp);
        ManifestItem mi = new ManifestItem("x","$.type");

        CarfEventDetail ced = cds.getCarfEventDetail(mi);
        System.out.println(ced.toString());

    }

    // Give the service a provider equipped with b4 AND af
    @Test
    public void testServiceQuote() {

        String json = AuditTestTools.jsonQuote("firstType","firstValue");

        // Create provider and use (with action) to create the service
        CarfDetailProvider cdp = new CarfDetailProviderImpl(json);
        CarfDetailService cds = new CarfDetailServiceImpl(cdp, CarfAction.READ);

        ManifestItem mi = new ManifestItem("y","$.type");
        CarfEventDetail ced = cds.getCarfEventDetail(mi);
        System.out.println(ced.toString());

        // genre is not mandatory, so a null response is expected
        mi= new ManifestItem("y","$.genre");
        ced = cds.getCarfEventDetail(mi);
        System.out.println(ced.toString());

        // make genre is not mandatory, so an exception
        mi.setMandatory(true);
        ced = cds.getCarfEventDetail(mi);
        System.out.println(ced.toString());

    }

    // Give the service a provider equipped with b4 AND af
    @Test
    public void testServiceAuthors() {

        List<Quote> b4quotes = new ArrayList<>();
        b4quotes.add(new Quote("1","a"));
        String jsonB4 = AuditTestTools.jsonAuthor("random", b4quotes);

        List<Quote> afquotes = new ArrayList<>();
        afquotes.add(new Quote("2","b"));
        String jsonAF = AuditTestTools.jsonAuthor("known", afquotes);

        // Feed the two author$ to provider
        CarfDetailProvider cdp = new CarfDetailProviderImpl(jsonB4, jsonAF);

        CarfDetailService cds = new CarfDetailServiceImpl(cdp);
        ManifestItem mi = new ManifestItem("x","$.quotes[0].typed");

        CarfEventDetail ced = cds.getCarfEventDetail(mi);
        System.out.println(ced.toString());

    }

    // Give the service a provider equipped with b4 AND af
    @Test
    public void testServiceAuthorsPlus() {

        List<Quote> b4quotes = new ArrayList<>();
        b4quotes.add(new Quote("1","a"));
        String jsonB4 = AuditTestTools.jsonAuthor("random", b4quotes);

        List<Quote> afquotes = new ArrayList<>();
        afquotes.add(new Quote("2","b"));
        String jsonAF = AuditTestTools.jsonAuthorPlus("known", "sci-fi",afquotes);

        // Feed the two author$ to provider
        CarfDetailProvider cdp = new CarfDetailProviderImpl(jsonB4, jsonAF);

        // Should not matter if field is only in one place
        CarfDetailService cds = new CarfDetailServiceImpl(cdp);
        ManifestItem mi = new ManifestItem("x","$.genre");

        CarfEventDetail ced = cds.getCarfEventDetail(mi);
        System.out.println(ced.toString());

        // Non-existent field needn't be a problem
        mi = new ManifestItem("Pseudonym","$.NomDePlume");
        ced = cds.getCarfEventDetail(mi);
        System.out.println(ced.toString());

        // Non-existent field needn't be a problem. unless we want it to be
        mi.setMandatory(true);
        ced = cds.getCarfEventDetail(mi);
        System.out.println(ced.toString());



    }

}
