package com.zaichick.user.enums;

public enum UserType {

    PATIENT("patient"),
    NURSE("nurse"),
    DOCTOR("doctor");

    private String type;

    UserType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
