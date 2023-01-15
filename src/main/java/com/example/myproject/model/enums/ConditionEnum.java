package com.example.myproject.model.enums;

public enum ConditionEnum {

    USED("Употребяван"),
    NEW("Нов"),
    FOR_PARTS("За части");
    private final String displayValue;

    private ConditionEnum(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
