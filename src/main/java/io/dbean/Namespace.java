package io.dbean;

public abstract class Namespace {

    private String namespace;

    public Namespace(String namespace) {
        this.namespace = namespace;
    }

    @Override
    public String toString() {
        return namespace;
    }

}
