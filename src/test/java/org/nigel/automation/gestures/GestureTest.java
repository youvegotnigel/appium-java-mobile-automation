package org.nigel.automation.gestures;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.remote.AutomationName;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Collections;

public class GestureTest {

    private static final String IOS_SAUCE_LABS_DEMO_APP_PATH = System.getProperty("user.dir") + File.separator + "apps" + File.separator + "SauceLabs-Demo-App.Simulator.zip";
    private static final String APPIUM_URL = "http://127.0.0.1:4723";

    @Test
    public void test_for_tab() throws MalformedURLException {

        XCUITestOptions options = new XCUITestOptions();
        options.setPlatformName("iOS"); //optional
        options.setAutomationName(AutomationName.IOS_XCUI_TEST); //optional
        options.setDeviceName("iPhone 13");
        options.setPlatformVersion("15.0");
        options.setApp(IOS_SAUCE_LABS_DEMO_APP_PATH);

        IOSDriver driver = new IOSDriver(new URL(APPIUM_URL), options);

        driver.findElement(AppiumBy.accessibilityId("Cart-tab-item")).click();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        WebElement goShoppingButton = driver.findElement(AppiumBy.accessibilityId("Go Shopping"));
        Assert.assertTrue(goShoppingButton.isDisplayed());

        // tap on go shopping button
        tap(driver, goShoppingButton);

        String productText = driver.findElement(AppiumBy.accessibilityId("Products")).getText();
        Assert.assertEquals(productText, "Products");
        driver.quit();
    }

    @Test
    public void test_for_doubleTab() throws MalformedURLException {

        XCUITestOptions options = new XCUITestOptions();
        options.setDeviceName("iPhone 13");
        options.setPlatformVersion("15.0");
        options.setApp(IOS_SAUCE_LABS_DEMO_APP_PATH);

        IOSDriver driver = new IOSDriver(new URL(APPIUM_URL), options);

        driver.findElement(AppiumBy.accessibilityId("Sauce Labs Bike Light")).click();

        WebElement plusIcon = driver.findElement(AppiumBy.accessibilityId("AddPlus Icons"));
        Assert.assertTrue(plusIcon.isDisplayed());

        // tap on go shopping button
        doubleTap(driver, plusIcon);

        String amountText = driver.findElement(AppiumBy.accessibilityId("Amount")).getText();
        Assert.assertEquals(amountText, "3");
        driver.quit();
    }



    private void tap(AppiumDriver driver, WebElement element) {

        Point location = element.getLocation();
        Dimension size = element.getSize();

        Point centerOfElement = getCenterOfElement(location, size);

        PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence sequence = new Sequence(finger1, 1)
                .addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerOfElement)) // move finger to button from viewport
                .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg())) // move finger down and click (similar to mouse left click)
                .addAction(new Pause(finger1, Duration.ofMillis(200))) // click and hold for a small duration
                .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg())); // move finger away from button

        driver.perform(Collections.singletonList(sequence));
    }

    private void doubleTap(AppiumDriver driver, WebElement element) {

        Point location = element.getLocation();
        Dimension size = element.getSize();

        Point centerOfElement = getCenterOfElement(location, size);

        PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence sequence = new Sequence(finger1, 1)
                .addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerOfElement)) // move finger to button from viewport
                .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg())) // move finger down and click (similar to mouse left click)
                .addAction(new Pause(finger1, Duration.ofMillis(100))) // click and hold for a small duration
                .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg())) // move finger away from button
                .addAction(new Pause(finger1, Duration.ofMillis(100))) // click and hold for a small duration
                .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg())) // move finger down and click (similar to mouse left click)
                .addAction(new Pause(finger1, Duration.ofMillis(100))) // click and hold for a small duration
                .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg())); // move finger away from button

        driver.perform(Collections.singletonList(sequence));
    }

    private Point getCenterOfElement(Point location, Dimension size) {
        return new Point(location.getX() + size.getWidth() / 2,
                location.getY() + size.getHeight() / 2);
    }

}
