package me.bucklb.auditDemo.Audit;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.bucklb.auditDemo.Domain.Quote;

/*
    Centralise the stuff we need to prepare test data
 */
public class AuditTestTools {

    // A basic json string
    public static String jsonQuote(String type, String value) {

        String s="";

        // Start with Jackson, but might move on to gSon?
        ObjectMapper objectMapper = new ObjectMapper();
        Quote quote = new Quote(type,value);

        // Just want something for now. Turn "proper" object to string
        try {
            s = objectMapper.writeValueAsString(quote);
        } catch (Exception e) {
            // Enough for now
            e.printStackTrace();
        }

        return s;
    }
}
