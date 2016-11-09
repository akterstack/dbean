package io.probean;

public abstract class Namespace {

    private String namespace;

    public Namespace(String namespace) {
        this.namespace = namespace;
    }



    public String value() {
        return namespace;
    }

}
