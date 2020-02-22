package me.bucklb.auditDemo.Domain;

import java.util.List;

/*
    Want something to hang quotes off, so why not an author.  Add a field to this version not in main author
 */
public class AuthorPlus {

    private String name;
    private String genre;
    private List<Quote> quotes;

    public AuthorPlus(String name, String genre, List<Quote> quotes) {
        this.name = name;
        this.genre = genre;
        this.quotes = quotes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String name) {
        this.genre = genre;
    }

    public List<Quote> getQuotes() {
        return quotes;
    }

    public void setQuotes(List<Quote> quotes) {
        this.quotes = quotes;
    }
}
