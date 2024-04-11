package org.nigel.automation.gestures;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.remote.AutomationName;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;

public class GestureTest {

    private static final String ANDROID_API_DEMO_APP_PATH = System.getProperty("user.dir") + File.separator + "apps" + File.separator + "ApiDemos.apk";
    private static final String ANDROID_SAUCE_LABS_DEMO_APP_PATH = System.getProperty("user.dir") + File.separator + "apps" + File.separator + "SauceLabs-Demo-App.apk";
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

    @Test
    public void test_for_longPress() throws MalformedURLException {

        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("nigel-test-device");
        options.setApp(ANDROID_API_DEMO_APP_PATH);

        AndroidDriver driver = new AndroidDriver(new URL(APPIUM_URL), options);

        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        driver.findElement(AppiumBy.accessibilityId("Expandable Lists")).click();
        driver.findElement(AppiumBy.accessibilityId("1. Custom Adapter")).click();

        WebElement peopleNames = driver.findElement(By.xpath("//android.widget.TextView[@text=\"People Names\"]"));
        longPress(driver, peopleNames);

        WebElement sampleActions = driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"android:id/title\" and @text=\"Sample action\"]"));
        Assert.assertTrue(sampleActions.isDisplayed());

        driver.quit();
    }

    @Test
    public void test_for_longPress_using_actions_class() throws MalformedURLException {

        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("nigel-test-device");
        options.setApp(ANDROID_API_DEMO_APP_PATH);

        AndroidDriver driver = new AndroidDriver(new URL(APPIUM_URL), options);

        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        driver.findElement(AppiumBy.accessibilityId("Expandable Lists")).click();
        driver.findElement(AppiumBy.accessibilityId("1. Custom Adapter")).click();

        WebElement peopleNames = driver.findElement(By.xpath("//android.widget.TextView[@text=\"People Names\"]"));
        Actions action = new Actions(driver);
        action.clickAndHold(peopleNames).perform();

        longPress(driver, peopleNames);

        WebElement sampleActions = driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"android:id/title\" and @text=\"Sample action\"]"));
        Assert.assertTrue(sampleActions.isDisplayed());

        driver.quit();
    }

    @Test
    public void test_for_zoom() throws MalformedURLException {

        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("nigel-test-device");
        options.setAppPackage("com.saucelabs.mydemoapp.android");
        options.setAppActivity("com.saucelabs.mydemoapp.android.view.activities.MainActivity");
        options.autoGrantPermissions(); // give all permissions to access files, images etc.
        options.setApp(ANDROID_SAUCE_LABS_DEMO_APP_PATH);

        AndroidDriver driver = new AndroidDriver(new URL(APPIUM_URL), options);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        driver.findElement(AppiumBy.accessibilityId("View menu")).click();
        driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.saucelabs.mydemoapp.android:id/itemTV\" and @text=\"Drawing\"]")).click();

        String title = driver.findElement(By.id("com.saucelabs.mydemoapp.android:id/drawingTV")).getText().trim();
        Assert.assertEquals(title, "Drawing");

        WebElement drawingPad = driver.findElement(AppiumBy.accessibilityId("Pad to draw on"));

        zoom(driver, drawingPad);

        driver.quit();
    }

    @Test
    public void test_for_scroll() throws MalformedURLException {

        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("nigel-test-device");
        options.setApp(ANDROID_API_DEMO_APP_PATH);

        AndroidDriver driver = new AndroidDriver(new URL(APPIUM_URL), options);
        driver.findElement(AppiumBy.accessibilityId("Views")).click();

        scrollUpOrDown(driver, "down");
        WebElement listMenu = driver.findElement(AppiumBy.accessibilityId("Lists"));
        Assert.assertTrue(listMenu.isDisplayed());

        scrollUpOrDown(driver, "up");
        WebElement buttonsMenu = driver.findElement(AppiumBy.accessibilityId("Buttons"));
        Assert.assertTrue(buttonsMenu.isDisplayed());

        driver.quit();
    }

    @Test
    public void test_for_scroll_to_element() throws MalformedURLException {

        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("nigel-test-device");
        options.setApp(ANDROID_API_DEMO_APP_PATH);

        AndroidDriver driver = new AndroidDriver(new URL(APPIUM_URL), options);
        driver.findElement(AppiumBy.accessibilityId("Views")).click();

        scrollToElement(driver, "Visibility");
        WebElement visibilityMenu = driver.findElement(AppiumBy.accessibilityId("Visibility"));
        Assert.assertTrue(visibilityMenu.isDisplayed());

        scrollToElement(driver, "Buttons");
        WebElement buttonsMenu = driver.findElement(AppiumBy.accessibilityId("Buttons"));
        Assert.assertTrue(buttonsMenu.isDisplayed());

        driver.quit();
    }

    @Test
    public void test_for_drag_and_drop() throws MalformedURLException {

        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("nigel-test-device");
        options.setApp(ANDROID_API_DEMO_APP_PATH);

        AndroidDriver driver = new AndroidDriver(new URL(APPIUM_URL), options);
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        driver.findElement(AppiumBy.accessibilityId("Drag and Drop")).click();

        WebElement sourceElement = driver.findElement(By.id("io.appium.android.apis:id/drag_dot_1"));
        WebElement targetElement = driver.findElement(By.id("io.appium.android.apis:id/drag_dot_2"));

        Point sourceElementCenter = getCenterOfElement(sourceElement.getLocation(), sourceElement.getSize());
        Point targetElementCenter = getCenterOfElement(targetElement.getLocation(), targetElement.getSize());

        dragAndDrop(driver, sourceElementCenter, targetElementCenter);

        String result = driver.findElement(By.id("io.appium.android.apis:id/drag_result_text")).getText().trim();
        Assert.assertEquals(result, "Dropped!");

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

    private void longPress(AppiumDriver driver, WebElement element) {

        Point location = element.getLocation();
        Dimension size = element.getSize();

        Point centerOfElement = getCenterOfElement(location, size);

        PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence sequence = new Sequence(finger1, 1)
                .addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerOfElement))
                .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger1, Duration.ofMillis(2000))) // just like tap but duration increased up to 2 seconds
                .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(sequence));
    }

    private void zoom(AppiumDriver driver, WebElement element) {

        Point centerOfElement = getCenterOfElement(element.getLocation(), element.getSize());

        PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        PointerInput finger2 = new PointerInput(PointerInput.Kind.TOUCH, "finger2");
        Sequence sequence = new Sequence(finger1, 1)
                .addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerOfElement))
                .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger1, Duration.ofMillis(200)))
                .addAction(finger1.createPointerMove(Duration.ofMillis(200),
                        PointerInput.Origin.viewport(), centerOfElement.getX() + 100,
                        centerOfElement.getY() - 100))
                .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        Sequence sequence2 = new Sequence(finger2, 1)
                .addAction(finger2.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerOfElement))
                .addAction(finger2.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger2, Duration.ofMillis(200)))
                .addAction(finger2.createPointerMove(Duration.ofMillis(200),
                        PointerInput.Origin.viewport(), centerOfElement.getX() - 100,
                        centerOfElement.getY() + 100))
                .addAction(finger2.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Arrays.asList(sequence, sequence2));
    }

    private void scrollUpOrDown(AppiumDriver driver, String direction) {

        Dimension size = driver.manage().window().getSize();
        int startX = (int) (size.getWidth() * 0.5);
        int startY = (int) (size.getHeight() * 0.5);
        int endX = startX; // since doing a scroll the x coordinate does not change
        int endY;

        if(direction.equalsIgnoreCase("down")) {
            endY = (int) (size.getHeight() * 0.25);
        } else if (direction.equalsIgnoreCase("up")) {
            endY = (int) (size.getHeight() * 0.75);
        } else {
            endY = (int) (size.getHeight() * 0.5);
        }

        PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence sequence = new Sequence(finger1, 1)
                // createPointerMove method 3rd parameter has changed from web element to coordinate system
                .addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
                .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger1, Duration.ofMillis(200)))
                .addAction(finger1.createPointerMove(Duration.ofMillis(100), PointerInput.Origin.viewport(), endX, endY))
                .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(sequence));
    }

    private void scrollToElement(AppiumDriver driver, String accessibilityId) {

        int maxScrollCount = 30;
        int i = 0;
        int j = 0;
        String previousPageSource = "";
        String currentPageSource;

        while (i < maxScrollCount) {
            try {
                previousPageSource = driver.getPageSource();

                WebElement element = driver.findElement(AppiumBy.accessibilityId(accessibilityId));
                if (element.isDisplayed()) {
                    System.out.printf("Element %s found while scrolling down%n", accessibilityId);
                    return;
                }

            } catch (NoSuchElementException e) {
                scrollDown(driver);
                currentPageSource = driver.getPageSource();

                // to check if bottom of the app is reached
                if(Objects.equals(previousPageSource, currentPageSource)) {
                    break;
                }

            }
            i++;
        }

        while (j < maxScrollCount) {
            try {
                WebElement element = driver.findElement(AppiumBy.accessibilityId(accessibilityId));
                if (element.isDisplayed()) {
                    System.out.printf("Element %s found while scrolling up%n", accessibilityId);

                    return;
                }
            } catch (NoSuchElementException e) {
                scrollUp(driver);
                currentPageSource = driver.getPageSource();

                // to check if top of the app is reached
                if(Objects.equals(previousPageSource, currentPageSource)) {
                    break;
                }
            }
            j++;
        }
    }

    private void dragAndDrop(AppiumDriver driver, Point sourcePoint, Point targetPoint) {

        PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence sequence = new Sequence(finger1, 1)
                .addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), sourcePoint))
                .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger1, Duration.ofMillis(500)))
                .addAction(finger1.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), targetPoint))
                .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Collections.singletonList(sequence));
    }

    private static void scrollDown(AppiumDriver driver) {

        Dimension size = driver.manage().window().getSize();
        int startX = (int) (size.getWidth() * 0.5);
        int startY = (int) (size.getHeight() * 0.5);
        int endY = (int) (size.getHeight() * 0.25);

        PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence sequence = new Sequence(finger1, 1)
                .addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
                .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger1, Duration.ofMillis(200)))
                .addAction(finger1.createPointerMove(Duration.ofMillis(100), PointerInput.Origin.viewport(), startX, endY))
                .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Collections.singletonList(sequence));
    }

    private static void scrollUp(AppiumDriver driver) {

        Dimension size = driver.manage().window().getSize();
        int startX = (int) (size.getWidth() * 0.5);
        int startY = (int) (size.getHeight() * 0.5);
        int endY = (int) (size.getHeight() * 0.75);

        PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence sequence = new Sequence(finger1, 1)
                .addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
                .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger1, Duration.ofMillis(200)))
                .addAction(finger1.createPointerMove(Duration.ofMillis(100), PointerInput.Origin.viewport(), startX, endY))
                .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Collections.singletonList(sequence));
    }

    private Point getCenterOfElement(Point location, Dimension size) {
        return new Point(location.getX() + size.getWidth() / 2,
                location.getY() + size.getHeight() / 2);
    }

}
