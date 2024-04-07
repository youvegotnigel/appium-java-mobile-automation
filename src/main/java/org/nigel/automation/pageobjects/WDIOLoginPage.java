package org.nigel.automation.pageobjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;

public class WDIOLoginPage extends PageBase {

    public WDIOLoginPage(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    @AndroidFindBy(id = "fab")
    @iOSXCUITFindBy(accessibility = "input-email")
    WebElement inputEmail;

    @AndroidFindBy(id = "fab")
    @iOSXCUITFindBy(accessibility = "input-password")
    WebElement inputPassword;

    @AndroidFindBy(id = "fab")
    @iOSXCUITFindBy(accessibility = "button-LOGIN")
    WebElement loginBtn;

    @AndroidFindBy(id = "fab")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeAlert[@name=\"Success\"]")
    WebElement successMessagePopup;

    @AndroidFindBy(id = "fab")
    @iOSXCUITFindBy(accessibility = "OK")
    WebElement okBtn;


    public void enterEmail(String email) {
        sendText(inputEmail, email);
    }

    public void enterPassword(String password) {
        sendText(inputPassword, password);
    }

    public void clickLoginBtn() {
        click(loginBtn);
    }

    public boolean isSuccessMessagePopupDispalyed() {
        return isDisplayed(successMessagePopup);
    }

    public void clickOkBtn() {
        click(okBtn);
    }

}
