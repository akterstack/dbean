package io.probean;

import io.hackable.Hackable;

public abstract class Probean<T> implements Hackable {

    public <P> P get(Namespace namespace) {
        return this.get(namespace);
    }

}
