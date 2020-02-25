package me.bucklb.auditDemo.Domain;

import java.util.List;

/*
    TODO - want body information, but as single header (integration stylee) or bunch oof headers (carf stylee)
    For now the focus is on the manifestItems

    Might want


 */
public class CarfManifest
{
    private List<ManifestItem> manifestItems;
    private String manifestId;      // Might be the same as the eventNumber, but that's driven by ATAS
    private String eventNumber;     // ATAS need to know what we're sending them

    // TODOD - originator/timestamp/etc either as primitives or as a set of header components

    // Assume we can have a manifest that can be used by many systems (like change of address)
    // each system will use its own eventNumber, so maybe that needs to be set later
    public CarfManifest(String manifestId, List<ManifestItem> manifestItems) {
        this.manifestId = manifestId;
        this.manifestItems = manifestItems;
    }

    public List<ManifestItem> getManifestItems() {
        return manifestItems;
    }

    public void setManifestItems(List<ManifestItem> manifestItems) {
        this.manifestItems = manifestItems;
    }

    public String getManifestId() {
        return manifestId;
    }

    public void setManifestId(String manifestId) {
        this.manifestId = manifestId;
    }

    public String getEventNumber() {
        return eventNumber;
    }

    public void setEventNumber(String eventNumber) {
        this.eventNumber = eventNumber;
    }


    // May want a builder??  There could be quite a few bits
    // Might even want to allow the building of the many possible headers
}
