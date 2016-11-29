package io.dbean.test;

import io.dbean.validator.XBasicPropertyValidator;

public class ValidationRules implements XBasicPropertyValidator {
    public boolean minLen(String instanceVal, int defaultVal) {
        return instanceVal.length() >= defaultVal;
    }

    public boolean maxLen(String instanceVal, int defaultVal) {
        return instanceVal.length() <= defaultVal;
    }
}
