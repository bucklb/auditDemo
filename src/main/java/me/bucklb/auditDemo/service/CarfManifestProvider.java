package me.bucklb.auditDemo.service;

import me.bucklb.auditDemo.Domain.CarfManifest;
import me.bucklb.auditDemo.Domain.ManifestItem;

import java.util.ArrayList;
import java.util.List;

/*
    Will want to get details of what we need to extract from json.
    Could be they will be in database (mongo/postgres/h2 etc)
    or via local file
    or for the time being rather hard coded
 */
public interface CarfManifestProvider {


    default public CarfManifest getManifest(String manifestId){

        // TODO : want this to thrown an exception in real use

        return null;
    }

    // TODO - want something that will take action, system and "description" to locate items


}
