package me.bucklb.auditDemo.Domain;

import java.util.List;

/*
    Want something to hang quotes off, so why not an author
 */
public class Author {

    private String name;
    private List<Quote> quotes;

    public Author(String name, List<Quote> quotes) {
        this.name = name;
        this.quotes = quotes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Quote> getQuotes() {
        return quotes;
    }

    public void setQuotes(List<Quote> quotes) {
        this.quotes = quotes;
    }
}
