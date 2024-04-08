package org.nigel.automation.pageobjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

public class WDIOHomePage extends PageBase {

    public WDIOHomePage(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    @AndroidFindBy(accessibility = "Home")
    @iOSXCUITFindBy(accessibility = "Home")
    WebElement homeIcon;

    @AndroidFindBy(accessibility = "Webview")
    @iOSXCUITFindBy(accessibility = "Webview")
    WebElement webViewIcon;

    @AndroidFindBy(accessibility = "Login")
    @iOSXCUITFindBy(accessibility = "Login")
    WebElement loginIcon;

    @AndroidFindBy(accessibility = "Forms")
    @iOSXCUITFindBy(accessibility = "Forms")
    WebElement formsIcon;

    @AndroidFindBy(accessibility = "Swipe")
    @iOSXCUITFindBy(accessibility = "Swipe")
    WebElement swipeIcon;

    @AndroidFindBy(accessibility = "Drag")
    @iOSXCUITFindBy(accessibility = "Drag")
    WebElement dragIcon;


    public void clickHomeIcon() {
        click(homeIcon);
    }
    public void clickWebViewIcon() {
        click(webViewIcon);
    }
    public void clickLoginIcon() {
        click(loginIcon);
    }
    public void clickFormsIcon() {
        click(formsIcon);
    }
    public void clickSwipeIcon() {
        click(swipeIcon);
    }
    public void clickDragIcon() {
        click(dragIcon);
    }


}
