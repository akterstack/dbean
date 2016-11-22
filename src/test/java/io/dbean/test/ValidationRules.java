package io.dbean.test;

import io.dbean.validator.BasicPropertyValidationRules;

public class ValidationRules implements BasicPropertyValidationRules{
    public boolean minLen(String instanceVal, int defaultVal) {
        return instanceVal.length() >= defaultVal;
    }

    public boolean maxLen(String instanceVal, int defaultVal) {
        return instanceVal.length() <= defaultVal;
    }
}
