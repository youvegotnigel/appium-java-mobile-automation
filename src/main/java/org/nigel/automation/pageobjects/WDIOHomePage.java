package org.nigel.automation.pageobjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

public class WDIOHomePage extends PageBase {

    public WDIOHomePage(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    @AndroidFindBy(id = "fab")
    @iOSXCUITFindBy(accessibility = "Home")
    WebElement homeIcon;

    @AndroidFindBy(id = "fab")
    @iOSXCUITFindBy(accessibility = "Webview")
    WebElement webViewIcon;

    @AndroidFindBy(id = "fab")
    @iOSXCUITFindBy(accessibility = "Login")
    WebElement loginIcon;

    @AndroidFindBy(id = "fab")
    @iOSXCUITFindBy(accessibility = "Forms")
    WebElement formsIcon;

    @AndroidFindBy(id = "fab")
    @iOSXCUITFindBy(accessibility = "Swipe")
    WebElement swipeIcon;

    @AndroidFindBy(id = "fab")
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
