package org.nigel.automation.appium2;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.AutomationName;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class DemoTest {


    @Test
    public void demo_android_test() throws MalformedURLException {

        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android"); //optional
        options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2); //optional
        options.setDeviceName("nigel-test-device"); // if u have only one device u can put any name here
        options.setAppPackage("com.saucelabs.mydemoapp.android");
        options.setAppActivity("com.saucelabs.mydemoapp.android.view.activities.MainActivity");
        options.setCapability("app",
                System.getProperty("user.dir") + File.separator + "apps" + File.separator + "SauceLabs-Demo-App.apk");

        AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);

        findElement(driver, AppiumBy.accessibilityId("View menu")).click();

        findElement(driver, By.xpath("//android.widget.TextView[@resource-id=\"com.saucelabs.mydemoapp.android:id/itemTV\" and @text=\"Log In\"]")).click();

        // Thread.sleep(5000);
        findElement(driver, AppiumBy.id("com.saucelabs.mydemoapp.android:id/nameET")).sendKeys("username");
        findElement(driver, AppiumBy.id("com.saucelabs.mydemoapp.android:id/passwordET")).sendKeys("password");
        findElement(driver, AppiumBy.accessibilityId("Tap to login with given credentials")).click();

        String productText = findElement(driver, By.id("com.saucelabs.mydemoapp.android:id/productTV")).getText();
        Assert.assertEquals(productText, "Products");

        driver.quit();
    }


    @Test
    public void demo_ios_test() {


    }


    public WebElement findElement(AndroidDriver driver, By element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

}
