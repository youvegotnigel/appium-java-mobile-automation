package org.nigel.automation.factories;

public enum DriverType {

    ANDROID("android"),
    IOS("ios"),
    WINDOWS("windows"),
    MAC("mac"),
    ANDROID_CHROMIUM("android_chromium"),
    IOS_CHROMIUM("ios_chromium"),
    SAFARI("safari"),
    ANDROID_FIREFOX("android_firefox"),
    IOS_FIREFOX("android_firefox");

    DriverType(String value){
        this.value = value;
    }

    private final String value;

    public String getValue(){
        return value;
    }
}
