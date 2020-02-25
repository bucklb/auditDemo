package me.bucklb.auditDemo.Audit;

import me.bucklb.auditDemo.Domain.CarfAction;
import me.bucklb.auditDemo.Domain.CarfManifest;
import me.bucklb.auditDemo.Domain.ManifestItem;
import me.bucklb.auditDemo.Domain.Quote;
import me.bucklb.auditDemo.service.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/*
    If we're mixing Providers and services then not really a unit test
    For the time being it's about eyeballing NOT asserting, but that will come
 */
public class IntegrationTest {


    @Test
    public void doQuoteTest() {

        String json = AuditTestTools.jsonQuote("firstType","firstValue");
        String kson = AuditTestTools.jsonQuote("secondType","secondValue");
        CarfDetailProvider cdp = new CarfDetailProviderImpl(json,kson);
        CarfDetailService cds = new CarfDetailServiceImpl(cdp);

        CarfManifestProvider cmp = new CarfManifestProviderImpl();
        CarfManifest cm = cmp.getManifest("quote");

        List<ManifestItem> manifestItems = cm.getManifestItems();
        for( ManifestItem mi: manifestItems){
            System.out.println("UPDT " + cm.getManifestId() + " > " + cds.getCarfEventDetail(mi));
        }
    }

    @Test
    public void doAuthorPlusTest() {
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

        CarfManifestProvider cmp = new CarfManifestProviderImpl();
        CarfManifest cm = cmp.getManifest("authorPlus");

        List<ManifestItem> manifestItems = cm.getManifestItems();
        for( ManifestItem mi: manifestItems){
            System.out.println("UPDT " + cm.getManifestId() + " > " + cds.getCarfEventDetail(mi));
        }
    }

    @Test
    public void doAddedAuthorPlusTest() {
        List<Quote> afquotes = new ArrayList<>();
        afquotes.add(new Quote("2","b"));
        String json = AuditTestTools.jsonAuthorPlus("known", "sci-fi",afquotes);

        // Feed the author to provider
        CarfDetailProvider cdp = new CarfDetailProviderImpl(json);
        CarfDetailService cds = new CarfDetailServiceImpl(cdp, CarfAction.READ);

        CarfManifestProvider cmp = new CarfManifestProviderImpl();
        CarfManifest cm = cmp.getManifest("authorPlus");

        List<ManifestItem> manifestItems = cm.getManifestItems();
        for( ManifestItem mi: manifestItems){
            System.out.println("READ " + cm.getManifestId() + " > " + cds.getCarfEventDetail(mi));
        }

    }


}
