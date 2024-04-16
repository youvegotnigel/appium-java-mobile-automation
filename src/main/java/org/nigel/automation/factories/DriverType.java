package org.nigel.automation.factories;

public enum DriverType {

    ANDROID("android"),
    IOS("ios"),
    WINDOWS("windows"),
    MAC("mac"),
    CHROMIUM("chromium"),
    SAFARI("safari"),
    FIREFOX("firefox");

    DriverType(String value){
        this.value = value;
    }

    private final String value;

    public String getValue(){
        return value;
    }
}
