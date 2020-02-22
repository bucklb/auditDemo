package me.bucklb.auditDemo.Audit;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.bucklb.auditDemo.Domain.Author;
import me.bucklb.auditDemo.Domain.Quote;

import java.util.List;

/*
    Centralise the stuff we need to prepare test data
 */
public class AuditTestTools {


    private static String jSonify(Object o) {
        String s="";

        // Start with Jackson, but might move on to gSon?
        ObjectMapper objectMapper = new ObjectMapper();

        // Just want something for now. Turn "proper" object to string
        try {
            s = objectMapper.writeValueAsString(o);
        } catch (Exception e) {
            // Enough for now
            e.printStackTrace();
        }

        return s;
    }



    // A basic json string of a query
    public static String jsonQuote(String type, String value) {

        String s="";
        Quote quote = new Quote(type,value);

        return jSonify(quote);

    }

    // A basic json string of an author and some quotes
    public static String jsonAuthor(String name, List<Quote> quotes) {
        String s="";

        Author hack = new Author(name, quotes);
        return jSonify(hack);
    }




}
