package io.dbean;

import java.io.Serializable;

public abstract class DBean implements Serializable {

    public void initialize() {
    }

    public boolean validate() {
        return true;
    }

    public boolean isEmpty() {
        return true;
    }
}
