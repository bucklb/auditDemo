package me.bucklb.auditDemo.claim;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.json.JSONObject;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class readClaimTest {

    // If we have just one "object", how does that look?
    @Test
    public void testReadJsonFile() {

        String fileName = "data/full-claim-data.json";
        String sub = getCitizenSubmissionFromFile(fileName);
    }


    // Will help to see what a realistic test claim gets turned in to
    private String getCitizenSubmissionFromFile(String fileName){


        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        ObjectMapper om = new ObjectMapper();

        Gson gson = new Gson();

        File file = new File(classLoader.getResource(fileName).getFile());
        try {

            System.out.println(file.toPath());
            BufferedReader br = new BufferedReader(new FileReader(file.getPath()));

            String s = "";
            String j = "";
            while((s=br.readLine())!=null) j = j +s ;
            System.out.println(j);
            System.out.println();
        } catch (Exception e) {
            // Perhaps ought to handle all the different exceptions, but this is test
            e.printStackTrace();
        }

        try {
            String pth = file.getPath();
            System.out.println(file.toPath());
            String j = new String( Files.readAllBytes(Paths.get( pth)) );
            System.out.println(j);
            System.out.println();
        } catch (Exception e) {
            // Perhaps ought to handle all the different exceptions, but this is test
            e.printStackTrace();
        }






        return "test";


    }





}
