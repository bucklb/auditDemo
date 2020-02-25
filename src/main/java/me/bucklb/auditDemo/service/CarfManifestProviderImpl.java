package me.bucklb.auditDemo.service;

import me.bucklb.auditDemo.Domain.CarfManifest;
import me.bucklb.auditDemo.Domain.ManifestItem;

import java.util.ArrayList;
import java.util.List;

public class CarfManifestProviderImpl implements CarfManifestProvider {

    // Keep it simple for now.  What do we need it to do?
//    public CarfManifestProviderImpl() {
//    }

    @Override
    public CarfManifest getManifest(String manifestId) {

        // Hardwire the manifestItems for now
        List<ManifestItem> manifestItems = new ArrayList<>();
        CarfManifest cm = new CarfManifest(manifestId, manifestItems);

        switch(manifestId) {
            case "quote":
                manifestItems.add(new ManifestItem("quoteTyp","$.type"));
                manifestItems.add(new ManifestItem("quoteVal","$.value"));
                break;
            case "author":
                manifestItems.add(new ManifestItem("authorNm","$.name"));
                manifestItems.add(new ManifestItem("quoteTyp","$.quotes[0].type"));
                manifestItems.add(new ManifestItem("quoteVal","$.quotes[0].value"));
                break;
            case "authorPlus":
                manifestItems.add(new ManifestItem("authorNm","$.name"));
                manifestItems.add(new ManifestItem("authorGn","$.genre"));
                manifestItems.add(new ManifestItem("quoteTyp","$.quotes[0].type"));
                manifestItems.add(new ManifestItem("quoteVal","$.quotes[0].value"));
                break;
            default:
        }

        // And return
        return cm;
    }
}
