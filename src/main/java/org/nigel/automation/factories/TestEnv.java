package org.nigel.automation.factories;

public enum TestEnv {

    LOCAL("local"),
    DEV("dev"),
    PROD("prod");

    TestEnv(String value){
        this.value = value;
    }

    private String value;

    public String getValue(){
        return value;
    }

}
