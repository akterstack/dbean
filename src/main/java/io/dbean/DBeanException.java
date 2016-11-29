package io.dbean;

public class DBeanException extends Exception {

    Throwable throwable;

    public DBeanException(Throwable throwable) {
        this.throwable = throwable;
    }

}
