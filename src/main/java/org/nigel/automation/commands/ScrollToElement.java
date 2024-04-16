package org.nigel.automation.commands;

import io.appium.java_client.AppiumDriver;

import java.util.NoSuchElementException;

public class ScrollToElement {


    private boolean isElementNotDisplayed(WebElementSource locator) {
        try {
            if (AppiumDriver.isAndroidDriver()) {
                return !locator.getWebElement().isDisplayed();
            } else {
                return !locator.getWebElement().getAttribute("visible").equals("true");
            }
        } catch (NoSuchElementException noSuchElementException) {
            return true;
        }
    }
}
