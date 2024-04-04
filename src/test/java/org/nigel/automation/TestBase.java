package org.nigel.automation;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class TestBase {
    public static AppiumDriver driver;

    public static void Android_setUp() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("platformVersion", "9.0");
        caps.setCapability("deviceName", "Android Emulator");
        caps.setCapability("app",
                System.getProperty("user.dir") + File.separator + "apps" + File.separator + "ToDo.apk");
        driver = new AndroidDriver(new URL("http://localhost:4723"), caps);
    }

    public static void iOS_setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("platformVersion", "14.4");
        capabilities.setCapability("deviceName", "iPhone 13");
        capabilities.setCapability("app",
                System.getProperty("user.dir") + File.separator + "apps" + File.separator + "DailyCheck.zip");
        driver = new IOSDriver(new URL("http://localhost:4723"), capabilities);
    }

    public static void tearDown() {
        if (null != driver) {
            driver.quit();
        }
    }

}
