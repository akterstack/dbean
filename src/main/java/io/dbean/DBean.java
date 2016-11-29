package io.dbean;

import java.io.Serializable;

public abstract class DBean<T> implements Serializable {

    public boolean validate() {

        return true;
    }

    public boolean isEmpty() {
        return true;
    }
}
