package me.bucklb.auditDemo.Exception;

import com.fasterxml.jackson.annotation.JsonIgnore;

/*
    Perhaps need a project specific exception to extend, but this will do for now
 */
public class NoSuchPathException extends RuntimeException{

    @JsonIgnore
    private final Throwable thrown;
    private final String localizedMessage;

    @JsonIgnore
    public Throwable getThrown() {
        return thrown;
    }
    public String getLocalizedMessage() {
        return localizedMessage;
    }

    public NoSuchPathException(String localizedMessage, Throwable e) {
        super(e);
        this.thrown = e;
        this.localizedMessage = localizedMessage;
    }

    public NoSuchPathException(Throwable e) {
        super(e);
        this.thrown = e;
        this.localizedMessage = e.getLocalizedMessage();
    }

}
